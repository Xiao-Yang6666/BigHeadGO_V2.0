package com.bigheadgo.controller;

import com.bigheadgo.annotation.SysLogAnnotation;
import com.bigheadgo.controller.base.BaseController;
import com.bigheadgo.entity.RespJson;
import com.bigheadgo.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录模块
 * <p>
 * author: xiaoYang
 * time: 2021/12/3 8:43
 */
@Api(tags = "登录模块")
@RestController
@RequestMapping("login")
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "微信小程序登录(客户)", notes = "微信小程序端登录,没有登录过的新用户自动注册进数据库")
    @ApiImplicitParam(name = "code", value = "小程序登录接口产生的临时code")
    @PostMapping("wechatClientLogin")
    @SysLogAnnotation(module = "登录模块", method = "微信小程序登录(客户)")
    public RespJson wechatClientLogin(@RequestParam String code, HttpSession session) {
        return loginService.wechatClientLogin(code, session);
    }

    @ApiOperation(value = "微信小程序登录(司机)", notes = "微信小程序端登录,没有登录过的新用户自动注册进数据库,默认状态为-1,需要提交资料由管理员审核")
    @ApiImplicitParam(name = "code", value = "小程序登录接口产生的临时code")
    @PostMapping("wechatDriverLogin")
    @SysLogAnnotation(module = "登录模块", method = "微信小程序登录(司机)")
    public RespJson wechatDriverLogin(@RequestParam String code, HttpSession session) {
        return loginService.wechatDriverLogin(code, session);
    }

    @ApiOperation(value = "公司管理员登录", notes = "公司管理员的登录,后台登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "map", value = "参数:admin_name, admin_pwd")})
    @GetMapping("companyAdminLogin")
    @SysLogAnnotation(module = "登录模块", method = "管理员登录")
    public RespJson companyAdminLogin(@RequestParam Map<String, String> map, HttpSession session) {
        return loginService.companyAdminLogin(this.getReqParaMap(map), session);
    }

    @ApiOperation(value = "判断用户的登录状态", notes = "判断的是当前session中是否保存的是什么类型的用户")
    @GetMapping("isLogin")
    @SysLogAnnotation(module = "登录模块", method = "判断用户的登录状态")
    public RespJson isLogin(HttpSession session) {
        return loginService.isLogin(session);
    }

}
