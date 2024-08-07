package com.youyou.moudules.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * Author 龙贵义
 * Date 2024/8/7 12:56
 * Description:
 */
@Data
public class UserPhoneDTO implements Serializable {

    private Long id;

    private Long userId;

    private String phone;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
