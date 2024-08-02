package com.youyou.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Author: long
 * Description: 主启动类
 */
@SpringBootApplication
@EnableDubbo//标注是一个主启动类
@EnableDiscoveryClient //开启可将其注册到nacos中
@MapperScan("com.youyou.provider.mapper")
public class UserProviderApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UserProviderApplication.class);
        springApplication.run(args);
    }
}
