package com.youyou.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youyou.provider.entity.UserPhoneDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author 龙贵义
 * Date 2024/8/7 14:19
 * Description:
 */
@Mapper
public interface UserMobileMapper extends BaseMapper<UserPhoneDO> {
}
