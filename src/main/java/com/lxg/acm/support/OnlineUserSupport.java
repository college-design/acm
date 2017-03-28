package com.lxg.acm.support;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.lxg.acm.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 在线用户
 * @author Administrator
 *
 */
public class OnlineUserSupport {

	private static final Log logger = LogFactory.getLog(OnlineUserSupport.class);// 日志

	private static List<User> users = new CopyOnWriteArrayList<User>();

	// list添加用户
	public static void add(User user) {
		logger.info(">>>>用户user=["+user+"]登录>>>>");
		users.add(user);
	}

	// list删除用户
	public static void remove(User user) {
		logger.info(">>>>用户user=["+user+"]退出>>>>");
		users.remove(user);
	}

	// list大小
	public static int size() {
		logger.info(">>>>用户登录总数=["+users.size()+"]>>>>");
		return users.size();
	}

	// 获取list对象
	public static List<User> getUsers() {
//		logger.info(">>>>所有用户=["+users+"]>>>>");
		return users;
	}
}
