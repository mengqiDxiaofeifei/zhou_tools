package com.dabaiyang.tools.utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "17881460";
    public static final String API_KEY = "AK4HKTM70teYcR7QkF7eUL2D";
    public static final String SECRET_KEY = "l15PqBncCBWRkeWMKPBAAHpbhG1Ddqmc";

    public static void main(String[] args) throws IOException {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
       // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "D:/d.jpg";
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while((len = in.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        byte[] fileByte = bos.toByteArray();
        JSONObject res = client.basicGeneral(fileByte, new HashMap<String, String>());
        System.out.println(res.toString(2));
        
    }
}