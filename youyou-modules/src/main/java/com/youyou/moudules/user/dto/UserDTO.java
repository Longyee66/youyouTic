package com.youyou.moudules.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author longYee
 * @Date 2024/7/27 14:52
 * @Description:
 * @Version 1.0
 */
@Data
public class UserDTO implements Serializable {

    private Long userId;

    private String nickName;

    private String avatar;

    private Integer sex;

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                '}';
    }
}
