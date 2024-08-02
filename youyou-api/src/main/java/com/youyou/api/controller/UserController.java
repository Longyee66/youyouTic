package com.youyou.api.controller;

import com.youyou.common.user.dto.UserDTO;
import com.youyou.interfaces.IUserRPCSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author longYee
 * Date 2024/7/30 14:47
 * Description:
 * Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @DubboReference(version = "1.0.0")
    private IUserRPCSService userRPCSService;

    @RequestMapping("/query")
    public UserDTO getUser(Long userId){
        log.info("新版本使用：{}",userId);
        return userRPCSService.getUserId(userId);
    }
}
