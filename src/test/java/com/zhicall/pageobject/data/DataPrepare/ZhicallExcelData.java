package com.zhicall.pageobject.data.DataPrepare;

import com.zhicall.constant.TestConstant;
import com.zhicall.util.ExcelUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhicallExcelData {

    private String testNo;
    private List<String> testDescribe;
    private List<String> method;
    private List<String> param;
    private List<String> status;
    private List<String> checkpoint;
    private List<String> expected;
    private List<String> actual;
    private List<String> url;

    /**
     * 获取Excel中所有的testNo
     *
     * @param list
     * @return
     */
    public List<String> getTestNo(List<Map<String, String>> list) {
        List<String> testNo = ForData(list, "testNo");
        return testNo;
    }

    /**
     * 获取Excel中所有的testDescribe
     *
     * @param list
     * @return
     */
    public List<String> getTestDescribe(List<Map<String, String>> list) {
        List<String> testDescribe = ForData(list, "testDescribe");
        return testDescribe;
    }

    /**
     * 获取Excel中所有的method
     *
     * @param list
     * @return
     */
    public List<String> getMethod(List<Map<String, String>> list) {
        List<String> method = ForData(list, "method");
        return method;
    }

    /**
     * 获取Excel中所有的param
     *
     * @param list
     * @return
     */
    public List<String> getParam(List<Map<String, String>> list) {
        List<String> param = ForData(list, "param");
        return param;
    }

    /**
     * 获取Excel中所有的status
     *
     * @param list
     * @return
     */
    public List<String> getStatus(List<Map<String, String>> list) {
        List<String> status = ForData(list, "status");
        return status;
    }

    /**
     * 获取Excel中所有的checkpoint
     *
     * @param list
     * @return
     */
    public List<String> getCheckpoint(List<Map<String, String>> list) {
        List<String> checkpoint = ForData(list, "checkpoint");
        return checkpoint;
    }

    /**
     * 获取Excel中所有的expected
     *
     * @param list
     * @return
     */
    public List<String> getActual(List<Map<String, String>> list) {
        List<String> actual = ForData(list, "actual");
        return actual;
    }

    /**
     * 获取Excel中所有的expected
     *
     * @param list
     * @return
     */
    public List<String> getExpected(List<Map<String, String>> list) {
        List<String> expected = ForData(list, "expected");
        return expected;
    }

    /**
     * 缺少一个写Excel的调用
     */


    /**
     * Excel获取的protocol address interfaceName
     * 形成最终完整url 调用
     *
     * @param list
     * @return
     */
    public List<String> getUrl(List<Map<String, String>> list) {
        List<String> urlList = new ArrayList<>();

        String url = "";

        for (int i = 0; i < list.size(); i++) {
            url = list.get(i).get("protocol") + "://" + list.get(i).get("address") + "/" + list.get(i).get("interfaceName");
            urlList.add(url);
        }
        return urlList;
    }

    /**
     * 将Excel内容导出到List<Map<String,String>>中
     *
     * @param FilePath
     * @param SheetName
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> getExcelData(String FilePath, String SheetName) throws Exception {
        Object[][] testObjArray = ExcelUtils.getTableArray(FilePath, SheetName);

        //用来记录Excel返回的行
        List<Map<String, String>> ExcelList = new ArrayList<>();
        //用来记录Excel返回的列
        Map<String, String> map = new HashMap<>();

        //遍历ExcelObject[][] 内容，并存储到List<Map<String,String>> 中
        for (int i = 0; i < testObjArray.length; i++) {
            for (int j = 0; j < testObjArray[i].length; j++) {
//                System.out.println(testObjArray[i][j].toString());
                switch (j) {
                    case 0:
                        map.put("testNo", testObjArray[i][j].toString());
                        break;
                    case 1:
                        map.put("testDescribe", testObjArray[i][j].toString());
                        break;
                    case 2:
                        map.put("protocol", testObjArray[i][j].toString());
                        break;
                    case 3:
                        map.put("method", testObjArray[i][j].toString());
                        break;
                    case 4:
                        map.put("address", testObjArray[i][j].toString());
                        break;
                    case 5:
                        map.put("interfaceName", testObjArray[i][j].toString());
                        break;
                    case 6:
                        map.put("param", testObjArray[i][j].toString());
                        break;
                    case 7:
                        map.put("status", testObjArray[i][j].toString());
                        break;
                    case 8:
                        map.put("checkpoint", testObjArray[i][j].toString());
                        break;
                    case 9:
                        map.put("expected", testObjArray[i][j].toString());
                        break;
                    case 10:
                        map.put("actual", testObjArray[i][j].toString());
                        break;
                }

            }
            ExcelList.add(map);
        }
        return ExcelList;
    }

    /**
     *
     *@Title:  ListToArray
     *@Description: list列表转换成二维数组
     *@Author: Administrator
     *@Since: 2018年1月7日下午2:01:25
     *@param: @param list
     *@param: @param KeyLenght每行的列数，按最长的计算
     *@param: @return
     *@return Object[][]
     */
    private Object[][] ListToArray(List<Map<String, Object>> list, int KeyLenght) {
        if (CollectionUtils.isEmpty(list)) {
            return new Object[0][];
        }
        int size = list.size();
        Object[][] array = new Object[size][KeyLenght];
        for (int i = 0; i < size; i++) {//循环遍历所有行
            array[i] = list.get(i).values().toArray();//每行的列数
        }
        return array;
    }

    /**
     * 用于公共调用
     *
     * @param list
     * @param param
     * @return
     */
    private static List<String> ForData(List<Map<String, String>> list, String param) {
        String name = "";
        List<String> receiveList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            name = list.get(i).get(param);
            receiveList.add(name);
        }
        return receiveList;
    }

}
