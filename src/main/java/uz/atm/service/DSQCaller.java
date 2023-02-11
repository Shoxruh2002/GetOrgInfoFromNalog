package uz.atm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import uz.atm.dto.DataDto;
import uz.atm.dto.callResponses.DSQResponse;
import uz.atm.dto.callResponses.LoginDto;
import uz.atm.enums.Session;
import uz.atm.model.juridicInfoResponse.faunders.Founders;

import java.util.List;
import java.util.Map;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 23/08/22
 * Time: 16:23
 */
@Service
@Slf4j
public class DSQCaller {

    private final WebClient webClient;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DSQCaller(@Qualifier( "dsq-webclient" ) WebClient webClient, RestTemplate restTemplate) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
    }

    public void loginCall() {
        try {
            String token = webClient.post()
                    .uri("minfin2/api/authenticate/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new LoginDto("$Finance%", "m8sxjV&LyT"))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            Session.INSTANCE.setValue(token);
        } catch ( Exception e ) {
            Session.INSTANCE.setValue(null);
            log.error("Token ololmadik aka Sabab : {}", e.getMessage());
        }
    }

    public <RES> DataDto<RES> postCall(Object body, String endpoint, Class<RES> responseType) {
        if ( Session.INSTANCE.getValue() == null ) {
            this.loginCall();
        }
        try {
            RES response = webClient.post()
                    .uri(endpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(responseType)
                    .block();
            return new DataDto<>(response);
        } catch ( Exception e ) {
            log.error("Exception Occurred calling DSQ : Request : {} ; Cause {}", body.toString(), e.getMessage());
            return new DataDto<>(false);
        }
    }

    public DataDto<List<Founders>> getCall(String tin, String endpoint) {
        if ( Session.INSTANCE.getValue() == null ) {
            this.loginCall();
        }
        try {
            List<Founders> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path(endpoint).queryParam("tin", tin).build())
                    .header("Authorization", "Bearer " + Session.INSTANCE.getValue())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Founders>>() {
                    })
                    .block();
            return new DataDto<>(response);
        } catch ( Exception e ) {
            log.error("Exception Occurred calling DSQ : Request : {} ; Cause {}", tin.toString(), e.getMessage());
            return new DataDto<>(false);
        }
    }


    public DSQResponse<String> getCall(Map<String, String> params, String endpoint) {

        if ( Session.INSTANCE.getValue() == null ) {
            this.loginCall();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(Session.INSTANCE.getValue());
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, String.class, params);
            if ( response.getStatusCode().value() == 401 || response.getStatusCode().value() == 403 ) {
                return new DSQResponse<>(response.getBody(), true);
            } else {
                String body = response.getBody();
                return new DSQResponse<>(body);
            }
        } catch ( Exception e ) {
            return new DSQResponse<>(e.getMessage());
        }
    }
}
