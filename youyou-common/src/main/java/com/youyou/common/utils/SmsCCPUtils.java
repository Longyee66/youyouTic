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
     * 初始化
     * @return sdk
     */
    private CCPRestSmsSDK initializeSdk() {
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(smsServerIp, port);
        sdk.setAccount(accountSID, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        return sdk;
    }

    /**
     * @param mobile 手机号
     * @param code   验证码
     * @return 响应结果
     */
    public HashMap<String, Object> sendVerificationCode(String mobile, Integer code) {
        CCPRestSmsSDK sdk = initializeSdk();
        String[] datas = {String.valueOf(code), "1"};
        String subAppend = "1234";  // 扩展码
        String reqId = String.valueOf(new Random().nextInt(99999999));  // 生成一个随机的请求ID
        try {
            return sdk.sendTemplateSMS(mobile, templateId, datas, subAppend, reqId);
        } catch (Exception e) {
            log.error("发送短信失败:{}， {}", reqId, e.getMessage());
            return new HashMap<>();  //返回空
        }
    }

    public boolean printResult(HashMap<String, Object> result) {
        if (test) {
            return true;
        }
        String statusCode = (String) result.get("statusCode");
        if ("000000".equals(result.get(statusCode))) {
            // 正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            data.forEach((key, value) -> log.info(key + " = " + value));
            return true;
        } else {
            // 异常返回输出错误码和错误信息
            log.error("短信发送失败，错误码: {}, 错误信息: {}", statusCode, result.get("statusMsg"));
            return false;
        }

    }
}

