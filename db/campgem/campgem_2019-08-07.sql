# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 118.31.226.196 (MySQL 5.7.26)
# Database: netoy-boot
# Generation Time: 2019-08-07 01:52:24 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `category_icon` varchar(255) DEFAULT NULL COMMENT '分类图标',
  `category_name` varchar(32) NOT NULL COMMENT '分类名称',
  `category_type` varchar(32) NOT NULL COMMENT '分类类别',
  `evaluation_dimension` varchar(255) DEFAULT NULL COMMENT '评价纬度',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类信息表';



# Dump of table city
# ------------------------------------------------------------

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `country` varchar(32) NOT NULL COMMENT '国家',
  `state` varchar(32) NOT NULL COMMENT '区',
  `city_name` varchar(32) NOT NULL COMMENT '城市名称',
  `post_code` varchar(32) NOT NULL COMMENT '邮政编码',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市信息表';



# Dump of table club
# ------------------------------------------------------------

DROP TABLE IF EXISTS `club`;

CREATE TABLE `club` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `category_id` varchar(32) NOT NULL COMMENT '社团所属分类',
  `club_icon` varchar(255) NOT NULL COMMENT '社团图标',
  `club_name` varchar(32) NOT NULL COMMENT '社团名称',
  `information` varchar(255) NOT NULL COMMENT '社团简介',
  `creator_id` varchar(32) NOT NULL COMMENT '社团创建人',
  `university_id` varchar(32) NOT NULL COMMENT '所属大学',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社团信息表';



# Dump of table club_activity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `club_activity`;

CREATE TABLE `club_activity` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `club_id` varchar(32) DEFAULT NULL COMMENT '社团ID',
  `activity_content` text NOT NULL COMMENT '社团活动内容',
  `activity_date` date NOT NULL COMMENT '活动日期',
  `activity_duration` varchar(255) NOT NULL COMMENT '活动持续时间',
  `activity_start_time` datetime NOT NULL COMMENT '活动开始时间',
  `activity_end_time` datetime NOT NULL COMMENT '活动结束时间',
  `activity_title` varchar(255) NOT NULL COMMENT '活动标题',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社团活动信息表';



# Dump of table club_member
# ------------------------------------------------------------

DROP TABLE IF EXISTS `club_member`;

CREATE TABLE `club_member` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `club_id` varchar(32) NOT NULL COMMENT '社团ID',
  `member_id` varchar(32) NOT NULL COMMENT '会员ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社团与会员关系表';



# Dump of table member
# ------------------------------------------------------------

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `user_base_id` varchar(32) NOT NULL COMMENT '用户基础信息ID',
  `city_id` varchar(32) DEFAULT NULL COMMENT '城市ID',
  `university_id` varchar(32) DEFAULT NULL COMMENT '学校ID',
  `member_name` varchar(255) NOT NULL COMMENT '会员名称',
  `member_type` varchar(255) NOT NULL COMMENT '会员类型',
  `business_date` varchar(255) DEFAULT NULL COMMENT '营业日期',
  `business_name` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `opening_time` datetime DEFAULT NULL COMMENT '营业开始时间',
  `closing_time` datetime DEFAULT NULL COMMENT '营业结束时间',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `admission_date` datetime DEFAULT NULL COMMENT '入学时间',
  `graduation_date` datetime DEFAULT NULL COMMENT '毕业时间',
  `pay_pal` varchar(255) DEFAULT NULL COMMENT '支付账号',
  `extend_1` varchar(255) DEFAULT NULL COMMENT '拓展字段1',
  `extend_2` varchar(255) DEFAULT NULL COMMENT '拓展字段2',
  `extend_3` varchar(255) DEFAULT NULL COMMENT '拓展字段3',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员信息表';



# Dump of table post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `post_icon` varchar(255) NOT NULL COMMENT '版块图片',
  `post_name` varchar(255) NOT NULL COMMENT '版块名称',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版块信息表';



# Dump of table post_moderator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `post_moderator`;

CREATE TABLE `post_moderator` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `post_id` varchar(32) NOT NULL COMMENT '版块ID',
  `moderator_id` varchar(32) NOT NULL COMMENT '管理员会员ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版块与版块管理员关系表';



