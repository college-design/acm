package com.lxg.acm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 刘雪岗 on 2017/2/9.
 */
@Controller
@RequestMapping("/test")
public class testController {

    @RequestMapping("/admin")
    public String index(){
        return "admin1/index";
    }
}
