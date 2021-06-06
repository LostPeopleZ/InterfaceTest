package com.zhicall.exception;

/**
 * 身份认证的接口请求异常
 *
 */
public class AuthException extends RuntimeException {
    /**
     * 构造器
     */
    public AuthException() {
        super();
    }

    /**
     * 有参构造器
     *
     * @param str 异常信息
     */
    public AuthException(String str) {
        super(str);
    }
}
