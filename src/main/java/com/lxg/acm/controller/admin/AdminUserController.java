package com.lxg.acm.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.context.ServerContext;
import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/userlist.action")
    public void userList1(@RequestParam(value = "page", required = false) Long page, HttpServletResponse response,
                          @RequestParam(value = "rows", required = false) Long pageSize, User user) throws Exception {
        Long offset = (page - 1) * pageSize;
        List<User> userList = userMapper.queryForList(offset, pageSize);
        JSONObject result = new JSONObject();
        result.put("rows", userList);
        result.put("total", userMapper.count());
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/addUserInfo.action")
    public void addUserInfo(User user, HttpServletResponse response, Integer uid) throws Exception {
        Long r;
        if (uid != null) {
            r = userMapper.update(user);
        } else {
            r = userMapper.save(user);
        }
        JSONObject result = new JSONObject();
        if (r != 0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/deleteuserInfo.action")
    public void deleteuserInfo(String ids, HttpServletResponse response) throws Exception {
        String[] idstr = ids.split(",");
        for (int i = 0; i < idstr.length; i++) {
            userMapper.delete(Long.parseLong(idstr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/updateUser.action")
    public void saveInfo(User user, HttpServletResponse response) throws Exception {
        StringBuffer result = new StringBuffer();
        if (user.getUid() != null) {
            userMapper.update(user);
            result.append("<script language='javascript'>alert('修改成功！');</script>");
            ServerContext.setCurrentUser(userMapper.query(user.getUid()));
        } else {
            result.append("<script language='javascript'>alert('修改失败！');</script>");
        }
        ResponseUtil.write(response, result);
    }
}
