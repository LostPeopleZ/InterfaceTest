package com.zhicall.pageobject.module;

import com.zhicall.basepage.BaseModule;
import io.restassured.response.Response;

import static com.zhicall.pageobject.data.DepartmentManagementData.createDepartmentRequest;
import static com.zhicall.pageobject.data.DepartmentManagementData.deleteDepartmentRequest;
import static io.restassured.RestAssured.given;

/**
 * 部门管理模块-接口操作
 *
 */
public class DepartmentManagementModule extends BaseModule {

    /**
     * 构造器
     *
     * @param token 身份信息
     */
    public DepartmentManagementModule(String token) {
        super(token);
    }

    /**
     * 企业微信创建部门
     *
     * @return Response
     */
    public Response createDepartment() {
        return given().when()
                .contentType("application/json")
                .body(createDepartmentRequest.get("body"))
                .post(hostUrl + createDepartmentRequest.get("api") + "?access_token=" + token)
                .then().log().body()
                .extract().response();
    }

    /**
     * 企业微信删除部门
     *
     * @return Response
     */
    public Response deleteDepartment() {
        return given().when()
                .contentType("application/json")
                .body(deleteDepartmentRequest.get("body"))
                .get(hostUrl + deleteDepartmentRequest.get("api") + "?access_token=" + token + "&" + deleteDepartmentRequest.get("body"))
                .then().log().body()
                .extract().response();
    }
}