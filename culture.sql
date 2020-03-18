/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : 101.37.28.182:3306
 Source Schema         : culture

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 16/03/2020 22:12:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `apply_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文物上链申请ID',
  `relic_id` bigint(20) DEFAULT NULL COMMENT '文物ID',
  `apply_date` datetime DEFAULT NULL COMMENT '文物上链申请时间',
  `from_id` bigint(20) DEFAULT NULL COMMENT '发起人ID',
  `check_status` int(255) DEFAULT NULL COMMENT '是否已审核',
  `apply_response` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`apply_id`) USING BTREE,
  KEY `relic_id` (`relic_id`),
  KEY `from_id` (`from_id`),
  CONSTRAINT `apply_ibfk_1` FOREIGN KEY (`relic_id`) REFERENCES `relic` (`relic_id`),
  CONSTRAINT `apply_ibfk_2` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for exit_entry
-- ----------------------------
DROP TABLE IF EXISTS `exit_entry`;
CREATE TABLE `exit_entry` (
  `exit_entry_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '出入境记录ID',
  `from_id` bigint(20) DEFAULT NULL COMMENT '发起人ID',
  `to_id` bigint(20) DEFAULT NULL COMMENT '接受人ID',
  `relic_id` bigint(20) DEFAULT NULL COMMENT '文物ID',
  `origin` varchar(255) DEFAULT NULL COMMENT '出关地点',
  `destination` varchar(255) DEFAULT NULL COMMENT '目的地',
  `exit_entry_check_status` int(255) DEFAULT NULL COMMENT '政府是否已审核',
  `exit_entry_response` varchar(255) DEFAULT NULL COMMENT '政府审核意见',
  `exit_entry_customs_status` int(255) DEFAULT NULL COMMENT '海关是否已审核',
  `exit_entry_customs_response` varchar(255) DEFAULT NULL COMMENT '海关审核意见',
  PRIMARY KEY (`exit_entry_id`) USING BTREE,
  KEY `from_id` (`from_id`),
  KEY `to_id` (`to_id`),
  KEY `relic_id` (`relic_id`),
  CONSTRAINT `exit_entry_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`),
  CONSTRAINT `exit_entry_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`),
  CONSTRAINT `exit_entry_ibfk_3` FOREIGN KEY (`relic_id`) REFERENCES `relic` (`relic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_login
-- ----------------------------
DROP TABLE IF EXISTS `log_login`;
CREATE TABLE `log_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) DEFAULT NULL,
  `loginip` varchar(255) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of log_login
