package com.youyou.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author 龙贵义
 * Date 2024/8/6 22:04
 * Description:
 */
@Component
@ConfigurationProperties(prefix = "youyou.autho")
@Data
public class AuthProperties {

    private String token; //标识

    private int expTime; //过期时间
}
