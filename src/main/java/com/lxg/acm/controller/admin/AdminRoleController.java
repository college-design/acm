package com.lxg.acm.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.entity.Role;
import com.lxg.acm.mapper.RoleMapper;
import com.lxg.acm.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminRoleController {

    @Autowired
    private RoleMapper roleMapper;

    @RequestMapping("/rolelist.action")
    public void queryRoleList1(@RequestParam(value = "page", required = false) Long page, HttpServletResponse response,
                               @RequestParam(value = "rows", required = false) Long pageSize) throws Exception {
        Long offset = (page - 1) * pageSize;
        List<Role> roleList = roleMapper.queryForList(offset, pageSize);
        JSONObject result = new JSONObject();
        result.put("rows", roleList);
        result.put("total", roleMapper.count());
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/addUserRole.action")
    public void addUserRole(Role role, HttpServletResponse response, Integer rid) throws Exception {
        Long r;
        if (rid != null) {
            r = roleMapper.update(role);
        } else {
            r = roleMapper.save(role);
        }
        JSONObject result = new JSONObject();
        if (r != 0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/deleteRole.action")
    public void deleteRole(String ids, HttpServletResponse response) throws Exception {
        String[] idstr = ids.split(",");
        for (int i = 0; i < idstr.length; i++) {
            roleMapper.delete(Integer.parseInt(idstr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

}
