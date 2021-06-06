package com.zhicall.testcase;

import com.zhicall.basetest.BaseTest;
import com.zhicall.pageobject.data.OpmsData;
import com.zhicall.pageobject.module.OpmsModule;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * opms测试类
 */
public class OpmsTest extends BaseTest {

    private OpmsModule opmsModule;

    @Story("Opms测试Story")
    @Description("Opms测试Description")
    @Test(dataProvider = "LoginOpms",dataProviderClass = OpmsData.class)
    public void testLoginOpms(String username,String password){
        opmsModule = new OpmsModule();
        String response = opmsModule.Login(username,password);
        System.out.println(response);

        JSONObject json = JSONObject.fromObject(response);

        Assertions.assertAll(
                ()-> {
                    Assertions.assertEquals(1,json.get("code"));
                },
                ()->{
                    Assertions.assertEquals("贺喜你，登录成功",json.get("message"));
                }
        );

    }


}
