package com.lxg.acm.context;

import com.lxg.acm.entity.Link;
import com.lxg.acm.mapper.LinkMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Beetl 模版配置加载
 * @author lxg
 *
 */
public class BeetlConfigLoader extends BeetlGroupUtilConfiguration {

	private static final Log logger = LogFactory.getLog(BeetlConfigLoader.class);// 日志

	@Autowired
	private LinkMapper linkMapper;

	@Override
	protected void initOther() {
		logger.info("Beetl模板配置加载");
		Map<String, Object> sharedVars = new HashMap<String, Object>();
		sharedVars.put("languages", OJConfig.instance.languages);
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
