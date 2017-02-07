package com.lxg.acm.context;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.lxg.acm.constant.Constants;
import com.lxg.acm.entity.User;

/**
 * 容器用户
 * @author Administrator
 *
 */
public class ServerContext {

	/**
	 * 获取当前用户
	 * @return
	 */
	public static User getCurrentUser() {
		return (User) getSession().getAttribute(Constants.CURRENT_USER);
	}

	/**
	 * 获取当前用户uid
	 * @return
	 */
	public static Long getCurrentUserId() {
		return getCurrentUser().getUid();
	}

	// 将当前用户存入session中
	public static void setCurrentUser(User user) {
		getSession().setAttribute(Constants.CURRENT_USER, user);
	}

	/**
	 * 获取session存在的用户
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
}
