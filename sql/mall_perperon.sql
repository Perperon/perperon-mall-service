-- MySQL dump 10.13  Distrib 5.7.38, for Win64 (x86_64)
--
-- Host: localhost    Database: mall_perperon
-- ------------------------------------------------------
-- Server version	5.7.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pp_account`
--

DROP TABLE IF EXISTS `pp_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pp_account` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `icon` varchar(50) DEFAULT NULL COMMENT '头像',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `remark` varchar(200) DEFAULT NULL COMMENT '注释',
  `status` bit(1) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) DEFAULT NULL COMMENT '创建人',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户名';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pp_account`
--

LOCK TABLES `pp_account` WRITE;
/*!40000 ALTER TABLE `pp_account` DISABLE KEYS */;
INSERT INTO `pp_account` VALUES ('c70fc552-15a5-11ef-a15d-d8bbc147c16b','test','$2a$10$7TQ8LedI/kng1ARxBBfSa.Wvkh81iO9qaiKZuQbjniFuOCuGn6KXG',NULL,'1443528055@qq.com','perperon',NULL,0x01,'2024-05-19 06:05:17',NULL,NULL),('f54963d8-15a3-11ef-a15d-d8bbc147c16b','admin','$2a$10$OED3aEs2lpHjSAhZ..Of6.y6ZpJjKXui46CDagQoQ44uVliG92b76',NULL,'1443528055@qq.com','perperon',NULL,0x01,'2024-05-18 16:00:00',NULL,NULL);
/*!40000 ALTER TABLE `pp_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pp_account_role`
--

DROP TABLE IF EXISTS `pp_account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pp_account_role` (
  `id` varchar(40) NOT NULL COMMENT '主键标识',
  `account_id` varchar(40) NOT NULL COMMENT '用户标识',
  `role_id` varchar(40) NOT NULL COMMENT '角色标识',
  `user_id` varchar(40) DEFAULT NULL COMMENT '创建人',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pp_account_role`
--

LOCK TABLES `pp_account_role` WRITE;
/*!40000 ALTER TABLE `pp_account_role` DISABLE KEYS */;
INSERT INTO `pp_account_role` VALUES ('1','c70fc552-15a5-11ef-a15d-d8bbc147c16b','1',NULL,'2024-05-20 02:37:01'),('2','c70fc552-15a5-11ef-a15d-d8bbc147c16b','2',NULL,'2024-05-20 02:37:12'),('3','f54963d8-15a3-11ef-a15d-d8bbc147c16b','1',NULL,'2024-05-20 02:37:35');
/*!40000 ALTER TABLE `pp_account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pp_menu`
--

DROP TABLE IF EXISTS `pp_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pp_menu` (
  `id` varchar(40) NOT NULL COMMENT '主键标识',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `perms` varchar(50) DEFAULT NULL COMMENT '权限标识;',
  `component` varchar(50) DEFAULT NULL COMMENT '组件路径;',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标;',
  `path` varchar(50) DEFAULT NULL COMMENT '菜单路径;',
  `status` bit(1) DEFAULT NULL COMMENT '菜单状态;',
  `visible` bit(1) DEFAULT NULL COMMENT '是否显示;',
  `parent_id` varchar(40) DEFAULT NULL COMMENT '上级菜单标识;',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) DEFAULT NULL COMMENT '创建人',
  `sort` int(11) DEFAULT NULL COMMENT '排序;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pp_menu`
--

LOCK TABLES `pp_menu` WRITE;
/*!40000 ALTER TABLE `pp_menu` DISABLE KEYS */;
INSERT INTO `pp_menu` VALUES ('1','商品管理','test:list',NULL,NULL,'/test/index',0x01,NULL,NULL,'2024-05-20 02:38:55',NULL,NULL),('2','系统管理','dept:list',NULL,NULL,'/dept/index',0x01,NULL,NULL,'2024-05-20 02:39:58',NULL,NULL),('3','商品列表','puduct:list',NULL,NULL,'/test/index',0x01,NULL,'1','2024-06-03 14:25:50',NULL,NULL);
/*!40000 ALTER TABLE `pp_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pp_role_menu`
--

DROP TABLE IF EXISTS `pp_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pp_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '主键标识',
  `role_id` varchar(40) NOT NULL COMMENT '角色标识',
  `menu_id` varchar(40) NOT NULL COMMENT '权限标识',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pp_role_menu`
--

LOCK TABLES `pp_role_menu` WRITE;
/*!40000 ALTER TABLE `pp_role_menu` DISABLE KEYS */;
INSERT INTO `pp_role_menu` VALUES ('1','1','1','2024-05-20 03:03:03',NULL),('2','2','1','2024-05-20 03:03:18',NULL),('3','1','2','2024-05-20 03:03:32',NULL);
/*!40000 ALTER TABLE `pp_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pp_roles`
--

DROP TABLE IF EXISTS `pp_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pp_roles` (
  `id` varchar(40) NOT NULL COMMENT '唯一编号',
  `code` varchar(50) NOT NULL COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '名字',
  `status` bit(1) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` varchar(40) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(40) DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pp_roles`
--

LOCK TABLES `pp_roles` WRITE;
/*!40000 ALTER TABLE `pp_roles` DISABLE KEYS */;
INSERT INTO `pp_roles` VALUES ('1','manager','管理员',0x01,'2024-05-20 02:35:56',NULL,NULL,NULL),('2','dept','部门管理',0x01,'2024-05-20 02:36:28',NULL,NULL,NULL);
/*!40000 ALTER TABLE `pp_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mall_perperon'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-03 23:50:05
