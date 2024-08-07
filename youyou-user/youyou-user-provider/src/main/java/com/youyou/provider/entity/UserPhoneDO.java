package com.youyou.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * Author 龙贵义
 * Date 2024/8/7 12:51
 * Description:
 */
@TableName("t_user_phone")
@Data
public class UserPhoneDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String phone;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
