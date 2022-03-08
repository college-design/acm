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

    @Before
    public void init() {
    }

    @Test
    public void testJava() {
        JudgeCore judge = new JudgeCore();
        judge.compileShell = "javac D:\\acm\\test\\Main.java";
        judge.executeShell = "java -classpath D:\\acm\\test Main";
        judge.dataForNum = "D:\\acm\\test\\data\\1";
        judge.run();
        System.out.println("result: " + judge.result);
        System.out.println("timeused: " + judge.timeUsed);
        System.out.println("memory: " + judge.memory);
        System.out.println("errorInfo: " + judge.errorInfo);
    }

    @Test
    public void testC() {
        JudgeCore judge = new JudgeCore();
        judge.compileShell = "gcc -o D:\\acm\\test\\Hello D:\\acm\\test\\Hello.c";
        judge.executeShell = "D:\\acm\\test\\Hello.exe";
        judge.dataForNum = "D:\\acm\\test\\data\\1";
        judge.run();
        System.out.println("result: " + judge.result);
        System.out.println("timeused: " + judge.timeUsed);
        System.out.println("memory: " + judge.memory);
        System.out.println("errorInfo: " + judge.errorInfo);
    }
}
