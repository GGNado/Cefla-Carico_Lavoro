package com.giggi.ceflacarico_lavoro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CeflaCaricoLavoroApplication {

    public static void main(String[] args) {
        SpringApplication.run(CeflaCaricoLavoroApplication.class, args);
    }

}
