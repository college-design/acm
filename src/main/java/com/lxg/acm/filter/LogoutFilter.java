package com.lxg.acm.filter;

import com.lxg.acm.context.ServerContext;
import com.lxg.acm.support.OnlineUserSupport;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
public class LogoutFilter extends
		org.apache.shiro.web.filter.authc.LogoutFilter {

	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		OnlineUserSupport.remove(ServerContext.getCurrentUser());
		return super.preHandle(request, response);
	}

}
