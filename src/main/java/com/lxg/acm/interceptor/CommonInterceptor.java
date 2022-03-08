package com.lxg.acm.interceptor;

import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.support.OnlineUserSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommonInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private UserMapper userMapper;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String contentPath = request.getContextPath();
        request.setAttribute("contentPath", contentPath);
        request.setAttribute("adminPath", contentPath + "/admin");
        request.setAttribute("imagesPath", contentPath + "/static/images");
        request.setAttribute("cssPath", contentPath + "/static/css");
        request.setAttribute("jsPath", contentPath + "/static/js");

        request.setAttribute("userOnlineNum", OnlineUserSupport.size());
        request.setAttribute("accessNum", userMapper.count());
        return true;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
