package com.dabaiyang.tools.utils;

import com.alibaba.fastjson.JSONObject;
import com.dabaiyang.tools.constant.XiaoBaiConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HttpUtils
 * @Description TODO
 * @Author K12
 * @Date 2019/11/26 11:14
 **/
public class HttpUtils {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlName = url + "?" + param;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "/n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "/n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    //TcYixnx9bg2IXJo0wIWjZnc6vk4M7ywwSe6s088tDSuct2YaLiB2grKhKtECrLG8RMuNWpbKsGFJ9
    public static void main(String[] args) {
        Map<String, String> param = new HashMap<>();
        param.put("app_key", "C5BFFAE19E27769EA5A50CBDAE7BAD24");
        param.put("s", "App.Common_University.Search");
       // param.put("school_name", "重庆");
        param.put("school_level", "本科");
        String secret = "TcYixnx9bg2IXJo0wIWjZnc6vk4M7ywwSe6s088tDSuct2YaLiB2grKhKtECrLG8RMuNWpbKsGFJ9";
        //String s = fetchRsSign(param, "TcYixnx9bg2IXJo0wIWjZnc6vk4M7ywwSe6s088tDSuct2YaLiB2grKhKtECrLG8RMuNWpbKsGFJ9");
        String s = sendPost("http://hd215.api.yesapi.cn/", buildParams(param, secret));
        System.out.println("s = " + s);
    }


    public static String fetchRsSign(Map<String, String> param, String secret) {
        String[] keyArray = param.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keyArray) {
            stringBuilder.append(param.get(key));
        }
        stringBuilder.append(secret);
        return MD5Util.encode(stringBuilder.toString()).toUpperCase();
    }


    public static String buildParams(Map<String, String> param, String secret) {
        String[] keyArray = param.keySet().toArray(new String[0]);
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keyArray) {
            stringBuilder.append(key).append("=").append(param.get(key)).append("&");
        }
        stringBuilder.append("sign=").append(fetchRsSign(param, secret));
        return stringBuilder.toString();
    }


    /**
     * 设置计数器
     * value  初始值
     * name  计数器名称
     */
    public static void createCounter(String name, String value) {
        Map<String, String> param = new HashMap<>();
        param.put("name", name);
        param.put("value", value);
        param.put("type", "forever");
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_COUNTER_SETUP_QUERY_VALUE);
        HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
    }

    /**
     * 增加
     * value  值
     * name  计数器名称
     */
    public static void addCounter(String name, String value) {
        Map<String, String> param = new HashMap<>();
        param.put("name", name);
        param.put("value", value);
        param.put("type", "forever");
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_COUNTER_UPDATE_QUERY_VALUE);
        HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
    }

    /**
     * 查询计数器
     * value  初始值
     * name  计数器名称
     */
    public static boolean queryCounter(String name) {
        Map<String, String> param = new HashMap<>();
        param.put("name", name);
        param.put("type", "forever");
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_COUNTER_GET_QUERY_VALUE);
        String resp = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        resp = resp.substring(2, resp.length());
        JSONObject object = JSONObject.parseObject(resp);
        if (object != null && object.get("ret") != null && (Integer) object.get("ret") == 200) {
            String value = object.get("data").toString();
            value = JSONObject.parseObject(value).get("err_code").toString();
            if ("1".equals(value)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 查询计数器
     * value  初始值
     * name  计数器名称
     */
    public static String queryCounterInfo(String name) {
        Map<String, String> param = new HashMap<>();
        param.put("name", name);
        param.put("type", "forever");
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_COUNTER_GET_QUERY_VALUE);
        String resp = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        resp = resp.substring(2, resp.length());
       return resp;
    }

//    public static void main(String[] args) {
//        String queryIpInfo = queryCounterInfo("queryIpInfo");
//        System.out.println("b = " + queryIpInfo);
//    }

}
