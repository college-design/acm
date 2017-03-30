package com.lxg.acm.core;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/30.
 */
public class TestJudgeCore {

    @Before
    public void init() {
    }

    @Test
    public void testJava() {
        JudgeCore judge = new JudgeCore();
        judge.compileShell = "D:\\acm\\test\\javac D:\\acm\\test\\Main.java";
        judge.executeShell = "D:\\acm\\test\\java -classpath D:\\acm\\test\\Main";
        judge.dataForNum = "D:\\acm\\test\\1";
        judge.run();
        System.out.println("result: " + judge.result);
        System.out.println("timeused: " + judge.timeUsed);
        System.out.println("memory: " + judge.memory);
        System.out.println("errorInfo: " + judge.errorInfo);
    }

    @Test
    public void testCpp() {
        JudgeCore judge = new JudgeCore();
        judge.compileShell = "D:\\acm\\test\\gcc -o D:\\acm\\test\\Hello D:\\acm\\test\\Hello.c";
        judge.executeShell = "D:\\acm\\test\\Hello.exe";
        judge.dataForNum = "D:\\acm\\test\\1";
        judge.run();
        System.out.println("result: " + judge.result);
        System.out.println("timeused: " + judge.timeUsed);
        System.out.println("memory: " + judge.memory);
        System.out.println("errorInfo: " + judge.errorInfo);
    }
}