# Dump of table reply
# ------------------------------------------------------------

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `topic_id` varchar(32) DEFAULT NULL COMMENT '话题ID',
  `floor_number` int(11) NOT NULL COMMENT '楼层',
  `reply_content` text NOT NULL COMMENT '回复内容',
  `reply_time` datetime NOT NULL COMMENT '回复时间',
  `dislike_count` int(11) DEFAULT '0' COMMENT '点赞数量',
  `like_count` int(11) DEFAULT '0' COMMENT '踩一踩数量',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题回复信息表';



# Dump of table sys_data_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_data_log`;

CREATE TABLE `sys_data_log` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `data_id` varchar(32) DEFAULT NULL COMMENT '数据ID',
  `data_version` int(11) DEFAULT NULL COMMENT '版本号',
  `data_table` varchar(32) DEFAULT NULL COMMENT '表名',
  `data_content` text COMMENT '数据内容',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人登录名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人登录名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `sindex` (`data_table`,`data_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sys_dict
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL,
  `type` int(1) unsigned zerofill DEFAULT '0' COMMENT '字典类型0为string,1为number',
  `dict_name` varchar(100) DEFAULT NULL COMMENT '字典名称',
  `dict_code` varchar(100) DEFAULT NULL COMMENT '字典编码',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `indextable_dict_code` (`dict_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sys_dict_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dict_item`;

CREATE TABLE `sys_dict_item` (
  `id` varchar(32) NOT NULL,
  `dict_id` varchar(32) DEFAULT NULL COMMENT '字典id',
  `item_text` varchar(100) DEFAULT NULL COMMENT '字典项文本',
  `item_value` varchar(100) DEFAULT NULL COMMENT '字典项值',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `sort_order` int(10) DEFAULT NULL COMMENT '排序',
  `status` int(11) DEFAULT NULL COMMENT '状态（1启用 0不启用）',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_table_dict_id` (`dict_id`) USING BTREE,
  KEY `index_table_sort_order` (`sort_order`) USING BTREE,
  KEY `index_table_dict_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sys_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` varchar(32) NOT NULL,
  `log_type` int(2) DEFAULT NULL COMMENT '日志类型（1登录日志，2操作日志）',
  `log_content` varchar(1000) DEFAULT NULL COMMENT '日志内容',
  `operate_type` int(2) DEFAULT NULL COMMENT '操作类型',
  `userid` varchar(32) DEFAULT NULL COMMENT '操作用户账号',
  `username` varchar(100) DEFAULT NULL COMMENT '操作用户名称',
  `ip` varchar(100) DEFAULT NULL COMMENT 'IP',
  `method` varchar(500) DEFAULT NULL COMMENT '请求java方法',
  `request_url` varchar(255) DEFAULT NULL COMMENT '请求路径',
  `request_param` text COMMENT '请求参数',
  `request_type` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `cost_time` bigint(20) DEFAULT NULL COMMENT '耗时',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_table_userid` (`userid`) USING BTREE,
  KEY `index_logt_ype` (`log_type`) USING BTREE,
  KEY `index_operate_type` (`operate_type`) USING BTREE,
  KEY `index_log_type` (`log_type`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统日志表';



# Dump of table sys_quartz_job
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_quartz_job`;

CREATE TABLE `sys_quartz_job` (
  `id` varchar(32) NOT NULL,
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `job_class_name` varchar(255) DEFAULT NULL COMMENT '任务类名',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `parameter` varchar(255) DEFAULT NULL COMMENT '参数',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(1) DEFAULT NULL COMMENT '状态 0正常 -1停止',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



# Dump of table sys_sms
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_sms`;

CREATE TABLE `sys_sms` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `es_title` varchar(100) DEFAULT NULL COMMENT '消息标题',
  `es_type` varchar(1) DEFAULT NULL COMMENT '发送方式：1短信 2邮件 3微信',
  `es_receiver` varchar(50) DEFAULT NULL COMMENT '接收人',
  `es_param` varchar(1000) DEFAULT NULL COMMENT '发送所需参数Json格式',
  `es_content` longtext COMMENT '推送内容',
  `es_send_time` datetime DEFAULT NULL COMMENT '推送时间',
  `es_send_status` varchar(1) DEFAULT NULL COMMENT '推送状态 0未推送 1推送成功 2推送失败 -1失败不再发送',
  `es_send_num` int(11) DEFAULT NULL COMMENT '发送次数 超过5次不再发送',
  `es_result` varchar(255) DEFAULT NULL COMMENT '推送失败原因',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人登录名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人登录名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `index_type` (`es_type`) USING BTREE,
  KEY `index_receiver` (`es_receiver`) USING BTREE,
  KEY `index_sendtime` (`es_send_time`) USING BTREE,
  KEY `index_status` (`es_send_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sys_sms_template
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_sms_template`;

CREATE TABLE `sys_sms_template` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `template_name` varchar(50) DEFAULT NULL COMMENT '模板标题',
  `template_code` varchar(32) NOT NULL COMMENT '模板CODE',
  `template_type` varchar(1) NOT NULL COMMENT '模板类型：1短信 2邮件 3微信',
  `template_content` varchar(1000) NOT NULL COMMENT '模板内容',
  `template_test_json` varchar(1000) DEFAULT NULL COMMENT '模板测试json',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人登录名称',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人登录名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_templatecode` (`template_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table topic
# ------------------------------------------------------------

DROP TABLE IF EXISTS `topic`;

CREATE TABLE `topic` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `topic_content` text NOT NULL COMMENT '话题内容',
  `topic_name` varchar(255) NOT NULL COMMENT '话题名称',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件文件地址',
  `reply_count` int(11) NOT NULL DEFAULT '0' COMMENT '回复数量',
  `view_count` int(11) NOT NULL DEFAULT '0' COMMENT '浏览数量',
  `top` varchar(2) NOT NULL DEFAULT '0' COMMENT '是否置顶',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题信息表';



# Dump of table university
# ------------------------------------------------------------

DROP TABLE IF EXISTS `university`;

CREATE TABLE `university` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `city_id` varchar(32) NOT NULL COMMENT '所属城市',
  `university_name` varchar(255) NOT NULL COMMENT '大学名称',
  `university_image` varchar(255) NOT NULL COMMENT '大学图片',
  `official_address` varchar(255) DEFAULT NULL COMMENT '官网地址',
  `enabled` varchar(2) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校信息表';



# Dump of table university_poster
# ------------------------------------------------------------

DROP TABLE IF EXISTS `university_poster`;

CREATE TABLE `university_poster` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `university_id` varchar(32) NOT NULL COMMENT '学校ID',
  `poster_name` varchar(255) NOT NULL COMMENT '海报名称',
  `poster_image` varchar(255) NOT NULL COMMENT '海报图片',
  `poster_content` text NOT NULL COMMENT '海报内容',
  `end_date` date DEFAULT NULL COMMENT '结束日期',
  `publisher_id` varchar(32) NOT NULL COMMENT '发布者',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校海报信息表';



# Dump of table user_auth
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_auth`;

CREATE TABLE `user_auth` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `user_base_id` varchar(32) NOT NULL COMMENT '用户基础信息ID',
  `identifier` varchar(255) NOT NULL COMMENT '用户认证（邮箱、手机等）',
  `identity_type` varchar(32) NOT NULL COMMENT '用户认证类别',
  `certificate` varchar(255) DEFAULT NULL COMMENT '用户凭证（token）',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户认证信息表';



# Dump of table user_base
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_base`;

CREATE TABLE `user_base` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `register_source` varchar(32) DEFAULT NULL COMMENT '用户来源',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名称',
  `user_status` varchar(32) NOT NULL COMMENT '用户状态',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `email_bind_time` datetime DEFAULT NULL COMMENT '邮箱绑定时间',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机',
  `mobile_bind_time` datetime DEFAULT NULL COMMENT '手机绑定时间',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `face` varchar(255) DEFAULT NULL COMMENT '头像',
  `face_200` varchar(255) DEFAULT NULL COMMENT '头像200x200',
  `src_face` varchar(255) DEFAULT NULL COMMENT '头像原文件',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
