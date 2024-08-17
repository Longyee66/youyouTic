package com.youyou.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * Author 龙贵义
 * Date 2024/8/16 15:25
 * Description:
 */
@Data
@TableName("t_movie")
public class MovieDO {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    //标题
    private String title;

    //图片
    private String image;

    //描述
    private String description;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
}
