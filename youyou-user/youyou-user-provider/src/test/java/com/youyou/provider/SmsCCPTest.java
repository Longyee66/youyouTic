package com.youyou.provider;

import com.youyou.common.utils.SmsCCPUtils;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Random;

/**
 * Author 龙贵义
 * Date 2024/8/8 19:55
 * Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsCCPTest {
    @Resource
    private SmsCCPUtils smsCCPUtils;

    @Test
    public void smsTest() {
        String mobile = "18585149631";
        Integer code = new Random().nextInt(8999) + 1000;
        HashMap<String, Object> stringObjectHashMap = smsCCPUtils.sendVerificationCode(mobile, code);
        boolean b = smsCCPUtils.printResult(stringObjectHashMap);
        if (b){
            System.out.println("短信发送成功");
        }
        System.out.println();
    }

}
