package com.zhicall.util.commonutil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static SimpleDateFormat df = new SimpleDateFormat("HH：mm：ss");

    public static String datenow(){
        return df.format(new Date());
    }

    public static void main(String[] args) throws ParseException {
        Date curDate = new Date();
        System.out.println(DateToString(curDate,"yyyyMMdd"));

        String myDate1 = getRandomDate();
        String myDate2 = getRandomDate("19700101");
        String myDate3 = getRandomDate("19000101","20170308");

        System.out.println(myDate1);
        System.out.println(myDate2);
        System.out.println(myDate3);

        System.out.println(isDate("19700228"));
        System.out.println(getNow_yyyyMMddHHmmss());

    }

    //19700101  ----19700101--Now
    public static String getRandomDate(){
        String strResult = "19700101";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);

        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1);

        Date dStart = cal.getTime();
        Date dEnd = new Date();

        long lDate = (long) (Math.random()*(dEnd.getTime()-dStart.getTime()) + dStart.getTime());
        strResult = sdf.format(new Date(lDate));

        return strResult;
    }

    //19900101  ----19700101--99991231
    public static String getRandomDate(String strStartDate) throws ParseException{
        String strResult = "19700101";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);

        try {
            Date dStart = sdf.parse(strStartDate);

            Calendar cal = Calendar.getInstance();
            cal.set(9999, 11, 31);
            Date dEnd = cal.getTime();

            long lStart = dStart.getTime();
            long lEnd = dEnd.getTime();

            long lDate = (long) (Math.random()*(lEnd-lStart) + lStart);
            strResult = sdf.format(new Date(lDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return strResult;
    }

    //19700101
    public static String getRandomDate(String strStartDate, String strEndDate){
        String strResult = "19700101";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date dStart = new Date();
        Date dEnd = new Date();
        long lStart = 0l;
        long lEnd = 0l;
        long lDate = 0l;

        try{
            dStart = sdf.parse(strStartDate);
            dEnd = sdf.parse(strEndDate);

            lStart = dStart.getTime();
            lEnd = dEnd.getTime();
            lDate = (long) (Math.random()*(lEnd - lStart) + lStart);

        }catch (Exception e) {
            Calendar cal = Calendar.getInstance();
            cal.set(1970, 0, 1);
            lStart = cal.getTimeInMillis();

            cal.set(9999, 11, 31);
            lEnd = cal.getTimeInMillis();
            lDate = (long) (Math.random()*(lEnd - lStart) + lStart);
            e.printStackTrace();
        }

        strResult = sdf.format(new Date(lDate));
        return strResult;
    }

    //yyyy-MM-dd
    //yy-MM-dd
    //yyyy/MM/dd HH:mm
    public static String DateToString(Date dTempDate,String format){
        DateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        String str = sdf.format(dTempDate);
        return str;
    }

    public static String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNow_yyyyMMddHHmmss(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    //true,false
    public static boolean isDate(String strDate){
        boolean bResult = false;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(strDate);
            bResult = true;
        } catch (ParseException e) {
            bResult = false;
            e.printStackTrace();
        }

        return bResult;
    }

    /**
     * 日期的加减
     * @param date
     * @param datenum	加减的时间 天
     * @return
     * @throws ParseException
     */
    public static String subDay(String date,int datenum) throws ParseException { 
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	Date dt = sdf.parse(date); 
    	Calendar rightNow = Calendar.getInstance(); 
    	rightNow.setTime(dt); 
    	rightNow.add(Calendar.DAY_OF_MONTH, datenum); 
    	Date dt1 = rightNow.getTime(); 
    	String reStr = sdf.format(dt1); 
    	return reStr; 
    }
}
