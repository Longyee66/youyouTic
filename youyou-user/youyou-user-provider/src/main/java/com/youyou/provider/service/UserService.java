package com.youyou.provider.service;

import com.youyou.common.enums.StatusEnum;
import com.youyou.common.properties.AuthProperties;
import com.youyou.common.utils.BuildUserKeyUtils;
import com.youyou.common.utils.CopyBeanUtils;
import com.youyou.common.utils.MD5Utils;
import com.youyou.id.inter.IGenerateIDRPCService;
import com.youyou.moudules.user.dto.LoginChaeckDTO;
import com.youyou.moudules.user.dto.UserDTO;
import com.youyou.provider.entity.UserDO;
import com.youyou.provider.entity.UserPhoneDO;
import com.youyou.provider.mapper.UserMapper;
import com.youyou.provider.mapper.UserMobileMapper;
import com.youyou.redis.build.UserCacheKeyBuilder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Author longYee
 * Date 2024/8/2 22:36
 * Description: 用户业务
 * Version: 1.0
 */
@Service
@Slf4j
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserMobileMapper userMobileMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.user.cache.expiration}")  // 设置缓存过期时间
    private long expiration;

    @DubboReference(version = "1.0.0")
    private IGenerateIDRPCService iGenerateIDRPCService;
    @Resource
    private UserCacheKeyBuilder userCacheKeyBuilder;

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    public UserDTO getUser(Long userId) {
        if (null == userId) {
            return null;
        }
        UserDTO userDTO;
        //先查询redis中是否存在
        String userInfoKey = BuildUserKeyUtils.BuildUserKey(userId);
        userDTO = (UserDTO) redisTemplate.opsForValue().get(userInfoKey);
        if (null != userDTO && userDTO.getUserId() > 0) {
            log.info("从redis中读取的数据：{}", userDTO);
            return userDTO;
        } else if (null != userDTO && userDTO.getUserId() < 0) {
            return null;
        }
        //不存在则查询数据库
        UserDO userDO = userMapper.selectById(userId);
        if (null != userDO) {
            log.info("mysql中读取的数据：{}", userDO);
            userDTO = CopyBeanUtils.copy(userDO, UserDTO.class);
            redisTemplate.opsForValue().set(userInfoKey, userDTO, expiration, TimeUnit.MINUTES);
            return userDTO;
        } else {
            //如果数据库内没有数据 纯在缓存击穿
            //1、布隆过滤器 2、设置空值
            //设置空值
            UserDTO notExitUser = new UserDTO();
            notExitUser.setUserId(-1L);
            redisTemplate.opsForValue().set(userInfoKey, notExitUser, expiration, TimeUnit.SECONDS);
            return null;
        }
    }


    public LoginChaeckDTO gennerateDefaultUserByMobile(String mobile) {
        log.info("用户手机号：{}开始注册账号", mobile);

        //TODO 生成主键ID
        Long userId = iGenerateIDRPCService.getSeqId();
        //向用户表中插入数据
        createUser(userId);
        //向用户手机号和用户关联表中插入数据
        createUserAndPhone(mobile, userId);

        return LoginChaeckDTO.loginSuccess(userId);
    }

    private void createUserAndPhone(String mobile, Long userId) {
        UserPhoneDO userPhoneDO = new UserPhoneDO();
        userPhoneDO.setUserId(userId);
        userPhoneDO.setPhone(MD5Utils.encryptMD5(mobile));
        userPhoneDO.setStatus(StatusEnum.VALID_STATUS.getCode());
        userMobileMapper.insert(userPhoneDO);
    }

    private void createUser(Long userId) {
        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        userDO.setAvatar("https://big-event-long01.oss-cn-beijing.aliyuncs.com/05cbacf8-ac2a-4115-a8e9-286eb8dcb762.jpg");
        userDO.setNickName("新用户-" + userId);
        userDO.setSex(0);
        userMapper.insert(userDO);
    }

    public String createLoginToken(Long userId) {
        //生成token
        String token = UUID.randomUUID().toString();
        //将token存储到redis中
        String userTokenKey = userCacheKeyBuilder.buildUserPhoneKey(token);
        redisTemplate.opsForValue().set(userTokenKey, userId, expiration, TimeUnit.MINUTES);
        return token;

    }

    public String checkToken(String token) {
        String userTokenKey = userCacheKeyBuilder.buildUserPhoneKey(token);
        Object value = redisTemplate.opsForValue().get(userTokenKey);
        if (value != null && StringUtils.hasText(value.toString())) {
            return value.toString();
        }
        return null;
    }
}
