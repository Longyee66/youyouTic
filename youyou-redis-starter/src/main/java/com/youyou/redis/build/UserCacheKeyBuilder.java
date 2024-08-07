package com.youyou.redis.build;

import org.springframework.context.annotation.Configuration;

/**
 * Author 龙贵义
 * Date 2024/8/7 14:09
 * Description:
 */
@Configuration
public class UserCacheKeyBuilder extends RedisKeyBuilder {

    private static final String USER_PHONE_KEY = "user:phone:";

    public String buildUserPhoneKey(String phone) {
        return super.getPrefix() + USER_PHONE_KEY + phone;
    }
}
