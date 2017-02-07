package com.lxg.acm.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;

import com.lxg.acm.context.ServerContext;
import com.lxg.acm.support.OnlineUserSupport;

/**
 * Session监听
 * @author Administrator
 *
 */
public class SessionListener implements HttpSessionListener {

	ApplicationContext ac;

	public void sessionCreated(HttpSessionEvent event) {
	}

	// 用户退出或超时等
	public void sessionDestroyed(HttpSessionEvent event) {
		OnlineUserSupport.remove(ServerContext.getCurrentUser());
	}

}
