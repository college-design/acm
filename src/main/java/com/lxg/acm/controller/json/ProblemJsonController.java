package com.lxg.acm.controller.json;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.entity.Problem;
import com.lxg.acm.entity.User;


@RestController
@RequestMapping("/#")
public class ProblemJsonController extends BaseJsonController{

	@RequestMapping("/problem/list/{page}/{pageSize}")
	public Object findList(@PathVariable Long page,
			@PathVariable Long pageSize,HttpSession session) {
		JSONObject json=new JSONObject();
		System.out.println(session.getId());
		Long offset = (page - 1) * pageSize;
		List<Problem> problemList = problemMapper.queryForList(null, offset,
				pageSize);
		User user=(User) session.getAttribute("current_user");
		if (user != null) {
			for (int i = 0; i < problemList.size(); i++) {
				Problem problem = problemList.get(i);
				Integer result = statusMapper.queryResult(user.getUid(),
						problem.getPid());
				if (result == null) {
					result = 0;
				} else if (result == 0) {
					result = 1;
				} else {
					result = 2;
				}
				problem.setStatus(result);
			}
		}
		json.put("problemList", problemList);
		json.put("total", problemMapper.count() / pageSize + 1);
		json.put("currentPage", page);
		json.put("pageSize", pageSize);
		return json;
	}

}
