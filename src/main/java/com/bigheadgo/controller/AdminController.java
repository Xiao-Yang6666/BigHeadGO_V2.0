package com.bigheadgo.controller;

import com.bigheadgo.annotation.SysLogAnnotation;
import com.bigheadgo.controller.base.BaseController;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 管理员模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 23:32
 */
@Api(tags = "管理员模块")
@RestController
@RequestMapping("admin")
public class AdminController extends BaseController {
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "查询管理员列表", notes = "集成模糊查询,分页,分类查询")
    @ApiImplicitParam(name = "map", value = "模糊字段,分页参数,分类字段....")
    @GetMapping("selectAdmin")
    @SysLogAnnotation(module = "管理员模块", method = "管理员查询")
    public RespJson selectAdmin(@RequestParam Map<String, String> map) {
        return adminService.selectAdmin(this.getReqParaMap(map));
    }

    @ApiOperation(value = "管理员添加接口", notes = "只有管理员才可以调用")
    @ApiImplicitParam(name = "map", value = "管理员的信息")
    @PostMapping("insertAdmin")
    @SysLogAnnotation(module = "管理员模块", method = "管理员添加")
    public RespJson insertAdmin(@RequestParam Map<String, String> map) {
        return adminService.insertAdmin(map);
    }

    @ApiOperation(value = "管理员信息修改", notes = "只有管理员才可以调用,传什么参修改什么")
    @ApiImplicitParam(name = "map", value = "需要修改的信息")
    @PostMapping("updateAdmin")
    @SysLogAnnotation(module = "管理员模块", method = "管理员修改")
    public RespJson updateAdmin(@RequestParam Map<String, String> map) {
        return adminService.updateAdmin(this.getReqParaMap(map));
    }
}
