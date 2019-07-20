CREATE DATABASE `littlecatimsdb` DEFAULT CHARACTER SET utf8;

# 系统==============================================================
CREATE TABLE `t_sys_menu` (
	`id` VARCHAR(255) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`targetUrl` VARCHAR(255) NULL,
	`pid` VARCHAR(255) NOT NULL DEFAULT '-1',
	`sortNum` VARCHAR(255) NOT NULL,
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	`isDefault` VARCHAR(1) NULL DEFAULT 'N',
	
	PRIMARY KEY (`id`)
);

CREATE TABLE `t_sys_param` (
	`name` VARCHAR(255) NOT NULL,
	`value` VARCHAR(255) NOT NULL,
	
	PRIMARY KEY (`name`)
);


CREATE TABLE `t_sys_sysoperator` (
	`id` VARCHAR(255) NOT NULL,
	`username` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`name` VARCHAR(255) NULL,
	`remark` VARCHAR(255) NULL,
	`isboss` VARCHAR(1) NULL DEFAULT 'N',
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `username` (`username`)
);


#字典
CREATE TABLE `t_basic_dicType` (
	`id` VARCHAR(255) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`sortid` INT NOT NULL,
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`)
);

CREATE TABLE `t_basic_dicContent` (
	`id` VARCHAR(255) NOT NULL,
	`typeid` VARCHAR(255) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`sortid` INT NOT NULL,
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`typeid`,`name`)
);


CREATE TABLE `t_student` (
	`id` VARCHAR(125) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`mobile` VARCHAR(50) NULL,
	`xuexiao` VARCHAR(125) NULL DEFAULT '-1',
	`nianji` VARCHAR(125) NULL DEFAULT '-1',
	`banji` VARCHAR(125) NULL DEFAULT '-1',
	`xiaoqu` VARCHAR(125) NULL DEFAULT '-1',
	`tuijianren` VARCHAR(125) NULL,
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	`remark` VARCHAR(255) NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`)
);

##################################基础信息

CREATE TABLE `t_kecheng` (
	`id` VARCHAR(125) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`teacher` VARCHAR(255) NULL,
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	`remark` VARCHAR(255) NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`)
);

CREATE TABLE `t_kecheng_student` (
	`id` VARCHAR(125) NOT NULL,
	`kecheng` VARCHAR(255) NOT NULL,
	`student` VARCHAR(255) NULL,
	`remaintimes` INT NULL,
	`remark` VARCHAR(255) NULL,
	`state` VARCHAR(255) NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`)
);
drop table t_kecheng_timesrecord;
CREATE TABLE `t_kecheng_timesrecord` (
	`id` VARCHAR(125) NOT NULL,
	`kecheng` VARCHAR(255) NOT NULL,
	`student` VARCHAR(255) NULL,
	`year` int NULL,
	`month` int NULL,
	`day` int NULL,
	`remark` VARCHAR(255) NULL,
	`operator` VARCHAR(255) NOT NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`)
);

##交费记录
CREATE TABLE `t_kecheng_payrecord` (
	`id` VARCHAR(125) NOT NULL,
	`student` VARCHAR(255) NULL,
	`kecheng` VARCHAR(255) NULL,
	`fee` INT NULL,
	`times` INT NULL,
	`remark` VARCHAR(255) NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`)
);

##personal#############################################################
##沟通记录
CREATE TABLE `t_personal_goutongjilu` (
	`id` VARCHAR(255) NOT NULL,
	`studentName` VARCHAR(255) NOT NULL,
	`topic` VARCHAR(255) NOT NULL,
	`content` VARCHAR(255) NOT NULL,
	`laterplan` VARCHAR(255) NULL,
	`remark` VARCHAR(255) NULL,
	`state` VARCHAR(10) NOT NULL DEFAULT '1',
	`operator` VARCHAR(255) NOT NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);
