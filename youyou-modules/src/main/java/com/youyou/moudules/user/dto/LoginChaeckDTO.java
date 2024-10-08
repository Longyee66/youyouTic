package com.youyou.moudules.user.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * Author 龙贵义
 * Date 2024/8/6 21:48
 * Description:
 */
@Data
public class LoginChaeckDTO implements Serializable {

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
