package com.youyou.common.utils;

/**
 * Author 龙贵义
 * Date 2024/8/13 13:35
 * Description: 存储用户数据
 */
public class ThreadLocalUtils {
    public static ThreadLocal<String> USER_DATA_ID = new ThreadLocal<>();

    public static void setUserDataId(String id){
        USER_DATA_ID.set(id);
    }
    public static String getUserDataId(){
        return USER_DATA_ID.get();
    }

    public static void removeUserId(){
        USER_DATA_ID.remove();
    }
}
