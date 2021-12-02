package com.bigheadgo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统日志实体
 * author: xiaoYang
 * time: 2021/11/18 0:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {

    private static final long serialVersionUID = -191580962803412824L;

    // 模块名
    private String module;
    // 方法名名
    private String method;
    // ip地址
    private String ip;
    // 用户名
    private String user;
    // 响应时间
    private String responseDate;
    // 访问日志 （访问时间）
    private String dates;
    // 访问结果
    private String commit;

}
