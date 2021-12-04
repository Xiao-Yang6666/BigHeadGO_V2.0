package com.bigheadgo.service.imp;

import com.bigheadgo.dao.Dao;
import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.CarService;
import com.bigheadgo.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 车辆服务实现类
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 21:09
 */
@Service("carServiceImp")
public class CarServiceImp implements CarService {

    @Resource(name = "supportDao")
    private Dao dao;

    @Override
    public RespJson selectCar(ReqParaMap reqParaMap) {
        return MyUtils.isCode("车辆列表",
                (Integer) dao.findForObject("carinfo", "count", reqParaMap),
                dao.findForList("carinfo", "findAll", reqParaMap));
    }

    @Override
    public RespJson insertCar(Map<String, String> map) {
        return MyUtils.isCode("新增车辆", dao.insert("carinfo", "insert", map));
    }

    @Override
    public RespJson updateCar(ReqParaMap reqParaMap) {
        return MyUtils.isCode("修改车辆信息", dao.update("carinfo", "update", reqParaMap));
    }

}
