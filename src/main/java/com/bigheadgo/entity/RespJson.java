package com.bigheadgo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 响应前台的json格式规约
 * <p>
 * 响应时一定要使用此规约,统一规范
 * <p>
 * author: xiaoYang
 * time: 2021/12/1 22:02
 */
@Data
@ApiModel
public class RespJson {
    // 状态码 0 为200
    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;
    // 携带的备注信息
    @ApiModelProperty(value = "携带的备注信息", required = true)
    private String msg;
    // 分页查询的总条数
    @ApiModelProperty(value = "分页查询的总条数")
    private Integer count;
    // 数据体
    @ApiModelProperty(value = "数据体", required = true)
    private Object data;

    /**
     * 不带携带分页信息(总条数)
     *
     * @param code 状态码
     * @param msg  备注信息
     * @param data 数据体
     */
    public RespJson(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 携带分页信息(总条数)
     *
     * @param code  状态码
     * @param msg   备注信息
     * @param count 分页总条数
     * @param data  数据体
     */
    public RespJson(Integer code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
