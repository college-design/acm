package com.lxg.acm.controller;

import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 运行
 * @author 
 *
 */
@Controller
public class RankController {

	@Autowired
	UserMapper userMapper;

	@RequestMapping("/ranklist/{page}/{pageSize}")
	public String find(@PathVariable Long page,
			@PathVariable Long pageSize, Model model) {
		Long offset = (page - 1) * pageSize;
		List<User> userList = userMapper.queryForList(offset, pageSize);
		model.addAttribute("userList", userList);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("total", (userMapper.count() % pageSize==0)? (userMapper.count() / pageSize): (userMapper.count() / pageSize+1));
		model.addAttribute("currentPage", page);
		return "ranklist";
	}
	
//	@RequestMapping("/rank/{s_uid}/{page}/{pageSize}")
//	public String findList(@PathVariable Long s_uid,@PathVariable Long page,
//			@PathVariable Long pageSize, Model model) {
//		Long offset = (page - 1) * pageSize;
//		List<Rank> rankList=userMapper.queryForListProblemstatus(s_uid,offset, pageSize);	
//		model.addAttribute("s_uid",s_uid);
//		model.addAttribute("rankList",rankList);
//		model.addAttribute("pageSize", pageSize);
//		model.addAttribute("total", userMapper.countProblemstatus() / pageSize + 1);
//		model.addAttribute("currentPage", page);
//		return "problemrank";
//	}
	
}
