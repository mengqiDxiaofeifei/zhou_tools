package com.dabaiyang.tools.site.kele;

import com.dabaiyang.tools.DouYinQushuiyin;
import com.dabaiyang.tools.annotion.SystemControllerCounter;
import com.dabaiyang.tools.constant.XiaoBaiConstant;
import com.dabaiyang.tools.enity.Response;
import com.dabaiyang.tools.utils.HttpUtils;
import com.dabaiyang.tools.utils.ImageQualityEnhance;
import com.dabaiyang.tools.utils.ImgUtils;
import com.dabaiyang.tools.utils.Plant;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ToolsController
 * @Description TODO
 * @Author K12
 * @Date 2019/11/26 17:02
 **/
@RestController
@RequestMapping("/kl/tools")
public class ToolsController {

    /**
     * 查询使用次数
     *
     * @param
     * @return
     */
    @RequestMapping("/getCounter")
    public String getCounter(String counterName) {
        return HttpUtils.queryCounterInfo(counterName);
    }

    /**
     * 查询IP地址归属信息
     *
     * @param ipAdr
     * @return
     */
    @RequestMapping("/Q.ip.info")
    @SystemControllerCounter
    public String queryIpInfo(String ipAdr) {
        Map<String, String> param = new HashMap<>();
        param.put("ip", ipAdr);
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_IP_QUERY_VALUE);

        String s = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        s = s.substring(2, s.length());
        return s;
    }


    /**
     * 简繁体转换
     *
     * @param text 文本
     *             type  类型
     * @return
     */
    @RequestMapping("/convert")
    @SystemControllerCounter
    public String convert(String text, String type) {
        Map<String, String> param = new HashMap<>();
        param.put("text", text);
        param.put("type", type);
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_CONVERT_QUERY_VALUE);
        String s = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        s = s.substring(2, s.length());
        return s;
    }

    /**
     * 获取全国城市
     *
     * @param
     * @return
     */
    @RequestMapping("/queryCity")
    @SystemControllerCounter
    public String queryCity() {
        Map<String, String> param = new HashMap<>();
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_CITY_QUERY_VALUE);
        String resp = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        resp = resp.substring(2, resp.length());
        return resp;

    }


    /**
     * 全国大学
     *
     * @param
     * @return
     */
    @RequestMapping("/queryUniversity")
    @SystemControllerCounter
    public String queryUniversity(String schoolName, String schoolLevel, String schoolProvince, String schoolCity) {
        Map<String, String> param = new HashMap<>();
        if (schoolName != null && !schoolName.equals("")) {
            param.put("school_name", schoolName);
        }
        if (schoolLevel != null && !schoolLevel.equals("")) {
            param.put("school_level", schoolLevel);
        }
        if (schoolProvince != null && !schoolProvince.equals("")) {
            param.put("school_province", schoolProvince);
        }
        if (schoolCity != null && !schoolCity.equals("")) {
            param.put("school_city", schoolCity);
        }
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_UNIVERSITY_SEARCH_QUERY_VALUE);
        String s = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        s = s.substring(2, s.length());
        return s;
    }

    /**
     * 随机获取一个昵称
     *
     * @param
     * @return
     */
    @RequestMapping("/queryNickname")
    @SystemControllerCounter
    public String queryNickname() {
        Map<String, String> param = new HashMap<>();
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_NICKNAME_QUERY_VALUE);
        String s = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        s = s.substring(2, s.length());
        return s;
    }


    /**
     * 身份证号码解析
     *
     * @param
     * @return
     */
    @RequestMapping("/queryIdNumber")
    @SystemControllerCounter
    public String queryIdNumber(String idNum) {
        Map<String, String> param = new HashMap<>();
        param.put("id_number", idNum);
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_IDCARD_QUERY_VALUE);
        String s = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        s = s.substring(2, s.length());
        return s;
    }


    /**
     * 手机号码归属地
     *
     * @param
     * @return
     */
    @RequestMapping("/queryPhoneLocation")
    @SystemControllerCounter
    public String queryPhoneLocation(String phone) {
        Map<String, String> param = new HashMap<>();
        param.put("phone", phone);
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_PHONE_QUERY_VALUE);
        String s = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        s = s.substring(2, s.length());
        return s;
    }


    /**
     * 姓名转拼音
     *
     * @param
     * @return
     */
    @RequestMapping("/queryPinyin")
    @SystemControllerCounter
    public String queryPinyin(String text) {
        Map<String, String> param = new HashMap<>();
        param.put("text", text);
        param.put(XiaoBaiConstant.XIAOBAI_APP_KEY, XiaoBaiConstant.XIAOBAI_APP_KEY_VALUE);
        param.put(XiaoBaiConstant.XIAOBAI_S_IP_QUERY, XiaoBaiConstant.XIAOBAI_S_PINYIN_QUERY_VALUE);
        String s = HttpUtils.sendPost(XiaoBaiConstant.XIAOBAI_API_URL, HttpUtils.buildParams(param, XiaoBaiConstant.XIAOBAI_SECRET));
        s = s.substring(2, s.length());
        return s;
    }

    /**
     * ocr图片文字识别
     *
     * @param
     * @return
     */
    @RequestMapping("/ocrImgDistinguish")
    @SystemControllerCounter
    public String ocrImgDistinguish(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return ImgUtils.orcImgDistinguish(bytes);
    }


    /**
     * 植物识别
     *
     * @param
     * @return
     */
    @RequestMapping("/plantDetect")
    @SystemControllerCounter
    public String plantDetect(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return Plant.plant(bytes);
    }


    /**
     * 图像无损放大
     * 输入一张图片，可以在尽量保持图像质量的条件下，将图像在长宽方向各放大两倍。
     *
     * @param
     * @return
     */
    @RequestMapping("/imageQualityEnhance")
    @SystemControllerCounter
    public String imageQualityEnhance(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return ImageQualityEnhance.imageQualityEnhance(bytes);
    }

    /**
     * 黑白图像上色
     *
     * @param
     * @return
     */
    @RequestMapping("/colourize")
    @SystemControllerCounter
    public String colourize(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return ImageQualityEnhance.colourize(bytes);
    }

    /**
     * 拉伸图像恢复
     *
     * @param
     * @return
     */
    @RequestMapping("/stretchRestore")
    @SystemControllerCounter
    public String stretchRestore(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return ImageQualityEnhance.stretchRestore(bytes);
    }



    /**
     * 抖音去水印
     *
     * @param
     * @return
     */
    @RequestMapping("/douyinRemoval")
    @SystemControllerCounter
    public Response douyinRemoval(String url, HttpServletResponse response) throws Exception {
         DouYinQushuiyin.downloadVideo(url,response);
         return Response.buildSuccessResponse();
    }

}