-- ----------------------------
BEGIN;
INSERT INTO `log_login` VALUES (3, '超级管理员-system', '127.0.0.1', '2018-12-21 16:55:15');
INSERT INTO `log_login` VALUES (4, '超级管理员-system', '127.0.0.1', '2018-12-21 17:29:22');
INSERT INTO `log_login` VALUES (5, '超级管理员-system', '127.0.0.1', '2018-12-22 09:05:22');
INSERT INTO `log_login` VALUES (6, '超级管理员-system', '127.0.0.1', '2018-12-22 09:20:43');
INSERT INTO `log_login` VALUES (7, '超级管理员-system', '127.0.0.1', '2018-12-22 09:25:40');
INSERT INTO `log_login` VALUES (8, '超级管理员-system', '127.0.0.1', '2018-12-22 09:27:11');
INSERT INTO `log_login` VALUES (9, '超级管理员-system', '127.0.0.1', '2018-12-22 09:29:58');
INSERT INTO `log_login` VALUES (10, '超级管理员-system', '127.0.0.1', '2018-12-22 09:36:49');
INSERT INTO `log_login` VALUES (11, '超级管理员-system', '127.0.0.1', '2018-12-22 09:46:56');
INSERT INTO `log_login` VALUES (12, '超级管理员-system', '127.0.0.1', '2018-12-22 09:48:29');
INSERT INTO `log_login` VALUES (13, '超级管理员-system', '127.0.0.1', '2018-12-22 09:49:13');
INSERT INTO `log_login` VALUES (14, '超级管理员-system', '127.0.0.1', '2018-12-22 09:49:57');
INSERT INTO `log_login` VALUES (15, '超级管理员-system', '127.0.0.1', '2018-12-22 09:57:46');
INSERT INTO `log_login` VALUES (16, '超级管理员-system', '127.0.0.1', '2018-12-22 10:21:53');
INSERT INTO `log_login` VALUES (17, '超级管理员-system', '127.0.0.1', '2018-12-22 10:38:25');
INSERT INTO `log_login` VALUES (18, '超级管理员-system', '127.0.0.1', '2018-12-22 10:49:21');
INSERT INTO `log_login` VALUES (19, '超级管理员-system', '127.0.0.1', '2018-12-22 14:01:42');
INSERT INTO `log_login` VALUES (20, '超级管理员-system', '127.0.0.1', '2018-12-22 14:19:48');
INSERT INTO `log_login` VALUES (21, '超级管理员-system', '127.0.0.1', '2018-12-22 14:45:29');
INSERT INTO `log_login` VALUES (22, '超级管理员-system', '127.0.0.1', '2018-12-22 15:58:05');
INSERT INTO `log_login` VALUES (23, '超级管理员-system', '127.0.0.1', '2018-12-22 16:40:53');
INSERT INTO `log_login` VALUES (24, '超级管理员-system', '127.0.0.1', '2018-12-22 17:12:01');
INSERT INTO `log_login` VALUES (25, '超级管理员-system', '127.0.0.1', '2018-12-24 09:19:15');
INSERT INTO `log_login` VALUES (26, '超级管理员-system', '127.0.0.1', '2018-12-24 09:37:28');
INSERT INTO `log_login` VALUES (27, '超级管理员-system', '127.0.0.1', '2018-12-24 09:46:51');
INSERT INTO `log_login` VALUES (28, '超级管理员-system', '127.0.0.1', '2018-12-24 09:50:40');
INSERT INTO `log_login` VALUES (29, '超级管理员-system', '127.0.0.1', '2018-12-24 09:52:52');
INSERT INTO `log_login` VALUES (30, '超级管理员-system', '127.0.0.1', '2018-12-24 10:00:26');
INSERT INTO `log_login` VALUES (31, '超级管理员-system', '127.0.0.1', '2018-12-24 10:10:58');
INSERT INTO `log_login` VALUES (32, '超级管理员-system', '127.0.0.1', '2018-12-24 10:21:28');
INSERT INTO `log_login` VALUES (37, 'test', '192.168.1.102', '2020-03-10 15:23:32');
INSERT INTO `log_login` VALUES (38, 'qy', '127.0.0.1', '2020-03-15 18:29:06');
INSERT INTO `log_login` VALUES (39, 'system', '127.0.0.1', '2020-03-15 18:30:57');
INSERT INTO `log_login` VALUES (40, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 18:49:26');
INSERT INTO `log_login` VALUES (41, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 18:53:51');
INSERT INTO `log_login` VALUES (42, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 18:54:31');
INSERT INTO `log_login` VALUES (43, 'qy', '0:0:0:0:0:0:0:1', '2020-03-15 18:54:50');
INSERT INTO `log_login` VALUES (44, 'system', '127.0.0.1', '2020-03-15 18:56:31');
INSERT INTO `log_login` VALUES (45, 'system', '127.0.0.1', '2020-03-15 20:02:59');
INSERT INTO `log_login` VALUES (46, 'system', '127.0.0.1', '2020-03-15 20:27:59');
INSERT INTO `log_login` VALUES (47, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 20:29:02');
INSERT INTO `log_login` VALUES (48, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 20:50:44');
INSERT INTO `log_login` VALUES (49, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 20:52:42');
INSERT INTO `log_login` VALUES (50, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 20:54:15');
INSERT INTO `log_login` VALUES (51, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 21:41:02');
INSERT INTO `log_login` VALUES (52, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 21:44:07');
INSERT INTO `log_login` VALUES (53, 'system', '0:0:0:0:0:0:0:1', '2020-03-15 22:17:37');
INSERT INTO `log_login` VALUES (54, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 09:00:14');
INSERT INTO `log_login` VALUES (55, 'system', '127.0.0.1', '2020-03-16 10:30:38');
INSERT INTO `log_login` VALUES (56, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 11:55:49');
INSERT INTO `log_login` VALUES (57, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 12:08:56');
INSERT INTO `log_login` VALUES (58, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 12:36:01');
INSERT INTO `log_login` VALUES (59, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 13:24:35');
INSERT INTO `log_login` VALUES (60, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 14:14:11');
INSERT INTO `log_login` VALUES (61, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 16:56:27');
INSERT INTO `log_login` VALUES (62, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 20:21:45');
INSERT INTO `log_login` VALUES (63, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 20:52:30');
INSERT INTO `log_login` VALUES (64, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 21:10:01');
INSERT INTO `log_login` VALUES (65, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 21:44:55');
INSERT INTO `log_login` VALUES (66, 'system', '0:0:0:0:0:0:0:1', '2020-03-16 21:55:34');
COMMIT;

-- ----------------------------
-- Table structure for movement
-- ----------------------------
DROP TABLE IF EXISTS `movement`;
CREATE TABLE `movement` (
  `movement_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流转ID',
  `explanation` varchar(1024) DEFAULT NULL COMMENT '流转说明',
  `move_type` varchar(255) DEFAULT NULL COMMENT '流转类型（交易、捐赠等）',
  `move_date` datetime DEFAULT NULL COMMENT '流转发生时间',
  `relic_id` bigint(20) DEFAULT NULL COMMENT '文物ID',
  `from_id` bigint(20) DEFAULT NULL COMMENT '流转发起人ID',
  `to_id` bigint(20) DEFAULT NULL COMMENT '流转接受人ID',
  `check_status` int(255) DEFAULT NULL COMMENT '是否已审核',
  `movement_response` varchar(255) DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`movement_id`) USING BTREE,
  KEY `relic_id` (`relic_id`),
  KEY `from_id` (`from_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `movement_ibfk_1` FOREIGN KEY (`relic_id`) REFERENCES `relic` (`relic_id`),
  CONSTRAINT `movement_ibfk_2` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`),
  CONSTRAINT `movement_ibfk_3` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '交易ID',
  `order_value` int(255) DEFAULT NULL COMMENT '交易金额',
  `order_date` datetime DEFAULT NULL COMMENT '交易日期',
  `order_status` int(255) DEFAULT NULL COMMENT '交易状态',
  `relic_id` bigint(20) DEFAULT NULL COMMENT '文物ID',
  `buyer_id` bigint(20) DEFAULT NULL COMMENT '买方ID',
  `seller_id` bigint(20) DEFAULT NULL COMMENT '卖方ID',
  `check_status` varchar(255) DEFAULT NULL COMMENT '是否已审核',
  `order_response` varchar(255) DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`order_id`) USING BTREE,
  KEY `relic_id` (`relic_id`),
  KEY `buyer_id` (`buyer_id`),
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`relic_id`) REFERENCES `relic` (`relic_id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) DEFAULT NULL,
  `percode` varchar(255) DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, 0, 'menu', '文物管理系统', NULL, '&#xe68e;', '', '', 1, 1, 1);
INSERT INTO `permission` VALUES (5, 1, 'menu', '系统管理', NULL, '&#xe614;', '', '', 0, 5, 1);
INSERT INTO `permission` VALUES (6, 1, 'menu', '其它管理', NULL, '&#xe628;', '', '', 0, 6, 1);
INSERT INTO `permission` VALUES (14, 5, 'menu', '部门管理', NULL, '&#xe770;', '/sys/toDeptManager', '', 0, 14, 1);
INSERT INTO `permission` VALUES (15, 5, 'menu', '菜单管理', NULL, '&#xe857;', '/sys/toMenuManager', '', 0, 15, 1);
INSERT INTO `permission` VALUES (16, 5, 'menu', '权限管理', '', '&#xe857;', '/sys/toPermissionManager', '', 0, 16, 1);
INSERT INTO `permission` VALUES (17, 5, 'menu', '角色管理', '', '&#xe650;', '/sys/toRoleManager', '', 0, 17, 1);
INSERT INTO `permission` VALUES (18, 5, 'menu', '用户管理', '', '&#xe612;', '/sys/toUserManager', '', 0, 18, 1);
INSERT INTO `permission` VALUES (21, 6, 'menu', '登录日志', NULL, '&#xe675;', '/sys/toLogLoginManager', '', 0, 21, 1);
INSERT INTO `permission` VALUES (22, 6, 'menu', '系统公告', NULL, '&#xe756;', '/sys/toNoticeManager', NULL, 0, 22, 1);
INSERT INTO `permission` VALUES (23, 6, 'menu', '图标管理', NULL, '&#xe670;', '/res/version1/views/plugin/iconPicker.html', NULL, 0, 23, 1);
INSERT INTO `permission` VALUES (30, 14, 'permission', '添加部门', 'dept:create', '', NULL, NULL, 0, 24, 1);
INSERT INTO `permission` VALUES (31, 14, 'permission', '修改部门', 'dept:update', '', NULL, NULL, 0, 26, 1);
INSERT INTO `permission` VALUES (32, 14, 'permission', '删除部门', 'dept:delete', '', NULL, NULL, 0, 27, 1);
INSERT INTO `permission` VALUES (34, 15, 'permission', '添加菜单', 'menu:create', '', '', '', 0, 29, 1);
INSERT INTO `permission` VALUES (35, 15, 'permission', '修改菜单', 'menu:update', '', NULL, NULL, 0, 30, 1);
INSERT INTO `permission` VALUES (36, 15, 'permission', '删除菜单', 'menu:delete', '', NULL, NULL, 0, 31, 1);
INSERT INTO `permission` VALUES (38, 16, 'permission', '添加权限', 'permission:create', '', NULL, NULL, 0, 33, 1);
INSERT INTO `permission` VALUES (39, 16, 'permission', '修改权限', 'permission:update', '', NULL, NULL, 0, 34, 1);
INSERT INTO `permission` VALUES (40, 16, 'permission', '删除权限', 'permission:delete', '', NULL, NULL, 0, 35, 1);
INSERT INTO `permission` VALUES (42, 17, 'permission', '添加角色', 'role:create', '', NULL, NULL, 0, 37, 1);
INSERT INTO `permission` VALUES (43, 17, 'permission', '修改角色', 'role:update', '', NULL, NULL, 0, 38, 1);
INSERT INTO `permission` VALUES (44, 17, 'permission', '角色删除', 'role:delete', '', NULL, NULL, 0, 39, 1);
INSERT INTO `permission` VALUES (46, 17, 'permission', '分配权限', 'role:selectPermission', '', NULL, NULL, 0, 41, 1);
INSERT INTO `permission` VALUES (47, 18, 'permission', '添加用户', 'user:create', '', NULL, NULL, 0, 42, 1);
INSERT INTO `permission` VALUES (48, 18, 'permission', '修改用户', 'user:update', '', NULL, NULL, 0, 43, 1);
INSERT INTO `permission` VALUES (49, 18, 'permission', '删除用户', 'user:delete', '', NULL, NULL, 0, 44, 1);
INSERT INTO `permission` VALUES (51, 18, 'permission', '用户分配角色', 'user:selectRole', '', NULL, NULL, 0, 46, 1);
INSERT INTO `permission` VALUES (52, 18, 'permission', '重置密码', 'user:resetPwd', NULL, NULL, NULL, 0, 47, 1);
INSERT INTO `permission` VALUES (53, 14, 'permission', '部门查询', 'dept:view', NULL, NULL, NULL, 0, 48, 1);
INSERT INTO `permission` VALUES (54, 15, 'permission', '菜单查询', 'menu:view', NULL, NULL, NULL, 0, 49, 1);
INSERT INTO `permission` VALUES (55, 16, 'permission', '权限查询', 'permission:view', NULL, NULL, NULL, 0, 50, 1);
INSERT INTO `permission` VALUES (56, 17, 'permission', '角色查询', 'role:view', NULL, NULL, NULL, 0, 51, 1);
INSERT INTO `permission` VALUES (57, 18, 'permission', '用户查询', 'user:view', NULL, NULL, NULL, 0, 52, 1);
INSERT INTO `permission` VALUES (73, 21, 'permission', '日志查询', 'info:view', NULL, NULL, NULL, NULL, 65, 1);
INSERT INTO `permission` VALUES (74, 21, 'permission', '日志删除', 'info:delete', NULL, NULL, NULL, NULL, 66, 1);
INSERT INTO `permission` VALUES (75, 21, 'permission', '日志批量删除', 'info:batchdelete', NULL, NULL, NULL, NULL, 67, 1);
INSERT INTO `permission` VALUES (76, 22, 'permission', '公告查询', 'notice:view', NULL, NULL, NULL, NULL, 68, 1);
INSERT INTO `permission` VALUES (77, 22, 'permission', '公告添加', 'notice:create', NULL, NULL, NULL, NULL, 69, 1);
INSERT INTO `permission` VALUES (78, 22, 'permission', '公告修改', 'notice:update', NULL, NULL, NULL, NULL, 70, 1);
INSERT INTO `permission` VALUES (79, 22, 'permission', '公告删除', 'notice:delete', NULL, NULL, NULL, NULL, 71, 1);
INSERT INTO `permission` VALUES (86, 22, 'permission', '公告查看', 'notice:viewnotice', NULL, NULL, NULL, NULL, 78, 1);
INSERT INTO `permission` VALUES (93, 1, 'menu', '博物馆', NULL, '&#xe614;', NULL, NULL, 0, 79, 1);
INSERT INTO `permission` VALUES (94, 1, 'menu', '交易所', NULL, '&#xe614;', NULL, NULL, 0, 80, 1);
INSERT INTO `permission` VALUES (95, 1, 'menu', '海关', NULL, '&#xe614;', NULL, NULL, 0, 81, 1);
INSERT INTO `permission` VALUES (96, 1, 'menu', '鉴定所', NULL, '&#xe614;', NULL, NULL, 0, 82, 1);
INSERT INTO `permission` VALUES (97, 1, 'menu', '政府', NULL, '&#xe614;', NULL, NULL, 0, 83, 1);
INSERT INTO `permission` VALUES (98, 1, 'menu', '普通用户', NULL, '&#xe614;', NULL, NULL, 0, 84, 1);
INSERT INTO `permission` VALUES (99, 96, 'menu', '文物鉴定', NULL, '&#xe614;', '', '', 0, 85, 1);
COMMIT;

-- ----------------------------
-- Table structure for relic
-- ----------------------------
DROP TABLE IF EXISTS `relic`;
CREATE TABLE `relic` (
  `relic_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文物ID',
  `gov_num` varchar(255) DEFAULT NULL COMMENT '国家认证编号',
  `relic_name` varchar(255) DEFAULT NULL COMMENT '文物名称',
  `relic_describe` varchar(2048) DEFAULT NULL COMMENT '文物描述',
  `picture` varchar(2048) DEFAULT NULL COMMENT '文物图片地址',
  `identity_status` int(255) DEFAULT NULL COMMENT '是否已鉴定',
  `relic_status` int(255) DEFAULT NULL COMMENT '鉴定结果',
  `identity_id` bigint(20) DEFAULT NULL COMMENT '鉴定人ID',
  `owner_id` bigint(11) DEFAULT NULL COMMENT '文物所有人ID(根据所有人ID查询部门)',
  PRIMARY KEY (`relic_id`) USING BTREE,
  KEY `identity_id` (`identity_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `relic_ibfk_1` FOREIGN KEY (`identity_id`) REFERENCES `user` (`id`),
  CONSTRAINT `relic_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '超级管理员', '拥有所有菜单权限', 1, '2020-03-10 14:06:32');
INSERT INTO `role` VALUES (2, '政府人员', '政府机构数据操作权限', 1, '2020-03-10 14:06:32');
INSERT INTO `role` VALUES (3, '博物馆人员', '博物馆数据操作权限', 1, '2020-03-10 14:06:32');
INSERT INTO `role` VALUES (4, '交易所人员', '交易所数据操作权限', 1, '2020-03-10 14:06:32');
INSERT INTO `role` VALUES (5, '海关人员', '海关数据操作权限', 1, '2020-03-10 14:06:32');
INSERT INTO `role` VALUES (6, '鉴定所人员', '鉴定所数据操作权限', 1, '2020-03-10 14:06:32');
INSERT INTO `role` VALUES (7, '普通用户', '文物相关操作权限', 1, '2020-03-10 14:06:32');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `rid` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  PRIMARY KEY (`pid`,`rid`) USING BTREE,
  KEY `FK_tcsr9ucxypb3ce1q5qngsfk33` (`rid`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `permission` (`id`) ON DELETE CASCADE,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (1, 5);
INSERT INTO `role_permission` VALUES (1, 6);
COMMIT;

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `rid` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL,
  PRIMARY KEY (`uid`,`rid`) USING BTREE,
  KEY `FK_203gdpkwgtow7nxlo2oap5jru` (`rid`) USING BTREE,
  CONSTRAINT `role_user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`id`),
  CONSTRAINT `role_user_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_user
-- ----------------------------
BEGIN;
INSERT INTO `role_user` VALUES (1, 8);
INSERT INTO `role_user` VALUES (7, 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密带盐',
  `type` int(1) DEFAULT NULL COMMENT '角色',
  `number` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `addr` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'qy', '532ac00e86893901af5f0be6b704dbc7', NULL, NULL, NULL, NULL, NULL, NULL, 1, '04A93C74C8294AA09A8B974FD1F4ECBB', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, 'abc', '34351', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, 'ttt', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (7, 'aaa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (8, 'system', '532ac00e86893901af5f0be6b704dbc7', NULL, NULL, NULL, NULL, NULL, NULL, 1, '04A93C74C8294AA09A8B974FD1F4ECBB', 0, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
