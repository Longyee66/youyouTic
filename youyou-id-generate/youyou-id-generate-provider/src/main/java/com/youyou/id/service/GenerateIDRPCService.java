package com.youyou.id.service;

import com.youyou.common.constants.IDConstant;
import com.youyou.id.inter.IGenerateIDRPCService;
import jakarta.annotation.Resource;
import me.ahoo.cosid.provider.IdGeneratorProvider;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Author 龙贵义
 * Date 2024/8/8 14:03
 * Description: 实现ID自动生成
 */
@DubboService(version = "1.0.0")
public class GenerateIDRPCService implements IGenerateIDRPCService {
    @Resource
    private IdGeneratorProvider idGeneratorProvider;

    /**
     * 严格有序ID
     * @return
     */
    @Override
    public Long getSeqId() {
        return idGeneratorProvider.get(IDConstant.ID_SEQUENCE).get().generate();
    }

    /**
     * 非严格有序ID
     * @return
     */
    @Override
    public Long getUnSeqId() {
        return idGeneratorProvider.get(IDConstant.ID_SNOWFLAKE).get().generate();
    }
}
