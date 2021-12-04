package com.bigheadgo.service.imp;

import com.bigheadgo.dao.Dao;
import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.UserService;
import com.bigheadgo.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户模块实现
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 21:43
 */
@Service("userServiceImp")
public class UserServiceImp implements UserService {

    @Resource(name = "supportDao")
    private Dao dao;

    @Override
    public RespJson selectUser(ReqParaMap reqParaMap) {
        return MyUtils.isCode("用户列表",
                (Integer) dao.findForObject("userinfo", "count", reqParaMap),
                dao.findForList("userinfo", "findAll", reqParaMap));
    }

    @Override
    public RespJson insertUser(Map<String, String> map) {
        return MyUtils.isCode("新增用户", dao.insert("userinfo", "insert", map));
    }

    @Override
    public RespJson updateUser(ReqParaMap reqParaMap) {
        return MyUtils.isCode("修改用户信息", dao.update("userinfo", "update", reqParaMap));
    }

}
