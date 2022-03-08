package com.lxg.acm.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class BeetlFunction {

    private static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public boolean isLogined() {
        return isUser();
    }

    public boolean isGuest() {
        return getSubject() == null || getSubject().getPrincipal() == null;
    }

    public String getUser() {
        return (String) getSubject().getPrincipal();
    }

    public boolean isUser() {
        return getSubject() != null && getSubject().getPrincipal() != null;
    }

    public boolean isAdmin() {
        return isUser() && getSubject().hasRole("admin");
    }

    public boolean isRoot() {
        return isUser() && getSubject().hasRole("root");
    }

    public String subSummary(String text) {
        return text.substring(0, Math.min(200, text.length())).replaceAll("<", "&lt;");
    }

    public String getRankTime(int n) {
        return (char) (65 + n) + "_time";
    }

    public String getRankWrong(int n) {
        return (char) (65 + n) + "_WrongSubmits";
    }
}
