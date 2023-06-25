package com.example.dnlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DnLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnLabApplication.class, args);
    }

}
