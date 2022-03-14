package com.lxg.acm.interceptor;

import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.support.OnlineUserSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class CommonInterceptor implements HandlerInterceptor {

    @Resource
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
