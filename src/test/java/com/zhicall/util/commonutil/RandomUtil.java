package com.zhicall.util.commonutil;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomUtil {

	/**
	 * 指定长度的中文字符
	 * @param StringLength
	 * @return
	 */
	public static String ChineseString(int StringLength){
		String strTemp = "";
		
		for(int i = 0;i<StringLength;i++){
			String str = null;
			int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try
            {
                str = new String(b, "UTF-8"); //转成中文
            }
            catch (UnsupportedEncodingException ex)
            {
                ex.printStackTrace();
            }
            strTemp+=str;
		}
		return strTemp;
	}

	/**
	 * 指定长度的大写英文字符
	 * @param StringLength
	 * @return
	 */
	public static String UpCaseEnglishString(int StringLength){
		String strTemp = "";
		
		for(int i = 0;i<StringLength;i++){
			String str = String.valueOf((char)(Math.round(Math.random() * 25) + 65)); //大写字符
            strTemp+=str;
		}
		return strTemp;
	}

	/**
	 * 指定长度的小英文字符
	 * @param StringLength
	 * @return
	 */
	public static String LowCaseEnglishString(int StringLength){
		String strTemp = "";
		
		for(int i = 0;i<StringLength;i++){
			String str = String.valueOf((char)(Math.round(Math.random() * 25) + 97)); //小写字符
            strTemp+=str;
		}
		return strTemp;
	}

	/**
	 * 指定长度的数字
	 * @param StringLength
	 * @return
	 */
	public static String NumberString(int StringLength){
		String strTemp = "";
		
		for(int i = 0;i<StringLength;i++){
			String str = String.valueOf((char)(Math.round(Math.random() * 9) + 48)); //数字
            strTemp+=str;
		}
		return strTemp;
	}

	/**
	 * 指定长度的数字
	 * @param StringLength
	 * @return
	 */
	public static String NumberStringNoZero(int StringLength){
		String strTemp = "";
		
		for(int i = 0;i<StringLength;i++){
			String str = String.valueOf((char)(Math.round(Math.random() * 8) + 49)); //非0数字
            strTemp+=str;
		}
		return strTemp;
	}


	/**
	 * 指定长度的混合字符，英文，数字，符号
	 * @param StringLength
	 * @return
	 */
	public static String MixedString(int StringLength){
		String strTemp = "";
		
		for(int i = 0;i<StringLength;i++){
			String str = String.valueOf((char)(Math.round(Math.random() * 92) + 33)); //英文，数字，符号
            strTemp+=str;
		}
		return strTemp;
	}

	/**
	 * 阿拉伯数字转中文数字
	 * @param sArabian
	 * @return
	 */
	public static String ArabianToChinese(String sArabian){
		String strTemp = "";
		String[] strChinese = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
		
		for(int i = 0;i<sArabian.length();i++){
			String str = strChinese[Integer.parseInt(String.valueOf(sArabian.charAt(i)))];
            strTemp+=str;
		}
		return strTemp;
	}

	/**
	 * 随机组织机构代码
	 * 组成：8位随机数字或大写字母 + "-"（可省略） + 一位校验码
	 * @param bIncludeHyphen
	 * @return
	 */
	public static String OranganizationCode(boolean bIncludeHyphen){
		String strFirst8 = "";
		String strTemp = "";
		int[] arrCheckCode = {3,7,9,10,5,8,4,2};
		int iTotalValue = 0;
		int iLast = 0;
				
		//随机生成8个数字，取值范围为0-35
		for(int i = 0; i < 8; i++){
			Random random = new Random();
			int iTemp = Math.round(random.nextInt(9));
			//long iTemp = Math.round(Math.random() * 35);
			if(iTemp<10){
				//值小于10则为数字
				strTemp = String.valueOf(iTemp);
			}
			else{
				//值大于等于10则为大写字母

				//排除统一社会信用代码中没有的字符：I,O,S,V,Z
				if(iTemp==18 || iTemp==24 || iTemp==28 || iTemp==31 || iTemp==35){
					iTemp = iTemp - 1;
				}
				
				strTemp = String.valueOf((char)(iTemp+55));
			}
			strFirst8 = strFirst8 + strTemp;
			iTotalValue = iTotalValue + iTemp * arrCheckCode[i];
		}
		
		//System.out.println(lTotalValue);
		iLast = 11 - iTotalValue % 11;
		//System.out.println(lLast);
		String strLast = "";
		if(iLast==10){
			strLast = "X";
		}else{
			if(iLast ==11){
				iLast = 0;
			}
			strLast = String.valueOf(iLast);
		}
		
		if(bIncludeHyphen){
			return strFirst8 + "-" + strLast;
		}else{
			return strFirst8 + strLast;
		}
	}

	/**
	 * 	随机组织机构代码
	 * 	组成：8位随机数字或大写字母 + "-"（可省略） + 一位校验码
	 * @param bIncludeHyphen
	 * @return
	 */
	public static String OrgCode(boolean bIncludeHyphen){
		String strFirst8 = "00000";
		String strTemp = "";
		int[] arrCheckCode = {3,7,9,10,5,8,4,2};
		int iTotalValue = 0;
		int iLast = 0;
				
		//随机生成3个数字，取值范围为0-9
		for(int i = 0; i < 3; i++){
			Random random = new Random();
			int iTemp = Math.round(random.nextInt(9));
			strTemp = String.valueOf(iTemp);
	
			strFirst8 = strFirst8 + strTemp;
			iTotalValue = iTotalValue + iTemp * arrCheckCode[5+i];
		}
		
		iLast = 11 - iTotalValue % 11;
		
		String strLast = "";
		if(iLast==10){
			strLast = "X";
		}else{
			if(iLast ==11){
				iLast = 0;
			}
			strLast = String.valueOf(iLast);
		}
		
		if(bIncludeHyphen){
			return strFirst8 + "-" + strLast;
		}else{
			return strFirst8 + strLast;
		}
	}

	/**
	 *  随机统一社会信用代码:
	 * 	登记管理部门代码1位 + 机构类别代码1位 + 登记管理机关行政区码6位 + 组织机构代码9位 + 校验码1位
	 * @param strDistCode
	 * @return
	 */
	public static String CreditCode(String strDistCode){
		String strDepartCode_All = "159Y";
		String strClassCode_All = "1239";
		String strClassCode_Com = "123";
		char[] Code_All = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','T','U','W','X','Y'};
		int[] arrCheckCode = {1,3,9,27,19,26,16,17,20,29,25,13,8,24,10,30,28};
				
		//随机生成8个数字，取值范围为0-35
		String strFirst1 = RandomUtil.RandomString(strDepartCode_All, 1);
		String strFirst2 ="1";
		
		if(strFirst1.equals("1") || strFirst1.equals("5")){
			strFirst2 = RandomUtil.RandomString(strClassCode_All, 1);
		}else if(strFirst1.equals("9")){
			strFirst2 = RandomUtil.RandomString(strClassCode_Com, 1);
		}else{
			strFirst2 = "1";
		}
				
		if(strDistCode =="" || strDistCode.length()!=6){
			strDistCode = "330100";
		}
		
		String strOrganCode = RandomUtil.OranganizationCode(false);
		String strFirst17 = strFirst1 + strFirst2 + strDistCode + strOrganCode;
		
		//System.out.println("17位： "+strFirst17);
		char[] cFirst17 = strFirst17.toCharArray();
		
		Map<String,Integer> datas = new HashMap<String, Integer>();
		for(int i=0;i<Code_All.length;i++){
           datas.put(Code_All[i]+"",i);
		}
		
       int sum = 0;
       for(int i=0;i<17;i++){
           int iCurValue = datas.get(cFirst17[i]+"");
           sum+=arrCheckCode[i]*iCurValue;
       }
       
       int temp = 31 - sum%31;
       
       String str18 = "";
       if(temp ==31){
    	   str18 = "0";
       }else{
    	   str18 = String.valueOf(Code_All[temp]);	
       }
       
       return strFirst17 + str18;
	}

	/**
	 * 判断是否是正整数
	 * @param str
	 * @return
	 */
	public static boolean isNaturalNumber(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	/**
	 * 判断是否是整数
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
		Pattern pattern = Pattern.compile("^-?[0-9]+");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	/**
	 * 随机获取字符串长度
	 * @param str
	 * @param ilength
	 * @return
	 */
	public static String RandomString(String str , int ilength){
		String strResult = "";
		//System.out.println(str.length());
		if(!str.equals("") && str.length() > ilength){
			Random random = new Random();
			StringBuffer sb = new StringBuffer();
			
			for(int i=0;i<ilength;i++){
				int iCharLocaltion = random.nextInt(ilength);
				sb.append(str.charAt(iCharLocaltion));
			}
			strResult = sb.toString();
		}else{
			strResult = str;
		}
		return strResult;
	}
}
