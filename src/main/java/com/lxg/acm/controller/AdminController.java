package com.lxg.acm.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.entity.*;
import com.lxg.acm.mapper.*;
import com.lxg.acm.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxg.acm.support.CurrentUser;
import com.lxg.acm.support.OnlineUserSupport;
import com.lxg.acm.util.StringUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserMapper userMapper;
	@Autowired
	private ProblemMapper problemMapper;
	@Autowired
	private ClassifierMapper classifierMapper;
	@Autowired
	private StatusMapper statusMapper;
	@Autowired
	ContestMapper contestMapper;
    @Autowired
    LinkMapper linkMapper;

	@RequestMapping("")
	public String index() {
		return "admin/index";
	}

	@RequestMapping("/problemlist/{page}/{pageSize}")
	public String queryProblemList(@PathVariable Long page,
			@PathVariable Long pageSize, Model model) {
		Long offset = (page - 1) * pageSize;
		List<Problem> problemList = problemMapper.queryForList(null, offset,
				pageSize);
		model.addAttribute("problemList", problemList);
		model.addAttribute("total", problemMapper.count() / pageSize + 1);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		return "admin/problemlist";
	}

	// easy-ui 问题列表
	@RequestMapping("/problemlist.action")
	public void queryProblemList1(@RequestParam(value="page",required=false)Long page,HttpServletResponse response,
									@RequestParam(value="rows",required=false)Long pageSize) throws Exception{
		Long offset = (page - 1) * pageSize;
		List<Problem> problemList = problemMapper.queryForList(null, offset,
				pageSize);
		JSONObject result=new JSONObject();
		result.put("rows", problemList);
		result.put("total", problemMapper.count());
		ResponseUtil.write(response, result);
	}

	// easy-ui 友情链接列表
    @RequestMapping("/linklist.action")
    public void queryLinkList1(@RequestParam(value="page",required=false)Long page,HttpServletResponse response,
                               @RequestParam(value="rows",required=false)Long pageSize) throws Exception{
        Long offset = (page - 1) * pageSize;
        List<Link> linkList = linkMapper.queryForList(offset,pageSize);
        JSONObject result=new JSONObject();
        result.put("rows", linkList);
        result.put("total", linkMapper.count());
        ResponseUtil.write(response, result);
    }
    // 删除友情链接
	@RequestMapping("/deletelink.action")
	public void deleteLink(String ids,HttpServletResponse response) throws Exception{
		String []idstr=ids.split(",");
		for(int i=0;i<idstr.length;i++){
			linkMapper.delete(Integer.parseInt(idstr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	// 添加链接
	@RequestMapping("/addlink.action")
	public void addLink(Link link,HttpServletResponse response) throws Exception{
		JSONObject result=new JSONObject();
		Long r = linkMapper.add(link.getName(),link.getUrl(),link.getType());
		if(r!=0)
			result.put("success", true);
		ResponseUtil.write(response, result);
	}

	@RequestMapping("/add/problem")
	public String addProblem(Model model) {
		List<Contest> contestList = contestMapper.queryForAllContest();
		List<Classifier> classifierList = classifierMapper.queryAllClassifier();
		model.addAttribute("classifierList", classifierList);
		model.addAttribute("contestList",contestList);
		return "admin/problem";
	}
	@RequestMapping("/show/problem/{pid}")
	public String showProblem(@PathVariable Long pid, Model model) {
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
		return "admin/showproblem";
	}
	
	@RequestMapping("/update/problem/{pid}")
	public String updateProblem(@PathVariable Long pid, Model model) {
		Problem problem = problemMapper.query(pid);
		if (problem != null) {
			problem.setDescription(StringUtil.toHtml(problem.getDescription()));
			problem.setInput(StringUtil.toHtml(problem.getInput()));
			problem.setOutput(StringUtil.toHtml(problem.getOutput()));
			problem.setSampleInput(StringUtil.toHtml(problem.getSampleInput()));
			problem.setSampleOutput(StringUtil.toHtml(problem.getSampleOutput()));
			problem.setHint(StringUtil.toHtml(problem.getHint()));
		}
		List<Contest> contestList = contestMapper.queryForAllContest();
		model.addAttribute("contestList",contestList);
		List<Classifier> classifierList = classifierMapper.queryAllClassifier();
		model.addAttribute("classifierList", classifierList);
		model.addAttribute("problem", problem);
		return "admin/updateproblem";
	}

	@RequestMapping("/contestlist/{page}/{pageSize}")
	public String queryContestList(@PathVariable long page,
			@PathVariable long pageSize, Model model) {
		Long offset = (page - 1) * pageSize;
		List<Contest> contestList = contestMapper.queryForList(null, offset,
				pageSize);
		model.addAttribute("contestList", contestList);
		model.addAttribute("total", contestMapper.count() / pageSize + 1);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		return "admin/contestlist";
	}

	// easy-ui 比赛列表
    @RequestMapping("/contestlist.action")
	public void queryContestList1(@RequestParam(value="page",required=false)Long page,HttpServletResponse response,
								  @RequestParam(value="rows",required=false)Long pageSize) throws Exception{
        Long offset = (page - 1) * pageSize;
        List<Contest> contestList = contestMapper.queryForList(null,offset,pageSize);
        JSONObject result=new JSONObject();
        result.put("rows", contestList);
        result.put("total", contestMapper.count()); // 查询总数
        ResponseUtil.write(response, result);
    }

	@RequestMapping("/add/contest")
	public String addContest() {
		return "admin/contest";
	}
	@RequestMapping("/show/contest/{cid}")
	public String showContest(@PathVariable long cid, Model model) {
		Contest contest = contestMapper.query(cid);
		List<Problem> problemList = contestMapper.queryProblemList(cid);

		model.addAttribute("cid", contest.getCid());
		model.addAttribute("contest", contest);
		model.addAttribute("problemList", problemList);
		return "admin/showcontest";
	}
		
	@RequestMapping("/update/contest")
	public String updateContest() {
		return "admin/contest";
	}

	@RequestMapping("/classifierlist/{page}/{pageSize}")
	public String queryClassifierList(@PathVariable Long page,
			@PathVariable Long pageSize, Model model) {
		Long offset = (page - 1) * pageSize;
		List<Classifier> classifierList = classifierMapper.queryForList(null,
				offset, pageSize);
		model.addAttribute("classifierList", classifierList);
		model.addAttribute("total", classifierMapper.count() / pageSize + 1);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		return "admin/classifierlist";
	}
	@RequestMapping("/show/classifier/{cid}")
	public String showClassifier(@CurrentUser User user,
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
		return "admin/showclassifier";
	}
	
	@RequestMapping("/add/classifier")
	public String addClassifier() {
		return "admin/classifier";
	}

	@RequestMapping("/update/classifier/{cid}")
	public String updateClassifier(@PathVariable Long cid, Model model) {
		Classifier classifier=classifierMapper.query(cid);
		model.addAttribute("classifier", classifier);
		return "admin/updateclassifier";
	}

	@RequestMapping("/manager/user")
	public String queryOnlineUser(Model model) {
		model.addAttribute("rootUserList", userMapper.queryAdminForList("root"));
		model.addAttribute("adminUserList",
				userMapper.queryAdminForList("admin"));
		model.addAttribute("onlineUserList", OnlineUserSupport.getUsers());
		return "admin/usermanager";
	}

	// easy-ui 用户列表
    @RequestMapping("/userlist.action")
	public void userList1(@RequestParam(value="page",required=false)Long page,HttpServletResponse response,
						  @RequestParam(value="rows",required=false)Long pageSize) throws Exception{
        Long offset = (page - 1) * pageSize;
        List<User> userList = userMapper.queryForList(offset,pageSize);
        JSONObject result=new JSONObject();
        result.put("rows", userList);
        result.put("total", userMapper.count()); // 查询总数
        ResponseUtil.write(response, result);
    }
}
