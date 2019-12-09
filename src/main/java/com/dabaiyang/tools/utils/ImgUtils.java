package com.dabaiyang.tools.utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @ClassName ImgUtils
 * @Description TODO
 * @Author K12
 * @Date 2019/11/28 14:35
 **/
@Component
public class ImgUtils {


    public static String APP_ID;

    public static String API_KEY;

    public static String SECRET_KEY;


    @Value("${baidu.appid}")
    public void setBaiduAppid(String appid) {
        APP_ID = appid;
    }

    @Value("${baidu.apikey}")
    public void setBaiduKey(String apiKey) {
        API_KEY = apiKey;
    }

    @Value("${baidu.secretkey}")
    public void setBaiduSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }


    public static AipOcr getAuth() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }


    public static String orcImgDistinguish(byte[] img) {
        AipOcr client = getAuth();
        // 调用接口
        JSONObject res = client.basicGeneral(img, new HashMap<String, String>());
        return res.toString(2);
    }



}



