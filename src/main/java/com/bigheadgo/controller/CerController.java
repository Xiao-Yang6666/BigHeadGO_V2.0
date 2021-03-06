package com.bigheadgo.controller;

import com.bigheadgo.annotation.SysLogAnnotation;
import com.bigheadgo.controller.base.BaseController;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 车辆模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/3 8:44
 */
@Api(tags = "车辆模块")
@RestController
@RequestMapping("car")
public class CerController extends BaseController {
    @Autowired
    private CarService carService;

    @ApiOperation(value = "查询车辆列表", notes = "集成模糊查询,分页,分类查询")
    @ApiImplicitParam(name = "map", value = "模糊字段,分页参数,分类字段....")
    @GetMapping("selectCar")
    @SysLogAnnotation(module = "车辆模块", method = "车辆查询")
    public RespJson selectCar(@RequestParam Map<String, String> map) {
        return carService.selectCar(this.getReqParaMap(map));
    }

    @ApiOperation(value = "车辆添加接口", notes = "只有管理员才可以调用")
    @ApiImplicitParam(name = "map", value = "车辆的信息")
    @PostMapping("insertCar")
    @SysLogAnnotation(module = "车辆模块", method = "车辆添加")
    public RespJson insertCar(@RequestParam Map<String, String> map) {
        return carService.insertCar(map);
    }

    @ApiOperation(value = "车辆信息修改", notes = "只有管理员才可以调用,传什么参修改什么")
    @ApiImplicitParam(name = "map", value = "需要修改的信息")
    @PostMapping("updateCar")
    @SysLogAnnotation(module = "车辆模块", method = "车辆修改")
    public RespJson updateCar(@RequestParam Map<String, String> map) {
        return carService.updateCar(this.getReqParaMap(map));
    }
}
