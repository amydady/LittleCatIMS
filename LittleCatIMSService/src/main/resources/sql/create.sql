CREATE DATABASE `littlecatimsdb` ;

CREATE TABLE `t_student` (
	`id` VARCHAR(125) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`sex` VARCHAR(10) NULL,
	`birthdayYear` VARCHAR(10) NULL,
	`birthdayMonth` VARCHAR(10) NULL,
	`birthdayDay` VARCHAR(10) NULL,
	`babaName` VARCHAR(50) NULL,
	`babaMobile` VARCHAR(50) NULL,
	`mamaName` VARCHAR(50) NULL,
	`mamaMobile` VARCHAR(50) NULL,
	`xuexiao` VARCHAR(125) NULL,
	`nianji` VARCHAR(125) NULL,
	`banji` VARCHAR(125) NULL,
	`xiaoqu` VARCHAR(125) NULL,
	`hobby` VARCHAR(255) NULL,
	`remark` VARCHAR(255) NULL,
	`createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (`id`)
);

##################################基础信息
#小区
CREATE TABLE `t_basic_xiaoqu` (
	`id` VARCHAR(125) NOT NULL,
	`area` VARCHAR(255) NULL,
	`name` VARCHAR(255) NOT NULL,
	`code` VARCHAR(255) NOT NULL,
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	
	PRIMARY KEY (`id`),
	UNIQUE INDEX `name` (`name`, `area`)
);

#学校
CREATE TABLE `t_basic_xuexiao` (
	`id` VARCHAR(125) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`enable` VARCHAR(1) NOT NULL DEFAULT 'Y',
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`)
);

#年级
CREATE TABLE `t_basic_nianji` (
	`id` VARCHAR(125) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`)
);

#班级
CREATE TABLE `t_basic_banji` (
	`id` VARCHAR(125) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`)
);

