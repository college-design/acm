//package com.lxg.acm.controller;
//
//import com.lxg.acm.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("/test")
//public class testController {
//
//    @Autowired
//    UserMapper userMapper;
//
//    @RequestMapping("/admin")
//    public String index() {
//        return "admin1/index";
//    }
//
//    @ResponseBody
//    @RequestMapping("/xxx.action")
//    public String xxx() {
//        Long r = userMapper.getUserSolved(1l);
//        return r + "";
//    }
//}
