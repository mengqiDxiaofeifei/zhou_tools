package com.dabaiyang.tools.site.kele;

import com.dabaiyang.tools.constant.XiaoBaiConstant;
import com.dabaiyang.tools.utils.HttpUtils;
import com.dabaiyang.tools.utils.ImageQualityEnhance;
import com.dabaiyang.tools.utils.ImgUtils;
import com.dabaiyang.tools.utils.Plant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * 查询IP地址归属信息
     *
     * @param ipAdr
     * @return
     */
    @RequestMapping("/Q.ip.info")
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
     * ocr图片文字识别
     *
     * @param
     * @return
     */
    @RequestMapping("/ocrImgDistinguish")
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
    public String stretchRestore(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return ImageQualityEnhance.stretchRestore(bytes);
    }


}
