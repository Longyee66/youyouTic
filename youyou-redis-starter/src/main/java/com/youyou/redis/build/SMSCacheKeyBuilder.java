package com.youyou.redis.build;

import org.springframework.context.annotation.Configuration;

/**
 * Author 龙贵义
 * Date 2024/8/5 19:34
 * Description: 构建短信存储规则
 */
@Configuration
public class SMSCacheKeyBuilder extends RedisKeyBuilder {

    private static final String SMS_LOGIN_CODE_KEY = "sms:login:code:";
    /**
     * 命名规则： 例如
     * "应用名:sms:login:code:mobile"
     */
    public String buildSmsLoginCodeKey(String mobile) {
        return super.getPrefix() + SMS_LOGIN_CODE_KEY + mobile;
    }
}
