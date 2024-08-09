package com.youyou.provider.config;

import com.youyou.common.properties.SMSCCPProperties;
import com.youyou.common.utils.SmsCCPUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author 龙贵义
 * Date 2024/8/8 16:10
 * Description:
 */
@Configuration
@Slf4j
public class CCPconfig {
    @Bean //将这个方法加入bean管理
    @ConditionalOnMissingBean //该工具类对象只用创建一次 条件对象
    public SmsCCPUtils smsCCPUtils(SMSCCPProperties smsccpProperties){
        log.info("短信发送SmsCCPUtils工具类开始创建.....");
        return new SmsCCPUtils(
                smsccpProperties.getSmsServerIp(),
                smsccpProperties.getPort(),
                smsccpProperties.getAccountSID(),
                smsccpProperties.getAccountToken(),
                smsccpProperties.getAppId(),
                smsccpProperties.getTemplateId(),
                smsccpProperties.isTest()
        );
    }
}
