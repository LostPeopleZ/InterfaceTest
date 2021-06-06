package com.zhicall.util.commonutil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  邮件相关
 *  1.随机获取邮箱  getEmail()
 *  2.指定长度返回随机邮箱  getEmail(int iHeadLength)
 *  3.判断是否为邮箱  isEmail(String email)
 */
public class EmailUtil {
	private static Logger logger = LogManager.getLogger(EmailUtil.class.getName());
//			Logger.getLogger(EmailUtil.class);
	/**
	 * 随机获取邮箱
	 * @return
	 */
	public static String getEmail(){
		String strMailHead = "";
		Random ran = new Random();
		
		//随机出邮箱前部长度：1-10
		int iHeadLength = ran.nextInt(9) + 1;
		
		for(int i = 0;i < iHeadLength; i++){
			int iCharType = ran.nextInt(2);
			String strTemp = "";
			
			if(iCharType==0){//数字
				strTemp = String.valueOf(ran.nextInt(9));
			}else if(iCharType==1){//小写字母
				strTemp = String.valueOf((char)(97 + ran.nextInt(26)));
			}else{//大写字母
				strTemp = String.valueOf((char)(65 + ran.nextInt(26)));
			}
			
			strMailHead = strMailHead + strTemp;
		}
		
		return strMailHead + "@" + RandomMailTail();
	}

	/**
	 * 指定长度返回随机邮箱
	 * @param iHeadLength
	 * @return
	 */
	public static String getEmail(int iHeadLength){
		String strMailHead = "";
		Random ran = new Random();
				
		for(int i = 0;i < iHeadLength; i++){
			int iCharType = ran.nextInt(2);
			String strTemp = "";
			
			if(iCharType==0){//数字
				strTemp = String.valueOf(ran.nextInt(9));
			}else if(iCharType==1){//小写字母
				strTemp = String.valueOf((char)(97 + ran.nextInt(26)));
			}else{//大写字母
				strTemp = String.valueOf((char)(65 + ran.nextInt(26)));
			}
			
			strMailHead = strMailHead + strTemp;
		}
		
		return strMailHead + "@" + RandomMailTail();
	}

	/**
	 * 判断是否为邮箱
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
	    //String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	    String str="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        logger.info(m.matches()+"---");     
        return m.matches();
        
	    }

	public static String RandomMailTail(){
		String[] CommonEmailSuffixs = { "gmail.com", "yahoo.com", "msn.com",
				"hotmail.com", "aol.com", "ask.com", "live.com", "qq.com",
				"0355.net", "163.com", "163.net", "263.net", "3721.net",
				"yeah.net", "googlemail.com", "mail.com", "aim.com",
				"walla.com", "inbox.com", "126.com", "sina.com", "21cn.com",
				"sohu.com", "yahoo.com.cn", "tom.com", "etang.com", "eyou.com",
				"56.com", "x.cn", "chinaren.com", "sogou.com", "citiz.com",
				"hongkong.com", "ctimail.com", "hknet.com", "netvigator.com",
				"mail.hk.com", "swe.com.hk", "itccolp.com.hk",
				"biznetvigator.com", "seed.net.tw", "topmarkeplg.com.tw",
				"pchome.com.tw" };

		Random ran = new Random();
		int iEmailIndex = ran.nextInt(CommonEmailSuffixs.length-1);
		return CommonEmailSuffixs[iEmailIndex];
	}
}
