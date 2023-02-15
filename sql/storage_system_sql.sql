create database if not exists storage_system;
use storage_system;

-- 设置编码
set names utf8mb4;
-- 禁止检查外键约束
set foreign_key_checks = 0;

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
	is_valid tinyint(1) default 1 comment '账号是否有效(0: 无效; 1: 有效)',
	create_time datetime not null default current_timestamp comment '创建时间',
	update_time datetime not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (id)
);

-- 添加超级管理员账号
insert into t_user (username, password, nickname, age, sex, phone, role_id) values ('admin', 'admin', '超级管理员', 18, 0, '13111111111', 0);
-- 添加管理员账号
insert into t_user (username, password, nickname, age, sex, phone, role_id, is_valid) values 
	('bob', '123456', '鲍勃', 19, 1, '13222222222', 1, 0),
	('carly', '123456', '卡莉', 20, 0, '13333333333', 1, 1),
	('creed', '123456', '克里德', 20, 2, '13444444444', 1, 1);
-- 添加普通用户账号
insert into t_user (username, password, nickname, age, sex, phone) values 
	('denny', '123456', '丹尼', 21, 1, '13555555555'),
	('diana', '123456', '黛安娜', 21, 0, '13666666666'),
	('dick', '123456', '迪克', null, 2, null),
	('irene', '123456', '艾琳', 22, 0, null),
	('ike', '123456', '艾克', 22, 1, null),
	('ina', '123456', '艾娜', 22, 2, null),
	('jalyn', '123456', '杰琳', 23, 0, null),
	('jackson', '123456', '杰克逊', 23, 1, null);

-- ----------------------------
-- 菜单表
-- ----------------------------
drop table if exists t_menu;
create table t_menu (
	id int(11) not null auto_increment comment '主键',
	pid int(11) default 0 comment '父级 id',
	name varchar(16) not null comment '菜单名称',
	path varchar(255) not null comment '菜单路径',
	icon varchar(64) default null comment '菜单图标',
	component varchar(255) default null comment '组件路径',
	level tinyint(4) not null default 1 comment '菜单排序级别',
	role_ids varchar(8) not null comment '菜单所需权限(0: 超级管理员; 1: 管理员; 2: 普通用户), 可使用逗号分割权限(如: "0,1,2")',
	is_valid tinyint(1) default 1 comment '菜单是否生效(0: 开启; 1: 关闭)',
	primary key (id)
);

insert into t_menu (pid, name, path, icon, component, level, role_ids) values 
	(0, '仓库管理', '/storage', 'icon-database', '@/pages/Storage', 1, '0,1'),
	(0, '种类管理', '/kind', 'icon-gold', '@/pages/Kind', 2, '0,1'),
	(0, '物品管理', '/goods', 'icon-tag', './pages/Goods', 3, '0,1,2'),
	(0, '账号管理', '/account', 'icon-team', './pages/Account', 4, '0,1'),
	(0, '菜单管理', '/menu', 'icon-menu', './pages/Menu', 5, '0'),
	(0, '日志记录', '/log', 'icon-book', './pages/Log', 6, '0,1,2'),
	(0, '报表管理', '/report', 'icon-Report', './pages/Report', 7, '0,1,2');
	
insert into t_menu (pid, name, path, icon, component, level, role_ids, is_valid) values 
			(0, '测试页面', '/test', 'icon-icon-test', './test.tsx', 8, '0,2', 0);
-- ----------------------------
-- 仓库表
-- ----------------------------
drop table if exists t_storage;
create table t_storage (
	id int(11) not null auto_increment comment '主键',
	name varchar(128) not null comment '仓库名称',
	remark varchar(1024) default null comment '备注',
	is_valid tinyint(1) default 1 comment '是否生效(0: 关闭, 1: 开启)',
	primary key (id)
);  

insert into t_storage (name, remark) values 
	('北京仓库', '这是北京总部的仓库。'),
	('上海仓库', '这是上海分部的仓库。'),
	('广州仓库', null);


