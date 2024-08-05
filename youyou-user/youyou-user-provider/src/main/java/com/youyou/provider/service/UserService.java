package com.youyou.provider.service;

import com.alibaba.fastjson2.JSON;
import com.youyou.common.utils.BuildUserKeyUtils;
import com.youyou.common.utils.CopyBeanUtils;
import com.youyou.moudules.user.dto.UserDTO;
import com.youyou.provider.entity.UserDO;
import com.youyou.provider.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.user.cache.expiration}")  // 设置缓存过期时间
    private long expiration;

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
            log.info("从redis中读取的数据：{}",userDTO);
            return userDTO;
        } else if (null != userDTO &&userDTO.getUserId() < 0) {
            return null;
        }
        //不存在则查询数据库
        UserDO userDO = userMapper.selectById(userId);
        if (null != userDO) {
            log.info("mysql中读取的数据：{}",userDO);
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
}
