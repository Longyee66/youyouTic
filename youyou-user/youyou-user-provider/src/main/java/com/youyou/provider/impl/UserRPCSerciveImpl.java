package com.youyou.provider.impl;

import com.youyou.interfaces.IUserRPCSService;
import com.youyou.moudules.user.dto.MsgCheckDTO;
import com.youyou.moudules.user.dto.UserDTO;
import com.youyou.provider.service.SmsService;
import com.youyou.provider.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;


/**
 * Author longYee
 * Date 2024/7/27 15:18
 * Description: 远程调用接口
 * Version 1.0
 */

@DubboService(version = "1.0.0")
@Slf4j
public class UserRPCSerciveImpl implements IUserRPCSService {
    @Resource
    private UserService userService;
    @Resource
    private SmsService smsService;
    @Override
    public UserDTO getUserById(Long id) {
        return userService.getUser(id);
    }

    /**
     * 发送短信验证码
     * @param mobile 手机号
     * @return 发送成功返回 true
     */
    @Override
    public boolean sendLoginCode(String mobile) {
        return smsService.sendSMS(mobile);
    }

    @Override
    public MsgCheckDTO checkLoginCode(String mobile, Integer code) {
        return smsService.checkLoginCode(mobile, code);
    }

    @Override
    public String createAndSaveLoginToToken(Long userId) {
        return null;
    }
}
