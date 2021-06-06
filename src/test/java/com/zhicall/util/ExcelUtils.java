package com.zhicall.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * excel工具类
 */
public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    /**
     * Excel工具 获取全部节点数值
     * @param FilePath
     * @param SheetName
     * @return
     * @throws Exception
     */
    public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
        String[][] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);
            // 创建excel 和 sheet 对象
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            //开始读取的行数 第一行是标题所以不计入
            int startRow = 1;
            int startCol = 0;
            int ci,cj;
            //获取最大行数
            int totalRows = ExcelWSheet.getLastRowNum();
//            System.out.println(totalRows);
            //获取列数 第一行不计入 所以从第2行算
            Row = ExcelWSheet.getRow(1);
            // excel中sheet的总列数
            int totalCols = Row.getLastCellNum();
//            System.out.println(totalCols);
            tabArray=new String[totalRows][totalCols];
            ci=0;
            for (int i=startRow;i<totalRows+1;i++, ci++) {

                cj=0;
                for (int j=startCol;j<totalCols;j++, cj++){
                    tabArray[ci][cj]=getCellData(i,j);
//                    System.out.println(tabArray[ci][cj]);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return(tabArray);
    }


    public static Object[][] getTableArray(String FilePath, String SheetName,int[] cellnum) throws Exception {

        String[][] tabArray = null;

        InputStream in = new FileInputStream(FilePath);
        ExcelWBook = new XSSFWorkbook(in);
        ExcelWSheet = ExcelWBook.getSheet(SheetName);

        //获取最大行数
        int totalRows = ExcelWSheet.getLastRowNum();
        tabArray=new String[totalRows][cellnum.length];
        //总列数
        int totalCols = cellnum.length;
        int ci,cj;
        ci=0;
        for (int i=1;i<totalRows+1;i++, ci++) {

            cj=0;
            for (int j=0;j<totalCols;j++, cj++){
                //因为excel读的时候默认从1开始，所以需要cellnum[j]-1
                tabArray[ci][cj]=getCellData(i,cellnum[j]-1);
//                    System.out.println(tabArray[ci][cj]);
            }
        }

        return (tabArray);
    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            int dataType = Cell.getCellType();
            if  (dataType == 3) {
                return "NULL";
            }
            if (dataType == 0){
                String CellData = String.valueOf(Cell.getNumericCellValue());
                return CellData;
            }
            else{
                String CellData = Cell.getStringCellValue();
                return CellData;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw (e);
        }
    }


}