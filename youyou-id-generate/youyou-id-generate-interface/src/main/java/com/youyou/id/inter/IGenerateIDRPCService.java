package com.youyou.id.inter;

/**
 * Author 龙贵义
 * Date 2024/8/7 19:27
 * Description:
 */
public interface IGenerateIDRPCService {
    /**
     * 严格有序ID 1 2 3 4
     * @return
     */
    Long getSeqId();

    /**
     * 非严格有序ID 1 3 5
     * @return
     */
    Long getUnSeqId();

}
