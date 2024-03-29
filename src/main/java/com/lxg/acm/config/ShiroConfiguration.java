package com.lxg.acm.config;

import com.lxg.acm.shiro.UserRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Resource
    private UserRealm userRealm;

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired  DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterHashMap = new HashMap<>();
        filterHashMap.put("authc", "com.lxg.acm.shiro.LoginFilter");
        filterHashMap.put("logout", "com.lxg.acm.shiro.LogoutFilter");
        filterHashMap.put("roles.loginUrl", "/user/login");
        filterHashMap.put("user.loginUrl", "/user/login");
        filterHashMap.put("authc.loginUrl", "/user/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterHashMap);

        Map<String, String> map = new HashMap<String, String>();
        map.put("/resources/**", "anon");
        map.put("/user/login", "authc");
        map.put("/admin/**", "roles[root]");
        map.put("/user/logout", "logout");
        map.put("/submit/**", "user");
        map.put("/ranklist/**", "anon");
        map.put("/**", "anon");
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
