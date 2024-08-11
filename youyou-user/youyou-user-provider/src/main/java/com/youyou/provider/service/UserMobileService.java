package com.youyou.provider.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.youyou.common.constants.MessageConstant;
import com.youyou.common.enums.StatusEnum;
import com.youyou.common.utils.CopyBeanUtils;
import com.youyou.moudules.user.dto.LoginChaeckDTO;
import com.youyou.moudules.user.dto.UserPhoneDTO;
import com.youyou.provider.entity.UserPhoneDO;
import com.youyou.provider.mapper.UserMobileMapper;
import com.youyou.redis.build.UserCacheKeyBuilder;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Author 龙贵义
 * Date 2024/8/7 12:46
 * Description:
 */
@Service
public class UserMobileService {
    @Resource
    private UserCacheKeyBuilder userCacheKeyBuilder;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserMobileMapper userMobileMapper;
    @Value("${redis.user.cache.expiration}")  // 设置缓存过期时间
    private long expiration;
    @Resource
    private UserService userService;

    /**
     * 用户手机号登录
     *
     * @param mobile
     * @return
     */
    public LoginChaeckDTO login(String mobile) {

        if (!StringUtils.hasText(mobile)) {
            return null;
        }
        //检查是否注册过
        UserPhoneDTO userPhoneDTO = queryByPhone(mobile);
        if (userPhoneDTO != null) {
            return LoginChaeckDTO.loginSuccess(userPhoneDTO.getUserId());
        }
        //没有注册过，生成注册记录，插入表中,并生成token
        return registerAndLogin(mobile);
    }

    private LoginChaeckDTO registerAndLogin(String mobile) {
        //生成用户信息， 并且绑定手机号码
        LoginChaeckDTO loginChaeckDTO = userService.gennerateDefaultUserByMobile(mobile);
        //清除手机号查询的缓存（清除击穿数据）
        redisTemplate.delete(userCacheKeyBuilder.buildUserPhoneKey(mobile));

        return loginChaeckDTO;
    }

    private UserPhoneDTO queryByPhone(String mobile) {
        //先查询redis中是否存在
        String userPhoneKey = userCacheKeyBuilder.buildUserPhoneKey(mobile);
        UserPhoneDTO userPhoneDTO;
        userPhoneDTO = (UserPhoneDTO) redisTemplate.opsForValue().get(userPhoneKey);

        if (userPhoneDTO != null) {
            return userPhoneDTO;
        }
        //再查询mysql
        UserPhoneDO userPhoneDO = userMobileMapper.selectOne(Wrappers.<UserPhoneDO>lambdaQuery()
                .eq(UserPhoneDO::getPhone, mobile)
                .eq(UserPhoneDO::getStatus, StatusEnum.VALID_STATUS.getCode())
                .last("limit 1"));
        if (userPhoneDO != null) {
            userPhoneDTO = CopyBeanUtils.copy(userPhoneDO, UserPhoneDTO.class);
            //插入到redis中
            redisTemplate.opsForValue().set(userPhoneKey, userPhoneDTO, expiration, TimeUnit.MINUTES);
            return userPhoneDTO;
        }
        //防止缓存击穿
        userPhoneDTO = new UserPhoneDTO();
        userPhoneDTO.setId(-1L);
        redisTemplate.opsForValue().set(userPhoneKey, userPhoneDTO, expiration, TimeUnit.SECONDS);
        return null;
    }
}
