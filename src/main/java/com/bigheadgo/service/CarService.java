package com.bigheadgo.service;

import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;

import java.util.Map;

/**
 * 车辆服务
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 21:09
 */
public interface CarService {
    /**
     * 查询车辆列表
     *
     * @param reqParaMap 参数
     * @return RespJson
     */
    RespJson selectCar(ReqParaMap reqParaMap);

    /**
     * 车辆新增
     *
     * @param map 车辆信息
     * @return RespJson 添加状态
     */
    RespJson insertCar(Map<String, String> map);

    /**
     * 车辆修改
     *
     * @param reqParaMap 需要修改的字段
     * @return RespJson 修改状态
     */
    RespJson updateCar(ReqParaMap reqParaMap);
}
