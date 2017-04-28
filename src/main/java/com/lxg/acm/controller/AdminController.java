//package com.lxg.acm.controller;
//
//import com.lxg.acm.entity.Classifier;
//import com.lxg.acm.entity.Contest;
//import com.lxg.acm.entity.Problem;
//import com.lxg.acm.entity.User;
//import com.lxg.acm.mapper.*;
//import com.lxg.acm.support.CurrentUser;
//import com.lxg.acm.support.OnlineUserSupport;
//import com.lxg.acm.util.StringUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//	@Autowired
//	UserMapper userMapper;
//	@Autowired
//	private ProblemMapper problemMapper;
//	@Autowired
//	private ClassifierMapper classifierMapper;
//	@Autowired
//	private StatusMapper statusMapper;
//	@Autowired
//	ContestMapper contestMapper;
//    @Autowired
//    LinkMapper linkMapper;
//
//	@RequestMapping("")
//	public String index() {
//		return "admin/index";
//	}
//
//	@RequestMapping("/problemlist/{page}/{pageSize}")
//	public String queryProblemList(@PathVariable Long page,
//			@PathVariable Long pageSize, Model model) {
//		Long offset = (page - 1) * pageSize;
//		List<Problem> problemList = problemMapper.queryForList(null, offset,
//				pageSize);
//		model.addAttribute("problemList", problemList);
//		model.addAttribute("total",  (problemMapper.count() %pageSize==0)?(problemMapper.count() / pageSize):(problemMapper.count() / pageSize+1));
//		model.addAttribute("currentPage", page);
//		model.addAttribute("pageSize", pageSize);
//		return "admin/problemlist";
//	}
//
//
//	@RequestMapping("/add/problem")
//	public String addProblem(Model model) {
//		List<Contest> contestList = contestMapper.queryForAllContest();
//		List<Classifier> classifierList = classifierMapper.queryAllClassifier();
//		model.addAttribute("classifierList", classifierList);
//		model.addAttribute("contestList",contestList);
//		return "admin/problem";
//	}
//	@RequestMapping("/show/problem/{pid}")
//	public String showProblem(@PathVariable Long pid, Model model) {
//		Problem problem = problemMapper.query(pid);
//		if (problem != null) {
//			problem.setDescription(StringUtil.toHtml(problem.getDescription()));
//			problem.setInput(StringUtil.toHtml(problem.getInput()));
//			problem.setOutput(StringUtil.toHtml(problem.getOutput()));
//			problem.setSampleInput(StringUtil.toHtml(problem.getSampleInput()));
//			problem.setSampleOutput(StringUtil.toHtml(problem.getSampleOutput()));
//			problem.setHint(StringUtil.toHtml(problem.getHint()));
//		}
//		model.addAttribute("problem", problem);
//		return "admin/showproblem";
//	}
//
//	@RequestMapping("/update/problem/{pid}")
//	public String updateProblem(@PathVariable Long pid, Model model) {
//		Problem problem = problemMapper.query(pid);
//		if (problem != null) {
//			problem.setDescription(StringUtil.toHtml(problem.getDescription()));
//			problem.setInput(StringUtil.toHtml(problem.getInput()));
//			problem.setOutput(StringUtil.toHtml(problem.getOutput()));
//			problem.setSampleInput(StringUtil.toHtml(problem.getSampleInput()));
//			problem.setSampleOutput(StringUtil.toHtml(problem.getSampleOutput()));
//			problem.setHint(StringUtil.toHtml(problem.getHint()));
//		}
//		List<Contest> contestList = contestMapper.queryForAllContest();
//		model.addAttribute("contestList",contestList);
//		List<Classifier> classifierList = classifierMapper.queryAllClassifier();
//		model.addAttribute("classifierList", classifierList);
//		model.addAttribute("problem", problem);
//		return "admin/updateproblem";
//	}
//
//	@RequestMapping("/contestlist/{page}/{pageSize}")
//	public String queryContestList(@PathVariable long page,
//			@PathVariable long pageSize, Model model) {
//		Long offset = (page - 1) * pageSize;
//		List<Contest> contestList = contestMapper.queryForList(null, offset,
//				pageSize);
//		model.addAttribute("contestList", contestList);
//		model.addAttribute("total", (contestMapper.count()%pageSize==0)?(contestMapper.count() / pageSize):(contestMapper.count() / pageSize+1));
//		model.addAttribute("currentPage", page);
//		model.addAttribute("pageSize", pageSize);
//		return "admin/contestlist";
//	}
//
//
//	@RequestMapping("/add/contest")
//	public String addContest() {
//		return "admin/contest";
//	}
//	@RequestMapping("/show/contest/{cid}")
//	public String showContest(@PathVariable long cid, Model model) {
//		Contest contest = contestMapper.query(cid);
//		List<Problem> problemList = contestMapper.queryProblemList(cid);
//
//		model.addAttribute("cid", contest.getCid());
//		model.addAttribute("contest", contest);
//		model.addAttribute("problemList", problemList);
//		return "admin/showcontest";
//	}
//
//	@RequestMapping("/update/contest")
//	public String updateContest() {
//		return "admin/contest";
//	}
//
//	@RequestMapping("/classifierlist/{page}/{pageSize}")
//	public String queryClassifierList(@PathVariable Long page,
//			@PathVariable Long pageSize, Model model) {
//		Long offset = (page - 1) * pageSize;
//		List<Classifier> classifierList = classifierMapper.queryForList(null,
//				offset, pageSize);
//		model.addAttribute("classifierList", classifierList);
//		model.addAttribute("total", (classifierMapper.count() % pageSize==0)?(classifierMapper.count() / pageSize):(classifierMapper.count() / pageSize+1));
//		model.addAttribute("currentPage", page);
//		model.addAttribute("pageSize", pageSize);
//		return "admin/classifierlist";
//	}
//	@RequestMapping("/show/classifier/{cid}")
//	public String showClassifier(@CurrentUser User user,
//			@PathVariable Long cid, Model model) {
//		model.addAttribute("cid", cid);
//		List<Problem> problemlist = classifierMapper.queryProblemList(cid);
//
//		if (user != null) {
//			for (int i = 0; i < problemlist.size(); i++) {
//				Problem problem = problemlist.get(i);
//				Integer result = statusMapper.queryResult(user.getUid(),
//						problem.getPid());
//				if (result == null) {
//					result = 0;
//				} else if (result == 0) {
//					result = 1;
//				} else {
//					result = 2;
//				}
//				problem.setStatus(result);
//			}
//		}
//		model.addAttribute("problemlist", problemlist);
//		return "admin/showclassifier";
//	}
//
//	@RequestMapping("/add/classifier")
//	public String addClassifier() {
//		return "admin/classifier";
//	}
//
//	@RequestMapping("/update/classifier/{cid}")
//	public String updateClassifier(@PathVariable Long cid, Model model) {
//		Classifier classifier=classifierMapper.query(cid);
//		model.addAttribute("classifier", classifier);
//		return "admin/updateclassifier";
//	}
//
//	@RequestMapping("/manager/user")
//	public String queryOnlineUser(Model model) {
//		model.addAttribute("rootUserList", userMapper.queryAdminForList("root"));
//		model.addAttribute("adminUserList",
//				userMapper.queryAdminForList("admin"));
//		model.addAttribute("onlineUserList", OnlineUserSupport.getUsers());
//		return "admin/usermanager";
//	}
//
//}
