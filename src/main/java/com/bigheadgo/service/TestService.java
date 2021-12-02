package com.bigheadgo.service;

import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;

/**
 * 测试模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/2 12:20
 */
public interface TestService {
    // 测试
    RespJson shows(ReqParaMap reqParaMap);
}
