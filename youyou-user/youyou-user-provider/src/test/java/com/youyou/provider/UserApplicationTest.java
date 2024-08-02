package com.youyou.provider;

import com.youyou.provider.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author longYee
 * Date 2024/8/2 16:22
 * Description:
 * Version: 1.0
 */
@SpringBootTest(classes = UserProviderApplication.class)
@RunWith(SpringRunner.class)
public class UserApplicationTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void getUserById(){
        System.out.println(111111);
        System.out.println(userMapper.selectById(1L));
    }
}
