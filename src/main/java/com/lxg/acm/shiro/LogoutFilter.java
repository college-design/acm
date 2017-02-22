package com.lxg.acm.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.lxg.acm.context.ServerContext;
import com.lxg.acm.support.OnlineUserSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 退出
 * @author Administrator
 *
 */
public class LogoutFilter extends
		org.apache.shiro.web.filter.authc.LogoutFilter {

	private static final Log logger = LogFactory.getLog(LogoutFilter.class);// 日志

	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		logger.info("==========退出->LogoutFilter->start==========");
		OnlineUserSupport.remove(ServerContext.getCurrentUser()); // 从在线用户中移除
		logger.info("==========退出->LogoutFilter->end==========");
		return super.preHandle(request, response);
	}

}
