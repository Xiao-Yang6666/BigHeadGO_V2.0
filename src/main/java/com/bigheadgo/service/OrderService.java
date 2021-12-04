package com.bigheadgo.service;

import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;

import java.util.Map;

/**
 * 订单服务
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 21:54
 */
public interface OrderService {
    /**
     * 查询订单列表
     *
     * @param reqParaMap 参数
     * @return RespJson
     */
    RespJson selectOrder(ReqParaMap reqParaMap);

    /**
     * 订单新增
     *
     * @param map 订单信息
     * @return RespJson 添加状态
     */
    RespJson insertOrder(Map<String, String> map);

    /**
     * 订单修改
     *
     * @param reqParaMap 需要修改的字段
     * @return RespJson 修改状态
     */
    RespJson updateOrder(ReqParaMap reqParaMap);

    /**
     * 根据两地经纬度获取金额
     *
     * @param start_lng 起始地经度
     * @param start_lat 起始地地维度
     * @param end_lng   目的地经度
     * @param end_lat   目的地维度
     * @return RespJson 金额
     */
    RespJson computePrice(String start_lat, String start_lng, String end_lat, String end_lng);
}
