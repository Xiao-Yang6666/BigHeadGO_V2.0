package com.bigheadgo.service;

import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;

import java.util.Map;

/**
 * 管理员服务
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 23:36
 */
public interface AdminService {
    /**
     * 查询车辆列表
     *
     * @param reqParaMap 参数
     * @return RespJson
     */
    RespJson selectAdmin(ReqParaMap reqParaMap);

    /**
     * 车辆新增
     *
     * @param map 车辆信息
     * @return RespJson 添加状态
     */
    RespJson insertAdmin(Map<String, String> map);

    /**
     * 车辆修改
     *
     * @param reqParaMap 需要修改的字段
     * @return RespJson 修改状态
     */
    RespJson updateAdmin(ReqParaMap reqParaMap);
}
