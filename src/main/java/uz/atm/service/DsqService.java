package uz.atm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.atm.dto.DSQDataDto;
import uz.atm.dto.DataDto;
import uz.atm.dto.callResponses.DSQResponse;
import uz.atm.exceptions.ApiMessages;
import uz.atm.model.juridicInfoResponse.JuridicInfoResponse;
import uz.atm.model.juridicInfoResponse.faunders.Founders;
import uz.atm.repository.TinsRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/28/22 9:02 AM
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class DsqService {

    private final TinsRepository tinsRepository;
    private final DSQCaller dsqCaller;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DSQDataDto<JuridicInfoResponse> getJuridicEntityInfo(String inn) {
        if ( inn.length() != 9 )
            throw new RuntimeException("Tin must be contains 9 numbers");
        Map<String, String> params = new LinkedHashMap<>();
        params.put("tin", inn);
        String infoApi = "https://mspd-api.soliq.uz/minfin2/finance/get-company?tin={tin}";
        DSQResponse<String> response = dsqCaller.getCall(params, infoApi);
        if ( response.isUnAuthorized ) {
            dsqCaller.loginCall();
            response = dsqCaller.getCall(params, infoApi);
        }
        if ( ! response.isUnAuthorized ) {
            try {
                JuridicInfoResponse newRes = objectMapper.readValue(response.body, JuridicInfoResponse.class);
                System.gc();
                return new DSQDataDto<>(newRes);
            } catch ( JsonProcessingException e ) {
                log.error("Exception : {},\n response : {},Couse : {}", ApiMessages.EXCEPTION_FROM_THIRD_PARTY_SERVICE + "with tin : " + inn, response.body, e.getMessage());
                throw new RuntimeException(ApiMessages.EXCEPTION_FROM_THIRD_PARTY_SERVICE + response.body);
            }
        } else {
            log.error("Couldn't integrated for Dsq Api may be Couldn't Authenticated for Dsq APi, \n Cause : {} ", response.body);
            throw new RuntimeException("Couldn't Authenticated for Dsq APi");
        }
    }


    public DataDto<List<Founders>> getFounders(String tin) {
        HashMap<String, String> map = new HashMap<>();
        map.put("tin", tin);
        DataDto<List<Founders>> dataDto;
        dataDto = dsqCaller.getCall(tin, "minfin2/api/get/founders-by-tin");
        if ( ! dataDto.success ) {
            dataDto = dsqCaller.getCall(tin, "minfin2/api/get/founders-by-tin");
        }
        return dataDto;
    }


//    public void gaz() {
//
//        List<Tins> allByIsDoneFalse = tinsRepository.findAllByIsDoneFalse();
//        for ( int i = 0; i < allByIsDoneFalse.size(); i++ ) {
//            try {
//                DSQDataDto<JuridicInfoResponse> juridicEntityInfo = this.getJuridicEntityInfo(allByIsDoneFalse.get(i).getTin());
//                if ( juridicEntityInfo.success ) {
//                    juridicResponseRepository.save(juridicEntityInfo.body);
//                    tinsRepository.updateIsDone(allByIsDoneFalse.get(i).getTin());
//                }
//            } catch ( Exception e ) {
//                log.error("Exception from try catch : {}", e.getMessage());
//            }
//            if ( i % 100 == 0 )
//                Session.INSTANCE.setValue(null);
//        }
//
//    }


//        doSomething();
//        if ( tinsRepository.findAllByIsDoneFalse(Pageable.ofSize(100)).size() > 0 ) {
//            System.gc();
//            dsqCaller.loginCall();
//            doSomething();
//        }


//        ExecutorService executorService = Executors.newFixedThreadPool(10000);
//        Future<Map<Long, List<JuridicInfoResponse>>> submit = executorService.submit(() -> {
//            return juridicResponseRepository.findAll().parallelStream().collect(Collectors.groupingBy(f -> f.getCompany().getTin()));
//        });
//        Map<Long, List<JuridicInfoResponse>> collect = new HashMap<>();
//        try {
//            collect = submit.get();
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//        collect.forEach((K, V) -> {
//            if (V.size() > 1) {
//                juridicResponseRepository.deleteByCompany_Tin(K);
//            }
//        });
}
//
//    private void doSomething() throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        executorService.submit(() -> {
//            for ( int i = 0; i < 17; i++ ) {
//
//            }
//        });
//        Thread.sleep(3600_000);
//        executorService.shutdown();
//        executorService.shutdownNow();
//    }



