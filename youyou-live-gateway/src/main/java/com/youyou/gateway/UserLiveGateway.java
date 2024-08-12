package com.youyou.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author 龙贵义
 * Date 2024/8/11 20:12
 * Description: 用户层网关
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserLiveGateway {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UserLiveGateway.class);
        springApplication.run(args);
    }
}
