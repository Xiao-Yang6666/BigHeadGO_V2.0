package com.bigheadgo.controller;

import com.bigheadgo.annotation.SysLogAnnotation;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/3 8:43
 */
@Api(value = "登录模块")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "微信小程序登录", notes = "微信小程序端登录,没有登录过的新用户自动注册进数据库")
    @ApiImplicitParams({@ApiImplicitParam(name = "小程序登录接口产生的临时code")})
    @PostMapping("wechatLogin")
    @SysLogAnnotation(module = "登录模块", method = "微信小程序登录")
    public RespJson wechatLogin(@RequestParam String code) {
        return loginService.wechatLogin(code);
    }

}
