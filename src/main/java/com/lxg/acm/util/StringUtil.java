package com.lxg.acm.util;

/**
 * 字符串工具类
 * @author Administrator
 *
 */
public class StringUtil {

	/**
	 * 将字符串中的换行符\n替换为<br>
	 * @param txt
	 * @return
	 */
	public static String toHtml(String txt){
		return txt.replaceAll("\n", "<br>");
	}
}
