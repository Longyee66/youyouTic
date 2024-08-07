package com.youyou.provider.impl;

import com.youyou.interfaces.IUserMobileRPCService;
import com.youyou.moudules.user.dto.LoginChaeckDTO;
import com.youyou.provider.service.UserMobileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Author 龙贵义
 * Date 2024/8/6 21:55
 * Description: 用户手机号登录服务
 */
@DubboService(version = "1.0.0")
@Slf4j
public class UserMobileServiceImpl implements IUserMobileRPCService {
    @Resource
    private UserMobileService userMobileService;
    @Override
    public LoginChaeckDTO login(String mobile) {
        return userMobileService.login(mobile);
    }
}
