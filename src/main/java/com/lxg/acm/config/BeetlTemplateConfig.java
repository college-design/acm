package com.lxg.acm.config;

import com.lxg.acm.context.BeetlFunction;
import com.lxg.acm.context.OJConfig;
import com.lxg.acm.entity.Link;
import com.lxg.acm.mapper.LinkMapper;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j
public class BeetlTemplateConfig {

    @Value("${RESOURCE.root:/templates}")
    String resourceRoot;

    @Autowired
    private LinkMapper linkMapper;

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUnilConfiguration() {
        Map<String, Object> sharedVars = new HashMap<String, Object>();
        sharedVars.put("languages", OJConfig.instance.languages);
        sharedVars.put("RANK_PAGE_SIZE", 10);
        sharedVars.put("CONTEST_PAGE_SIZE", 5);
        sharedVars.put("STATUS_PAGE_SIZE", 10);
        sharedVars.put("PROBLEM_PAGE_SIZE", 10);
        sharedVars.put("CLASSIFIER_PAGE_SIZE", 5);
        List<Link> links = linkMapper.selectAll();
        sharedVars.put("links", links);

        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ClasspathResourceLoader classPathLoader = new ClasspathResourceLoader(this.getClass().getClassLoader(), resourceRoot);
        beetlGroupUtilConfiguration.setResourceLoader(classPathLoader);
        beetlGroupUtilConfiguration.setSharedVars(sharedVars);
        beetlGroupUtilConfiguration.init();
        beetlGroupUtilConfiguration.getGroupTemplate().registerFunctionPackage("so", BeetlFunction.class);
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"));
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(
            @Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
//        beetlSpringViewResolver.setPrefix("/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }
}