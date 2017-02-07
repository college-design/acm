package com.lxg.acm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxg.acm.mapper.ProblemMapper;

/**
 * 首页
 * @author Administrator
 *
 */
@Controller
public class IndexController {

	@Autowired
	private ProblemMapper problemMapper;

	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("msg", "hello world!");
		model.addAttribute("date", new Date());
		return "index";
	}
}
