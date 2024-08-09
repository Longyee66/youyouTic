package com.youyou.id;

import com.youyou.id.inter.IGenerateIDRPCService;
import jakarta.annotation.Resource;
import me.ahoo.cosid.provider.IdGeneratorProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdTest {

    @Resource
    private IGenerateIDRPCService iGenerateIDRPCService;

    @Test
    public void idTest() {
        for (int i = 0; i < 10; i++) {
            System.out.println("SeqId生成：" + iGenerateIDRPCService.getSeqId());
            System.out.println("unSeqId生成：" + iGenerateIDRPCService.getUnSeqId());
        }
    }

}
