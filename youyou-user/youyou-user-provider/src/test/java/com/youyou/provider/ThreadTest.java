package com.youyou.provider;

import com.youyou.common.utils.ThreadPoolMengerUtils;

/**
 * Author 龙贵义
 * Date 2024/8/9 14:15
 * Description:
 */
public class ThreadTest {
    public static void main(String[] args) {
        ThreadPoolMengerUtils.commonAsyncPool.execute(() ->
                {

                    System.out.println(1111);
                }
        );
    }
}
