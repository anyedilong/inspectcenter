package com.java.until.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java.moudle.common.message.JsonResult;
import com.java.until.StringUtils;

public class HttpUtil {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static JsonResult providePost(String url, Object o) {
        String request = JSON.toJSONString(o);
        String resStr = doPost(url, request);
        JSONObject result = JSONObject.parseObject(resStr);
        String data = result.get("data").toString();
        JSONObject resultJson = result.getJSONObject("result");
        if(!StringUtils.isNull(data)) {
            data = lineToHump(data);
            if ("{".equals(data.substring(0, 1))) {
                JSONObject resultData = JSONObject.parseObject(data);
                return new JsonResult(resultData, resultJson.getInteger("ret_code"), resultJson.getString("ret_msg"));
            } else if ("[".equals(data.substring(0, 1))) {
                JSONArray resultData = JSON.parseArray(data);
                return new JsonResult(resultData, resultJson.getInteger("ret_code"), resultJson.getString("ret_msg"));
            } else {
                return new JsonResult(data, resultJson.getInteger("ret_code"), resultJson.getString("ret_msg"));
            }
        }
        return new JsonResult("", resultJson.getInteger("ret_code"), resultJson.getString("ret_msg"));
    }

    //下划线转驼峰
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String doPost(String url, String json) {
        String returnValue = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            //第四步：发送HttpPost请求，获取返回值
            CloseableHttpResponse response = httpClient.execute(httpPost); //调接口获取返回值时，必须用此方法
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                HttpEntity responseEntity = response.getEntity();
                returnValue = EntityUtils.toString(responseEntity, "UTF-8");
                System.out.println("http返回：" + returnValue);
            }
            System.out.println("返回状态码：" + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }
    
    // 实名制检测
    public static String realNamePost(String address, String param, String appCode) throws IOException {
        address = address + param;
        System.out.println(address);
        URL url = new URL(address);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "APPCODE " + appCode);
        int httpCode = httpURLConnection.getResponseCode();
        System.out.println(httpCode);
        if (httpCode == 200) {
            InputStream inputStream = httpURLConnection.getInputStream();
            String json = read(inputStream);
            System.out.print(json);
    		return json;
        } else {
        	return "-1";
        }
    }
    
    
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
    
    public static String doGet(String url, Map<String, Object> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            List<BasicNameValuePair> list = new ArrayList<>();
            if (param != null) {
                for (String key : param.keySet()) {
                    list.add(new BasicNameValuePair(key, param.get(key).toString()));
                }
            }
            String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(url + "?" + params);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

}
