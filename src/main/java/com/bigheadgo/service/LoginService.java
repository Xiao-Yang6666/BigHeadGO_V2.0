package com.bigheadgo.service;

import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;

import javax.servlet.http.HttpSession;

/**
 * 登录服务层
 * <p>
 * author: xiaoYang
 * time: 2021/12/3 11:20
 */
public interface LoginService {
    /**
     * 微信小程序登录客户
     *
     * @param code    临时code
     * @param session session
     * @return 响应前台的Json规范
     */
    RespJson wechatClientLogin(String code, HttpSession session);

    /**
     * 微信小程序登录司机
     *
     * @param code    临时code
     * @param session session
     * @return 响应前台的Json规范
     */
    RespJson wechatDriverLogin(String code, HttpSession session);

    /**
     * 公司管理员的登录
     *
     * @param reqParaMap 参数
     * @return 响应前台的Json规范
     */
    RespJson companyAdminLogin(ReqParaMap reqParaMap, HttpSession session);

    /**
     * 判断用户登录状态
     *
     * @param session session
     * @return 是否登录 登录的是什么用户
     */
    RespJson isLogin(HttpSession session);
}
