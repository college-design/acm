package com.lxg.acm.context;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class BeetlFunction {

	/**
	 * 获取当前用户
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
	 * 是否为游客
	 * @return
	 */
	public boolean isGuest() {
		return getSubject() == null || getSubject().getPrincipal() == null;
	}

	public String getUser(){
		return (String)getSubject().getPrincipal();
	}

	/**
	 * 是否为用户
	 * @return
	 */
	public boolean isUser() {
		return getSubject() != null && getSubject().getPrincipal() != null;
	}

	/**
	 * 是否为Admin
	 * @return
	 */
	public boolean isAdmin() {
		return isUser() && getSubject().hasRole("admin");
	}

	/**
	 * 是否为Root
	 * @return
	 */
	public boolean isRoot() {
		return isUser() && getSubject().hasRole("root");
	}

	// 将'<' 换为'&lt;'
	public String subSummary(String text) {
		return text.substring(0, Math.min(200, text.length())).replaceAll("<", "&lt;");
	}

	// 传入下标值返回X_time
	public String getRankTime(int n){
		return (char)(65+n)+"_time";
	}

	// 传入下标值返回X_WrongSubmits
	public String getRankWrong(int n){
		return (char)(65+n)+"_WrongSubmits";
	}
}
