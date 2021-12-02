package com.bigheadgo.interceptor;

import com.bigheadgo.utils.mybatisPage.MybatisInterUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import javax.annotation.Resource;
import java.sql.Connection;

/**
 * 自定义mybatis插件
 * author: xiaoYang
 * time: 2021/12/1 23:30
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MybatisInterceptor implements Interceptor {
    // 引入自定义mybatis插件
    @Resource(name = "mybatisInterUtil")
    private MybatisInterUtil mybatisInterUtil;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 调用自定义插件核心方法
        return mybatisInterUtil.core(invocation);
    }
}
