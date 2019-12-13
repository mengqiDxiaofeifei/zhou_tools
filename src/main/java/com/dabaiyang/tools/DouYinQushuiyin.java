package com.dabaiyang.tools;

import org.jsoup.Jsoup;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DouYinQushuiyin {
private static Pattern pattern = Pattern.compile("(?<=playAddr: \")https?://.+(?=\",)");

    public static void downloadVideo(String url, HttpServletResponse response) throws Exception{

        //●抖音链接(使用手机分享功能,复制链接)
       // String url = "http://v.douyin.com/2MKBC6/";

        //String url1 ="#在抖音，记录美好生活#坝坝宴居然也能弄的这么唯美，爱了。#婚礼现场 https://v.douyin.com/XK9TNn/ 复制此链接，打开【抖音短视频】，直接观看视频！";


        //过滤链接，获取http连接地址
        String finalUrl = decodeHttpUrl(url);

        //1.利用Jsoup抓取抖音链接
        //抓取抖音网页
        String htmls = Jsoup.connect(finalUrl).ignoreContentType(true).execute().body();

        //3.匹配后封装成Matcher对象
         Matcher m = pattern.matcher(htmls);

        //4.①利用Matcher中的group方法获取匹配的特定字符串 ②利用String的replace方法替换特定字符,得到抖音的去水印链接
        String matchUrl ="";
        while(m.find()) {
            matchUrl = m.group(0).replaceAll("playwm", "play");
        }

        //5.将链接封装成流
        //注:由于抖音对请求头有限制,只能设置一个伪装手机浏览器请求头才可实现去水印下载
        Map<String, String> headers = new HashMap<>();
        headers.put("Connection", "keep-alive");
        headers.put("Host", "aweme.snssdk.com");
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");

        //6.利用Joup获取视频对象,并作封装成一个输入流对象
        BufferedInputStream in = Jsoup.connect(matchUrl).headers(headers).timeout(10000).ignoreContentType(true).execute().bodyStream();
        //8.新建一个输出流对象
        OutputStream out =
                new BufferedOutputStream(response.getOutputStream());

        //9.遍历输出文件
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = in.read(buff, 0, buff.length))) {
            out.write(buff, 0, bytesRead);
        }
        out.close();//关闭输出流
        in.close(); //关闭输入流

    }

    public static String decodeHttpUrl(String url) {
        int start = url.indexOf("http");
        int end = url.lastIndexOf("/");
        String decodeurl = url.substring(start, end);
        return decodeurl;
    }
}