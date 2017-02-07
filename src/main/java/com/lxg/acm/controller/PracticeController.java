package com.lxg.acm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lxg.acm.context.OJConfig;
import com.lxg.acm.core.JudgeResultType;
import com.lxg.acm.entity.Problem;
import com.lxg.acm.entity.Status;
import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.ProblemMapper;
import com.lxg.acm.mapper.StatusMapper;
import com.lxg.acm.support.CurrentUser;
import com.lxg.acm.support.JudgeSupport;
import com.lxg.acm.util.StringUtil;


/**
 * 练习
 * @author Administrator
 *
 */
@Controller
public class PracticeController {

	@Autowired
	private ProblemMapper problemMapper;

	@Autowired
	private StatusMapper statusMapper;
	

	/**
	 * 练习列表
	 * @param user
	 * @param page
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping("/problemlist/{page}/{pageSize}")
	public String findList(@CurrentUser User user, @PathVariable Long page,
			@PathVariable Long pageSize, Model model) {
		Long offset = (page - 1) * pageSize;
		List<Problem> problemList = problemMapper.queryForList(null, offset,
				pageSize);
		if (user != null) {
			for (int i = 0; i < problemList.size(); i++) {
				Problem problem = problemList.get(i);
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
		model.addAttribute("problemList", problemList);
		model.addAttribute("total", problemMapper.count() / pageSize + 1);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		return "problemlist";
	}

	@RequestMapping("/problem/{pid}")
	public String find(@PathVariable Long pid, Model model) {
		Problem problem = problemMapper.query(pid);
		if (problem != null) {
			problem.setDescription(StringUtil.toHtml(problem.getDescription()));
			problem.setInput(StringUtil.toHtml(problem.getInput()));
			problem.setOutput(StringUtil.toHtml(problem.getOutput()));
			problem.setSampleInput(StringUtil.toHtml(problem.getSampleInput()));
			problem.setSampleOutput(StringUtil.toHtml(problem.getSampleOutput()));
			problem.setHint(StringUtil.toHtml(problem.getHint()));
		}
		model.addAttribute("problem", problem);
		return "problem";
	}

	// 跳转题目提交页面
	@RequestMapping("/submit/{pid}")
	public String findsubmit(@PathVariable int pid, Model model) {
		model.addAttribute("pid", pid);
		model.addAttribute("langs", OJConfig.instance.languages);
		return "submit";
	}

	// 提交代码
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(@CurrentUser User user, Long pid, int language,
			String code) {
		Status status = new Status();
		status.setUid(user.getUid());
		status.setUsername(user.getUsername());
		status.setPid(pid);
		status.setLanguage(language);
		status.setResult(JudgeResultType.WAITING);
		statusMapper.save(status);
//		System.out.println(status.getSid());
		statusMapper.insertCode(status.getSid(), code);
		JudgeSupport judge = new JudgeSupport(status, code);
		return "redirect:/statuslist/1/50";
	}

}
