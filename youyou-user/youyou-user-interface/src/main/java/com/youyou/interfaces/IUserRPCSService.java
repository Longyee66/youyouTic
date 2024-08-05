package com.youyou.interfaces;


import com.youyou.moudules.user.dto.UserDTO;

/**
 * Author: long
 * Description:
 */
public interface IUserRPCSService {
    UserDTO getUserById(Long id);
}
