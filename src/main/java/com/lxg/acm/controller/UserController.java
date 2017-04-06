package com.lxg.acm.controller;

import com.lxg.acm.entity.User;
import com.lxg.acm.exception.UserException;
import com.lxg.acm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/userAdd")
	public String addUser(User user){
//		user.setPassword(StringUtil.md5(user.getPassword(),"acm"));
		Long result = userMapper.save(user);
		return "redirect:/problemlist/1/50";
	}
	
	/**
	 * 用户列表
	 * @param page
	 * @param offset
	 * @return
	 */
	@RequestMapping("/userlist/{page}/{offset}")
	public String findList(@PathVariable Long page, @PathVariable Long offset) {
		return "userlist";
	}
	
	/**
	 * 用户信息
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping("/userinfo/{uid}")
	public String find(@PathVariable Long uid, Model model) {
		// 用户解决的题目pid列表
		List<Integer> problemSolvedList=userMapper.queryByUidSolved(uid);
		// 用户未解决的题目pid列表
		List<Integer> problemNotSolvedList=userMapper.queryByUidNoSolved(uid);

		model.addAttribute("problemSolvedList", problemSolvedList);
		model.addAttribute("problemNotSolvedList", problemNotSolvedList);

		User user = userMapper.query(uid); // 查询当前用户
		if (user == null) {
			throw new UserException("用户不存在");
		}
		model.addAttribute("user", user);
		return "userinfo";
	}

	/**
	 * 跳转用户注册页面
	 * @param user
	 * @return
	 */
	@RequestMapping("/regist")
	public String regist(User user) {
		return "regist";
	}

	/**
	 * 跳转修改用户页面
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{uid}", method = RequestMethod.GET)
	public String updatePage(@PathVariable Long uid, Model model) {
		User user = userMapper.query(uid);
		model.addAttribute("user", user);
		return "update";
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(User user) {
		userMapper.update(user);
		return "redirect:/problemlist/1/50";
	}

	// 跳转登陆页面
	@RequestMapping(value = "/login")
	public String login(User user) {
		return "login";
	}

	// 退出
	@RequestMapping(value = "/logout")
	public String logout() {
		return "index";
	}

	// 删除用户
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(Long user) {
//		userMapper.delete(user);
		return "";
	}

	/**
	 *  用户名是否注册过
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "isUserNameAdd",method = RequestMethod.GET)
	public int isUserNameAdd(@RequestParam String username){
		return userMapper.isUserNameAdd(username);
	}

}
