package com.zhicall.util.commonutil;

import com.zhicall.util.StringDeal;

import java.util.Random;

/**
 * 手机号生成
 * 1.自动生成手机号 getMobile()
 * 2.指定手机号开头随机生成手机号 getMobile(String strHead)
 */
public class MobileUtil {

	/**
	 * 自动生成手机号
	 * @return
	 */
	public static String getMobile(){
		String[] strLegalHead  = {"13","14","15","17","18"};
		Random ran = new Random();
		int iHeadIndex = ran.nextInt(strLegalHead.length-1);
		
		return strLegalHead[iHeadIndex] + RandomUtil.NumberString(9);
	}

	/**
	 * 指定手机号开头随机生成手机号
	 * @param strHead
	 * @return
	 */
	public static String getMobile(String strHead){
		String[] strLegalHead  = {"13","14","15","17","18"};
		String strTemp = "";
		int iHeadLength = strHead.length();
		
		if(iHeadLength == 0 || iHeadLength > 11){
			System.out.println("参数错误，自动生成手机号");
			strTemp = MobileUtil.getMobile();
		}else if(iHeadLength == 1){
			if(strHead.startsWith("1")){
				strTemp = MobileUtil.getMobile();
			}else{
				System.out.println("手机号开头错误，自动生成手机号");
				strTemp = MobileUtil.getMobile();
			}
		}else {
			if(StringDeal.bStartWith(strHead,strLegalHead)){
				strTemp = strHead + RandomUtil.NumberString(11- iHeadLength);
			}else{
				System.out.println("手机号开头错误，自动生成手机号");
				strTemp = MobileUtil.getMobile();
			}
		}
		
		return strTemp;
	}

}
