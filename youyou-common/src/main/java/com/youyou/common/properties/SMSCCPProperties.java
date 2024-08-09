package com.youyou.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author 龙贵义
 * Date 2024/8/8 15:15
 * Description:
 */
@Component
@ConfigurationProperties(prefix = "youyou.sms.ccp")
@Data
public class SMSCCPProperties {

    private String smsServerIp;

    private String port;

    private String accountSID;

    private String accountToken;

    private String appId;

    private String templateId;

    //标识是否是测试环境，如果是直接返回true
    private boolean test;

}
