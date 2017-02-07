package com.lxg.acm.context;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class BeetlFunction {

	/**
	 * 当前用户主题
	 * @return
	 */
	private static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 用户是否登陆
	 * @return
	 */
	public boolean isLogined() {
		return isUser();
	}

	/**
	 * 是否为普通用户
	 * @return
	 */
	public boolean isGuest() {
		return getSubject() == null || getSubject().getPrincipal() == null;
	}

	/**
	 * 是否为用户
	 * @return
	 */
	public boolean isUser() {
		return getSubject() != null && getSubject().getPrincipal() != null;
	}

	/**
	 * 是否为管理员
	 * @return
	 */
	public boolean isAdmin() {
		return isUser() && getSubject().hasRole("admin");
	}

	/**
	 * 是否为管理员
	 * @return
	 */
	public boolean isRoot() {
		return isUser() && getSubject().hasRole("root");
	}

	public String subSummary(String text) {
		return text.substring(0, Math.min(200, text.length())).replaceAll("<", "&lt");
	}
	
	public String getRankTime(int n){
		return (char)(65+n)+"_time";
	}
	
	public String getRankWrong(int n){
		return (char)(65+n)+"_WrongSubmits";
	}
}
