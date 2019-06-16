/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50558
Source Host           : 127.0.0.1:3306
Source Database       : spider_blog

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2019-06-16 18:49:25
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2154 DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;
