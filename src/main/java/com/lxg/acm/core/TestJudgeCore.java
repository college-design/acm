//package com.lxg.acm.core;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class TestJudgeCore {
//
//	@Before
//	public void init() {
//	}
//
//	@Test
//	public void testJava() {
//		JudgeCore judge = new JudgeCore();
//		judge.compileShell = "C:\\Program Files\\Java\\jdk1.7.0_75\\bin\\javac d:\\Main.java";
//		judge.executeShell = "C:\\Program Files\\Java\\jdk1.7.0_75\\bin\\java -classpath d: Main";
//		judge.dataForNum = "d:\\data\\1065";
//		judge.run();
//		System.out.println("result: " + judge.result);
//		System.out.println("timeused: " + judge.timeUsed);
//		System.out.println("memory: " + judge.memory);
//		System.out.println("errorInfo: " + judge.errorInfo);
//	}
//
//	@Test
//	public void testCpp() {
//		JudgeCore judge = new JudgeCore();
//		judge.compileShell = "E:\\dev\\cfg\\MinGW\\bin\\gcc -o d:\\main d:\\main.c";
//		judge.executeShell = "d:\\main.exe";
//		judge.dataForNum = "d:\\data\\1065";
//		judge.run();
//		System.out.println("result: " + judge.result);
//		System.out.println("timeused: " + judge.timeUsed);
//		System.out.println("memory: " + judge.memory);
//		System.out.println("errorInfo: " + judge.errorInfo);
//	}
//
//}
