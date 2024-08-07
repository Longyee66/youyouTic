package com.youyou.interfaces;


import com.youyou.moudules.user.dto.MsgCheckDTO;
import com.youyou.moudules.user.dto.UserDTO;

/**
 * Author: long
 * Description:
 */
public interface IUserRPCSService {
    /**
     * 用户信息查询
     * @param id
     * @return
     */
    UserDTO getUserById(Long id);

    /**
     * 短信验证
     * @param mobile
     * @return
     */
    boolean sendLoginCode(String mobile);

    /**
     * 用户信息校验
     * @param code 验证码
     * @return 校验结果
     */
    MsgCheckDTO checkLoginCode(String mobile, Integer code);

    /**
     * 创建用户标识
     * @param userId
     * @return
     */
    String createAndSaveLoginToToken(Long userId);
}
