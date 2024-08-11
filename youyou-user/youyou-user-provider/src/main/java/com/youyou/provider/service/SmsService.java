package com.youyou.provider.service;
//

import com.youyou.common.constants.MessageConstant;
import com.youyou.common.utils.SmsCCPUtils;
import com.youyou.common.utils.ThreadPoolMengerUtils;
import com.youyou.moudules.user.dto.MsgCheckDTO;
import com.youyou.provider.entity.SmsDO;
import com.youyou.provider.mapper.SmsMapper;
import com.youyou.redis.build.SMSCacheKeyBuilder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Author 龙贵义
 * Date 2024/8/5 19:29
 * Description:
 */
@Service
@Slf4j
public class SmsService {

    //    private Logger logger = LoggerFactory.getLogger(SmsService.class);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private SMSCacheKeyBuilder smsCacheKeyBuilder;
    @Resource
    private SmsMapper smsMapper;
    @Value("${redis.user.cache.expiration}")  // 设置缓存过期时间
    private long expiration;

    @Autowired
    private SmsCCPUtils smsCCPUtils;

    public boolean sendSMS(String mobile) {
        //1.参数校验
        if (!StringUtils.hasText(mobile)) {
            return false;
        }
        //2.生成和手机号关联的redisKey
        String smsCacheKey = smsCacheKeyBuilder.buildSmsLoginCodeKey(mobile);
        //3.去redis中查询有没有发送验证码
        //3.1直接判断key是否存在
        if (Boolean.TRUE.equals(redisTemplate.hasKey(smsCacheKey))) {
            //如果有，表示已经发送验证码，返回false
            log.info("手机号：{}申请短信频繁", mobile);
            return false;
        }
        //如果没有，就发送验证码，并保存在redis中
        int smsCode = new Random().nextInt(8999) + 1000;
        redisTemplate.opsForValue().set(smsCacheKey, smsCode, expiration, TimeUnit.MINUTES);

        // TODO 发送验证码
        ThreadPoolMengerUtils.commonAsyncPool.execute(() -> {
            //多发几次确保成功
            for (int i = 0; i < 3; i++) {
                //发送成功保存到信息发送表中
                boolean flag = sendSMSCode(mobile, smsCode);
                if (flag){
                    insertSMSCode(mobile, smsCode);
                    break;
                }

            }
        });
        return true;
    }

    private boolean sendSMSCode(String mobile, int smsCode) {
        //发送验证码
        HashMap<String, Object> sentResultMap = smsCCPUtils.sendVerificationCode(mobile, smsCode);
        return smsCCPUtils.printResult(sentResultMap);
    }

    private void insertSMSCode(String mobile, int smsCode) {
        SmsDO smsDO = SmsDO.builder()
                // TODO 主键生成
                .phone(mobile)
                .code(smsCode)
                .build();
        smsMapper.insert(smsDO);
    }

    /**
     * 登录验证码校验
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    public MsgCheckDTO checkLoginCode(String mobile, Integer code) {
        if (!StringUtils.hasText(mobile) || code < 1000 || code > 9999) {
            return new MsgCheckDTO(false, MessageConstant.FORMAT_ERROR);
        }
        //获取redis中code
        Integer cacheCode = (Integer) redisTemplate.opsForValue().get(smsCacheKeyBuilder.buildSmsLoginCodeKey(mobile));

        if (!code.equals(cacheCode) && null == cacheCode) {
            return new MsgCheckDTO(false, MessageConstant.DATA_NULL_ERROR);
        }
        redisTemplate.delete(smsCacheKeyBuilder.buildSmsLoginCodeKey(mobile));//校验完成就删掉
        return new MsgCheckDTO(true, MessageConstant.CHECK_SUCCESS);
    }
}
