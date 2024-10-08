package com.youyou.api;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.youyou.api.controller","com.youyou.common"})
@EnableDubbo
public class ApiSpringApplication {
    public static void main(String[] args) {
       SpringApplication.run(ApiSpringApplication.class,args);
    }
}
