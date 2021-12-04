package com.bigheadgo.service.imp;

import com.bigheadgo.dao.Dao;
import com.bigheadgo.entity.ReqParaMap;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.OrderService;
import com.bigheadgo.utils.MapUtil;
import com.bigheadgo.utils.MyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 订单服务实现
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 21:55
 */
@Service("orderServiceImp")
public class OrderServiceImp implements OrderService {

    @Resource(name = "supportDao")
    private Dao dao;

    @Override
    public RespJson selectOrder(ReqParaMap reqParaMap) {
        return MyUtils.isCode("订单列表",
                (Integer) dao.findForObject("orderinfo", "count", reqParaMap),
                dao.findForList("orderinfo", "findAll", reqParaMap));
    }

    @Override
    public RespJson insertOrder(Map<String, String> map) {
        map.put("order_state", "0");
        return MyUtils.isCode("新增订单", dao.insert("orderinfo", "insert", map));
    }

    @Override
    public RespJson updateOrder(ReqParaMap reqParaMap) {
        return MyUtils.isCode("修改订单信息", dao.update("orderinfo", "update", reqParaMap));
    }

    @Override
    public RespJson computePrice(String start_lat, String start_lng, String end_lat, String end_lng) {
        // 调用地图工具类计算金额
        return MyUtils.isCode("金额计算", MapUtil.getCost(start_lat, start_lng, end_lat, end_lng));
    }
}
