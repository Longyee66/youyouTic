package com.youyou.moudules.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author 龙贵义
 * Date 2024/8/3 21:14
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code; //状态码 0-成功；1-失败
    private String message; //提示信息
    private T data;  //数据

    public static <E> Result<E> success(E data) {
        return new Result<>(200, "操作成功", data);
    }

    //快速返回操作成功响应结果
    public static Result success() {
        return new Result(200, "操作成功", null);
    }

    public static Result error(String message) {
        return new Result(0, message, null);
    }
}
