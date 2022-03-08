package com.lxg.acm.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/3/30.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JudgeCoreTest {
    private static final String file_path = "/Users/xuegangliu/IdeaProjects/lxg/acm/db/data/code/";
    private static final String file_data_path = "/Users/xuegangliu/IdeaProjects/lxg/acm/db/data/";

    @Before
    public void init() {
    }

    @Test
    public void testJava() {
        JudgeCore judge = new JudgeCore();
        judge.compileShell = String.format("javac %s%s", file_path, "Main.java");
        judge.executeShell = String.format("java %s", file_path, "Main");
        judge.dataForNum = String.format("%s%s", file_data_path, "1");
        judge.run();
        System.out.println("result: " + judge.result);
        System.out.println("timeused: " + judge.timeUsed);
        System.out.println("memory: " + judge.memory);
        System.out.println("errorInfo: " + judge.errorInfo);
    }

    @Test
    public void testC() {
        JudgeCore judge = new JudgeCore();
        judge.compileShell = String.format("gcc -o %s%s",file_path, "Hello.c");
        judge.executeShell = String.format("%s%s%s",file_path,"Hello","");
        judge.dataForNum = String.format("%s%s", file_data_path, "1");
        judge.run();
        System.out.println("result: " + judge.result);
        System.out.println("timeused: " + judge.timeUsed);
        System.out.println("memory: " + judge.memory);
        System.out.println("errorInfo: " + judge.errorInfo);
    }
}
