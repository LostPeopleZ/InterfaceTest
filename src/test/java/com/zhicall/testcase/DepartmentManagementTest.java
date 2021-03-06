package com.zhicall.testcase;

import com.zhicall.basetest.BaseTest;
import com.zhicall.pageobject.module.DepartmentManagementModule;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class DepartmentManagementTest extends BaseTest {
    public DepartmentManagementModule departmentManagementModule;

    @Test(description = "创建部门", priority = 1)
    public void testCreateDepartment() {
        departmentManagementModule = new DepartmentManagementModule(token);
        Response response = departmentManagementModule.createDepartment();
        // 多个断言
        Assertions.assertAll(
                () -> {
                    Assertions.assertEquals(0, (int) response.path("errcode"));
                },
                () -> {
                    Assertions.assertEquals("created", response.path("errmsg"));
                }
        );
    }

    @Test(description = "删除部门", priority = 2)
    public void testDeleteDepartment() {
        departmentManagementModule = new DepartmentManagementModule(token);
        Response response = departmentManagementModule.deleteDepartment();
        // 多个断言
        Assertions.assertAll(
                () -> {
                    Assertions.assertEquals(0, (int) response.path("errcode"));
                },
                () -> {
                    Assertions.assertEquals("deleted", response.path("errmsg"));
                }
        );
    }

    @Test(description = "其他操作", priority = 3)
    public void testOther() {
        // todo : 测试企业微信的部门管理的其他接口
    }
}
