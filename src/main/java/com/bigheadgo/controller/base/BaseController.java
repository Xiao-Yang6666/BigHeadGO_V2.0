package com.bigheadgo.controller.base;

import com.bigheadgo.entity.ReqParaMap;

import java.util.Map;

/**
 * 所有接口的父类
 * <p>
 * author: xiaoYang
 * time: 2021/12/2 12:12
 */
public class BaseController {

    /**
     * 处理页面参数
     * 处理成大map套小map, 剔除分页参数
     *
     * @param map 页面参数
     * @return ReqParaMap 输入参数规范
     */
    public ReqParaMap getReqParaMap(Map<String, String> map) {
        return new ReqParaMap(map);
    }
    
}
