/*
MySQL Backup
Database: shiro
Backup Time: 2019-01-21 17:13:10
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `shiro`.`permission`;
DROP TABLE IF EXISTS `shiro`.`role`;
DROP TABLE IF EXISTS `shiro`.`role_permission`;
DROP TABLE IF EXISTS `shiro`.`user`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `sex` enum('男','女') DEFAULT '男' COMMENT '性别',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `phone` char(11) NOT NULL COMMENT '联系方式',
  `status` enum('0','1') DEFAULT '1' COMMENT '用户状态',
  `role` int(11) DEFAULT NULL COMMENT '用户角色',
  PRIMARY KEY (`id`),
  KEY `role` (`role`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
BEGIN;
LOCK TABLES `shiro`.`permission` WRITE;
DELETE FROM `shiro`.`permission`;
INSERT INTO `shiro`.`permission` (`id`,`name`,`description`) VALUES (1, 'manager:grant', '给用户授权为总经理'),(2, 'user:add', '添加用户'),(3, 'user:delete', '删除用户'),(4, 'user:update', '修改用户'),(5, 'user:select', '查看用户');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shiro`.`role` WRITE;
DELETE FROM `shiro`.`role`;
INSERT INTO `shiro`.`role` (`id`,`name`,`description`) VALUES (1, '管理员', '拥有全部权限'),(2, '总经理', '拥有管理员工的权限'),(3, '员工', '没有权限，只能查看');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shiro`.`role_permission` WRITE;
DELETE FROM `shiro`.`role_permission`;
INSERT INTO `shiro`.`role_permission` (`id`,`role_id`,`permission_id`) VALUES (1, 1, 1),(2, 1, 2),(3, 1, 3),(4, 1, 4),(5, 1, 5),(6, 2, 2),(7, 2, 3),(8, 2, 5),(9, 3, 4);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shiro`.`user` WRITE;
DELETE FROM `shiro`.`user`;
INSERT INTO `shiro`.`user` (`id`,`username`,`password`,`sex`,`age`,`phone`,`status`,`role`) VALUES (1, '张三', 'ce0784c8f38ebd06933e5eaa8b9bbf71', '男', 20, '110', '0', 1),(2, 'admin', 'c41d7c66e1b8404545aa3a0ece2006ac', '男', 20, '18852895100', '1', 1),(3, '李四', '0cd22e5283ab1f0298b9e64d7de2df03', '男', 21, '123456', '1', 2),(4, '王五', '9c580a8901cfe4eecdfab6039d06a9c5', '男', 22, '666666', '1', 3);
UNLOCK TABLES;
COMMIT;
