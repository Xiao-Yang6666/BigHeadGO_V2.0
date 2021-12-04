package com.bigheadgo.service.imp;

import com.bigheadgo.dao.Dao;
import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.AdminService;
import com.bigheadgo.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 管理员服务实现
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 23:38
 */
@Service("AdminServiceImp")
public class AdminServiceImp implements AdminService {

    @Resource(name = "supportDao")
    private Dao dao;

    @Override
    public RespJson selectAdmin(ReqParaMap reqParaMap) {
        return MyUtils.isCode("管理员列表",
                (Integer) dao.findForObject("companyAdmin", "count", reqParaMap),
                dao.findForList("companyAdmin", "findAll", reqParaMap));
    }

    @Override
    public RespJson insertAdmin(Map<String, String> map) {
        return MyUtils.isCode("管理员车辆", dao.insert("carinfo", "insert", map));
    }

    @Override
    public RespJson updateAdmin(ReqParaMap reqParaMap) {
        return MyUtils.isCode("修改管理员信息", dao.update("carinfo", "update", reqParaMap));
    }
}
