package com.bigheadgo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigheadgo.annotation.SysLogAnnotation;
import com.bigheadgo.controller.base.BaseController;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 测试接口
 * <p>
 * author: xiaoYang
 * time: 2021/12/2 1:16
 */
@Api(tags = "测试模块") // 接口文档
@RestController
@RequestMapping(value = "/test")
public class TestController extends BaseController {
    @Autowired
    private TestService testService;

    /**
     * 测试
     *
     * @param map 前台传递的参数
     * @return RespJson
     */
    @ApiOperation(value = "测试项目运行", notes = "测试") // 接口文档
    @ApiImplicitParam(name = "map", value = "用户名,密码", dataType = "map")
    @SysLogAnnotation(method = "dd", module = "ss") // 记录日志
    @GetMapping("ss")// Get类型请求
    public RespJson ss(@RequestParam Map<String, String> map) {
        System.out.println(map);
        return testService.shows(this.getReqParaMap(map));
    }

    /**
     * 前台发送json型串 研究中
     *
     * @param jsonObject json格式
     * @return null
     */
    @ApiOperation(value = "接受前台传来的json串", notes = "还在研究中,不知道前台怎么传值过来")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户信息", required = true, paramType = "body")
    })
    @PostMapping("aa")
    public RespJson aa(@ApiIgnore @RequestBody JSONObject jsonObject) {
        // 转为map
        Map map = JSON.toJavaObject(jsonObject, Map.class);
        System.out.println(map);
        return testService.shows(this.getReqParaMap(map));
    }
}
