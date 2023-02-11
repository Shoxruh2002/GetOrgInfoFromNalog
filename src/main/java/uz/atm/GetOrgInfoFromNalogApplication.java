package uz.atm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class GetOrgInfoFromNalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetOrgInfoFromNalogApplication.class, args);
    }


    //    @Bean
    public void run() throws Exception {
        CommandLineRunner runner = (a) -> {

        };
        runner.run("s", "b"
        );
    }


}
