package com.youyou.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiSpringApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApiSpringApplication.class);
        springApplication.run(args);
    }
}
