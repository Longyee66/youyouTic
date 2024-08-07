package com.youyou.redis.build;

import org.springframework.beans.factory.annotation.Value;

/**
 * Author 龙贵义
 * Date 2024/8/5 19:36
 * Description:
 */
public class RedisKeyBuilder {


    @Value("${spring.application.name}")
    private String applicationName;

    private static final String SPLIT_ITEM=":"; //组合名":"分割


    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    //命名前缀
    public String getPrefix(){
        return applicationName+SPLIT_ITEM;
    }
}
