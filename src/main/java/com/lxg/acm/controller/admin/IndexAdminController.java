package com.lxg.acm.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IndexAdminController {

    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }
}
