package com.openclassrooms.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class PatientApplication extends CorsRegistry {

    public static void main(String[] args) {

        SpringApplication.run(PatientApplication.class, args);
    }

}
