package com.youyou.moudules.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Author 龙贵义
 * Date 2024/8/6 12:59
 * Description: 前端传递的参数
 */
@Data
public class MobileLoginDTO implements Serializable {

    private String mobile;

    private  Integer code;
}
