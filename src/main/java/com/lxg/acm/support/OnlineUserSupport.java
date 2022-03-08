package com.lxg.acm.support;

import com.lxg.acm.entity.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class OnlineUserSupport {

	private static List<User> users = new CopyOnWriteArrayList<User>();

	public static void add(User user) {
		users.add(user);
	}

	public static void remove(User user) {
		users.remove(user);
	}

	public static int size() {
		return users.size();
	}

	public static List<User> getUsers() {
		return users;
	}
}
