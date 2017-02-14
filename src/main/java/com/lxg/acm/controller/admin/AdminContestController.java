package com.lxg.acm.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.entity.Contest;
import com.lxg.acm.entity.Link;
import com.lxg.acm.mapper.ContestMapper;
import com.lxg.acm.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 比赛管理
 * Created by 刘雪岗 on 2017/2/13.
 */

@Controller
@RequestMapping("/admin")
public class AdminContestController {

    @Autowired
    ContestMapper contestMapper;

    // 比赛列表
    @RequestMapping("/contestlist.action")
    public void queryContestList1(@RequestParam(value="page",required=false)Long page, HttpServletResponse response,
                                  @RequestParam(value="rows",required=false)Long pageSize) throws Exception{
        Long offset = (page - 1) * pageSize;
        List<Contest> contestList = contestMapper.queryForList(null,offset,pageSize);
        JSONObject result=new JSONObject();
        result.put("rows", contestList);
        result.put("total", contestMapper.count()); // 查询总数
        ResponseUtil.write(response, result);
    }

    // 添加于修改
    @RequestMapping("/addConteset.action")
    public void addContest(Contest contest, HttpServletResponse response, Integer cid) throws Exception{
        Long r;
        if(cid != null){ // 更新
            r = contestMapper.update(contest);
        }else {
            r = contestMapper.save(contest);
        }
        JSONObject result=new JSONObject();
        if(r!=0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }

    // 删除比赛
    @RequestMapping("/deleteContest.action")
    public void deleteContest(String ids,HttpServletResponse response) throws Exception{
        String []idstr=ids.split(",");
        for(int i=0;i<idstr.length;i++){
            contestMapper.delete(Long.parseLong(idstr[i]));
        }
        JSONObject result=new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
