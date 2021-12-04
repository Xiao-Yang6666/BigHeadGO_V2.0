package com.bigheadgo.service.imp;

import com.bigheadgo.dao.Dao;
import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.DriverService;
import com.bigheadgo.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * author: xiaoYang
 * time: 2021/12/4 22:56
 */
@Service
public class DriverServiceImp implements DriverService {
    @Resource(name = "supportDao")
    private Dao dao;

    @Override
    public RespJson selectDriver(ReqParaMap reqParaMap) {
        return MyUtils.isCode("司机列表",
                (Integer) dao.findForObject("driverinfo", "count", reqParaMap),
                dao.findForList("driverinfo", "findAll", reqParaMap));
    }

    @Override
    public RespJson insertDriver(Map<String, String> map) {
        return MyUtils.isCode("新增司机", dao.insert("driverinfo", "insert", map));
    }

    @Override
    public RespJson updateDriver(ReqParaMap reqParaMap) {
        return MyUtils.isCode("修改司机信息", dao.update("driverinfo", "update", reqParaMap));
    }

    @Override
    public RespJson selectDriverAddress(Integer driver_id) {
        return MyUtils.isCode("查询司机当前位置", dao.findForObject("driverinfo", "findAddress", driver_id));
    }

    @Override
    public RespJson updateDriverAddress(ReqParaMap reqParaMap) {
        return MyUtils.isCode("修改司机位置信息", dao.update("driverinfo", "update", reqParaMap));
    }
}
