package com.youyou.common.utils;

/**
 * Author 龙贵义
 * Date 2024/8/3 15:24
 * Description:
 */
public class BuildUserKeyUtils {
    private static final String USER_INFO_KEY="user:info:";
    public static String BuildUserKey(Long userId){
        return USER_INFO_KEY+userId;
    }
}
