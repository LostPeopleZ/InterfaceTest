package com.zhicall.pageobject.data;

import com.zhicall.constant.TestConstant;
import com.zhicall.util.ExcelUtil;
import com.zhicall.util.ExcelUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * opms数据类
 */
public class OpmsData {

    /**
     * opms登录
     * 只用作登录 或只需调用一次接口即可用此
     */
    public final static Map<String,String> LoginOpms = new HashMap<String, String>(){
        {
            put("username","lock");
            put("password","opms123456");
        }
    };

    /**
     * opms登录
     *  Excel数据驱动 提供批量的数据
     * @return
     * @throws Exception
     */
    @DataProvider(name = "LoginOpms")
    public final static Object[][] LoginOpms () throws Exception {
        Object[][] objects = new Object[][]{
                {"lock","opms123456"},
                {"lock","password"}
        };

        return objects;
    }

    @DataProvider(name = "LoginOpmsExcel")
    public final static Object[][] LoginOpmsExcel () throws Exception {
        return testObject();
    }

    public static Object[][] testObject() throws Exception {

        Object[][] objects ;
        List<List<Object>> listobjects = new ArrayList<>();
        List<Object> plistString = new ArrayList<>();;
        FileInputStream ExcelFile = new FileInputStream(TestConstant.EXCEL_DIR);
        // 创建excel 和 sheet 对象
        XSSFWorkbook ExcelWBook= new XSSFWorkbook(ExcelFile);
        ExcelUtil excelUtil = new ExcelUtil(ExcelWBook);
        //获取行数
        excelUtil.getRowCount(0);

        //获取想要的第几个sheet 从0开始
        List<List<String>> sheet1 = excelUtil.read(0);
        //获取行的最大值
         int rowCount = excelUtil.getRowCount(0);
         //获取列的最大值
        int columnCount = excelUtil.getColumnCount(0,0);

        String url,testNo,testDescribe,protocol,method,address,param,status,checkpoint,expected,actual = "";

        for (int i=1;i<rowCount;i++){

            testNo = excelUtil.getValueAt(0,i,0);
            testDescribe = excelUtil.getValueAt(0,i,1);
            protocol = excelUtil.getValueAt(0,i,2);
            method  = excelUtil.getValueAt(0,i,3);  //获取请求类型 GET POST POST/FORM
            address = excelUtil.getValueAt(0,i,4);
            param = excelUtil.getValueAt(0,i,5);
            status = excelUtil.getValueAt(0,i,6);
            checkpoint = excelUtil.getValueAt(0,i,7);
            expected = excelUtil.getValueAt(0,i,8);
            actual = excelUtil.getValueAt(0,i,9);

            url = protocol + "://" + address ;  //拼接url

            //请求类型为post/form 表单提交
            if (method.equalsIgnoreCase("post/form")){
                List<String> paramlist = Arrays.asList(param.split(","));
                for (int j=0;j<paramlist.size();j++){

                    //去掉转义\n \"
                    String plist = paramlist.get(j).replaceAll("\n","").replaceAll("\"","");
                    //获取参数value值
                    List<String> plistlist = Arrays.asList(plist.split(":"));

                    plistString.add(plistlist.get(1));
                }
                listobjects.add(plistString);
            }
            //请求类型为post/json json提交
            else if (method.equalsIgnoreCase("post/json")){

            }
            //请求类型为get
            else if(method.equalsIgnoreCase("get")){

            }

        }
        //转为Object[][]
        objects = getObject(listobjects);
        return objects;
    }

    /**
     * 根据Excel的行数列数 以及读取到的List
     *  赋值到Object[][]
     *  注意：调用此方法需要在循环中 因为需要先在上一层对数据进行清洗
     * @param list
     * @return
     */
    public static Object[][] getObject(List<List<Object>> list /*,int rowCount,int columnCount*/){

        Object[][] objects =new Object[list.size()-1][list.get(0).size()];

        for (int i=0;i<list.size()-1;i++){

            for (int j=0;j<list.size()-1;j++){
                objects[i][j] = list.get(i).get(j).toString();
                System.out.println(1111);
            }
        }
        return objects;
    }


    public static void main(String[] args) throws Exception {
        FileInputStream ExcelFile = new FileInputStream(TestConstant.EXCEL_DIR);
        // 创建excel 和 sheet 对象
        XSSFWorkbook ExcelWBook= new XSSFWorkbook(ExcelFile);
        ExcelUtil excelUtil = new ExcelUtil(ExcelWBook);
//        List<List<String>> list = excelUtil.read(1);
//        System.out.println(excelUtil.toString(0));
//        System.out.println(excelUtil.getRowCount(0));
//        System.out.println(excelUtil.getValueAt(0,2,3));
//        System.out.println(excelUtil.getColumnCount(0,0));
//        System.out.println(excelUtil.getValueAt(0,1,2) + "://" + excelUtil.getValueAt(0,1,4) );

//        String param = "\"username:lock,\n" +
//                "password:opms123456\"\n";
//
//        List<String> paramlist = Arrays.asList(param.split(","));
//        List<Object> plistString = new ArrayList<>();
//        for (int j=0;j<paramlist.size();j++){
//            //去掉转义\n \"
//            String plist = paramlist.get(j).replaceAll("\n","").replaceAll("\"","");
//            List<String> plistlist = Arrays.asList(plist.split(":"));
//            //获取参数值
//            plistString.add(plistlist.get(1));
//        }
//        System.out.println(plistString.get(0).toString());
//        System.out.println(plistString.get(1).toString());
    }
}