package com.youyou.moudules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Author 龙贵义
 * Date 2024/8/6 20:56
 * Description: 校验验证码参数
 */
@Data
@AllArgsConstructor
public class MsgCheckDTO implements Serializable {

    //校验结果
    private boolean checkStatus;

    //描述
    private String description;
}
