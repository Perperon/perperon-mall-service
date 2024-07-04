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

 Date: 04/07/2024 15:52:35
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
INSERT INTO `pp_account` VALUES ('f520b246-2d18-11ef-b6ec-80e82cda4197', 'perperon', '$2a$10$zxc3GxyWVV7pAJuB9VN4sesQo0LTFQi.nZylHvG0P1.LDZ6AOdX8u', '/attach/20240627122306000000110.jpg', '15215015915@163.com', '杜鹏程', NULL, b'1', '2024-06-18 10:17:48', NULL, '2024-07-01 15:39:02');
INSERT INTO `pp_account` VALUES ('f54963d8-15a3-11ef-a15d-d8bbc147c16b', 'admin', '$2a$10$nEZLtBc7r0Ja/evkiKkRDO1zx4ElB9NeT8DRUhdX5MKli3RBa1fZW', '/attach/avatar.jpeg', '1443528055@qq.com', 'perperon', NULL, b'1', '2024-05-19 00:00:00', NULL, '2024-07-04 09:14:58');

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
INSERT INTO `pp_account_role` VALUES ('1bdff9ed-343d-11ef-a088-80e82cda4197', 'f520b246-2d18-11ef-b6ec-80e82cda4197', '93ed1de2-2d48-11ef-b6ec-80e82cda4197', NULL, '2024-06-27 12:24:13');
INSERT INTO `pp_account_role` VALUES ('2', 'c70fc552-15a5-11ef-a15d-d8bbc147c16b', '2', NULL, '2024-05-20 10:37:12');
INSERT INTO `pp_account_role` VALUES ('bc5e7939-377b-11ef-acb1-00ff59042dc6', 'f54963d8-15a3-11ef-a15d-d8bbc147c16b', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', NULL, '2024-07-01 15:29:56');
INSERT INTO `pp_account_role` VALUES ('bc5e9257-377b-11ef-acb1-00ff59042dc6', 'f54963d8-15a3-11ef-a15d-d8bbc147c16b', '93ed1de2-2d48-11ef-b6ec-80e82cda4197', NULL, '2024-07-01 15:29:56');

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
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否显示;',
  `parent_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级菜单标识;',
  `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `level` int(11) NULL DEFAULT NULL COMMENT '菜单等级',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pp_menu
-- ----------------------------
INSERT INTO `pp_menu` VALUES ('0db5e793-39ce-11ef-a774-80e82cda4197', '角色修改', 'role:update', '', 'edit', '', b'1', 'F', 'b1e4fb5c-2d42-11ef-b6ec-80e82cda4197', '2024-07-04 14:24:21', NULL, 3, 2);
INSERT INTO `pp_menu` VALUES ('1111ce79-39a3-11ef-a774-80e82cda4197', '菜单修改', 'menu:update', '', 'update', '', b'1', 'F', '6dcb89b1-2d26-11ef-b6ec-80e82cda4197', '2024-07-04 09:16:40', NULL, 3, 2);
INSERT INTO `pp_menu` VALUES ('2fe8c39a-39ce-11ef-a774-80e82cda4197', '角色删除', 'role:delete', '', 'delete', '', b'1', 'F', 'b1e4fb5c-2d42-11ef-b6ec-80e82cda4197', '2024-07-04 14:25:18', NULL, 3, 3);
INSERT INTO `pp_menu` VALUES ('368bfe0d-3914-11ef-87cf-80e82cda4197', '菜单添加', 'menu:add', '', 'add', '', b'1', 'F', '6dcb89b1-2d26-11ef-b6ec-80e82cda4197', '2024-07-03 16:14:02', NULL, 3, 1);
INSERT INTO `pp_menu` VALUES ('6582716e-39ce-11ef-a774-80e82cda4197', '用户添加', 'account:add', '', 'add', '', b'1', 'F', 'c59a854a-2794-11ef-920f-80e82cda4197', '2024-07-04 14:26:48', NULL, 3, 1);
INSERT INTO `pp_menu` VALUES ('66434385-2d26-11ef-b6ec-80e82cda4197', '权限管理', 'roles', '', 'auth', '/role', b'1', 'N', NULL, '2024-06-18 11:54:01', NULL, 1, 1);
INSERT INTO `pp_menu` VALUES ('67e9a508-3841-11ef-90e3-80e82cda4197', '字典管理', 'dictionary', NULL, 'dictionary', '/dictionary', b'1', 'N', '', '2024-07-02 15:05:02', NULL, 1, 4);
INSERT INTO `pp_menu` VALUES ('6dcb89b1-2d26-11ef-b6ec-80e82cda4197', '菜单列表', 'menu:list', '/auth/menu/index', 'menu_list', '/menu/list', b'1', 'C', '66434385-2d26-11ef-b6ec-80e82cda4197', '2024-06-18 11:54:14', '', 2, 1);
INSERT INTO `pp_menu` VALUES ('7daaab22-39ce-11ef-a774-80e82cda4197', '用户修改', 'account:update', '', 'edit', '', b'1', 'F', 'c59a854a-2794-11ef-920f-80e82cda4197', '2024-07-04 14:27:28', NULL, 3, 2);
INSERT INTO `pp_menu` VALUES ('863a2f1f-39b9-11ef-a774-80e82cda4197', '角色增加', 'role:add', '', 'add', '', b'1', 'F', 'b1e4fb5c-2d42-11ef-b6ec-80e82cda4197', '2024-07-04 11:57:25', NULL, 3, 1);
INSERT INTO `pp_menu` VALUES ('90a03fac-39ce-11ef-a774-80e82cda4197', '用户删除', 'account:delete', '', 'delete', '', b'1', 'F', 'c59a854a-2794-11ef-920f-80e82cda4197', '2024-07-04 14:28:00', NULL, 3, 3);
INSERT INTO `pp_menu` VALUES ('aa6282ad-3843-11ef-90e3-80e82cda4197', '字典列表', 'dictionary:list', '/dictionary/index', 'dictionary_list', '/dictionary/list', b'1', 'C', '67e9a508-3841-11ef-90e3-80e82cda4197', '2024-07-02 15:21:12', NULL, 2, 1);
INSERT INTO `pp_menu` VALUES ('b1e4fb5c-2d42-11ef-b6ec-80e82cda4197', '角色列表', 'role:list', '/auth/role/index', 'role_list', '/role/list', b'1', 'C', '66434385-2d26-11ef-b6ec-80e82cda4197', '2024-06-18 11:54:14', '', 2, 2);
INSERT INTO `pp_menu` VALUES ('bab9f995-2794-11af-960f-80e82cda4198', '商品管理', 'product', '', 'product', '/product', b'1', 'N', NULL, '2024-05-20 10:38:55', NULL, 1, 2);
INSERT INTO `pp_menu` VALUES ('bab9f995-2794-11ef-920f-80e82cda4197', '用户管理', 'account', '', 'user', '/account', b'1', 'N', NULL, '2024-06-11 09:48:32', NULL, 1, 3);
INSERT INTO `pp_menu` VALUES ('bab9f995-2794-11ef-920f-80e82cda4198', '商品列表', 'puduct:list', '/product/index', 'product_list', '/product/list', b'1', 'C', 'bab9f995-2794-11af-960f-80e82cda4198', '2024-06-03 22:25:50', NULL, 2, 1);
INSERT INTO `pp_menu` VALUES ('c59a854a-2794-11ef-920f-80e82cda4197', '用户列表', 'account:list', '/account/index', 'user_list', '/account/list', b'1', 'C', 'bab9f995-2794-11ef-920f-80e82cda4197', '2024-06-11 09:48:50', '', 2, 1);
INSERT INTO `pp_menu` VALUES ('f5b9b6e3-39b8-11ef-a774-80e82cda4197', '菜单删除', 'menu:delete', '', 'delete', '', b'1', 'F', '6dcb89b1-2d26-11ef-b6ec-80e82cda4197', '2024-07-04 11:53:23', NULL, 3, 3);

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
INSERT INTO `pp_role_menu` VALUES ('1bad63a8-39cf-11ef-a774-80e82cda4197', '93ed1de2-2d48-11ef-b6ec-80e82cda4197', '66434385-2d26-11ef-b6ec-80e82cda4197', '2024-07-04 14:31:54', NULL);
INSERT INTO `pp_role_menu` VALUES ('1bad6685-39cf-11ef-a774-80e82cda4197', '93ed1de2-2d48-11ef-b6ec-80e82cda4197', '6dcb89b1-2d26-11ef-b6ec-80e82cda4197', '2024-07-04 14:31:54', NULL);
INSERT INTO `pp_role_menu` VALUES ('1bad66e9-39cf-11ef-a774-80e82cda4197', '93ed1de2-2d48-11ef-b6ec-80e82cda4197', 'b1e4fb5c-2d42-11ef-b6ec-80e82cda4197', '2024-07-04 14:31:54', NULL);
INSERT INTO `pp_role_menu` VALUES ('1bad6735-39cf-11ef-a774-80e82cda4197', '93ed1de2-2d48-11ef-b6ec-80e82cda4197', 'bab9f995-2794-11ef-920f-80e82cda4197', '2024-07-04 14:31:54', NULL);
INSERT INTO `pp_role_menu` VALUES ('1bad6774-39cf-11ef-a774-80e82cda4197', '93ed1de2-2d48-11ef-b6ec-80e82cda4197', 'c59a854a-2794-11ef-920f-80e82cda4197', '2024-07-04 14:31:54', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3a74-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '66434385-2d26-11ef-b6ec-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3d75-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '6dcb89b1-2d26-11ef-b6ec-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3de6-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '368bfe0d-3914-11ef-87cf-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3e2a-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '1111ce79-39a3-11ef-a774-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3e67-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', 'f5b9b6e3-39b8-11ef-a774-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3ea6-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', 'b1e4fb5c-2d42-11ef-b6ec-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3f09-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '863a2f1f-39b9-11ef-a774-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3f3f-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '0db5e793-39ce-11ef-a774-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3f73-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '2fe8c39a-39ce-11ef-a774-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3fb3-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', 'bab9f995-2794-11ef-920f-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb3fe9-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', 'c59a854a-2794-11ef-920f-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb401d-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '6582716e-39ce-11ef-a774-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb406a-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '7daaab22-39ce-11ef-a774-80e82cda4197', '2024-07-04 14:30:40', NULL);
INSERT INTO `pp_role_menu` VALUES ('efcb40a0-39ce-11ef-a774-80e82cda4197', 'd8705bf2-2d47-11ef-b6ec-80e82cda4197', '90a03fac-39ce-11ef-a774-80e82cda4197', '2024-07-04 14:30:40', NULL);

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
