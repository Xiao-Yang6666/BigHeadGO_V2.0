package com.bigheadgo.service.imp;

import com.bigheadgo.dao.Dao;
import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.TestService;
import com.bigheadgo.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试模块
 * author: xiaoYang
 * time: 2021/12/2 12:22
 */
@Service("testServiceImp")
public class TestServiceImp implements TestService {
    @Autowired
    private Dao dao;

    @Override
    public RespJson shows(ReqParaMap reqParaMap) {
        // 通过util封装json数据返回前台
        return MyUtils.isCode("测试", dao.findForList("userMapper", "findAll", reqParaMap));
    }
}
