package com.bigheadgo.controller;

import com.bigheadgo.annotation.SysLogAnnotation;
import com.bigheadgo.controller.base.BaseController;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.OrderService;
import com.bigheadgo.utils.MyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 21:53
 */
@Api(tags = "订单模块")
@RestController
@RequestMapping("order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "计算金额", notes = "根据两地的经纬度计算金额")
    @ApiImplicitParams({@ApiImplicitParam(name = "start_lat", value = "起始地经度", required = true),
            @ApiImplicitParam(name = "start_lng", value = "起始地维度", required = true),
            @ApiImplicitParam(name = "end_lat", value = "目的地经度", required = true),
            @ApiImplicitParam(name = "end_lng", value = "目的地维度", required = true)})
    @GetMapping("computePrice")
    @SysLogAnnotation(module = "订单模块", method = "计算金额")
    public RespJson computePrice(@RequestParam String start_lat,
                                 @RequestParam String start_lng,
                                 @RequestParam String end_lat,
                                 @RequestParam String end_lng) {
        return orderService.computePrice(start_lat, start_lng, end_lat, end_lng);
    }

    @ApiOperation(value = "查询订单列表", notes = "集成模糊查询,分页,分类查询")
    @ApiImplicitParam(name = "map", value = "模糊字段,分页参数,分类字段....")
    @GetMapping("selectOrder")
    @SysLogAnnotation(module = "订单模块", method = "订单查询")
    public RespJson selectOrder(@RequestParam Map<String, String> map) {
        return orderService.selectOrder(this.getReqParaMap(map));
    }

    @ApiOperation(value = "订单添加接口", notes = "用户提交订单")
    @ApiImplicitParam(name = "map", value = "订单的信息")
    @PostMapping("insertOrder")
    @SysLogAnnotation(module = "订单模块", method = "订单添加")
    public RespJson insertOrder(@RequestParam Map<String, String> map) {
        // 添加创建时间
        map.put("order_create_time", MyUtils.getTime());
        return orderService.insertOrder(map);
    }

    @ApiOperation(value = "订单信息修改", notes = "传什么参修改什么,司机接单也用这个接口")
    @ApiImplicitParam(name = "map", value = "需要修改的信息")
    @PostMapping("updateOrder")
    @SysLogAnnotation(module = "订单模块", method = "订单修改")
    public RespJson updateOrder(@RequestParam Map<String, String> map) {
        // 添加修改时间
        map.put("order_update_time", MyUtils.getTime());
        // 修改版本号
        map.put("order_version", (Integer.getInteger(map.remove("order_version") + 1) + ""));
        return orderService.updateOrder(this.getReqParaMap(map));
    }

}
