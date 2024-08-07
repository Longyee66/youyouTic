package com.youyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author 龙贵义
 * Date 2024/8/7 14:30
 * Description:
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    INVALID_STATUS(0, "无效"),

    VALID_STATUS(1, "有效");
    private final int code;
    private final String message;
}
