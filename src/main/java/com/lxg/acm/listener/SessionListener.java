package com.lxg.acm.listener;

import com.lxg.acm.context.ServerContext;
import com.lxg.acm.support.OnlineUserSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session监听
 * @author Administrator
 *
 */
public class SessionListener implements HttpSessionListener {

	private static final Log logger = LogFactory.getLog(SessionListener.class);

	ApplicationContext ac;

	public void sessionCreated(HttpSessionEvent event) {
	}

	// 用户退出或超时等
	public void sessionDestroyed(HttpSessionEvent event) {
		OnlineUserSupport.remove(ServerContext.getCurrentUser());
	}

}
