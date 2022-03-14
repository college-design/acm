package com.lxg.acm.filter;

import com.alibaba.fastjson.JSON;
import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.support.OnlineUserSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
public class LoginFilter extends FormAuthenticationFilter {

	@Autowired
	private UserMapper userMapper;

	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		User user = userMapper.queryByUsername((String) subject.getPrincipal());
		subject.getSession().setAttribute("user", user);
		log.info("用户user={}登陆成功", JSON.toJSON(user));
		OnlineUserSupport.add(user);
		return super.onLoginSuccess(token, subject, request, response);
	}

	protected void setFailureAttribute(ServletRequest request,
			AuthenticationException ae) {
		if (ae instanceof UnknownAccountException) {
			request.setAttribute(getFailureKeyAttribute(), "账户不存在");
		} else if (ae instanceof IncorrectCredentialsException) {
			request.setAttribute(getFailureKeyAttribute(), "密码不正确");
		} else {
			log.info("其它错误");
		}
	}

}
