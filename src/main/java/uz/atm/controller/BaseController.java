package uz.atm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.atm.dto.DataDto;
import uz.atm.model.juridicInfoResponse.faunders.Founders;
import uz.atm.service.DsqService;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 1/13/23 12:07 PM
 **/
@RestController
@RequestMapping( "/base" )
public class BaseController {

    private final DsqService dsqService;

    public BaseController(DsqService dsqService) {
        this.dsqService = dsqService;
    }

    @GetMapping( "/gaz" )
    public DataDto<List<Founders>> gaz(@RequestParam String tin) {
        return dsqService.getFounders(tin);

    }
}
