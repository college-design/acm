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
  `cid` int(11) NOT NULL DEFAULT '0',
  `accepts` int(11) DEFAULT '0' COMMENT '通过数',
  `penalty` int(11) DEFAULT '0',
  `A_time` int(11) DEFAULT '0',
  `A_WrongSubmits` int(11) DEFAULT '0',
  `B_time` int(11) DEFAULT '0',
  `B_WrongSubmits` int(11) DEFAULT '0',
  `C_time` int(11) DEFAULT '0',
  `C_WrongSubmits` int(11) DEFAULT '0',
  `D_time` int(11) DEFAULT '0',
  `D_WrongSubmits` int(11) DEFAULT '0',
  `E_time` int(11) DEFAULT '0',
  `E_WrongSubmits` int(11) DEFAULT '0',
  `F_time` int(11) DEFAULT '0',
  `F_WrongSubmits` int(11) DEFAULT '0',
  `G_time` int(11) DEFAULT '0',
  `G_WrongSubmits` int(11) DEFAULT '0',
  `H_time` int(11) DEFAULT '0',
  `H_WrongSubmits` int(11) DEFAULT '0',
  `I_time` int(11) DEFAULT '0',
  `I_WrongSubmits` int(11) DEFAULT '0',
  `J_time` int(11) DEFAULT '0',
  `J_WrongSubmits` int(11) DEFAULT '0',
  `K_time` int(11) DEFAULT '0',
  `K_WrongSubmits` int(11) DEFAULT '0',
  `L_time` int(11) DEFAULT '0',
  `L_WrongSubmits` int(11) DEFAULT '0',
  `M_time` int(11) DEFAULT '0',
  `M_WrongSubmits` int(11) DEFAULT '0',
  `N_time` int(11) DEFAULT '0',
  `N_WrongSubmits` int(11) DEFAULT '0',
  `O_time` int(11) DEFAULT '0',
  `O_WrongSubmits` int(11) DEFAULT '0',
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `classifier` */

/*Table structure for table `classifier_problem` */

DROP TABLE IF EXISTS `classifier_problem`;

CREATE TABLE `classifier_problem` (
  `cpid` bigint(20) NOT NULL COMMENT '分类与问题关联标识',
  `pid` bigint(20) DEFAULT NULL COMMENT '问题标识',
  `cid` bigint(20) DEFAULT NULL COMMENT '分类标识',
  PRIMARY KEY (`cpid`),
  KEY `cid` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `classifier_problem` */

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
  `private` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `contest` */

/*Table structure for table `contest_problem` */

DROP TABLE IF EXISTS `contest_problem`;

CREATE TABLE `contest_problem` (
  `cpid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛问题关联标识',
  `cid` bigint(20) DEFAULT NULL COMMENT '比赛标识',
  `pid` bigint(20) DEFAULT NULL COMMENT '问题标识',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`cpid`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `contest_problem` */

/*Table structure for table `link` */

DROP TABLE IF EXISTS `link`;

CREATE TABLE `link` (
  `id` int(11) DEFAULT NULL COMMENT '链接标识',
  `name` varchar(150) DEFAULT NULL COMMENT '链接名称',
  `url` varchar(150) DEFAULT NULL COMMENT '链接地址',
  `type` varchar(150) DEFAULT NULL COMMENT '链接类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `link` */

insert  into `link`(`id`,`name`,`url`,`type`) values (1,'浙大acm','http://www.zju.edu.cn/','ACM'),(2,'杭电acm','http://acm.hdu.edu.cn/','ACM'),(3,'北大oj','http://poj.org/','oj'),(4,'南阳理工学院ACM','http://acm.nyist.net/JudgeOnline/problemset.php','oj'),(5,'九度Online Judge','http://ac.jobdu.com/index.php','oj'),(6,'神技大学的oj','http://acm.uestc.edu.cn/#/','oj'),(7,'leetcode','https://leetcode.com/','--'),(10,'vvvvvv','http://a.com','vvvvvv'),(11,'vvvvvv','http://liuxuegang.xyz','vzvz'),(12,'bbbbbbb','http://liuxuegang.xyz','b'),(13,'bbxbxb','http://liuxuegang.xyz','xbxb');

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
) ENGINE=InnoDB AUTO_INCREMENT=1093 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `problem` */

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
) ENGINE=MyISAM AUTO_INCREMENT=4096 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5197 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=2668 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
