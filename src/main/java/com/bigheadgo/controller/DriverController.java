package com.bigheadgo.controller;

import com.bigheadgo.annotation.SysLogAnnotation;
import com.bigheadgo.controller.base.BaseController;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 司机模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 22:48
 */
@Api(tags = "司机模块")
@RestController
@RequestMapping("driver")
public class DriverController extends BaseController {
    @Autowired
    private DriverService driverService;

    @ApiOperation(value = "查询司机列表", notes = "集成模糊查询,分页,分类查询")
    @ApiImplicitParam(name = "map", value = "模糊字段,分页参数,分类字段....")
    @GetMapping("selectDriver")
    @SysLogAnnotation(module = "司机模块", method = "司机查询")
    public RespJson selectDriver(@RequestParam Map<String, String> map) {
        return driverService.selectDriver(this.getReqParaMap(map));
    }

    @ApiOperation(value = "司机添加接口", notes = "只有管理员才可以调用")
    @ApiImplicitParam(name = "map", value = "司机的信息")
    @PostMapping("insertDriver")
    @SysLogAnnotation(module = "司机模块", method = "司机添加")
    public RespJson insertDriver(@RequestParam Map<String, String> map) {
        return driverService.insertDriver(map);
    }

    @ApiOperation(value = "司机信息修改", notes = "只有管理员才可以调用,传什么参修改什么")
    @ApiImplicitParam(name = "map", value = "需要修改的信息")
    @PostMapping("updateDriver")
    @SysLogAnnotation(module = "司机模块", method = "司机修改")
    public RespJson updateDriver(@RequestParam Map<String, String> map) {
        return driverService.updateDriver(this.getReqParaMap(map));
    }

    @ApiOperation(value = "司机位置信息查询", notes = "查询司机当前位置信息,后期换redis")
    @ApiImplicitParam(name = "driver_id", value = "司机id")
    @GetMapping("selectDriverAddress")
    @SysLogAnnotation(module = "司机模块", method = "司机位置信息查询")
    public RespJson selectDriverAddress(@RequestParam Integer driver_id) {
        return driverService.selectDriverAddress(driver_id);
    }

    @ApiOperation(value = "司机位置信息修改", notes = "修改司机当前位置信息,后期换redis")
    @ApiImplicitParam(name = "map", value = "司机id, 经纬度")
    @PostMapping("updateDriverAddress")
    @SysLogAnnotation(module = "司机模块", method = "司机位置信息修改")
    public RespJson updateDriverAddress(@RequestParam Map<String, String> map) {
        return driverService.updateDriverAddress(this.getReqParaMap(map));
    }


}
