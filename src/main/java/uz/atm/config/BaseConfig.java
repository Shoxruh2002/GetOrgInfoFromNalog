package uz.atm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/28/22 6:36 PM
 **/
@Configuration
public class BaseConfig {


    @Bean( "dsq-webclient" )
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://mspd-api.soliq.uz/")
                .build();
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
