package com.dabaiyang.tools.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author K12
 * @Date 2019/11/26 11:08
 **/
@Controller
public class IndexController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/ip-q")
    public String ipquery(){
        return "ip-q";
    }

    @RequestMapping("/orc")
    public String orc(){
        return "orc";
    }

    @RequestMapping("/plant")
    public String orcLoc(){
        return "pant";
    }

    @RequestMapping("/imgdeal")
    public String imgdeal(){
        return "img_deal";
    }
}
