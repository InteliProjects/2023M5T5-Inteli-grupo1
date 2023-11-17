package com.pegasus.pegasus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.pegasus.pegasus.entities")
public class PegasusApplication {

    // O método main é o ponto de entrada da aplicação Spring Boot.
    public static void main(String[] args) {
        SpringApplication.run(PegasusApplication.class, args);
    }
}
