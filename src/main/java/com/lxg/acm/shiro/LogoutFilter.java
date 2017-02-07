package com.lxg.acm.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.lxg.acm.context.ServerContext;
import com.lxg.acm.support.OnlineUserSupport;


/**
 * 退出
 * @author Administrator
 *
 */
public class LogoutFilter extends
		org.apache.shiro.web.filter.authc.LogoutFilter {

	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		OnlineUserSupport.remove(ServerContext.getCurrentUser()); // 从在线用户中移除
		return super.preHandle(request, response);
	}

}
