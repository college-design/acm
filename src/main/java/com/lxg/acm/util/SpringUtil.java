package com.lxg.acm.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring工具类
 * @author Administrator
 *
 */
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	// 按beanName获取bean
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) applicationContext.getBean(beanName);
	}

	// 反射获取bean
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz) {
		return (T) applicationContext.getBean(clazz);
	}

}
