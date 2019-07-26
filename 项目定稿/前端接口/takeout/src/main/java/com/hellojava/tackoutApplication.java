package com.hellojava;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class tackoutApplication {
    public static void main(String[] args) {
        SpringApplication.run(tackoutApplication.class,args);
    }
}
