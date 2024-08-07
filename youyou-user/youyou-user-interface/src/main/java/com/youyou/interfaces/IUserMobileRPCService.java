package com.youyou.interfaces;

import com.youyou.moudules.user.dto.LoginChaeckDTO;

/**
 * Author 龙贵义
 * Date 2024/8/6 21:54
 * Description: 用户手机号登录服务
 */
public interface IUserMobileRPCService {
    LoginChaeckDTO login(String mobile);
}
