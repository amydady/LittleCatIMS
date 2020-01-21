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

#员工请假记录
CREATE TABLE `t_staff_leaverecord` (
	`id` VARCHAR(255) NOT NULL,
	`staff` VARCHAR(255) NOT NULL,
	`leavedate` DATE NOT NULL,
	`remark` VARCHAR(255) NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`state` VARCHAR(1) NOT NULL DEFAULT '1',
	
	PRIMARY KEY (`id`)
);

#签到记录
CREATE TABLE `t_sys_qiandao` (
	`id` VARCHAR(255) NOT NULL,
	`userid` VARCHAR(255) NOT NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`)
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
	`tuijianren` VARCHAR(125) NULL DEFAULT '-1',
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	`needyongcan` VARCHAR(1) NOT NULL DEFAULT 'N',
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
	`needremind` VARCHAR(1) NOT NULL DEFAULT 'Y',
	`remark` VARCHAR(255) NULL,
	`shangkeshijian` VARCHAR(255) NULL,
	
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`)
);

CREATE TABLE `t_kecheng_student` (
	`id` VARCHAR(125) NOT NULL,
	`kecheng` VARCHAR(255) NOT NULL,
	`student` VARCHAR(255) NULL,
	`remaintimes` INT NULL,
	`state` VARCHAR(255) NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`),
	UNIQUE INDEX `kecheng_student` (`kecheng`, `student`)
);
drop table t_kecheng_timesrecord;
CREATE TABLE `t_kecheng_timesrecord` (
	`id` VARCHAR(125) NOT NULL,
	`kecheng` VARCHAR(255) NOT NULL,
	`student` VARCHAR(255) NULL,
	`year` VARCHAR(50) NULL,
	`month` VARCHAR(50) NULL,
	`day` VARCHAR(50) NULL,
	`starttime` VARCHAR(50) NULL,
	`remark` VARCHAR(255) NULL,
	`operator` VARCHAR(255) NOT NULL,
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`kecheng`,`student`,`year`,`month`,`day`,`starttime`)
);

#课程表
CREATE TABLE `t_kecheng_kechengbiao` (
	`id` VARCHAR(125) NOT NULL,
	`kecheng` VARCHAR(255) NOT NULL,
	`rulebegindate` VARCHAR(255) NOT NULL,
	`ruleenddate` VARCHAR(255) NOT NULL,
	`begindate` VARCHAR(255)  NULL,
	`enddate` VARCHAR(255)  NULL,
	`shangkedate` VARCHAR(255)  NULL,
	`cycle` VARCHAR(1) NULL,
	`timebegin` TIME NULL,
	`timeend` TIME NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`)
);

#学生用餐记录
#drop table t_yongcan_timesrecord;
CREATE TABLE `t_yongcan_timesrecord` (
	`id` VARCHAR(125) NOT NULL,
	`student` VARCHAR(255) NOT NULL,
	`year` VARCHAR(50) NOT NULL,
	`month` VARCHAR(50) NOT NULL,
	`day` VARCHAR(50) NOT NULL,
	`operator` VARCHAR(255) NOT NULL,
	`remark` VARCHAR(255) NULL,
	`state` VARCHAR(2) NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`student`,`year`,`month`,`day`)
);


#员工信息
drop table t_staff;
CREATE TABLE `t_staff` (
	`id` VARCHAR(255) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`mobile` VARCHAR(255)  NULL,
	`idcard` VARCHAR(255)  NULL,
	`worktype` VARCHAR(5)  NULL,
	`sex` VARCHAR(5) NULL,
	`age` int NULL,
	`biyexuexiao` VARCHAR(255)  NULL,
	`biyeshijian` DATE NULL,
	`xueli` VARCHAR(255)  NULL,
	`zhuanye` VARCHAR(255)  NULL,
	`techangjirongyu` VARCHAR(1024) NULL,
	`joindate` DATE  NULL,
	`shiyongqi` int NULL,
	`zhuanzhengdate` DATE NULL,
	`shiyonggongzi` int NULL,
	`zhengshigongzi` int NULL,
	`keshigongzi` VARCHAR(1024) NULL,
	`baoxianxinxi` VARCHAR(1024) NULL,
	`remark` VARCHAR(255) NULL,
	`leavedate` DATE NULL,
	`state` VARCHAR(5) NOT NULL,
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
	`paydate` DATE  NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`)
);

##结转记录
CREATE TABLE `t_kecheng_jiezhuanrecord` (
	`id` VARCHAR(125) NOT NULL,
	`student` VARCHAR(255) NULL,
	`kechengs` VARCHAR(255) NULL,
	`kechengd` VARCHAR(255) NULL,
	`times` INT NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`operator` VARCHAR(255) NOT NULL,
	
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
