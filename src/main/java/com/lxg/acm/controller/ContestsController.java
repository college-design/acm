package com.lxg.acm.controller;

import com.lxg.acm.entity.Contest;
import com.lxg.acm.entity.Problem;
import com.lxg.acm.mapper.ContestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 比赛
 * @author 
 *
 */
@Controller
public class ContestsController {

	@Autowired
	ContestMapper contestMapper;

	@RequestMapping("/contestlist/{page}/{pageSize}")
	public String findList(@PathVariable long page,
			@PathVariable long pageSize, Model model) {
		Long offset = (page - 1) * pageSize;
		List<Contest> contestList = contestMapper.queryForList(null, offset,
				pageSize);
		for(Contest list:contestList){
			if(list.getDefunct()=='N'){
				list.setStatus(1);
			}else{
				list.setStatus(0);
			}
		}
		model.addAttribute("contestList", contestList);
		model.addAttribute("total", (contestMapper.count()%pageSize==0)?(contestMapper.count() / pageSize):(contestMapper.count() / pageSize+1));
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);
		return "contestlist";
	}

	@RequestMapping("/contest/{cid}")
	public String find(@PathVariable long cid, Model model) {
		Contest contest = contestMapper.query(cid);
		List<Problem> problemList = contestMapper.queryProblemList(cid);

		model.addAttribute("cid", contest.getCid());
		model.addAttribute("contest", contest);
		model.addAttribute("problemList", problemList);
		return "contest";
	}
	@RequestMapping("/conteststatistics/{cid}")
	public String findstatistics(@PathVariable long cid, Model model) {
		Contest contest = contestMapper.query(cid);//查询比赛信息
		List<Problem> problemList = contestMapper.queryProblemList(cid);//该场比赛题目按num排序
		Long pidcountList[][]=new Long[20][20];
		Long pidcount;
		Long sum[]=new Long[20];
		for(int i=0;i<problemList.size();i++)
		{	
			pidcountList[i][0]=contestMapper.countResult1(problemList.get(i).getPid());//通过此题目的总数
			pidcountList[i][1]=contestMapper.countResult2(problemList.get(i).getPid());//result=1
			pidcountList[i][2]=contestMapper.countResult3(problemList.get(i).getPid());//result=2
			pidcountList[i][3]=contestMapper.countResult4(problemList.get(i).getPid());//result=3
			pidcountList[i][4]=contestMapper.countResult5(problemList.get(i).getPid());//result=4
			pidcountList[i][5]=contestMapper.countResult6(problemList.get(i).getPid());//result=5
			pidcountList[i][6]=contestMapper.countResult7(problemList.get(i).getPid());//result=6
			pidcountList[i][7]=contestMapper.countResult8(problemList.get(i).getPid());//language=0
			pidcountList[i][8]=contestMapper.countResult9(problemList.get(i).getPid());//language=1
			pidcountList[i][9]=contestMapper.countResult10(problemList.get(i).getPid());//language=2
			pidcountList[i][10]=contestMapper.countResult11(problemList.get(i).getPid());//language=3
			sum[i]=pidcountList[i][0]+pidcountList[i][1]+pidcountList[i][2]+pidcountList[i][3]+
					+pidcountList[i][4]+pidcountList[i][5]+pidcountList[i][6];//总共提交数
		}
		model.addAttribute("cid", contest.getCid());
		model.addAttribute("contest", contest);
		model.addAttribute("problemList", problemList);
		model.addAttribute("pidcountList",pidcountList);
		model.addAttribute("sum",sum);
		return "conteststatistics";
	}

	//============比赛排名
	@RequestMapping("/contest/{cid}/rank")
	public String rank(@PathVariable long cid, Model model) {
		Long pNum = contestMapper.queryProblemNum(cid);

		model.addAttribute("num", pNum);
		model.addAttribute("ranklist", contestMapper.queryRank(cid));
		model.addAttribute("cid", cid);
		return "contestrank";
	}
}
