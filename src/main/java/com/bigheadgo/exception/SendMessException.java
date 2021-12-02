package com.bigheadgo.exception;

/**
 * 短信异常 自定义异常
 * <p>
 * author: xiaoYang
 * time: 2021/11/19 18:37
 */
public class SendMessException extends Exception {
    public SendMessException() {
        super();
    }

    public SendMessException(String message) {
        super(message);
    }
}
