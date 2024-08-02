package com.youyou.api;

import com.youyou.common.user.dto.UserDTO;
import com.youyou.interfaces.IUserRPCSService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author longYee
 * Date 2024/7/28 11:50
 * Description:
 * Version: 1.0
 */
@Component
public class ConsumerTest implements CommandLineRunner {
    @DubboReference
    private IUserRPCSService userRPCSService;

    @Override
    public void run(String... args) throws Exception {
        UserDTO user = userRPCSService.getUserId(1L);
        System.out.println("Receive result ======> " + user);

//        new Thread(()-> {
//            while (true) {
//                try {
//                    Thread.sleep(1000);
//                    System.out.println(new Date() + " Receive result ======> " + userRPCSService.getUserId(1L));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }).start();
    }
}
