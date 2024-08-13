package com.youyou.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author 龙贵义
 * Date 2024/8/12 17:21
 * Description: 访问白名单
 */
@Component
@ConfigurationProperties("youyou.gateway")
@Data
public class GatewayWhiteListProperties {

    private List<String> whiteUrlList;
}
