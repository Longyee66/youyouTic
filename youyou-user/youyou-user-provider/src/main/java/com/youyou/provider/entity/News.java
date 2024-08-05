package com.youyou.provider.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * Author longYee
 * Date 2024/7/30 22:24
 * Description: 短信发送记录
 * Version: 1.0
 */
@TableName("t_sms")
@Data
public class News implements Serializable {

    //验证码code
    private Integer code;

    //手机号
    private String phone;

    //发送时间
    private Date sendTime;

    //更新时间
    private Date updateTime;

}
