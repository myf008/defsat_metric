/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : metric_admin

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-10 15:48:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app`
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `app_id` varchar(64) DEFAULT NULL COMMENT 'app唯一标识',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `owner` varchar(255) DEFAULT NULL COMMENT '联系人',
  `mail` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(255) DEFAULT NULL COMMENT '电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `appId_unique` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('3', 'aaa', 'aaa', 'zhangsan', null, null, '2017-07-09 17:02:00', '2017-07-09 17:02:28');
INSERT INTO `app` VALUES ('4', 'phystrix', 'phystrix demo', 'myf', null, null, '2017-07-10 11:47:32', '2017-07-10 11:47:32');

-- ----------------------------
-- Table structure for `app_influxdb_relation`
-- ----------------------------
DROP TABLE IF EXISTS `app_influxdb_relation`;
CREATE TABLE `app_influxdb_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `app_id` varchar(64) NOT NULL COMMENT 'app唯一标识',
  `influxdb_id` varchar(64) NOT NULL COMMENT 'influxdb id',
  `db_name` varchar(64) DEFAULT NULL COMMENT '数据库名',
  `retention` varchar(64) DEFAULT NULL COMMENT '保留策略',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `appId_unique` (`app_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of app_influxdb_relation
-- ----------------------------
INSERT INTO `app_influxdb_relation` VALUES ('1', 'aaa', '192.168.48.129:8086', 'stg', 'default', '2017-07-10 09:08:07', '2017-07-10 09:08:07');
INSERT INTO `app_influxdb_relation` VALUES ('2', 'phystrix', '192.168.48.129:8086', 'phystrix', 'default', '2017-07-10 11:47:58', '2017-07-10 11:47:58');

-- ----------------------------
-- Table structure for `blacklist`
-- ----------------------------
DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `app_id` varchar(64) DEFAULT NULL COMMENT 'app唯一标识',
  `db_name` varchar(64) DEFAULT NULL COMMENT '数据库名',
  `measurement` varchar(128) DEFAULT NULL COMMENT '表名',
  `field_name` varchar(128) DEFAULT NULL COMMENT '字段名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of blacklist
-- ----------------------------
INSERT INTO `blacklist` VALUES ('5', 'aaa', 'stg', 'stg', 'value11', '2017-07-10 09:08:35', '2017-07-10 09:08:35');

-- ----------------------------
-- Table structure for `influxdb`
-- ----------------------------
DROP TABLE IF EXISTS `influxdb`;
CREATE TABLE `influxdb` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `influxdb_id` varchar(128) DEFAULT NULL COMMENT 'influxdb唯一标识(ip : port)',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `influxdbId_unique` (`influxdb_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of influxdb
-- ----------------------------
INSERT INTO `influxdb` VALUES ('2', '192.168.48.129:8086', 'root', 'root', '2017-07-10 09:07:52', '2017-07-10 09:07:52');
