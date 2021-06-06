package com.zhicall.pageobject.module;

import com.zhicall.basepage.BaseModule;
import com.zhicall.pageobject.data.BaiduData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * 百度接口操作
 *
 */
public class BaiduModule extends BaseModule {
    /**
     * 有参构造器
     *
     * @param token 身份信息
     */
    public BaiduModule(String token) {
        super(token);
    }

    /**
     * 无参构造器
     */
    public BaiduModule() {
        super();
    }

    /**
     * 百度搜索
     *
     * @return Response
     */
    public Response search() {
        return given().when()
                .headers("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36")
                .get("https://www.baidu.com" + BaiduData.searchRequest.get("api") + "?" + BaiduData.searchRequest.get("body"))
                .then()
                .extract().response();
    }
}