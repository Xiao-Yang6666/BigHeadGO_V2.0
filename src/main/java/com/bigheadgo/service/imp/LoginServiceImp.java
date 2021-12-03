package com.bigheadgo.service.imp;

import com.bigheadgo.dao.Dao;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.LoginService;
import com.bigheadgo.utils.MyUtils;
import com.bigheadgo.utils.WeChatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public RespJson wechatLogin(String code) {
        // 调用微信接口获取当前用户的openId
        String openid = weChatUtil.getOpenid(code);
        // 异步注册 将openid保存进数据库
        serviceThreadPool.execute(() -> {
            // 封装参数
            Map<String, Object> map = new HashMap<>();
            map.put("openid", openid);
            // 通过openid 查询用户
            Map<String, Object> userMap = (Map<String, Object>) dao.findForObject("userMapper", "findOne", map);
            // 注册用户进数据库
            if (null == userMap) {
                dao.insert("userMapper", "insert", map);
            }
        });

        // 直接返回前台登录成功
        return MyUtils.isCode("用户登录", true);
    }
}
