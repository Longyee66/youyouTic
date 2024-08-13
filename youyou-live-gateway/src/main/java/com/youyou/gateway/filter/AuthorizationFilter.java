package com.youyou.gateway.filter;

import com.youyou.common.constants.IDConstant;
import com.youyou.common.constants.MessageConstant;
import com.youyou.common.properties.AuthProperties;
import com.youyou.common.properties.GatewayWhiteListProperties;
import com.youyou.interfaces.IUserRPCSService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Author 龙贵义
 * Date 2024/8/12 16:38
 * Description: 用户登录认证拦截
 */
@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered {

    @Resource
    private GatewayWhiteListProperties gatewayWhiteListProperties;
    @Resource
    private AuthProperties authProperties;
    @DubboReference(version = "1.0.0")
    private IUserRPCSService userRPCSService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("过滤器开始执行");

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String pathUrl = request.getURI().getPath();

        log.info("请求路径: {}", pathUrl);

        // 白名单检查
        for (String whiteUrl : gatewayWhiteListProperties.getWhiteUrlList()) {
            if (pathUrl.startsWith(whiteUrl)) {
                log.info("请求白名单：{}, 放行", whiteUrl);
                return chain.filter(exchange);
            }
        }

        // Cookie 检查
        List<HttpCookie> httpCookies = request.getCookies().get(authProperties.getToken());
        if (CollectionUtils.isEmpty(httpCookies)) {
            log.info("携带的Cookie为空，拒绝访问");
            return errorMessageResponse(response, HttpStatus.UNAUTHORIZED);
        }

        String cookieValue = httpCookies.get(0).getValue();
        if (!StringUtils.hasText(cookieValue)) {
            log.info("携带的Cookie为空，拒绝访问");
            return errorMessageResponse(response, HttpStatus.UNAUTHORIZED);
        }

        log.info("携带的Token: {}", cookieValue);

        // Token 验证
        try {
            String userId = userRPCSService.checkToken(cookieValue);
            log.info("Token 验证结果: {}", userId);

            if (!StringUtils.hasText(userId)) {
                log.info("携带的Token已失效");
                return errorMessageResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            log.info("当前登录的用户ID: {}", userId);
            // 更新请求头
            ServerHttpRequest serverHttpRequest = request.mutate()
                    .headers(httpHeaders -> httpHeaders.add(IDConstant.USER_ID, userId))
                    .build();
            ServerWebExchange updatedExchange = exchange.mutate().request(serverHttpRequest).build();
            return chain.filter(updatedExchange);
        } catch (Exception e) {
            log.error("Token 验证出错: ", e);
            return errorMessageResponse(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static Mono<Void> errorMessageResponse(ServerHttpResponse response,HttpStatus status) {
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // 写入自定义错误消息
        DataBuffer dataBuffer = response.bufferFactory().wrap(MessageConstant.USER_NOT_LOGIN.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * 配置优先级  值越小优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
