package com.lxg.acm.listener;

import com.lxg.acm.context.ServerContext;
import com.lxg.acm.support.OnlineUserSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session监听
 * @author Administrator
 *
 */
public class SessionListener implements HttpSessionListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	ApplicationContext ac;

	public void sessionCreated(HttpSessionEvent event) {
	}

	// 用户退出或超时等
	public void sessionDestroyed(HttpSessionEvent event) {
		OnlineUserSupport.remove(ServerContext.getCurrentUser());
	}

}
