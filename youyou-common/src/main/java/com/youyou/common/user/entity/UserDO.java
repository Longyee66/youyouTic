package com.youyou.common.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * Author longYee
 * Date 2024/7/30 22:11
 * Description: 用户基础信息
 * Version: 1.0
 */
@TableName("t_user")
@Data
public class UserDO {

    //用户id
    @TableId(type = IdType.INPUT)
    private Long userId;

    //用户昵称
    private String nickName;

    //头像
    private String avatar;

    //真实姓名
    private String trueName;

    //性别
    private Integer sex;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
}
