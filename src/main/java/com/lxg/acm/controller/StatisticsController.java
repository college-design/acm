package com.lxg.acm.controller;

import com.lxg.acm.entity.Problem;
import com.lxg.acm.mapper.ProblemMapper;
import com.lxg.acm.mapper.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
@Controller
public class StatisticsController {

    @Autowired
    ProblemMapper problemMapper;
    @Autowired
    StatusMapper statusMapper;

    @RequestMapping("/statisticsList/{page}/{pageSize}")
    public String statisticsList(@PathVariable Long page,
                           @PathVariable Long pageSize, Model model) {
        Long offset = (page - 1) * pageSize;
        List<Problem> problemList = problemMapper.queryForList(null, offset, pageSize);;
        Long psCount[][]=new Long[200][200];
        for(int i=0;i<problemList.size();i++)
        {
            psCount[i][0]=statusMapper.countResultAC(problemList.get(i).getPid());//通过此题目的总数
            psCount[i][1]=statusMapper.countResultis1(problemList.get(i).getPid());//result=1
            psCount[i][2]=statusMapper.countResultTLE(problemList.get(i).getPid());//result=2
            psCount[i][3]=statusMapper.countResultMLE(problemList.get(i).getPid());//result=3
            psCount[i][4]=statusMapper.countResultWA(problemList.get(i).getPid());//result=4
            psCount[i][5]=statusMapper.countResultRE(problemList.get(i).getPid());//result=5
            psCount[i][6]=statusMapper.countResultOLE(problemList.get(i).getPid());//result=6
            psCount[i][7]=statusMapper.countResultC(problemList.get(i).getPid());//language=0 C
            psCount[i][8]=statusMapper.countResultGCC(problemList.get(i).getPid());//language=1 GCC
            psCount[i][9]=statusMapper.countResultJAVA(problemList.get(i).getPid());//language=2 JAVA
            psCount[i][10]=statusMapper.countResultCount(problemList.get(i).getPid());//language=2 JAVA
        }
        model.addAttribute("problemList", problemList);
        model.addAttribute("psCount",psCount);
        model.addAttribute("total", (problemMapper.count() % pageSize==0)?(problemMapper.count() / pageSize):(problemMapper.count() / pageSize+1));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", pageSize);
        return "count";
    }
}
