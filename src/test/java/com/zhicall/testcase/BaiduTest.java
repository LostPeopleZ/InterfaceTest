package com.zhicall.testcase;

import com.zhicall.basetest.BaseTest;
import com.zhicall.pageobject.module.BaiduModule;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 测试类
 */
public class BaiduTest extends BaseTest {
    private BaiduModule baiduModule;

    @Test(description = "百度搜索", priority = 1)
    public void testSearch() {
        baiduModule = new BaiduModule();
        Assert.assertTrue(baiduModule.search().getBody().asString().contains("百度搜索"));
    }

    @Test(description = "其他操作", priority = 2)
    public void testOther() {
        // todo : 测试其他接口
    }

}