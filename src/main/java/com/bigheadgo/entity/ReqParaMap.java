package com.bigheadgo.entity;

import lombok.Data;

import java.util.Map;

/**
 * 接收前台参数的格式规约
 * <p>
 * 响应时一定要使用此规约,统一规范,后期可以在此做别的操作
 * 改为大map套小map格式,使mapper循环sql、分页操作更简单
 * 目前可处理分页参数,拿到小map外部
 * <p>
 * author: xiaoYang
 * time: 2021/12/1 21:42
 */
@Data
public class ReqParaMap {
    // 别的参数
    private Map<String, ?> map;
    // 分页开始下标
    private Integer page;
    // 每页显示条数
    private Integer limit;

    /**
     * 处理数据
     *
     * @param map 页面接收的map
     */
    public ReqParaMap(Map<String, ?> map) {
        // 处理分页参数
        if (map.containsKey("page") && map.containsKey("limit")) {
            // 获取值
            Integer page = map.get("page") == null ? 0 : Integer.getInteger(map.remove("page") + "");
            Integer limit = map.get("limit") == null ? 10 : Integer.getInteger(map.remove("limit") + "");
            // 处理分页开始下标
            this.page = page * limit;
            this.limit = limit;
        }
        // 赋值
        this.map = map;
    }
}
