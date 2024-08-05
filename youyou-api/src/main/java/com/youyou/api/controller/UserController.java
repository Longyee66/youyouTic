package com.youyou.api.controller;
import com.youyou.interfaces.IUserRPCSService;
import com.youyou.moudules.result.AppResultCode;
import com.youyou.moudules.result.Result;
import com.youyou.moudules.user.dto.UserDTO;
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

    @RequestMapping("/queryUser")
    public Result getUser(Long userId){
        log.info("查询用户id：{}",userId);
        UserDTO user = userRPCSService.getUserById(userId);
        if (null == user){
            return Result.error("数据不存在");
        }
        return Result.success(user);
    }

}
