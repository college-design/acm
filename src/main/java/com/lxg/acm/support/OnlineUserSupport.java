package com.lxg.acm.support;

import com.lxg.acm.entity.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * 在线用户
 * @author Administrator
 *
 */
public class OnlineUserSupport {

	private static List<User> users = new CopyOnWriteArrayList<User>();

	// list添加用户
	public static void add(User user) {
		users.add(user);
	}

	// list删除用户
	public static void remove(User user) {
		users.remove(user);
	}

	// list大小
	public static int size() {
		return users.size();
	}

	// 获取list对象
	public static List<User> getUsers() {
		return users;
	}
}
