package com.zhicall.pageobject.module;

import com.zhicall.basepage.BaseModule;
import com.zhicall.util.PropertiesReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.zhicall.pageobject.data.OpmsData.LoginOpms;
import static com.zhicall.util.httpclient.HTTPClientUtil.doPost;
import static io.restassured.RestAssured.given;
@Slf4j
public class OpmsModule extends BaseModule {

    /**
     * 有参构造器
     *
     * @param token
     */
    public OpmsModule(String token){
        super(token);
    }

    /**
     * 无参构造器
     */
    public OpmsModule(){
        super();
    }


    /**
     * 示例1 表单的post调用 只用于测试
     * @return
     */
    public Response Login(){
        return given().when().
                formParam("username",LoginOpms.get("username")).
                formParam("password",LoginOpms.get("password")).
                post(PropertiesReader.getKey("serverName")).
                then().
                extract().response();

    }

    /**
     * 示例2 表单的post调用 可用于用例层的调用
     * @param username
     * @param password
     * @return
     */
    public String Login(String username,String password){

        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);

        return doPost("http://localhost:8088/login",map);
    }

    public static void main(String[] args) {
        System.out.println(PropertiesReader.getKey("serverName"));
    }
}
