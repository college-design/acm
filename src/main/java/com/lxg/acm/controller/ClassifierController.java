package com.lxg.acm.controller;

import com.lxg.acm.entity.Classifier;
import com.lxg.acm.entity.Problem;
import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.ClassifierMapper;
import com.lxg.acm.mapper.StatusMapper;
import com.lxg.acm.support.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 分类
 * @author xwzhou
 */
@Controller
public class ClassifierController {
	@Autowired
	private ClassifierMapper classifierMapper;
	
	@Autowired
	private StatusMapper statusMapper;

	@RequestMapping("/classifier/{page}/{pageSize}")
	public String findList(@PathVariable Long page,
			@PathVariable Long pageSize, Model model) {
		Long offset = (page - 1) * pageSize;
		List<Classifier> classifierList = classifierMapper.queryForList(null,
				offset, pageSize);
		model.addAttribute("classifierList", classifierList);
		model.addAttribute("total", (classifierMapper.count() % pageSize==0)?(classifierMapper.count() / pageSize):(classifierMapper.count() / pageSize+1));
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		return "classifier";
	}

	@RequestMapping("/classifierlist/{cid}")
	public String findProblemList(@CurrentUser User user,
			@PathVariable Long cid, Model model) {
		model.addAttribute("cid", cid);
		List<Problem> problemlist = classifierMapper.queryProblemList(cid);

		if (user != null) {
			for (int i = 0; i < problemlist.size(); i++) {
				Problem problem = problemlist.get(i);
				Integer result = statusMapper.queryResult(user.getUid(),
						problem.getPid());
				if (result == null) {
					result = 0;
				} else if (result == 0) {
					result = 1;
				} else {
					result = 2;
				}
				problem.setStatus(result);
			}
		}
		model.addAttribute("problemlist", problemlist);
		return "classifierlist";
	}

}
