package com.bigheadgo.utils.mybatisPage;

import com.bigheadgo.entity.ReqParaMap;
import lombok.Data;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.stereotype.Component;

/**
 * mybatis 自定义插件 util
 * author: xiaoYang
 * time: 2021/11/17 9:51
 */
@Data
@Component("mybatisInterUtil")
public class MybatisInterUtil {
    // 方法权限定名
    private String id = null;
    // 拦截下来的 sql语句
    private String sql = null;
    // 参数实体类
    private Object parameterObject = null;

    private PreparedStatementHandler delegate = null;

    /**
     * 自定义 mybatis 插件的核心方法
     *
     * @return Invocation
     */
    public Invocation core(Invocation invocation) throws IllegalAccessException, NoSuchFieldException {
        // 通过反射获取想要的对象 
        RoutingStatementHandler target = (RoutingStatementHandler) invocation.getTarget();
        delegate = (PreparedStatementHandler) ReflectUtil.getValueByFieldName(target, "delegate");
        MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getValueByFieldName(delegate, "mappedStatement");
        // 方法权限定名
        id = mappedStatement.getId();
        // 所执行的sql 语句
        sql = delegate.getBoundSql().getSql();
        // 参数实体类
        parameterObject = delegate.getParameterHandler().getParameterObject();
        // 方法名
        String methodName = id.substring(id.lastIndexOf(".") + 1);
        // 调用响应的分支 包含findAll的都做分页
        if (methodName.contains("findAll")) {
            // 判断分页的方法 
            findAll();
        }
        return invocation;
    }

    /**
     * 分页管理
     */
    public void findAll() throws NoSuchFieldException, IllegalAccessException {
        // 参数实体类 转为  pageMap
        ReqParaMap pageMap = (ReqParaMap) parameterObject;
        // 捕获空指针
        try {
            // 获取分页参数
            int page = pageMap.getPage();
            int pageSize = pageMap.getLimit();
            // 修改sql语句
            ReflectUtil.setValueByFieldName(delegate.getBoundSql(), "sql", sql + " limit " + page + "," + pageSize);
        } catch (NullPointerException e) {
            ReflectUtil.setValueByFieldName(delegate.getBoundSql(), "sql", sql);
        }
    }

}
