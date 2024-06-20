/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : perperon_mall

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 20/06/2024 14:41:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pp_account
-- ----------------------------
DROP TABLE IF EXISTS `pp_account`;
CREATE TABLE `pp_account`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注释',
  `status` bit(1) NULL DEFAULT NULL COMMENT '状态',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pp_account
-- ----------------------------
INSERT INTO `pp_account` VALUES ('f520b246-2d18-11ef-b6ec-80e82cda4197', 'perperon', '$2a$10$zxc3GxyWVV7pAJuB9VN4sesQo0LTFQi.nZylHvG0P1.LDZ6AOdX8u', NULL, '15215015915@163.com', '杜鹏程', NULL, b'1', '2024-06-18 10:17:48', NULL, NULL);
INSERT INTO `pp_account` VALUES ('f54963d8-15a3-11ef-a15d-d8bbc147c16b', 'admin', '$2a$10$OED3aEs2lpHjSAhZ..Of6.y6ZpJjKXui46CDagQoQ44uVliG92b76', NULL, '1443528055@qq.com', 'perperon', NULL, b'1', '2024-05-19 00:00:00', NULL, '2024-06-20 11:19:59');

-- ----------------------------
-- Table structure for pp_account_role
-- ----------------------------
DROP TABLE IF EXISTS `pp_account_role`;
CREATE TABLE `pp_account_role`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键标识',
  `account_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户标识',
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pp_account_role
-- ----------------------------
INSERT INTO `pp_account_role` VALUES ('1', 'c70fc552-15a5-11ef-a15d-d8bbc147c16b', '1', NULL, '2024-05-20 10:37:01');
INSERT INTO `pp_account_role` VALUES ('2', 'c70fc552-15a5-11ef-a15d-d8bbc147c16b', '2', NULL, '2024-05-20 10:37:12');
INSERT INTO `pp_account_role` VALUES ('3', 'f54963d8-15a3-11ef-a15d-d8bbc147c16b', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', NULL, '2024-05-20 10:37:35');

-- ----------------------------
-- Table structure for pp_menu
-- ----------------------------
DROP TABLE IF EXISTS `pp_menu`;
CREATE TABLE `pp_menu`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键标识',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `perms` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识;',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径;',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标;',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单路径;',
  `status` bit(1) NULL DEFAULT NULL COMMENT '菜单状态;',
  `visible` bit(1) NULL DEFAULT NULL COMMENT '是否显示;',
  `parent_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级菜单标识;',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序;',
  `level` int(255) NULL DEFAULT NULL COMMENT '菜单等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pp_menu
-- ----------------------------
INSERT INTO `pp_menu` VALUES ('66434385-2d26-11ef-b6ec-80e82cda4197', '权限管理', 'roles', NULL, 'auth', '/auth', b'1', NULL, NULL, '2024-06-18 11:54:01', NULL, NULL, 1);
INSERT INTO `pp_menu` VALUES ('6dcb89b1-2d26-11ef-b6ec-80e82cda4197', '菜单列表', 'menu:list', NULL, 'menu_list', '/menu/list', b'1', NULL, '66434385-2d26-11ef-b6ec-80e82cda4197', '2024-06-18 11:54:14', '', NULL, 2);
INSERT INTO `pp_menu` VALUES ('b1e4fb5c-2d42-11ef-b6ec-80e82cda4197', '角色列表', 'role:list', NULL, 'role_list', '/role/list', b'1', NULL, '66434385-2d26-11ef-b6ec-80e82cda4197', '2024-06-18 11:54:14', '', NULL, 2);
INSERT INTO `pp_menu` VALUES ('bab9f995-2794-11af-960f-80e82cda4198', '商品管理', 'product', NULL, 'product', '/product', b'1', NULL, NULL, '2024-05-20 10:38:55', NULL, NULL, 1);
INSERT INTO `pp_menu` VALUES ('bab9f995-2794-11ef-920f-80e82cda4197', '用户管理', 'account', NULL, 'user', '/account', b'1', NULL, NULL, '2024-06-11 09:48:32', NULL, NULL, 1);
INSERT INTO `pp_menu` VALUES ('bab9f995-2794-11ef-920f-80e82cda4198', '商品列表', 'puduct:list', NULL, 'product_list', '/product/list', b'1', NULL, 'bab9f995-2794-11af-960f-80e82cda4198', '2024-06-03 22:25:50', NULL, NULL, 2);
INSERT INTO `pp_menu` VALUES ('c59a854a-2794-11ef-920f-80e82cda4197', '用户列表', 'account:list', NULL, 'user_list', '/account/list', b'1', NULL, 'bab9f995-2794-11ef-920f-80e82cda4197', '2024-06-11 09:48:50', '', NULL, 2);

-- ----------------------------
-- Table structure for pp_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `pp_role_menu`;
CREATE TABLE `pp_role_menu`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键标识',
  `role_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  `menu_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限标识',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pp_role_menu
-- ----------------------------
INSERT INTO `pp_role_menu` VALUES ('1', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '66434385-2d26-11ef-b6ec-80e82cda4197', '2024-05-20 11:03:03', NULL);
INSERT INTO `pp_role_menu` VALUES ('f4efda1f-2d50-11ef-b6ec-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '6dcb89b1-2d26-11ef-b6ec-80e82cda4197', '2024-06-18 16:58:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('f4f09bee-2d50-11ef-b6ec-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', 'b1e4fb5c-2d42-11ef-b6ec-80e82cda4197', '2024-06-18 16:58:40', NULL);

-- ----------------------------
-- Table structure for pp_roles
-- ----------------------------
DROP TABLE IF EXISTS `pp_roles`;
CREATE TABLE `pp_roles`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编号',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名字',
  `status` bit(1) NULL DEFAULT NULL COMMENT '状态',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pp_roles
-- ----------------------------
INSERT INTO `pp_roles` VALUES ('93ed1de2-2d48-11ef-b6ec-80e82cda4197', 'dept', '部门管理', b'1', '2024-06-18 15:58:41', NULL, NULL, NULL);
INSERT INTO `pp_roles` VALUES ('d8705bf2-2d47-11ef-b6ec-80e82cda4197', 'system', '超级管理员', b'1', '2024-06-18 15:53:26', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
