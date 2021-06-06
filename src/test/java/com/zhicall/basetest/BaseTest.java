package com.zhicall.basetest;

import com.zhicall.util.PropertiesReader;
import com.zhicall.util.WordartDisplayer;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 测试基础类
 *
 */
public class BaseTest {
    /**
     * 用于接口登录
     */
    private BaseToken baseToken;
    public List<Map<String,String>> ExcelData;

    /**
     * 产生的登录身份信息
     */
    public String token;

    /**
     * 执行一个测试套之前执行
     * 进行测试配置文件的读取工作
     * 由于 BeforeSuite 不会多线程去执行，因此对于配置文件读取未使用线程安全的操作
     *
     * @param propertiesPath 整个项目的测试配置文件相对于项目的路径
     * @throws IOException IOException
     */
    @BeforeSuite(alwaysRun = true)
    @Parameters({"propertiesPath"})
    public void beforeSuite(@Optional("src/test/resources/config/config.properties") String propertiesPath) throws IOException {
        // 显示文字 apitest4j
        WordartDisplayer.display();
        // 配置文件读取
        PropertiesReader.readProperties(propertiesPath);
    }

    /**
     * 在每个 <test> 之前执行
     *
     */
    @BeforeTest(alwaysRun = true)
    @Parameters({"FilePath", "SheetName"})
    public void beforeTest(@Optional() String FilePath, @Optional() String SheetName) throws Exception {
        /* 产生登录身份信息 */
        System.out.println();
        baseToken = new BaseToken();
//        ExcelData = ZhicallExcelData.getExcelData(FilePath,SheetName);
//        token = baseToken.login(corpid, corpsecret);
        // todo : 由于线程隔离设为 test，这里可以通过 new 一个对象来达到线程隔离的效果，可以做其他的扩展定制（看需要）
    }

    /**
     * BeforeClass
     */
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        // todo : 自己可以做一些其他的初始化操作
    }

    /**
     * AfterClass
     */
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        // todo : 资源释放
    }

    /**
     * AfterTest
     */
    @AfterTest(alwaysRun = true)
    public void afterTest() {
        baseToken.releaseToken();
        token = null;
        // todo : 其他资源释放
    }

    /**
     * AfterSuite
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        // todo : 资源释放
    }
}
