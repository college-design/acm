package com.lxg.acm.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lxg.acm.entity.Link;
import com.lxg.acm.mapper.LinkMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

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
		logger.info("==========Beetl模板配置加载start==========");
		Map<String, Object> sharedVars = new HashMap<String, Object>();
		sharedVars.put("languages", OJConfig.instance.languages);
		logger.info(">>>>加载Beetl配置文件");
		// 分页大小
		sharedVars.put("RANK_PAGE_SIZE", 10);
		sharedVars.put("CONTEST_PAGE_SIZE", 5);
		sharedVars.put("STATUS_PAGE_SIZE", 10);
		sharedVars.put("PROBLEM_PAGE_SIZE", 10);
		sharedVars.put("CLASSIFIER_PAGE_SIZE", 5);

		List<Link> links = linkMapper.selectAll();
		sharedVars.put("links",links);
		logger.info(">>>>加载links");
		groupTemplate.setSharedVars(sharedVars);
		logger.info(">>>>加载分页信息");
		groupTemplate.registerFunctionPackage("so", new BeetlFunction());
		logger.info(">>>>加载BeetlFunction()");
		logger.info("==========Beetl模板配置加载end==========");
	}

}
