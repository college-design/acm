package com.lxg.acm.controller;

import com.lxg.acm.entity.Status;
import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.StatusMapper;
import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.support.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StatusController {

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/statuslist/{page}/{pageSize}")
    public String findList(Long pid, String username, @PathVariable Long page,
                           @PathVariable Long pageSize, Model model) {
        Long offset = (page - 1) * pageSize;
        List<Status> statusList = statusMapper.queryForList(pid, username,
                offset, pageSize);
        model.addAttribute("statusList", statusList);
        model.addAttribute("username", username);
        model.addAttribute("pid", pid);
        model.addAttribute("total", (statusMapper.count() % pageSize == 0) ? (statusMapper.count() / pageSize) : (statusMapper.count() / pageSize + 1));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", pageSize);
        return "statuslist";
    }

    @RequestMapping("/status/code/{sid}")
    public String findCode(@CurrentUser User user, @PathVariable Long sid, Model model) {
        Status status = statusMapper.queryBySid(sid);
        String code = statusMapper.queryCodeBySid(sid);
        model.addAttribute("status", status);
        model.addAttribute("user", user);
        model.addAttribute("sid", sid);
        model.addAttribute("code", code.replaceAll("<", "&lt"));
        return "code";
    }

}
