package com.openclassrooms.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@SpringBootApplication
@Configuration
public class NoteApplication extends CorsRegistry {

    public static void main(String[] args) {

        SpringApplication.run(NoteApplication.class, args);
    }

}
