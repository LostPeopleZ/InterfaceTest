package com.zhicall.util.httpclient;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * GET POST PUT等接口请求
 */
public class HTTPClientUtil {

    /**
     * GET提交
     *
     * @return
     */
    public static String doGet(String url) {
        String strResult = "";
        // 1. 创建一个默认的client实例
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            // 2. 创建一个httpget对象
            HttpGet httpGet = new HttpGet(url);
            System.out.println("executing GET request " + httpGet.getURI());

            // 3. 执行GET请求并获取响应对象
            CloseableHttpResponse resp = client.execute(httpGet);
            try {
                // 6. 打印响应长度和响应内容
                if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    // 4. 获取响应体
                    HttpEntity entity = resp.getEntity();
                    System.out.println("Response content length = "
                            + entity.getContentLength());
                    System.out.println("------");
                    strResult = EntityUtils.toString(resp.getEntity());
                }
            } finally {
                //无论请求成功与否都要关闭resp
                resp.close();
            }
        } catch (ClientProtocolException e) {
//            logger.error("get请求失败:", e);
            // e.printStackTrace();
        } catch (ParseException e) {
//            logger.error("get请求解析出错:", e);
            // e.printStackTrace();
        } catch (IOException e) {
//            logger.error("get请求IO出错:", e);
            // e.printStackTrace();
        } finally {
            // 8. 最终要关闭连接，释放资源
            try {
                client.close();
            } catch (Exception e) {
//                logger.error("get请求完毕关闭连接出错:", e);
                // e.printStackTrace();
            }
        }
        return strResult;
    }

    /**
     * 表单POST提交
     * @param url
     * @param map
     * @return
     */
    public static String doPost(String url, Map<String, Object> map) {
        String strResult = "";
        // 1. 获取默认的client实例
        CloseableHttpClient client = HttpClients.createDefault();
        // 2. 创建httppost实例
        HttpPost httpPost = new HttpPost(url);
        // 3. 创建参数队列（键值对列表）
        List<NameValuePair> paramPairs = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            Object val = map.get(key);
            paramPairs.add(new BasicNameValuePair(key, val.toString()));
        }
        UrlEncodedFormEntity entity;
        try {
            // 4. 将参数设置到entity对象中
            entity = new UrlEncodedFormEntity(paramPairs, "UTF-8");
            // 5. 将entity对象设置到httppost对象中
            httpPost.setEntity(entity);
            // 6. 发送请求并回去响应
            CloseableHttpResponse resp = client.execute(httpPost);
            try {
                // 7. 获取响应entity
                HttpEntity respEntity = resp.getEntity();
                strResult = EntityUtils.toString(respEntity, "UTF-8");
            } finally {
                // 9. 关闭响应对象
                resp.close();
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 10. 关闭连接，释放资源
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }

    /**
     * json参数方式POST提交
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, JSONObject params){
        String strResult = "";
        // 1. 获取默认的client实例
        CloseableHttpClient client = HttpClients.createDefault();
        // 2. 创建httppost实例
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8"); //添加请求头
        try {
            httpPost.setEntity(new StringEntity(params.toString(),"utf-8"));
            CloseableHttpResponse resp = client.execute(httpPost);
            try {
                // 7. 获取响应entity
                HttpEntity respEntity = resp.getEntity();
                strResult = EntityUtils.toString(respEntity, "UTF-8");
            } finally {
                resp.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }

    public static void main(String[] args) throws Exception {

        Map<String,Object> map = new HashMap<>();
        map.put("username","lock");
        map.put("password","opms123456");

        System.out.println(doPost("http://localhost:8088/login",map));
    }
}
