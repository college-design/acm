package com.lxg.acm.context;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.lxg.acm.constant.Constants;
import com.lxg.acm.entity.User;

public class ServerContext {

    public static User getCurrentUser() {
        return (User) getSession().getAttribute(Constants.CURRENT_USER);
    }

    public static Long getCurrentUserId() {
        return getCurrentUser().getUid();
    }

    public static void setCurrentUser(User user) {
        getSession().setAttribute(Constants.CURRENT_USER, user);
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }
}
