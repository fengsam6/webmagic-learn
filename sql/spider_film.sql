/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50645
Source Host           : localhost:3306
Source Database       : spider_blog

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2019-10-20 17:12:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_blog
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog`;
CREATE TABLE `tb_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(120) DEFAULT NULL,
  `publish_time` varchar(120) DEFAULT NULL,
  `read_num` varchar(255) DEFAULT NULL,
  `source_url` varchar(180) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title-url-unique` (`title`,`source_url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1860 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_film
-- ----------------------------
DROP TABLE IF EXISTS `tb_film`;
CREATE TABLE `tb_film` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_url` varchar(180) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `title` varchar(120) DEFAULT NULL,
  `url` varchar(170) DEFAULT NULL,
  `url_source` varchar(120) DEFAULT '' COMMENT '电影爬虫来源',
  `type` varchar(100) DEFAULT '' COMMENT '电影类型',
  `description` text,
  `actor` varchar(220) DEFAULT '' COMMENT '导演',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title-url-unique` (`title`,`url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21966 DEFAULT CHARSET=utf8;
