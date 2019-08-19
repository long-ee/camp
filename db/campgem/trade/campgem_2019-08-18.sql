/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : campgem

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 18/08/2019 13:27:37
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(32) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(32) NOT NULL,
  `country` varchar(255) NOT NULL COMMENT '国家',
  `state` varchar(255) NOT NULL COMMENT '州',
  `city` varchar(255) NOT NULL COMMENT '城市',
  `street_address` varchar(255) NOT NULL COMMENT '街道地址',
  `detail_address` varchar(255) DEFAULT NULL COMMENT '具体地址',
  `postcode` varchar(32) NOT NULL COMMENT '邮政编码',
  `is_billing_same` tinyint(1) unsigned DEFAULT '1' COMMENT '账单地址是否与收货地址一样',
  `b_first_name` varchar(255) DEFAULT NULL COMMENT '账单收货人名',
  `b_last_name` varchar(255) DEFAULT NULL COMMENT '账单收货人姓',
  `b_phone` varchar(32) DEFAULT NULL COMMENT '账单手机',
  `b_country` varchar(255) DEFAULT NULL,
  `b_state` varchar(255) DEFAULT NULL,
  `b_city` varchar(255) DEFAULT NULL,
  `b_street_address` varchar(255) DEFAULT NULL,
  `b_detail_address` varchar(255) DEFAULT NULL,
  `b_postcode` varchar(32) DEFAULT NULL,
  `del_flag` tinyint(1) unsigned DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户地址';

-- ----------------------------
-- Table structure for advertisement
-- ----------------------------
DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(255) DEFAULT NULL,
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '类型，1图片，2视频',
  `advertisement_url` varchar(255) DEFAULT NULL COMMENT '图片或视频地址',
  `page_location` varchar(32) DEFAULT NULL COMMENT '位置',
  `play_order` int(11) DEFAULT NULL COMMENT '轮播顺序',
  `create_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='广告';

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(32) NOT NULL COMMENT '会员ID',
  `seller_id` varchar(32) NOT NULL COMMENT '卖家ID',
  `seller_name` varchar(255) NOT NULL COMMENT '卖家名称',
  `goods_id` varchar(32) NOT NULL COMMENT '商品ID',
  `specifications_id` varchar(32) DEFAULT NULL COMMENT '规格ID',
  `quantity` int(11) unsigned DEFAULT '1' COMMENT '购买数量',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `mid` (`uid`) USING BTREE,
  KEY `cart_goods_ibfk_1` (`goods_id`),
  KEY `cart_specifications_ibfk_1` (`specifications_id`),
  CONSTRAINT `cart_goods_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cart_specifications_ibfk_1` FOREIGN KEY (`specifications_id`) REFERENCES `goods_specifications` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` varchar(32) NOT NULL,
  `uid` varchar(32) NOT NULL COMMENT '关联的会员ID',
  `seller_name` varchar(255) NOT NULL COMMENT '卖家名',
  `identity` tinyint(1) unsigned NOT NULL COMMENT '类型 1:Business 2:Student/Individual',
  `category_id` varchar(32) NOT NULL COMMENT '商品分类',
  `city_id` varchar(32) DEFAULT NULL COMMENT '城市ID，如果identity是1',
  `university_id` varchar(32) DEFAULT NULL COMMENT '大学ID，如果identity是2',
  `end_date` datetime DEFAULT NULL COMMENT '结束日期',
  `quality` tinyint(1) unsigned DEFAULT NULL COMMENT '新旧程度 identity是2才展示 1:Brand new 2:Almost new 3:Gently used',
  `origin_price` decimal(10,2) DEFAULT NULL COMMENT '原价，仅在identity是2才有效',
  `sale_price` decimal(10,2) DEFAULT NULL COMMENT '现价，仅在identity是2才有效',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `status` tinyint(1) unsigned NOT NULL COMMENT '状态，1:in sale 2:off shelf 3:sold 4:expired',
  `taxes` decimal(4,2) DEFAULT NULL COMMENT '税率',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签，以,分隔',
  `description` text COMMENT '描述',
  `review_count` int(11) unsigned DEFAULT '0' COMMENT '商品留言次数',
  `del_flag` tinyint(1) unsigned DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Table structure for goods_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `goods_evaluation`;
CREATE TABLE `goods_evaluation` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(32) NOT NULL COMMENT '用户ID',
  `goods_id` varchar(32) NOT NULL COMMENT '商品ID',
  `content` text NOT NULL COMMENT '评价内容',
  `rating` tinyint(1) unsigned NOT NULL COMMENT '评价星级',
  `del_flag` tinyint(1) unsigned DEFAULT '0' COMMENT '删除状态',
  `is_open` tinyint(255) DEFAULT '1' COMMENT '是否公开',
  `create_time` datetime NOT NULL COMMENT '评价时间',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`) USING BTREE,
  KEY `goods_evaluation_ibfk_1` (`goods_id`),
  CONSTRAINT `goods_evaluation_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价';

