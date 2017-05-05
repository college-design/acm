/*
SQLyog v10.2 
MySQL - 5.5.45 : Database - db_acm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_acm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_acm`;

/*Table structure for table `attend` */

DROP TABLE IF EXISTS `attend`;

CREATE TABLE `attend` (
  `uid` char(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '用户标识',
  `cid` int(11) NOT NULL DEFAULT '0' COMMENT '比赛标识',
  `accepts` int(11) DEFAULT '0' COMMENT '通过总数',
  `penalty` int(11) DEFAULT '0' COMMENT '提交错误加时',
  `A_time` int(11) DEFAULT '0' COMMENT 'A语言时间',
  `A_WrongSubmits` int(11) DEFAULT '0' COMMENT 'A提交错误次数',
  `B_time` int(11) DEFAULT '0' COMMENT 'B语言时间',
  `B_WrongSubmits` int(11) DEFAULT '0' COMMENT 'B提交错误次数',
  `C_time` int(11) DEFAULT '0' COMMENT 'C语言时间',
  `C_WrongSubmits` int(11) DEFAULT '0' COMMENT 'C提交错误次数',
  `D_time` int(11) DEFAULT '0' COMMENT 'D语言时间',
  `D_WrongSubmits` int(11) DEFAULT '0' COMMENT 'D提交错误次数',
  `E_time` int(11) DEFAULT '0' COMMENT 'E语言时间',
  `E_WrongSubmits` int(11) DEFAULT '0' COMMENT 'E提交错误次数',
  `F_time` int(11) DEFAULT '0' COMMENT 'F语言时间',
  `F_WrongSubmits` int(11) DEFAULT '0' COMMENT 'F提交错误次数',
  `G_time` int(11) DEFAULT '0' COMMENT 'G语言时间',
  `G_WrongSubmits` int(11) DEFAULT '0' COMMENT 'G提交错误次数',
  `H_time` int(11) DEFAULT '0' COMMENT 'H语言时间',
  `H_WrongSubmits` int(11) DEFAULT '0' COMMENT 'H提交错误次数',
  `I_time` int(11) DEFAULT '0' COMMENT 'I语言时间',
  `I_WrongSubmits` int(11) DEFAULT '0' COMMENT 'I提交错误次数',
  `J_time` int(11) DEFAULT '0' COMMENT 'J语言时间',
  `J_WrongSubmits` int(11) DEFAULT '0' COMMENT 'J提交错误次数',
  `K_time` int(11) DEFAULT '0' COMMENT 'K语言时间',
  `K_WrongSubmits` int(11) DEFAULT '0' COMMENT 'K提交错误次数',
  `L_time` int(11) DEFAULT '0' COMMENT 'L语言时间',
  `L_WrongSubmits` int(11) DEFAULT '0' COMMENT 'L提交错误次数',
  `M_time` int(11) DEFAULT '0' COMMENT 'M语言时间',
  `M_WrongSubmits` int(11) DEFAULT '0' COMMENT 'M提交错误次数',
  `N_time` int(11) DEFAULT '0' COMMENT 'N语言时间',
  `N_WrongSubmits` int(11) DEFAULT '0' COMMENT 'N提交错误次数',
  `O_time` int(11) DEFAULT '0' COMMENT 'O语言时间',
  `O_WrongSubmits` int(11) DEFAULT '0' COMMENT 'O提交错误次数',
  `nick` char(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  PRIMARY KEY (`uid`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `attend` */

/*Table structure for table `classifier` */

DROP TABLE IF EXISTS `classifier`;

CREATE TABLE `classifier` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类标识',
  `title` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '分类标题',
  `createTime` datetime DEFAULT NULL COMMENT '分类创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '分类修改时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `classifier` */

insert  into `classifier`(`cid`,`title`,`createTime`,`modifyTime`) values (1,'分类1','2017-05-05 11:01:39','2017-05-05 11:01:39'),(2,'分类2','2017-05-05 11:01:39','2017-05-05 11:01:39'),(3,'分类3','2017-05-05 11:01:39','2017-05-05 11:01:39'),(4,'分类4','2017-05-05 11:01:40','2017-05-05 11:01:40'),(5,'分类5','2017-05-05 11:01:40','2017-05-05 11:01:40');

/*Table structure for table `classifier_problem` */

DROP TABLE IF EXISTS `classifier_problem`;

CREATE TABLE `classifier_problem` (
  `cpid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类与问题关联标识',
  `pid` bigint(20) DEFAULT NULL COMMENT '问题标识',
  `cid` bigint(20) DEFAULT NULL COMMENT '分类标识',
  PRIMARY KEY (`cpid`),
  KEY `cid` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `classifier_problem` */

insert  into `classifier_problem`(`cpid`,`pid`,`cid`) values (1,1,1),(2,2,1),(3,3,1),(4,4,2),(5,5,2),(6,6,3),(7,7,3),(8,8,4),(9,9,5);

/*Table structure for table `code` */

DROP TABLE IF EXISTS `code`;

CREATE TABLE `code` (
  `sid` bigint(20) NOT NULL DEFAULT '0' COMMENT '代码标识',
  `code` text COLLATE utf8_unicode_ci COMMENT '代码',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `code` */

/*Table structure for table `contest` */

DROP TABLE IF EXISTS `contest`;

CREATE TABLE `contest` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛标识',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '比赛标题',
  `startTime` datetime DEFAULT NULL COMMENT '开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '结束时间',
  `defunct` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `description` text COLLATE utf8_unicode_ci COMMENT '比赛描述',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `contest` */

insert  into `contest`(`cid`,`title`,`startTime`,`endTime`,`defunct`,`description`) values (1,'比赛1','2017-05-05 11:01:40','2017-05-05 11:01:40','N','这是比赛1的描述'),(2,'比赛2','2017-05-05 11:01:40','2017-05-05 11:01:40','N','这是比赛2的描述'),(3,'比赛3','2017-05-05 11:01:40','2017-05-05 11:01:40','N','这是比赛3的描述'),(4,'比赛4','2017-05-05 11:01:40','2017-05-05 11:01:40','N','这是比赛4的描述'),(5,'比赛5','2017-05-05 11:01:40','2017-05-05 11:01:40','N','这是比赛5的描述');

/*Table structure for table `contest_problem` */

DROP TABLE IF EXISTS `contest_problem`;

CREATE TABLE `contest_problem` (
  `cpid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛问题关联标识',
  `cid` bigint(20) DEFAULT NULL COMMENT '比赛标识',
  `pid` bigint(20) DEFAULT NULL COMMENT '问题标识',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`cpid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `contest_problem` */

insert  into `contest_problem`(`cpid`,`cid`,`pid`,`title`,`num`) values (1,1,1,'aa',1),(2,1,2,'bb',2),(3,1,3,'cc',3),(4,2,4,'a',1),(5,3,5,'aa',1),(6,4,8,'aa',1),(7,5,9,'aaa',1);

/*Table structure for table `link` */

DROP TABLE IF EXISTS `link`;

CREATE TABLE `link` (
  `id` int(11) DEFAULT NULL COMMENT '链接标识',
  `name` varchar(150) DEFAULT NULL COMMENT '链接名称',
  `url` varchar(150) DEFAULT NULL COMMENT '链接地址',
  `type` varchar(150) DEFAULT NULL COMMENT '链接类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `link` */

insert  into `link`(`id`,`name`,`url`,`type`) values (1,'浙江大学ACM','http://www.zju.edu.cn/','学校ACM'),(2,'杭州科技大学ACM','http://acm.hdu.edu.cn/','学校ACM'),(3,'北京大学ACM','http://poj.org/','大学ACM'),(4,'南阳理工学院ACM','http://acm.nyist.net/JudgeOnline/problemset.php','oj'),(5,'九度Online Judge','http://ac.jobdu.com/index.php','oj'),(6,'神技大学的oj','http://acm.uestc.edu.cn/#/','oj');

/*Table structure for table `problem` */

DROP TABLE IF EXISTS `problem`;

CREATE TABLE `problem` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '问题标识',
  `title` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '问题标题',
  `description` text COLLATE utf8_unicode_ci COMMENT '问题描述',
  `input` text COLLATE utf8_unicode_ci COMMENT '问题输入描述',
  `output` text COLLATE utf8_unicode_ci COMMENT '问题输出描述',
  `sampleInput` text COLLATE utf8_unicode_ci COMMENT '问题实例输入',
  `sampleOutput` text COLLATE utf8_unicode_ci COMMENT '问题实例输出',
  `hint` text COLLATE utf8_unicode_ci,
  `source` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '来源',
  `sampleCode` text COLLATE utf8_unicode_ci COMMENT '实例代码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `timeLimit` int(11) NOT NULL DEFAULT '0' COMMENT '运行时间最大限制',
  `memoryLimit` int(11) NOT NULL DEFAULT '0' COMMENT '运行大小最大限制',
  `defunct` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `contestId` bigint(20) DEFAULT NULL COMMENT '比赛标识',
  `accepted` int(11) DEFAULT '0' COMMENT '通过总数',
  `submit` int(11) DEFAULT '0' COMMENT '提交总数',
  `ratio` float NOT NULL DEFAULT '0' COMMENT '通过与提交比例',
  `error` int(11) DEFAULT '0',
  `difficulty` tinyint(4) NOT NULL DEFAULT '0',
  `submitUser` bigint(20) DEFAULT '0',
  `solved` int(11) DEFAULT '0',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `problem` */

insert  into `problem`(`pid`,`title`,`description`,`input`,`output`,`sampleInput`,`sampleOutput`,`hint`,`source`,`sampleCode`,`createTime`,`timeLimit`,`memoryLimit`,`defunct`,`contestId`,`accepted`,`submit`,`ratio`,`error`,`difficulty`,`submitUser`,`solved`) values (1,'A+B Problem','Calculate a+b 						','Two integer a,b (0<=a,b<=10)			','Output a+b						','1 2						','3						','Q: Where are the input and the output?  A: Your program shall always <font color=red>read input from stdin (Standard Input) and write output to stdout (Standard Output)</font>. For example, you can use \'scanf\' in C or \'cin\' in C++ to read from stdin, and use \'printf\' in C or \'cout\' in C++ to write to stdout.  You <font color=red>shall not output any extra data</font> to standard output other than that required by the problem, otherwise you will get a \"Wrong Answer\".  User programs are not allowed to open and read from/write to files. You will get a \"Runtime Error\" or a \"Wrong Answer\" if you try to do so.   Here is a sample solution for problem 1000 using C++/G++: <pre> #include <iostream> using namespace std; int  main() {     int a,b;     cin >> a >> b;     cout << a+b << endl;     return 0; }</pre> It\'s important that the return type of main() must be int when you use G++/GCC,or you may get compile error.  Here is a sample solution for problem 1000 using C/GCC: <pre> #include <stdio.h>  int main() {     int a,b;     scanf(\"%d %d\",&a, &b);     printf(\"%d\\n\",a+b);     return 0; }</pre> Here is a sample solution for problem 1000 using PASCAL: <pre> program p1000(Input,Output);  var    a,b:Integer;  begin     Readln(a,b);     Writeln(a+b);  end.</pre> Here is a sample solution for problem 1000 using JAVA:  Now java compiler is jdk 1.5, next is program for 1000 <pre> import java.io.*; import java.util.*; public class Main {             public static void main(String args[]) throws Exception             {                     Scanner cin=new Scanner(System.in);                     int a=cin.nextInt(),b=cin.nextInt();                     System.out.println(a+b);             } }</pre> Old program for jdk 1.4 <pre> import java.io.*; import java.util.*;  public class Main {     public static void main (String args[]) throws Exception     {         BufferedReader stdin =              new BufferedReader(                 new InputStreamReader(System.in));          String line = stdin.readLine();         StringTokenizer st = new StringTokenizer(line);         int a = Integer.parseInt(st.nextToken());         int b = Integer.parseInt(st.nextToken());         System.out.println(a+b);     } }</pre>','POJ','#include<stdio.h>\r\nint main()\r\n{\r\n    int a,b;\r\n    scanf(\"%d %d\",&a, &b);\r\n    printf(\"%d\\n\",a+b);\r\n    return 0;\r\n}						',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(2,'5个数求最值','设计一个从5个整数中取最小数和最大数的程序','输入只有一组测试数据，为五个不大于1万的正整数','输出两个数，第一个为这五个数中的最小值，第二个为这五个数中的最大值，两个数字以空格格开。','1 2 3 4 5','1 5','最后需要换行','POJ','',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(3,'二进制计算','计算两个二进制数的和或差。','输入由两个二进制数和一个运算符组成，二进制数和运算符之间用一个空格分隔，格式如下：\r\n\r\nnum1 op num2\r\n\r\n其中num1和num2为要参与运算的二进制数，二进制数只可能是大于零的无符号整数，且num1>=num2，op为运算符，运算符只可能取+或-；当num1和num2的长度不同时，在长度短的数的左侧补零，比如：\r\n\r\n1000 - 1\r\n\r\n将被视为\r\n\r\n1000 - 0001','运算结果，不能有多余的零。','1011 + 1','1100','','','',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(4,'最小公倍数','求n个数的最小公倍数。						','输入包含多个测试实例，每个测试实例的开始是一个正整数n，然后是n个正整数。						','为每组测试数据输出它们的最小公倍数，每个测试实例的输出占一行。你可以假设最后的输出是一个32位的整数。						','2 4 6\r\n3 2 5 7						','12\r\n70						','','','						',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(5,'A+B(1)','Your task is to Calculate a + b.\r\nToo easy?! Of course! I specially designed the problem for acm beginners. \r\nYou must have found that some problems have the same titles with this one, yes, all these problems were designed for the same aim. ','The input will consist of a series of pairs of integers a and b, separated by a space, one pair of integers per line. ','For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input. ','1 5\r\n10 20','6\r\n30','','','',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(6,'A+B(2)','Your task is to Calculate a + b.','Input contains an integer N in the first line, and then N lines follow. Each line consists of a pair of integers a and b, separated by a space, one pair of integers per line.','For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input. ','2\r\n1 5\r\n10 20','6\r\n30\r\n','','','',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(7,'A+B(3)','Your task is to Calculate the sum of some integers.','Input contains multiple test cases. Each test case contains a integer N, and then N integers follow in the same line. A test case starting with 0 terminates the input and this test case is not to be processed.','For each group of input integers you should output their sum in one line, and with one line of output for each line in input. ','4 1 2 3 4\r\n5 1 2 3 4 5\r\n0 ','10\r\n15','','','',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(8,'A+B(4)','Your task is to Calculate a + b.','Input contains multiple test cases. Each test case contains a pair of integers a and b, one pair of integers per line. A test case containing 0 0 terminates the input and this test case is not to be processed.','For each pair of input integers a and b you should output the sum of a and b in one line, and with one line of output for each line in input. ','1 5\r\n10 20\r\n0 0','6\r\n30','','','',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(9,'A+B(5)','Your task is to calculate the sum of some integers.\r\n						','Input contains an integer N in the first line, and then N lines follow. Each line starts with a integer M, and then M integers follow in the same line. 						','For each group of input integers you should output their sum in one line, and with one line of output for each line in input. 						','2\r\n4 1 2 3 4\r\n5 1 2 3 4 5\r\n						','10\r\n15						','','','						',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(10,'A+B(6)','Your task is to calculate the sum of some integers.','Input contains multiple test cases, and one case one line. Each case starts with an integer N, and then N integers follow in the same line. ','For each test case you should output the sum of N integers in one line, and with one line of output for each line in input. ','4 1 2 3 4\r\n5 1 2 3 4 5','10\r\n15','','','',NULL,0,0,'N',NULL,0,0,0,0,0,0,0),(11,'A+B(7)','Your task is to Calculate a + b.\r\n','The input will consist of a series of pairs of integers a and b, separated by a space, one pair of integers per line. ','For each pair of input integers a and b you should output the sum of a and b, and followed by a blank line.','1 5\r\n10 20','6\r\n30','','','',NULL,0,0,'N',NULL,0,0,0,0,0,0,0);

/*Table structure for table `problemstatus` */

DROP TABLE IF EXISTS `problemstatus`;

CREATE TABLE `problemstatus` (
  `s_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '问题状态关联标识',
  `s_runid` bigint(20) DEFAULT NULL COMMENT '运行标识',
  `s_uid` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `s_pid` bigint(20) DEFAULT NULL COMMENT '问题标识',
  `s_username` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `s_time` bigint(20) DEFAULT NULL COMMENT '时间',
  `s_memory` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '大小',
  `s_codelength` bigint(20) DEFAULT NULL COMMENT '代码大小',
  `s_language` bigint(20) DEFAULT NULL COMMENT '语言',
  `s_submittime` datetime DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`s_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `problemstatus` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限标识',
  `type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'guest 游客权限，\nuser 用户权限，\nadmin 管理权限，\nroot  最高权限',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `username` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `role` */

insert  into `role`(`rid`,`type`,`uid`,`username`) values (1,'root',1,'test1'),(2,'admin',2,'test2'),(3,'user',3,'test3');

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '运行状态标识',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户标识',
  `pid` bigint(20) DEFAULT NULL COMMENT '问题标识',
  `username` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `result` int(11) DEFAULT NULL COMMENT '结果',
  `time` int(11) DEFAULT NULL COMMENT '运行时间',
  `memory` int(11) DEFAULT NULL COMMENT '运行大小',
  `language` int(11) DEFAULT NULL COMMENT '运行语言',
  `submittime` datetime DEFAULT NULL COMMENT '提交时间',
  `code` text COLLATE utf8_unicode_ci COMMENT '代码',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `status` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户标识',
  `username` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `submit` int(11) DEFAULT '0' COMMENT '用户提交总数',
  `accepted` int(11) DEFAULT '0' COMMENT '用户通过总数',
  `solved` int(11) DEFAULT '0' COMMENT '用户解决总数',
  `defunct` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `password` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `createtime` datetime DEFAULT NULL COMMENT '用户创建时间',
  `accesstime` datetime DEFAULT NULL,
  `nick` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `school` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '学校',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`email`,`submit`,`accepted`,`solved`,`defunct`,`password`,`createtime`,`accesstime`,`nick`,`school`) values (1,'test1','test1@qq.com',0,0,0,'N','test1','2017-05-05 11:01:39',NULL,'测试1','内蒙古师范大学'),(2,'test2','test2@qq.com',0,0,0,'N','test2','2017-05-05 11:01:39',NULL,'测试2','内蒙古师范大学'),(3,'test3','test3@qq.com',0,0,0,'N','test3','2017-05-05 11:01:39',NULL,'测试3','内蒙古师范大学'),(4,'test4','test4@qq.com',0,0,0,'N','test4','2017-05-05 11:01:39',NULL,'测试4','内蒙古师范大学'),(5,'test5','test5@qq.com',0,0,0,'N','test5','2017-05-05 11:01:39',NULL,'测试5','内蒙古师范大学'),(6,'test6','test6@qq.com',0,0,0,'N','test6','2017-05-05 11:01:39',NULL,'测试6','内蒙古师范大学'),(7,'test7','test7@qq.com',0,0,0,'N','test7','2017-05-05 11:01:39',NULL,'测试7','内蒙古师范大学'),(8,'test8','test8@qq.com',0,0,0,'N','test8','2017-05-05 11:01:39',NULL,'测试8','内蒙古师范大学'),(9,'test9','test9@qq.com',0,0,0,'N','test9','2017-05-05 11:01:39',NULL,'测试9','内蒙古师范大学'),(10,'test10','test10@qq.com',0,0,0,'N','test10','2017-05-05 11:01:39',NULL,'测试10','内蒙古师范大学'),(11,'test11','test11@qq.com',0,0,0,'N','test11','2017-05-05 11:01:39',NULL,'测试11','内蒙古师范大学'),(12,'test12','test12@qq.com',0,0,0,'N','test12','2017-05-05 11:01:39',NULL,'测试12','内蒙古师范大学'),(13,'test13','test13@qq.com',0,0,0,'N','test13','2017-05-05 11:01:39',NULL,'测试13','内蒙古师范大学'),(14,'test14','test14@qq.com',0,0,0,'N','test14','2017-05-05 11:01:39',NULL,'测试14','内蒙古师范大学'),(15,'test15','test15@qq.com',0,0,0,'N','test15','2017-05-05 11:01:39',NULL,'测试15','内蒙古师范大学');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
