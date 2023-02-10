create database if not exists storage_system;

use storage_system;

set names utf8mb4;

-- ----------------------------
-- 用户表
-- ----------------------------
drop table if exists t_user;
create table t_user (
	id int(11) not null auto_increment comment '主键',
	username varchar(24) not null comment '用户名',
	password varchar(24) not null comment '密码',
	nickname varchar(255) default null comment '昵称',
	age int(11) default null comment '年龄',
	sex tinyint(4) default 2 comment '性别(0: 女; 1: 男; 2: 未知)',
	phone char(11) default null comment '手机号码',
	role_id tinyint(4) default 2 comment '用户权限(0: 超级管理员; 1: 管理员; 2: 普通账号)',
	is_valid tinyint(4) default 1 comment '账号是否有效(0: 无效; 1: 有效)',
	primary key (id)
);

-- 添加管理员账号
insert into t_user (username, password, nickname, age, sex, phone, role_id) values ('admin', 'admin', '超级管理员', 18, 0, '13111111111', 0);

