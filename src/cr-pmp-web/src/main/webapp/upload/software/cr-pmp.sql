/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.56
Source Server Version : 50173
Source Host           : 192.168.0.56:3306
Source Database       : cr-pmp

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-06-16 09:45:45
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_navigation
-- ----------------------------
INSERT INTO `cr_pmp_navigation` VALUES ('34', '系统管理', '1', 'STATIC', '0', '', 'gears', '5', '2016-09-07 15:52:38', '2016-09-07 15:52:38');
INSERT INTO `cr_pmp_navigation` VALUES ('35', '菜单管理', '2', 'DYNAMIC', '34', '/nav/index.do', 'gears', '1', '2016-09-07 15:56:39', '2016-09-07 15:56:39');
INSERT INTO `cr_pmp_navigation` VALUES ('36', '首页', '1', 'DYNAMIC', '0', '/nav/index.do', 'home', '1', '2016-09-07 16:29:54', '2016-09-07 16:29:54');
INSERT INTO `cr_pmp_navigation` VALUES ('37', '部门管理', '2', 'DYNAMIC', '34', '/dept/index.do', 'gears', '2', '2016-09-08 09:44:48', '2016-09-08 09:44:48');
INSERT INTO `cr_pmp_navigation` VALUES ('47', '用户管理', '2', 'DYNAMIC', '34', '/user/index.do', 'users', '3', '2016-09-19 15:33:48', '2016-09-19 15:33:48');
INSERT INTO `cr_pmp_navigation` VALUES ('48', '项目管理', '1', 'DYNAMIC', '0', '/project/index.do', 'cubes', '2', '2017-06-15 10:48:09', '2017-06-15 10:48:09');

-- ----------------------------
-- Table structure for cr_pmp_project
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_project`;
CREATE TABLE `cr_pmp_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_project
-- ----------------------------
INSERT INTO `cr_pmp_project` VALUES ('1', '测试', '1', '2017-06-15 10:48:41', '2017-06-15 10:48:41');

-- ----------------------------
-- Table structure for cr_pmp_project_leaguer
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_project_leaguer`;
CREATE TABLE `cr_pmp_project_leaguer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_project_leaguer
-- ----------------------------
INSERT INTO `cr_pmp_project_leaguer` VALUES ('1', '1', 'admin', '2017-06-15 10:48:41', '2017-06-15 10:48:41');
INSERT INTO `cr_pmp_project_leaguer` VALUES ('2', '1', 'asdas', '2017-06-15 11:11:58', '2017-06-15 11:11:58');

-- ----------------------------
-- Table structure for cr_pmp_sub_task
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_sub_task`;
CREATE TABLE `cr_pmp_sub_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) DEFAULT NULL,
  `tbid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `remarks` varchar(400) DEFAULT NULL,
  `performer` varchar(20) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_sub_task
-- ----------------------------
INSERT INTO `cr_pmp_sub_task` VALUES ('1', '1', '1', '1', '注册功能', '注册功能开发', 'asdas', '2017-06-15 00:00:00', '2017-06-16 00:00:00', '2017-06-15 11:13:52', '2017-06-15 11:13:52');

-- ----------------------------
-- Table structure for cr_pmp_task
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_task`;
CREATE TABLE `cr_pmp_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `tbid` int(11) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `performer` varchar(20) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `priority` tinyint(2) DEFAULT NULL,
  `remarks` varchar(400) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_task
-- ----------------------------
INSERT INTO `cr_pmp_task` VALUES ('1', '1', '2', '开发登录', 'admin', '2017-06-15 00:00:00', '2017-06-16 00:00:00', '1', '登录功能开发', '2017-06-15 11:13:18', '2017-06-15 11:14:38');
INSERT INTO `cr_pmp_task` VALUES ('2', '1', '2', '测试登录功能', 'asdas', '2017-06-15 00:00:00', '2017-06-16 00:00:00', '1', '测试', '2017-06-15 11:14:13', '2017-06-15 11:14:13');

-- ----------------------------
-- Table structure for cr_pmp_task_board
-- ----------------------------
DROP TABLE IF EXISTS `cr_pmp_task_board`;
CREATE TABLE `cr_pmp_task_board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_task_board
-- ----------------------------
INSERT INTO `cr_pmp_task_board` VALUES ('1', '1', '开发', '2017-06-15 11:12:24', '2017-06-15 11:12:24');
INSERT INTO `cr_pmp_task_board` VALUES ('2', '1', '测试', '2017-06-15 11:12:30', '2017-06-15 11:12:30');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cr_pmp_user
-- ----------------------------
INSERT INTO `cr_pmp_user` VALUES ('1', 'admin', '202cb962ac59T075b964bT07152d234b70', '张三', '2016-08-24', '男', '15', 'cc@gmail.com', '13677889900', '超级管理员', '/upload/user-file/default-avatar.jpg', '2016-08-24 16:22:49', '2016-08-24 16:22:57');
INSERT INTO `cr_pmp_user` VALUES ('3', 'asdas', '12312312', '哦批破', '2016-09-20', '女', '15', 'hr@qq.com', '17010988753', '人事', '/upload/user-file/31A0156C0317436C94A34058C2016E09Koala.jpg', '2016-09-20 16:41:38', '2016-09-20 16:41:38');
INSERT INTO `cr_pmp_user` VALUES ('4', 'lisi', '202cb962ac59T075b964bT07152d234b70', '李四', '2017-06-15', '男', '18', '123@123.com', '12312312312', '随便', '/upload/user-file/default-avatar.jpg', '2017-06-15 11:35:31', '2017-06-15 11:35:31');
