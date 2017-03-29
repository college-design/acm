package com.lxg.acm.controller;

import com.lxg.acm.entity.User;
import com.lxg.acm.exception.UserException;
import com.lxg.acm.mapper.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Log logger = LogFactory.getLog(UserController.class);// 日志
	
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
		logger.info("========UserController->addUser添加用户user={"+user+"}========");
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
		logger.info("========UserController->用户分页列表信息========未完成");
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
		logger.info("========UserController->登录用户信息={"+user.toString()+"}========");
		return "userinfo";
	}

	/**
	 * 跳转用户注册页面
	 * @param user
	 * @return
	 */
	@RequestMapping("/regist")
	public String regist(User user) {
		logger.info("========UserController->用户注册={"+user.toString()+"}========");
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
		logger.info("========UserController->用户修改页面========");
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
		logger.info("========UserController->用户修改={"+user.toString()+"}========");
		userMapper.update(user);
		return "redirect:/problemlist/1/50";
	}

	// 跳转登陆页面
	@RequestMapping(value = "/login")
	public String login(User user) {
		logger.info("========UserController->用户登录={"+user.toString()+"}========");
		return "login";
	}

	// 退出
	@RequestMapping(value = "/logout")
	public String logout() {
		logger.info("========UserController->用户退出========");
		return "index";
	}

	// 删除用户
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(Long user) {
//		userMapper.delete(user);
		logger.info("========UserController->删除用户={"+user.toString()+"}========未完成");
		return "";
	}

}
