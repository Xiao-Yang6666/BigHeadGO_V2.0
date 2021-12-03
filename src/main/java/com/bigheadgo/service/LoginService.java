package com.bigheadgo.service;

import com.bigheadgo.entity.RespJson;

/**
 * 登录服务层
 * <p>
 * author: xiaoYang
 * time: 2021/12/3 11:20
 */
public interface LoginService {
    /**
     * 微信小程序登录
     *
     * @param code 临时code
     * @return 响应前台的Json规范
     */
    RespJson wechatLogin(String code);
}
