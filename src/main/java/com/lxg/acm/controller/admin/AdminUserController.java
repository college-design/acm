package com.lxg.acm.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户管理
 * Created by 刘雪岗 on 2017/2/13.
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    UserMapper userMapper;

    // 用户列表
    @RequestMapping("/userlist.action")
    public void userList1(@RequestParam(value="page",required=false)Long page, HttpServletResponse response,
                          @RequestParam(value="rows",required=false)Long pageSize) throws Exception{
        Long offset = (page - 1) * pageSize;
        List<User> userList = userMapper.queryForList(offset,pageSize);
        JSONObject result=new JSONObject();
        result.put("rows", userList);
        result.put("total", userMapper.count()); // 查询总数
        ResponseUtil.write(response, result);
    }

    // 添加修改用户信息
    @RequestMapping("/addUserInfo.action")
    public void addUserInfo(User user, HttpServletResponse response, Integer uid) throws Exception{
        Long r;
        if(uid != null){ // 更新
            r = userMapper.update(user);
        }else {
            r = userMapper.save(user);
        }
        JSONObject result=new JSONObject();
        if(r!=0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }

    // 删除用户信息
    @RequestMapping("/deleteuserInfo.action")
    public void deleteuserInfo(String ids,HttpServletResponse response) throws Exception{
        String []idstr=ids.split(",");
        for(int i=0;i<idstr.length;i++){
            userMapper.delete(Long.parseLong(idstr[i]));
        }
        JSONObject result=new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

    //-----------
    @RequestMapping("/updateUser.action")
    public void saveInfo(User user, HttpServletResponse response) throws Exception{
        StringBuffer result=new StringBuffer();
        if(user.getUid()!=null){
            userMapper.update(user);
            result.append("<script language='javascript'>alert('修改成功！');</script>");
        }else{
            result.append("<script language='javascript'>alert('修改失败！');</script>");
        }
        ResponseUtil.write(response, result);
    }
}
