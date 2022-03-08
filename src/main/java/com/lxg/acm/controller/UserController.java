package com.lxg.acm.controller;

import com.lxg.acm.entity.User;
import com.lxg.acm.exception.UserException;
import com.lxg.acm.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/userAdd")
    public String addUser(User user) {
//		user.setPassword(StringUtil.md5(user.getPassword(),"acm"));
        Long result = userMapper.save(user);
        return "redirect:/problemlist/1/50";
    }

    @RequestMapping("/userlist/{page}/{offset}")
    public String findList(@PathVariable Long page, @PathVariable Long offset) {
        return "userlist";
    }

    @RequestMapping("/userinfo/{uid}")
    public String find(@PathVariable Long uid, Model model) {
        List<Integer> problemSolvedList = userMapper.queryByUidSolved(uid);
        List<Integer> problemNotSolvedList = userMapper.queryByUidNoSolved(uid);

        model.addAttribute("problemSolvedList", problemSolvedList);
        model.addAttribute("problemNotSolvedList", problemNotSolvedList);

        User user = userMapper.query(uid);
        if (user == null) {
            throw new UserException("用户不存在");
        }
        model.addAttribute("user", user);
        return "userinfo";
    }


    @RequestMapping("/regist")
    public String regist(User user) {
        return "regist";
    }


    @RequestMapping(value = "/update/{uid}", method = RequestMethod.GET)
    public String updatePage(@PathVariable Long uid, Model model) {
        User user = userMapper.query(uid);
        model.addAttribute("user", user);
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        userMapper.update(user);
        return "redirect:/problemlist/1/50";
    }

    @RequestMapping(value = "/login")
    public String login(User user) {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "index";
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(Long user) {
//		userMapper.delete(user);
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "isUserNameAdd", method = RequestMethod.GET)
    public int isUserNameAdd(@RequestParam String username) {
        return userMapper.isUserNameAdd(username);
    }

    @ResponseBody
    @RequestMapping(value = "isUserPasswordPass", method = RequestMethod.GET)
    public int isUserPasswordPass(User user) {
        if (null != user && StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword())) {
            return userMapper.isUserPasswordPass(user.getUsername(), user.getPassword());
        } else {
            return 0;
        }
    }

    @ResponseBody
    @RequestMapping(value = "updateUserPassword", method = RequestMethod.GET)
    public int updateUserPassword(User user) {
        if (null != user && StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword())) {
            return userMapper.updateUserPassword(user.getUsername(), user.getPassword());
        } else {
            return 0;
        }
    }

}
