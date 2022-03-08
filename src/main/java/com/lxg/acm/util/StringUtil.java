package com.lxg.acm.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static void main(String[] args) {
        StringUtil test = new StringUtil();
        String password = "123456";
        System.out.println("Md5加密：" + test.md5(password, "com.lxg"));
    }

    public static String toHtml(String txt) {
        return txt.replaceAll("\n", "<br>");
    }

    public static String md5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        if ((str != null) && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static String formatLike(String str) {
        if (isNotEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    public static List<String> filterWhite(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String l : list) {
            if (isNotEmpty(l)) {
                resultList.add(l);
            }
        }
        return resultList;
    }
}
