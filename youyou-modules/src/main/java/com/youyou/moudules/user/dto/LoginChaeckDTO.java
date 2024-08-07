package com.youyou.moudules.user.dto;

import jdk.jpackage.internal.Log;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Author 龙贵义
 * Date 2024/8/6 21:48
 * Description:
 */
@Data
public class LoginChaeckDTO{

    //校验结果
    private boolean loginStatus;

    //描述
    private String description;

    //用户id
    private Long userId;
    public static LoginChaeckDTO loginSuccess(Long userId){
        LoginChaeckDTO loginChaeckDTO = new LoginChaeckDTO();
        loginChaeckDTO.setLoginStatus(true);
        loginChaeckDTO.setUserId(userId);
        return loginChaeckDTO;
    }
}
