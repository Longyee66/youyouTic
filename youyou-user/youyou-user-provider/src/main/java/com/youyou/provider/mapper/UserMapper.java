package com.youyou.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyou.common.user.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * Author longYee
 * Date 2024/7/31 15:02
 * Description: 用户信息
 * Version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

//    @Select("select * from t_user where user_id=#{id}")
//    UserDO selectById(Long id);
}
