package com.zhicall.constant;

/**
 * 项目中的常量类
 *
 */
public class TestConstant {

    /**
     * 获取项目根目录
     */
    public final static String USER_DIR = System.getProperty("user.dir");

    /**
     * Excel地址
     */
    public final static String EXCEL_DIR = USER_DIR + "/src/test/resources/Excel/APITest.xlsx";

    /**
     * allure-result报告地址
     */
    public final static String ALLURE_DIR = USER_DIR + "/allure-results";

    public final static String ALLURE_EXECUTE = "cd " + USER_DIR + "| allure serve allure-results";
}