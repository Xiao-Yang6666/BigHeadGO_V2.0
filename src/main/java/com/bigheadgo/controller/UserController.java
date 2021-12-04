package com.bigheadgo.controller;

import com.bigheadgo.annotation.SysLogAnnotation;
import com.bigheadgo.controller.base.BaseController;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/4 21:40
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询用户列表", notes = "集成模糊查询,分页,分类查询")
    @ApiImplicitParam(name = "map", value = "模糊字段,分页参数,分类字段....")
    @GetMapping("selectCar")
    @SysLogAnnotation(module = "用户模块", method = "用户查询")
    public RespJson selectCar(@RequestParam Map<String, String> map) {
        return userService.selectUser(this.getReqParaMap(map));
    }

    @ApiOperation(value = "用户添加接口", notes = "只有管理员才可以调用")
    @ApiImplicitParam(name = "map", value = "用户的信息")
    @PostMapping("insertCar")
    @SysLogAnnotation(module = "用户模块", method = "用户添加")
    public RespJson insertCar(@RequestParam Map<String, String> map) {
        return userService.insertUser(map);
    }

    @ApiOperation(value = "用户信息修改", notes = "只有管理员才可以调用,传什么参修改什么")
    @ApiImplicitParam(name = "map", value = "需要修改的信息")
    @PostMapping("updateCar")
    @SysLogAnnotation(module = "用户模块", method = "用户修改")
    public RespJson updateCar(@RequestParam Map<String, String> map) {
        return userService.updateUser(this.getReqParaMap(map));
    }
}
