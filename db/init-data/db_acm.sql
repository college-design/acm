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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `classifier` */

insert  into `classifier`(`cid`,`title`,`createTime`,`modifyTime`) values (1,'分类1','2017-02-21 20:51:04',NULL),(2,'分类2','2017-02-21 20:51:10',NULL),(3,'分类3','2017-02-21 20:51:19',NULL),(4,'分类4','2017-02-21 20:51:29',NULL),(5,'分类5','2017-02-21 20:51:36',NULL),(6,'分类6','2017-02-21 20:51:42',NULL),(7,'分类7','2017-02-21 20:51:48',NULL),(8,'分类8','2017-02-21 20:51:54',NULL),(9,'分类9','2017-02-21 20:52:01',NULL),(10,'分类10','2017-02-21 20:52:08',NULL),(11,'分类11','2017-02-21 20:52:14',NULL),(12,'分类12','2017-02-21 20:52:21',NULL),(13,'分类13','2017-02-21 20:52:29',NULL),(14,'分类14','2017-02-21 20:52:35',NULL),(15,'分类15','2017-02-21 20:52:42',NULL);

/*Table structure for table `classifier_problem` */

DROP TABLE IF EXISTS `classifier_problem`;

CREATE TABLE `classifier_problem` (
  `cpid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类与问题关联标识',
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `contest` */

insert  into `contest`(`cid`,`title`,`startTime`,`endTime`,`defunct`,`description`,`private`) values (1,'比赛A','2017-01-01 00:00:00','2017-01-07 00:00:00','Y','比赛A',0),(2,'比赛B','2017-01-09 00:00:00','2017-01-12 00:00:00','Y','比赛B',0),(3,'比赛C','2017-01-13 00:00:00','2017-01-18 00:00:00','N','比赛C',0),(4,'比赛D','2017-01-22 00:00:00','2017-01-28 00:00:00','Y','比赛D',0),(5,'比赛E','2017-02-01 00:00:00','2017-02-08 00:00:00','Y','比赛E',0);

/*Table structure for table `contest_problem` */

DROP TABLE IF EXISTS `contest_problem`;

CREATE TABLE `contest_problem` (
  `cpid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '比赛问题关联标识',
  `cid` bigint(20) DEFAULT NULL COMMENT '比赛标识',
  `pid` bigint(20) DEFAULT NULL COMMENT '问题标识',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`cpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `problem` */

insert  into `problem`(`pid`,`title`,`description`,`input`,`output`,`sampleInput`,`sampleOutput`,`hint`,`source`,`sampleCode`,`createTime`,`timeLimit`,`memoryLimit`,`defunct`,`contestId`,`accepted`,`submit`,`ratio`,`error`,`difficulty`,`submitUser`,`solved`) values (1000,'A+B Problem','Calculate a+b 					','Two integer a,b (0<=a,b<=10)						','Output a+b						','1 2						','3						','','POJ','		',NULL,0,0,'N',NULL,0,0,0,0,0,0,0);

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

insert  into `role`(`rid`,`type`,`uid`,`username`) values (1,'root',1,'root'),(2,'admin',10,'admin'),(3,'user',11,'user');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`email`,`submit`,`accepted`,`solved`,`defunct`,`password`,`createtime`,`accesstime`,`nick`,`school`) values (1,'root','1453860636@qq.com',0,0,0,'N','root','2017-02-21 20:41:48',NULL,'root','内蒙古师范大学'),(2,'test01','test01',0,0,0,'N','test01','2017-02-21 20:42:47',NULL,'test01','test01'),(3,'test02','test02',0,0,0,'N','test02','2017-02-21 20:43:00',NULL,'test02','test02'),(4,'test03','test03',0,0,0,'N','test03','2017-02-21 20:43:11',NULL,'test03','test03'),(5,'test04','test04',0,0,0,'N','test04','2017-02-21 20:43:21',NULL,'test04','test04'),(6,'test05','test05',0,0,0,'N','test05','2017-02-21 20:43:31',NULL,'test05','test05'),(7,'test06','test06',0,0,0,'N','test06','2017-02-21 20:43:42',NULL,'test06','test06'),(8,'test07','test06',0,0,0,'N','test06','2017-02-21 20:43:57',NULL,'test06','test06'),(9,'test07','test07',0,0,0,'N','test07','2017-02-21 20:44:17',NULL,'test07','test07'),(10,'admin','admin',0,0,0,'N','admin','2017-02-21 20:53:37',NULL,'admin','admin'),(11,'user','user',0,0,0,'N','user','2017-02-21 20:53:54',NULL,'user','user');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
