package com.bigheadgo.service.imp;

import com.bigheadgo.dao.Dao;
import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.LoginService;
import com.bigheadgo.utils.MyUtils;
import com.bigheadgo.utils.WeChatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * 登录服务实现类
 * <p>
 * author: xiaoYang
 * time: 2021/12/3 11:31
 */
@Service("loginServiceImp")
public class LoginServiceImp implements LoginService {
    @Resource(name = "serviceThreadPool")
    private Executor serviceThreadPool;

    @Resource(name = "weChatUtil")
    private WeChatUtil weChatUtil;

    @Resource(name = "supportDao")
    private Dao dao;

    @Override
    public RespJson wechatClientLogin(String code, HttpSession session) {
        // 调用微信接口获取当前用户的openId
        String openid = weChatUtil.getOpenid(code);

        // 异步注册 将openid保存进数据库
        serviceThreadPool.execute(() -> {
            // 封装参数
            Map<String, Object> map = new HashMap<>();
            map.put("openid", openid);
            // 通过openid 查询用户
            Map<String, Object> userinfo = (Map<String, Object>) dao.findForObject("userMapper", "findOne", map);
            // 注册用户进数据库
            if (null == userinfo) {
                dao.insert("userinfo", "insert", map);
                userinfo = (Map<String, Object>) dao.findForObject("userinfo", "findOne", map);
            }
            session.setAttribute("userinfo", userinfo);
        });

        // 直接返回前台登录成功
        return MyUtils.isCode("用户登录", true);
    }

    @Override
    public RespJson wechatDriverLogin(String code, HttpSession session) {
        // 调用微信接口获取当前用户的openId
        String openid = weChatUtil.getOpenid(code);

        // 异步注册 将openid保存进数据库
        serviceThreadPool.execute(() -> {
            // 封装参数
            Map<String, Object> map = new HashMap<>();
            map.put("openid", openid);
            // 通过openid 查询用户
            Map<String, Object> driverinfo = (Map<String, Object>) dao.findForObject("driverinfo", "findOne", map);
            // 注册用户进数据库
            if (null == driverinfo) {
                dao.insert("driverinfo", "insert", map);
                driverinfo = (Map<String, Object>) dao.findForObject("driverinfo", "findOne", map);
            }
            session.setAttribute("driverinfo", driverinfo);
        });

        // 直接返回前台登录成功
        return MyUtils.isCode("司机登录", true);
    }

    @Override
    public RespJson companyAdminLogin(ReqParaMap reqParaMap, HttpSession session) {
        Map<String, Object> companyAdmin = (Map<String, Object>) dao.findForObject("companyAdmin", "findOne", reqParaMap);
        session.setAttribute("companyAdmin", companyAdmin);

        return MyUtils.isCode("管理员登录", companyAdmin);
    }

    @Override
    public RespJson isLogin(HttpSession session) {
        if (session.getAttribute("userinfo") != null)
            return MyUtils.isCode("判断登录状态", "userinfo");
        if (session.getAttribute("driverinfo") != null)
            return MyUtils.isCode("判断登录状态", "driverinfo");
        if (session.getAttribute("companyAdmin") != null)
            return MyUtils.isCode("判断登录状态", "companyAdmin");

        return MyUtils.isCode("判断登录状态", null);
    }
}
