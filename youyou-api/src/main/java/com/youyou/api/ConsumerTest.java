package com.youyou.api;

import com.youyou.interfaces.IUserRPCSService;
import com.youyou.moudules.user.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;

/**
 * Author longYee
 * Date 2024/7/28 11:50
 * Description:
 * Version: 1.0
 */
//@Component
public class ConsumerTest implements CommandLineRunner {
    @DubboReference(version = "1.0.0")
    private IUserRPCSService userRPCSService;

    @Override
    public void run(String... args) throws Exception {
        UserDTO user = userRPCSService.getUserById(1L);
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
