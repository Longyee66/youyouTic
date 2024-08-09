package com.youyou.id;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author 龙贵义
 * Date 2024/8/7 18:57
 * Description:
 */
@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
public class IdGenerateIdRPCService {
    public static void main(String[] args) {
        SpringApplication.run(IdGenerateIdRPCService.class,args);
    }
}
