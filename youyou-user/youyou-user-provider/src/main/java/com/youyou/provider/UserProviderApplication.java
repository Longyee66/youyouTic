package com.youyou.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Author: long
 * Description: 主启动类
 */
@SpringBootApplication
@ComponentScan({"com.youyou.common","com.youyou.provider"})
@EnableDubbo//标注是一个主启动类
@EnableDiscoveryClient //开启可将其注册到nacos中
public class UserProviderApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UserProviderApplication.class);
        springApplication.run(args);
    }
}
