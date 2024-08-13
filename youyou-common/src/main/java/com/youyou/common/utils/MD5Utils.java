package com.youyou.common.utils;

import org.springframework.util.DigestUtils;

/**
 * Author 龙贵义
 * Date 2024/8/13 16:21
 * Description: 号码加密处理
 */
public class MD5Utils {
    //秘钥
    public static final String KEY_ALGORITHM = "youyoutic-web";
    //算法名称

    /**
     * 加密方法
     * @param data
     * @return
     */
    public static String encryptMD5(String data) {
        String dataEncrypt = DigestUtils.md5DigestAsHex((KEY_ALGORITHM + data).getBytes());
        return DigestUtils.md5DigestAsHex(dataEncrypt.getBytes());
    }

    public static void main(String[] args) {
        String a = "123";
        a = encryptMD5(a);
        System.out.println(a);
    }
}
