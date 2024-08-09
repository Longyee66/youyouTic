package com.youyou.common.utils;

import java.util.concurrent.*;

/**
 * Author 龙贵义
 * Date 2024/8/9 14:09
 * Description: 创建线程池，作为异步访问，防止出现阻塞
 */
public class ThreadPoolMengerUtils {

    public static ThreadPoolExecutor commonAsyncPool = new ThreadPoolExecutor(
            2, 8, 3, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread newThread = new Thread(r);
                    newThread.setName("commonAsyncPool-"+ ThreadLocalRandom.current().nextInt(10000));
                    return newThread;
                }
            }
    );
}
