package com.lxg.acm.context;

import java.util.HashMap;
import java.util.Map;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;


/**
 * Beetl 模版配置加载
 * @author Administrator
 *
 */
public class BeetlConfigLoader extends BeetlGroupUtilConfiguration {

	@Override
	protected void initOther() {
		Map<String, Object> sharedVars = new HashMap<String, Object>();
		sharedVars.put("languages", OJConfig.instance.languages);
		System.out.println("loading  beetl config.");
		// String contentPath = "/oj";
		// sharedVars.put("contentPath", contentPath);
		// sharedVars.put("imagesPath", contentPath + "/resources/images");
		// sharedVars.put("cssPath", contentPath + "/resources/css");
		// sharedVars.put("jsPath", contentPath + "/resources/js");

		// 分页大小
		sharedVars.put("RANK_PAGE_SIZE", 10);
		sharedVars.put("CONTEST_PAGE_SIZE", 5);
		sharedVars.put("STATUS_PAGE_SIZE", 10);
		sharedVars.put("PROBLEM_PAGE_SIZE", 10);
		sharedVars.put("CLASSIFIER_PAGE_SIZE", 5);

		groupTemplate.setSharedVars(sharedVars);
		groupTemplate.registerFunctionPackage("so", new BeetlFunction());
	}

}