-- ----------------------------
-- Table structure for goods_images
-- ----------------------------
DROP TABLE IF EXISTS `goods_images`;
CREATE TABLE `goods_images` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `goods_id` varchar(32) NOT NULL COMMENT '商品ID',
  `goods_image` varchar(255) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`) USING BTREE,
  CONSTRAINT `goods_images_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods_reviews
-- ----------------------------
DROP TABLE IF EXISTS `goods_reviews`;
CREATE TABLE `goods_reviews` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(32) NOT NULL COMMENT '用户ID',
  `goods_id` varchar(32) NOT NULL COMMENT '商品ID',
  `content` text NOT NULL COMMENT '留言内容',
  `del_flag` tinyint(1) unsigned DEFAULT '0',
  `is_open` tinyint(1) unsigned DEFAULT '0' COMMENT '是否公开',
  `create_time` datetime DEFAULT NULL COMMENT '留言时间',
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE,
  CONSTRAINT `goods_review_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品留言';

-- ----------------------------
-- Table structure for goods_reviews_shields
-- ----------------------------
DROP TABLE IF EXISTS `goods_reviews_shields`;
CREATE TABLE `goods_reviews_shields` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(32) NOT NULL,
  `shield_uid` varchar(32) NOT NULL COMMENT '被屏蔽的用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '屏蔽时间',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品留言屏蔽';

-- ----------------------------
-- Table structure for goods_specifications
-- ----------------------------
DROP TABLE IF EXISTS `goods_specifications`;
CREATE TABLE `goods_specifications` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `goods_id` varchar(32) NOT NULL COMMENT '商品ID',
  `specifications_name` varchar(255) DEFAULT NULL COMMENT '规格名',
  `specifications_price` decimal(10,2) DEFAULT NULL COMMENT '规格价格',
  `specifications_stock` int(11) DEFAULT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`) USING BTREE,
  CONSTRAINT `goods_specifications_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格表';

-- ----------------------------
-- Table structure for requirements
-- ----------------------------
DROP TABLE IF EXISTS `requirements`;
CREATE TABLE `requirements` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(32) NOT NULL COMMENT '用户ID',
  `category_id` varchar(32) NOT NULL COMMENT '分类ID',
  `requirement_name` varchar(255) NOT NULL COMMENT '需求名',
  `buying_price` decimal(10,2) NOT NULL COMMENT '购买价格',
  `requirement_description` text NOT NULL COMMENT '需求描述',
  `del_flag` tinyint(1) unsigned DEFAULT '0' COMMENT '删除状态',
  `review_count` int(11) unsigned DEFAULT '0' COMMENT '需求留言数',
  `create_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`,`uid`,`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求';

-- ----------------------------
-- Table structure for requirements_images
-- ----------------------------
DROP TABLE IF EXISTS `requirements_images`;
CREATE TABLE `requirements_images` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `requirement_id` varchar(32) NOT NULL COMMENT '需求ID',
  `requirement_image` varchar(255) NOT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`),
  KEY `requirement_id` (`requirement_id`) USING BTREE,
  CONSTRAINT `requirement_image_ibfk_1` FOREIGN KEY (`requirement_id`) REFERENCES `requirements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求图片';

-- ----------------------------
-- Table structure for requirements_reviews
-- ----------------------------
DROP TABLE IF EXISTS `requirements_reviews`;
CREATE TABLE `requirements_reviews` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(32) NOT NULL COMMENT '用户ID',
  `requirement_id` varchar(32) NOT NULL COMMENT '需求ID',
  `content` text NOT NULL COMMENT '留言内容',
  `del_flag` tinyint(1) unsigned DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '留言时间',
  PRIMARY KEY (`id`),
  KEY `requirement_id` (`requirement_id`) USING BTREE,
  CONSTRAINT `requirement_review_ibfk_1` FOREIGN KEY (`requirement_id`) REFERENCES `requirements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求留言';

-- ----------------------------
-- Table structure for wishes
-- ----------------------------
DROP TABLE IF EXISTS `wishes`;
CREATE TABLE `wishes` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(32) NOT NULL COMMENT '用户ID',
  `goods_id` varchar(32) NOT NULL COMMENT '添加的商品ID',
  `specification_id` varchar(32) DEFAULT NULL COMMENT '商品的规格ID',
  `del_flag` tinyint(1) unsigned DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `wish_goods_ibfk_1` (`goods_id`),
  CONSTRAINT `wish_goods_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='心愿表';

SET FOREIGN_KEY_CHECKS = 1;
