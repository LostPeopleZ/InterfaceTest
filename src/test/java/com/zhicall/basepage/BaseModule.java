package com.zhicall.basepage;

import com.zhicall.util.PropertiesReader;

/**
 * 测试模块基础类
 *
 */
public class BaseModule {
    /**
     * token
     */
    public String token;

    /**
     * 前缀
     */
    public final String hostUrl = PropertiesReader.getKey("serverName");

    /**
     * 有参构造器
     *
     * @param token 身份信息
     */
    public BaseModule(String token) {
        this.token = token;
    }

    /**
     * 无参构造器
     */
    public BaseModule() {
    }
}
