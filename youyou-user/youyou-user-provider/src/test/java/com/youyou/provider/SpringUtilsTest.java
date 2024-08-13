package com.youyou.provider;

import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * Author 龙贵义
 * Date 2024/8/13 0:03
 * Description:
 */
public class SpringUtilsTest {
    @Test
    public void test(){

        String a1 = "1234";
        String a2 = "";
        String a3 = null;

        System.out.println(StringUtils.hasText(a1));
        System.out.println(StringUtils.hasText(a2));
        System.out.println(StringUtils.hasText(a3));


    }
}
