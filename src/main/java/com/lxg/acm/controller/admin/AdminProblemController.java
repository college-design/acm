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

@Controller
@RequestMapping("/admin")
public class AdminProblemController {

    @Autowired
    private ProblemMapper problemMapper;

    @RequestMapping("/problemlist.action")
    public void queryProblemList1(@RequestParam(value = "page", required = false) Long page, HttpServletResponse response,
                                  @RequestParam(value = "rows", required = false) Long pageSize) throws Exception {
        Long offset = (page - 1) * pageSize;
        List<Problem> problemList = problemMapper.queryForList(null, offset,
                pageSize);
        JSONObject result = new JSONObject();
        result.put("rows", problemList);
        result.put("total", problemMapper.count());
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/addProblem.action")
    public void addProblem(Problem problem, HttpServletResponse response, Integer pid) throws Exception {
        Long r = null;
        if (pid != null) {
            r = problemMapper.updateAdminProblem(problem);
        } else {
            r = problemMapper.save(problem);
        }
        JSONObject result = new JSONObject();
        if (r != 0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/deleteProblem.action")
    public void deleteProblem(String ids, HttpServletResponse response) throws Exception {
        String[] idstr = ids.split(",");
        for (int i = 0; i < idstr.length; i++) {
            problemMapper.delete(Long.parseLong(idstr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
