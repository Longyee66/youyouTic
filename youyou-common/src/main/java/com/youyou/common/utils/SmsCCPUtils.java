package com.youyou.common.utils;

/**
 * Author 龙贵义
 * Date 2024/8/8 15:47
 * Description:
 */

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

@Data
@AllArgsConstructor
@Slf4j
public class SmsCCPUtils {

    private String smsServerIp;
    private String port;
    private String accountSID;
    private String accountToken;
    private String appId;
    private String templateId;
    private boolean test;

    /**
     * 发送短信
     * 生成验证码并发送短信
     *
     * @param mobile 接收短信的手机号码
     * @return 发送短信的结果
     */
    public HashMap<String, Object> sendVerificationCode(String mobile, Integer code) {
        // 生成随机验证码
//        int code = new Random().nextInt(8999) + 1000;
        String[] datas = {String.valueOf(code), "1"};
        String subAppend = "1234";  // 扩展码
        String reqId = String.valueOf(new Random().nextInt(99999999));  // 生成一个随机的请求ID
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        //初始化
        sdk.init(smsServerIp, port);
        sdk.setAccount(accountSID, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        // 发送短信
        HashMap<String, Object> result = sdk.sendTemplateSMS(mobile, templateId, datas, subAppend, reqId);
        return result;
        // 发送短信
    }

    /**
     * 打印短信发送结果
     *
     * @param result 发送短信的结果
     */
    public boolean printResult(HashMap<String, Object> result) {
        if (test) {
            return true;
        } else {
            if ("000000".equals(result.get("statusCode"))) {
                // 正常返回输出data包体信息（map）
                HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for (String key : keySet) {
                    Object object = data.get(key);
                    log.info(key + " = " + object);
                }
                return true;
            } else {
                // 异常返回输出错误码和错误信息
                log.info("错误码={} ,错误信息= {}", result.get("statusCode"), result.get("statusMsg"));
                return false;
            }
        }
    }
}

