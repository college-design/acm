package com.lxg.acm.interceptor;

import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.support.OnlineUserSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @author Administrator
 *
 */
public class CommonInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;

	// 预处理回调方法，实现处理器的预处理，第三个参数为响应的处理器
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String contentPath = request.getContextPath();
		request.setAttribute("contentPath", contentPath);
		request.setAttribute("adminPath", contentPath + "/admin");
		request.setAttribute("imagesPath", contentPath + "/resources/images");
		request.setAttribute("cssPath", contentPath + "/resources/css");
		request.setAttribute("jsPath", contentPath + "/resources/js");

		//online user 在线用户数量
		request.setAttribute("userOnlineNum",OnlineUserSupport.size());
		request.setAttribute("accessNum", userMapper.count());
		return true;
	}

	// 后处理回调方法，实现处理器的后处理
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	// 请求处理完毕回调方法
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
