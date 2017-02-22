package com.lxg.acm.util;

/**
 * Created by 刘雪岗 on 2017/2/22.
 */
public class Test {
    public static void main(String args[]) {
        Test test = new Test();
        String content="faafa<dafasdf<afdadsfasdgasdf<<<<<fasdfw";
        String folder = "A";
        String runshell = "g++ -o ${path}${name} ${path}${name}.${ext}";
        // Mysql第2页偏移量
        System.out.println(test.offset(2,5));
        System.out.println(test.subSummary(content));
        System.out.println(test.getRankTime(0));
        System.out.println(runshell.replace("${path}", folder)
                .replace("${path}", folder).replace("${name}", "Main")
                .replace("${name}", "Main"));
    }

    /**
     *     MySQL分页
     * @param page 第几页
     * @param pageSize 每页大小
     * @return 偏移量
     */
    public int offset(int page,int pageSize){
        return (page - 1) * pageSize;
    }

    /**
     * 将字符串中前200个字符中的'<'换为'&lt;'
     * @param text
     * @return
     */
    public String subSummary(String text) {
        return text.substring(0, Math.min(200, text.length())).replaceAll("<", "&lt;");
    }

    public String getRankTime(int n){
        return (char)(65+n)+"_time";
    }
}
