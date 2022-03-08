package com.lxg.acm.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.entity.Link;
import com.lxg.acm.mapper.LinkMapper;
import com.lxg.acm.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminLinkController {

    @Autowired
    LinkMapper linkMapper;

    @RequestMapping("/linklist.action")
    public void queryLinkList1(@RequestParam(value = "page", required = false) Long page, HttpServletResponse response,
                               @RequestParam(value = "rows", required = false) Long pageSize) throws Exception {
        Long offset = (page - 1) * pageSize;
        List<Link> linkList = linkMapper.queryForList(offset, pageSize);
        JSONObject result = new JSONObject();
        result.put("rows", linkList);
        result.put("total", linkMapper.count());
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/deletelink.action")
    public void deleteLink(String ids, HttpServletResponse response) throws Exception {
        String[] idstr = ids.split(",");
        for (int i = 0; i < idstr.length; i++) {
            linkMapper.delete(Integer.parseInt(idstr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/addlink.action")
    public void addLink(Link link, HttpServletResponse response, Integer id) throws Exception {
        Long r;
        if (id != null) {
            r = linkMapper.update(link.getName(), link.getUrl(), link.getType(), link.getId());
        } else {
            r = linkMapper.add(link.getName(), link.getUrl(), link.getType());
        }
        JSONObject result = new JSONObject();
        if (r != 0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }
}
