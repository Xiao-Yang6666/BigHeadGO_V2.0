package com.bigheadgo.service;

import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;

import java.util.Map;

/**
 * 司机模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 22:53
 */
public interface DriverService {
    /**
     * 查询司机列表
     *
     * @param reqParaMap 参数
     * @return RespJson
     */
    RespJson selectDriver(ReqParaMap reqParaMap);

    /**
     * 司机新增
     *
     * @param map 司机信息
     * @return RespJson 添加状态
     */
    RespJson insertDriver(Map<String, String> map);

    /**
     * 司机修改
     *
     * @param reqParaMap 需要修改的字段
     * @return RespJson 修改状态
     */
    RespJson updateDriver(ReqParaMap reqParaMap);

    /**
     * 司机位置信息查询
     *
     * @param driver_id 司机id
     * @return RespJson 经纬度信息
     */
    RespJson selectDriverAddress(Integer driver_id);

    /**
     * 司机位置信息修改
     *
     * @param reqParaMap 司机id, 经纬度
     * @return RespJson 修改状态
     */
    RespJson updateDriverAddress(ReqParaMap reqParaMap);
}
