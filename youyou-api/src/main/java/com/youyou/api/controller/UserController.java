package com.youyou.api.controller;

import com.youyou.common.constants.MessageConstant;
import com.youyou.common.properties.AuthProperties;
import com.youyou.interfaces.IUserMobileRPCService;
import com.youyou.interfaces.IUserRPCSService;
import com.youyou.moudules.result.Result;
import com.youyou.moudules.user.dto.LoginChaeckDTO;
import com.youyou.moudules.user.dto.MobileLoginDTO;
import com.youyou.moudules.user.dto.MsgCheckDTO;
import com.youyou.moudules.user.dto.UserDTO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @DubboReference(version = "1.0.0")
    private IUserMobileRPCService mobileRPCService;

    @Resource
    private AuthProperties authProperties;

    @RequestMapping("/queryUser")
    public Result getUser(Long userId) {
        log.info("查询用户id：{}", userId);
        UserDTO user = userRPCSService.getUserById(userId);
        if (null == user) {
            return Result.error(MessageConstant.DATA_NULL_ERROR);
        }
        return Result.success(user);
    }

    /**
     * 发送短信验证
     */
    @PostMapping("/sendSMS")
    public Result sendSMS(String mobile) {
        if (!StringUtils.hasText(mobile)) {
            return Result.error(MessageConstant.MOBILE_NULL_ERROR);
        }
        boolean flag = userRPCSService.sendLoginCode(mobile);
        if (flag) {
            return Result.success();
        } else {
            return Result.error(MessageConstant.CODE_SEND_ERROR);
        }

    }

    /**
     * 用户登录请求
     */
    @PostMapping("/mobileLogin")
    public Result mobileLogin(@RequestBody MobileLoginDTO mobileLoginDTO, HttpServletResponse response) {
        if (!StringUtils.hasText(mobileLoginDTO.getMobile())) {
            return Result.error(MessageConstant.MOBILE_NULL_ERROR);
        }
        if (mobileLoginDTO.getCode() < 1000 || mobileLoginDTO.getCode() > 9999) {
            return Result.error(MessageConstant.FORMAT_ERROR);
        }
        //校验token
        MsgCheckDTO msgCheckDTO = userRPCSService.checkLoginCode(mobileLoginDTO.getMobile(), mobileLoginDTO.getCode());
        if (!msgCheckDTO.isCheckStatus()) {
            return Result.error(msgCheckDTO.getDescription());
        }
        //校验成功完成手机登录
        //如果没有登录过---注册，有登录---返回token
        LoginChaeckDTO loginChaeckDTO = mobileRPCService.login(mobileLoginDTO.getMobile());

        //用户标识
        String token = userRPCSService.createAndSaveLoginToToken(loginChaeckDTO.getUserId());
        Cookie cookie = new Cookie(authProperties.getToken(), token);
        cookie.setMaxAge(authProperties.getExpTime());//设置过期时间
        response.addCookie(cookie);
        //设置token
        return Result.success();
    }
}
