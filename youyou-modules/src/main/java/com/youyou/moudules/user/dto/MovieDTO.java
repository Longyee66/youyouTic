package com.youyou.moudules.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * Author 龙贵义
 * Date 2024/8/16 15:44
 * Description:
 */
@Data
public class MovieDTO implements Serializable {

    private Long id;

    //标题
    private String title;

    //图片
    private String image;

    //描述
    private String description;

}
