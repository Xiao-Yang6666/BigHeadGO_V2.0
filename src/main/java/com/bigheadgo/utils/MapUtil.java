package com.bigheadgo.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 地图工具类
 * <p>
 * author: xiaoYang
 * time: 2021/11/28 13:40
 */
@Data
@Slf4j
@Component
@ConfigurationProperties("map-util")
public class MapUtil {

    // 默认起始点经纬度 配置文件中赋值
    private double start_lng;
    private double start_lat;

    /**
     * 获取金额
     * 计算公式待优化
     *
     * @param end_lng 目的地经度
     * @param end_lat 目的地维度
     * @return 金额
     */
    public String getCost(String end_lng, String end_lat) {
        // 每经度单位米;
        double jl_lat = 102834.74258026089786013677476285;
        // 每纬度单位米;
        double jl_lng = 111712.69150641055729984301412873;
        //获取差
        double lat = Math.abs((start_lat - Double.parseDouble(end_lat)) * jl_lat);
        double lng = Math.abs((start_lng - Double.parseDouble(end_lng)) * jl_lng);
        double sum = Math.sqrt((lat * lat + lng * lng));
        double a = sum % 1;
        double lon = sum - a;

        double cost = 0;
        // 具体计算 待优化
        if (lon <= 5000) {
            cost = 80;
        } else if (lon <= 10000) {
            cost = ((lon - 5000) / 1000 * 10 + 80);
        } else {
            cost = ((lon - 5000) / 1000 * 10 + 80);
            cost = ((lon - 10000) / 1000 * 8) + cost;
        }
        return MyUtils.dealingScientificNotation(cost);
    }
}
