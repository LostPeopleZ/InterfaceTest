package com.zhicall.util;

import java.io.*;

public class Folder {

    /**
     * 删除完文件后删除文件夹
     * param folderPath 文件夹完整绝对路径
     */
    public static boolean delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            //删除空文件夹
            deleteDir(new File(folderPath));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除指定文件夹下所有文件
     * param path 文件夹完整绝对路径
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
//              delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     *
     * 读取文件内容
     * @param filepath
     * @return <br/>
     * 修改历史：<br/>
     */
    public static String GetCongtent(String filepath){
        String result="";
        try {
            FileReader fileReader=new FileReader(filepath);
            @SuppressWarnings("resource")
            BufferedReader bufferReader=new BufferedReader(fileReader);
            StringBuffer stringBuffer=new StringBuffer();
            String line="";
            while((line=bufferReader.readLine())!=null){
                stringBuffer.append(line);
            }
            result=stringBuffer.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
