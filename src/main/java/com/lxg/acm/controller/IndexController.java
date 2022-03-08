package com.lxg.acm.controller;

import java.util.Date;
import java.util.List;

import com.lxg.acm.entity.Link;
import com.lxg.acm.mapper.LinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxg.acm.mapper.ProblemMapper;

@Controller
public class IndexController {

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private LinkMapper linkMapper;

    @RequestMapping("/")
    public String index(Model model) {
        List<Link> links = linkMapper.selectAll();
        model.addAttribute("msg", "hello world!");
        model.addAttribute("date", new Date());
        model.addAttribute("links", links);
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}
