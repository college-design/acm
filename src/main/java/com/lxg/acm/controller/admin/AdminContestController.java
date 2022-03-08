package com.lxg.acm.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.entity.Contest;
import com.lxg.acm.entity.ContestProblem;
import com.lxg.acm.mapper.ContestMapper;
import com.lxg.acm.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminContestController {

    @Autowired
    ContestMapper contestMapper;

    @RequestMapping("/contestlist.action")
    public void queryContestList1(@RequestParam(value = "page", required = false) Long page, HttpServletResponse response,
                                  @RequestParam(value = "rows", required = false) Long pageSize, @RequestParam(value = "title", required = false) String title) throws Exception {
        Long offset = (page - 1) * pageSize;
        List<Contest> contestList = contestMapper.queryForList(null, offset, pageSize);
        JSONObject result = new JSONObject();
        result.put("rows", contestList);
        result.put("total", contestMapper.count());
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/addConteset.action")
    public void addContest(Contest contest, HttpServletResponse response, Integer cid) throws Exception {
        Long r;
        if (cid != null) {
            r = contestMapper.update(contest);
        } else {
            r = contestMapper.save(contest);
        }
        JSONObject result = new JSONObject();
        if (r != 0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/deleteContest.action")
    public void deleteContest(String ids, HttpServletResponse response) throws Exception {
        String[] idstr = ids.split(",");
        for (int i = 0; i < idstr.length; i++) {
            contestMapper.delete(Long.parseLong(idstr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/contestProblem.action")
    public void queryContestProblem(@RequestParam(value = "page", required = false) Long page, HttpServletResponse response,
                                    @RequestParam(value = "rows", required = false) Long pageSize) throws Exception {
        Long offset = (page - 1) * pageSize;
        List<ContestProblem> contestProblem = contestMapper.queryForContestProblemList(null, offset, pageSize);
        JSONObject result = new JSONObject();
        result.put("rows", contestProblem);
        result.put("total", contestMapper.contestProblemcount());
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/addContesetProblem.action")
    public void addContest(ContestProblem contestProblem, HttpServletResponse response, Integer cpid) throws Exception {
        Long r;
        if (cpid != null) {
            r = contestMapper.updateContestProblem(contestProblem);
        } else {
            r = contestMapper.saveContestProblem(contestProblem);
        }
        JSONObject result = new JSONObject();
        if (r != 0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/deleteContestProblem.action")
    public void deleteContestProblem(String ids, HttpServletResponse response) throws Exception {
        String[] idstr = ids.split(",");
        for (int i = 0; i < idstr.length; i++) {
            contestMapper.deleteContestProblem(Long.parseLong(idstr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
