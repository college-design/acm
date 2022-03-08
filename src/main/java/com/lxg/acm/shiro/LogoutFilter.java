package com.lxg.acm.shiro;

import com.lxg.acm.context.ServerContext;
import com.lxg.acm.support.OnlineUserSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogoutFilter extends
		org.apache.shiro.web.filter.authc.LogoutFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		OnlineUserSupport.remove(ServerContext.getCurrentUser());
		return super.preHandle(request, response);
	}

}
