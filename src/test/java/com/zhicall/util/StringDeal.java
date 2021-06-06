package com.zhicall.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;


public class StringDeal {

	/**
	 * @param args
	 */
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
/*		String myJson = "{\"accountId\":\"771\",\"errCode\":0,\"errShow\":false,\"msg\":\"成功\"}";
		String myKey = "accountId";
		System.out.println(getDataFromJsonString(myJson,myKey));*/
		
//		byte[] stream = getContent("E:\\test1\\test1.pdf");
//		boolean b = bByteToFile(stream, "E:\\test1\\", "testsigned.pdf");
//		System.out.println(b);


		
	}
	
	public static boolean bStartWith(String strAll, String[] arrValueRange){
		boolean bTemp = false;
		
		if(arrValueRange.length ==0){
			bTemp = true;
		}else{
			if(strAll.length()==0){
				bTemp = false;
			}else{
				int iTrueCount = 0;
				for(int i = 0; i< arrValueRange.length -1; i++){
					if(strAll.startsWith(arrValueRange[i])){
						iTrueCount = iTrueCount + 1;
					}
				}
				
				if(iTrueCount > 0){
					bTemp = true;
				}	
			}
		}
		
		return bTemp;
	}

	public static String getDataFromJsonString(String jsonContent,String KEYWORD) throws Exception {
    	JsonNode result = null;
    	String strTemp = "";
        JsonNode jsonNode = mapper.readTree(jsonContent);
        result = jsonNode.get(KEYWORD);

        strTemp = result.toString();
//		System.out.println(strTemp);
		//去掉首尾空格
        strTemp = strTemp.substring(1, strTemp.length()-1);

        return strTemp;
    }

    public static boolean bIsEmptyStringArray(String[] str){
    	boolean bTempResult = false;
    		
    	if(str!=null && str.length!=0){
    		StringBuffer sb = new StringBuffer();
        	for(int i=0;i<str.length-1;i++){
        		sb.append(str[i]);
        	}
        	if(!sb.toString().equals("")){
        		bTempResult = true;
        	}
    	}
    	
    	return bTempResult;
    }
    
	/**
	 * 获取本地的文件流
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] getContent(String filePath) throws IOException {  
		File file = new File(filePath);  
		long fileSize = file.length();  
		if (fileSize > Integer.MAX_VALUE) {  
			System.out.println("file too big...");  
			return null;  
		}  
		FileInputStream fi = new FileInputStream(file);  
		byte[] buffer = new byte[(int) fileSize];  
		int offset = 0;  
		int numRead = 0;  
		while (offset < buffer.length  
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {  
			offset += numRead;  
		}  
		// 确保所有数据均被读取  
		if (offset != buffer.length) {  
			fi.close();
			throw new IOException("Could not completely read file "  
					+ file.getName());  
		}  
		fi.close();  
		return buffer;  
	}
	
	public static boolean bByteToFile(byte[] buf, String filePath, String fileName)  
    {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;
        boolean bResult = false;
        try  
        {  
            File dir = new File(filePath);  
            if (!dir.exists() && dir.isDirectory())  
            {  
                dir.mkdirs();  
            }  
            file = new File(filePath + File.separator + fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(buf);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            if (bos != null)  
            {  
                try  
                {  
                    bos.close();  
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
            if (fos != null)  
            {  
                try  
                {  
                    fos.close();
                    bResult = true;
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
        }
        return bResult;
    }

	/**
	 * 拼接url
	 * @param map
	 * @return
	 */
    public static String SplitMap(Map<String,String> map){

		int i = 0;
		String url;
		for(String key:map.keySet()){
			if (i==0){
				url = "?" + key + "=" + map.get(key);
			}else {
				url ="&" + key + "=" + map.get(key);
			}
			return url;
		}

		return "";
	}

	/**
	 * 将字符串分割然后存到一个
	 * 二维数组中[{A,80},{B,60},{C,70}]
	 * @return 返回值为一个二维数组
	 */
	public static String[][] splitStringToArrary(String s) {

		String[] temp =  s.split(","); // 通过逗号将字符串拆分成一维数组{"A:80","B:60","C:70"}
		String[][] arr = new String[temp.length][];// 初始化一个二维字符串数组，只指定了行数
		for (int i = 0; i < temp.length; i++) {
			String[] tempAgain = temp[i].split(":"); //继续分割并存到另一个一临时的一维数组当中
			arr[i] = new String[tempAgain.length]; //根据tempAgain中的数组长度，为二维数组的列赋值
			for (int j = 0; j < tempAgain.length; j++) {
				arr[i][j] = tempAgain[j];  //为二维数组赋值
			}
		}
		return arr;
	}

	/**
	 * 将字符串分割然后以键值对存入
	 * LinkHashMap
	 * @return 返回为LinkHashMap
	 */
	public static Map<String,String> splitStringToMap(String s) {
		LinkedHashMap<String, String> map =  new LinkedHashMap<String, String>();
		String[] temp = s.split(","); //通过逗号进行分割
		for (int i = 0; i < temp.length; i++) {
			String[] arr = temp[i].split(":"); //通过冒号继续分割
			String[] tempAagin = new String[arr.length]; //再开辟一个数组用来接收分割后的数据
			for (int j = 0; j < arr.length; j++) {
				tempAagin[j] = arr[j];
			}
			map.put(arr[0], arr[1]);
		}
		return map;
	}

}
