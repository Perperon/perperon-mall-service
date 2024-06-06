/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : mall_perperon

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 06/06/2024 22:24:54
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
INSERT INTO `pp_account` VALUES ('c70fc552-15a5-11ef-a15d-d8bbc147c16b', 'test', '$2a$10$7TQ8LedI/kng1ARxBBfSa.Wvkh81iO9qaiKZuQbjniFuOCuGn6KXG', NULL, '1443528055@qq.com', 'perperon', NULL, b'1', '2024-05-19 14:05:17', NULL, NULL);
INSERT INTO `pp_account` VALUES ('f54963d8-15a3-11ef-a15d-d8bbc147c16b', 'admin', '$2a$10$OED3aEs2lpHjSAhZ..Of6.y6ZpJjKXui46CDagQoQ44uVliG92b76', NULL, '1443528055@qq.com', 'perperon', NULL, b'1', '2024-05-19 00:00:00', NULL, NULL);

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
INSERT INTO `pp_account_role` VALUES ('3', 'f54963d8-15a3-11ef-a15d-d8bbc147c16b', '1', NULL, '2024-05-20 10:37:35');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pp_menu
-- ----------------------------
INSERT INTO `pp_menu` VALUES ('1', '商品管理', 'test:list', NULL, 'product', '/test/index', b'1', NULL, NULL, '2024-05-20 10:38:55', NULL, NULL);
INSERT INTO `pp_menu` VALUES ('2', '系统管理', 'dept:list', NULL, 'setting', '/dept/index', b'1', NULL, NULL, '2024-05-20 10:39:58', NULL, NULL);
INSERT INTO `pp_menu` VALUES ('3', '商品列表', 'puduct:list', NULL, 'product_list', '/test/index', b'1', NULL, '1', '2024-06-03 22:25:50', NULL, NULL);

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
INSERT INTO `pp_role_menu` VALUES ('1', '1', '1', '2024-05-20 11:03:03', NULL);
INSERT INTO `pp_role_menu` VALUES ('2', '2', '1', '2024-05-20 11:03:18', NULL);
INSERT INTO `pp_role_menu` VALUES ('3', '1', '2', '2024-05-20 11:03:32', NULL);

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
INSERT INTO `pp_roles` VALUES ('1', 'manager', '管理员', b'1', '2024-05-20 10:35:56', NULL, NULL, NULL);
INSERT INTO `pp_roles` VALUES ('2', 'dept', '部门管理', b'1', '2024-05-20 10:36:28', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
