package com.lxg.acm.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.entity.Problem;
import com.lxg.acm.mapper.ProblemMapper;
import com.lxg.acm.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 题目管理
 * Created by 刘雪岗 on 2017/2/13.
 */

@Controller
@RequestMapping("/admin")
public class AdminProblemController {

    @Autowired
    private ProblemMapper problemMapper;

    // 问题列表
    @RequestMapping("/problemlist.action")
    public void queryProblemList1(@RequestParam(value="page",required=false)Long page, HttpServletResponse response,
                                  @RequestParam(value="rows",required=false)Long pageSize) throws Exception{
        Long offset = (page - 1) * pageSize;
        List<Problem> problemList = problemMapper.queryForList(null, offset,
                pageSize);
        JSONObject result=new JSONObject();
        result.put("rows", problemList);
        result.put("total", problemMapper.count());
        ResponseUtil.write(response, result);
    }
}
