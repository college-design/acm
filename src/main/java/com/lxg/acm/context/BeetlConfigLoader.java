package com.lxg.acm.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lxg.acm.entity.Link;
import com.lxg.acm.mapper.LinkMapper;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Beetl 模版配置加载
 * @author Administrator
 *
 */
public class BeetlConfigLoader extends BeetlGroupUtilConfiguration {

	@Autowired
	private LinkMapper linkMapper;

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

		List<Link> links = linkMapper.selectAll();

		sharedVars.put("links",links);

		groupTemplate.setSharedVars(sharedVars);
		groupTemplate.registerFunctionPackage("so", new BeetlFunction());
	}

}
