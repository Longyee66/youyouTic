package com.youyou.provider.impl;

import com.youyou.common.user.dto.UserDTO;
import com.youyou.interfaces.IUserRPCSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Author longYee
 * Date 2024/7/27 15:18
 * Description:
 * Version 1.0
 */

//@DubboService(version = "1.0.0")
@Slf4j
public class UserRPCSerciveImpl implements IUserRPCSService {
    @Override
    public UserDTO getUserId(Long id) {
        log.info("新版本使用：{}",id);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(id);
        userDTO.setNickName("test");
        userDTO.setAvatar("logo-long-server");
        return userDTO;
    }
}
