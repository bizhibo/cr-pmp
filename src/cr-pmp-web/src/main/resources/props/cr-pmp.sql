/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : cr-pmp

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-09-21 15:36:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cr_pmp_demand
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_demand`;
CREATE TABLE `cr_pmp_demand` (
  `id` int(11) NOT NULL,
  `name` varchar(40) DEFAULT NULL COMMENT '需求名称',
  `productId` int(11) DEFAULT NULL COMMENT '关联产品ID',
  `source` varchar(20) DEFAULT NULL COMMENT '需求来源',
  `priority` tinyint(2) DEFAULT NULL COMMENT '需求优先级',
  `describe` text COMMENT '需求描述',
  `fileUrl` varchar(200) DEFAULT NULL COMMENT '关联文件',
  `leader` varchar(20) DEFAULT NULL COMMENT '需求负责人',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_demand
-- ----------------------------

-- ----------------------------
-- Table structure for cr_pmp_dept
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_dept`;
CREATE TABLE `cr_pmp_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL COMMENT '部门名称',
  `parentId` int(11) DEFAULT NULL COMMENT '父ID',
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_dept
-- ----------------------------
INSERT INTO `cr_pmp_dept` VALUES ('1', '组织架构', '0', '2016-09-08 13:54:25', '2016-09-08 13:54:29');
INSERT INTO `cr_pmp_dept` VALUES ('15', '研发部门', '1', '2016-09-09 14:23:44', '2016-09-09 14:23:44');
INSERT INTO `cr_pmp_dept` VALUES ('16', '测试组', '15', '2016-09-09 14:23:54', '2016-09-09 14:23:54');
INSERT INTO `cr_pmp_dept` VALUES ('17', '研发组', '15', '2016-09-09 14:24:00', '2016-09-09 14:24:00');
INSERT INTO `cr_pmp_dept` VALUES ('18', '产品组', '15', '2016-09-09 14:24:11', '2016-09-09 14:24:11');
INSERT INTO `cr_pmp_dept` VALUES ('19', '运营部门', '1', '2016-09-09 14:24:25', '2016-09-09 14:24:25');
INSERT INTO `cr_pmp_dept` VALUES ('20', '人事部门', '1', '2016-09-09 14:50:04', '2016-09-09 14:48:49');

-- ----------------------------
-- Table structure for cr_pmp_navigation
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_navigation`;
CREATE TABLE `cr_pmp_navigation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `level` tinyint(2) DEFAULT NULL COMMENT '菜单等级（1、2、3）',
  `type` varchar(10) DEFAULT NULL COMMENT '菜单类型（静态、动态）',
  `parentId` int(11) DEFAULT NULL COMMENT '上级菜单ID',
  `url` varchar(400) DEFAULT NULL COMMENT '菜单url',
  `icon` varchar(30) DEFAULT NULL COMMENT '菜单图标',
  `sequence` tinyint(4) DEFAULT NULL COMMENT '菜单顺序',
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_navigation
-- ----------------------------
INSERT INTO `cr_pmp_navigation` VALUES ('34', '系统管理', '1', 'STATIC', '0', '', 'gears', '5', '2016-09-07 15:52:38', '2016-09-07 15:52:38');
INSERT INTO `cr_pmp_navigation` VALUES ('35', '菜单管理', '2', 'DYNAMIC', '34', '/nav/index.do', 'gears', '1', '2016-09-07 15:56:39', '2016-09-07 15:56:39');
INSERT INTO `cr_pmp_navigation` VALUES ('36', '首页', '1', 'DYNAMIC', '0', '/nav/index.do', 'home', '1', '2016-09-07 16:29:54', '2016-09-07 16:29:54');
INSERT INTO `cr_pmp_navigation` VALUES ('37', '部门管理', '2', 'DYNAMIC', '34', '/dept/index.do', 'gears', '2', '2016-09-08 09:44:48', '2016-09-08 09:44:48');
INSERT INTO `cr_pmp_navigation` VALUES ('46', '产品管理', '1', 'DYNAMIC', '0', '/product/index.do', 'cubes', '2', '2016-09-10 21:19:07', '2016-09-10 21:19:07');
INSERT INTO `cr_pmp_navigation` VALUES ('47', '用户管理', '2', 'DYNAMIC', '34', '/user/index.do', 'users', '3', '2016-09-19 15:33:48', '2016-09-19 15:33:48');

-- ----------------------------
-- Table structure for cr_pmp_product
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_product`;
CREATE TABLE `cr_pmp_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL COMMENT '产品名称',
  `code` varchar(20) DEFAULT NULL COMMENT '产品代码',
  `leader` varchar(20) DEFAULT NULL COMMENT '产品负责人',
  `productDescribe` text COMMENT '产品描述',
  `fileUrl` varchar(200) DEFAULT NULL COMMENT '关联文件',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_product
-- ----------------------------

-- ----------------------------
-- Table structure for cr_pmp_user
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_user`;
CREATE TABLE `cr_pmp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `deptId` int(11) DEFAULT NULL COMMENT '所属部门ID',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `portraitUrl` varchar(200) DEFAULT NULL COMMENT '头像URL',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName_index` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_user
-- ----------------------------
INSERT INTO `cr_pmp_user` VALUES ('1', 'admin', '202cb962ac59T075b964bT07152d234b70', '张三', '2016-08-24', '男', '15', 'cc@gmail.com', '13677889900', '超级管理员', null, '2016-08-24 16:22:49', '2016-08-24 16:22:57');
INSERT INTO `cr_pmp_user` VALUES ('3', 'asdas', '12312312', '哦批破', '2016-09-20', '女', '15', 'hr@qq.com', '17010988753', '人事', '/upload/user-file/31A0156C0317436C94A34058C2016E09Koala.jpg', '2016-09-20 16:41:38', '2016-09-20 16:41:38');
