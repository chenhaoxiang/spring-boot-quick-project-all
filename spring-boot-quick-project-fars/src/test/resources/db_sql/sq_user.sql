/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : spring-boot-quick-project

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-09-03 20:37:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sq_user
-- ----------------------------
DROP TABLE IF EXISTS `sq_user`;
CREATE TABLE `sq_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '盐',
  `age` int(11) NOT NULL COMMENT '年龄',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sq_user
-- ----------------------------
INSERT INTO `sq_user` VALUES ('1', '1234', '1234', '12555', '21', '1', '2018-08-31 19:07:01', '2018-08-31 19:07:01');
INSERT INTO `sq_user` VALUES ('2', 'chen', '1234', '1234', '21', '0', '2018-09-03 20:36:10', '2018-09-03 20:36:10');
INSERT INTO `sq_user` VALUES ('3', 'test', '123', '123', '22', '2', '2018-09-03 20:36:20', '2018-09-03 20:36:20');
INSERT INTO `sq_user` VALUES ('4', 'admin', '1234', '12358', '22', '0', '2018-09-03 20:36:35', '2018-09-03 20:36:35');
INSERT INTO `sq_user` VALUES ('5', 'user', '123456', '4589', '21', '0', '2018-09-03 20:36:43', '2018-09-03 20:36:43');
INSERT INTO `sq_user` VALUES ('6', 'zhangsan', '123455', '9945', '22', '1', '2018-09-03 20:36:57', '2018-09-03 20:37:00');
