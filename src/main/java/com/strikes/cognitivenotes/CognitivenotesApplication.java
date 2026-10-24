package com.strikes.cognitivenotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CognitivenotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CognitivenotesApplication.class, args);
        System.out.println("Server Running on port 8080");
    }
}
