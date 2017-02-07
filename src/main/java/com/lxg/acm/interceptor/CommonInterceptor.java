package com.lxg.acm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lxg.acm.support.OnlineUserSupport;


/**
 * 拦截器
 * @author Administrator
 *
 */
public class CommonInterceptor implements HandlerInterceptor {

	private final static Log LOG = LogFactory.getLog(CommonInterceptor.class);

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		LOG.info(request.getRequestURI() + "  " + request.getQueryString());
		String contentPath = request.getContextPath();
		request.setAttribute("contentPath", contentPath);
		request.setAttribute("adminPath", contentPath + "/admin");
		request.setAttribute("imagesPath", contentPath + "/resources/images");
		request.setAttribute("cssPath", contentPath + "/resources/css");
		request.setAttribute("jsPath", contentPath + "/resources/js");

		//online user 在线用户数量
		request.setAttribute("userOnlineNum",OnlineUserSupport.size());
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
