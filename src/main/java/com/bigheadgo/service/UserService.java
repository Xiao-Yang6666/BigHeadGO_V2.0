package com.bigheadgo.service;

import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;

import java.util.Map;

/**
 * 用户模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 21:42
 */
public interface UserService {
    /**
     * 查询用户列表
     *
     * @param reqParaMap 参数
     * @return RespJson
     */
    RespJson selectUser(ReqParaMap reqParaMap);

    /**
     * 用户新增
     *
     * @param map 车辆信息
     * @return RespJson 添加状态
     */
    RespJson insertUser(Map<String, String> map);


    /**
     * 用户修改
     *
     * @param reqParaMap 需要修改的字段
     * @return RespJson 修改状态
     */
    RespJson updateUser(ReqParaMap reqParaMap);
}