-- ----------------------------
-- 种类表
-- ----------------------------
drop table if exists t_kind;
create table t_kind (
	id int(11) not null auto_increment comment '主键',
	storage_id int(11) default null comment '所属仓库 id',
	name varchar(128) not null comment '种类名称',
	remark varchar(1024) default null comment '备注',
	is_valid tinyint(1) default 1 comment '是否生效(0: 关闭, 1: 开启)',
	primary key (id),
	foreign key (storage_id) references t_storage(id)
);  

insert into t_kind (storage_id, name, remark) values 
	(1, '食品类', '可供人类食用或饮用的物质，包括加工食品，半成品和未加工食品，不包括烟草或只作药品用的物质。'),
	(1, '服饰类', '装饰人体的物品总称，包括服装、鞋、帽、袜子、手套、围巾、领带、配饰、包、伞等。古人用来遮羞，而今人对于新事物的认识不断进步，服饰的材质、款式也多种多样。'),
	(1, '图书类', '图书是以文字或图像的形式记录信息的媒介，通常由许多页（由纸莎草、羊皮纸、牛皮纸或纸制成）装订在一起并用封面保护。'),
	(null, '其它', null);
	

-- ----------------------------
-- 物品表
-- ----------------------------
drop table if exists t_goods;
create table t_goods (
	id int(11) not null auto_increment comment '主键',
	storage_id int(11) default null comment '所属仓库 id',
	kind_id int(11) default null comment '所属种类 id',
	name varchar(128) not null comment '物品名称',
	remark varchar(1024) default null comment '备注',
	img_url  varchar(255) default null comment '物品图片地址',
	price decimal(10, 2) default null comment '物品单价(单位: 元)',
	count int(11) default 0 comment '物品数量',
	is_valid tinyint(1) default 1 comment '是否生效(0: 关闭, 1: 开启)',
	primary key (id),
	foreign key (storage_id) references t_storage(id),
	foreign key (kind_id) references t_kind(id)
);  

insert into t_goods (storage_id, kind_id, name, remark, img_url, price, count) values 
	(1, 1, '三只松鼠黄金肉松饼1000g/箱', '早餐办公室特色小吃休闲零食网红点心', 'https://img10.360buyimg.com/n7/jfs/t1/204964/6/18410/131376/63df8479Fd336a2d5/2b075c9d12111f1e.jpg', 24.90, 30),
	(1, 2, '啄木鸟（TUCANO）', '夹克男2022春秋季休闲韩版外套男士潮流刺绣立领青年工装上衣纯色男装 卡其色 XL', 'https://img14.360buyimg.com/n7/jfs/t1/144069/2/31684/67877/636c9ef8E8fa7e785/032a0f81ad4d3991.jpg', 158.00, 2000),
	(2, 3, '三体全册正版', '刘慈欣全套全集1-2-3 流浪地球2小说书作者 三体典藏版共3册', 'https://img12.360buyimg.com/n1/s200x200_jfs/t1/155915/37/31100/195992/63440475Ea9b5435b/de59a7343b0efa4b.jpg', 55.80, 500),
	(null, null, '未知物品', '此物品质地华美，透彻晶莹，暂不知晓其用途', null, null, null);
	
	
-- ----------------------------
-- 日志表
-- ----------------------------
drop table if exists t_log;
create table t_log (
	id int(11) not null auto_increment comment '主键',
	goods_id int(11) default null comment '物品 id',
	type tinyint(4) default null comment '出、入库状态(0: 出库; 1: 入库)',
	operator_id int(11) default null comment '操作人 id',
	count int(11) not null comment '数量',
	remark varchar(1024) default null comment '备注',
	create_time datetime default current_timestamp comment '操作时间',
	primary key (id),
	foreign key (goods_id) references t_goods(id),
	foreign key (operator_id) references t_user(id)
);  

insert into t_log (goods_id, type, operator_id, count, remark) values 
	(1, 1, 1, 10, '超级管理员「入库」操作：30 ×『「三只松鼠黄金肉松饼1000g/箱」』'),
	(2, 0, 5, 30, '丹尼「出库」操作：30 ×『啄木鸟（TUCANO）』');
	
	
	
