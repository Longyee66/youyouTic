package com.youyou.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * Author 龙贵义
 * Date 2024/8/3 15:42
 * Description:
 */
public class CopyBeanUtils {
    /**
     * 重新封装拷贝方法
     * @param source 源对象
     * @param targetClass 目标对象
     * @return 返回拷贝结果
     * @param <T> 以泛型为变量
     */
    public static <T> T copy(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T targetObj = null;
        try {
            //通过 target.newInstance() 法在 Java 9 及以后版本中已被弃用，建议使用 target.getDeclaredConstructor().newInstance() 代替。
            targetObj = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetObj);
        } catch (Exception e) {
            e.printStackTrace();  // Consider using a logging framework or rethrowing the exception
        }
        return targetObj;
    }
}
