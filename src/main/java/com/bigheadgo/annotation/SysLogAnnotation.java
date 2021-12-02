package com.bigheadgo.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 操作日志
 * <p>
 * author: xiaoYang
 * time: 2021/11/17 23:47
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnnotation {
    /**
     * @return 模块名
     */
    String module() default "模块未指定";

    /**
     * @return 方法名
     */
    String method() default "方法为指定";
}
