/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : bms

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2024-03-16 15:42:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` varchar(30) NOT NULL,
  `book_name` varchar(30) DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `press` varchar(30) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `book_status` varchar(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('10000', '小王子', '哈哈', '', '23', '可借阅', '其他');
INSERT INTO `book` VALUES ('10002', '大数据', '科学社', '北京出版社', '30', '已借出', '文学');
INSERT INTO `book` VALUES ('10003', 'java基础', '黑马', '浙江出版社', '80', '可借阅', '计算机');
INSERT INTO `book` VALUES ('10004', 'java基础', '黑马', '浙江出版社', '80', '可借阅', '计算机');
INSERT INTO `book` VALUES ('10005', 'java基础', '黑马', '浙江出版社', '80', '已借出', '计算机');
INSERT INTO `book` VALUES ('10006', '算法', '李大宝', '成都社', '40', '可借阅', '文学');
INSERT INTO `book` VALUES ('10007', 'java基础', '黑马', '浙江出版社', '58', '可借阅', '计算机');
INSERT INTO `book` VALUES ('10009', '数据库概论', '长青', '', '45', '可借阅', '计算机');
INSERT INTO `book` VALUES ('10010', '创业技能书', '菲菲', '', '10', '可借阅', '其他');
INSERT INTO `book` VALUES ('10011', '哈哈', '', '', '42', '可借阅', '文学');
INSERT INTO `book` VALUES ('12345', 'java', '', '', '20', '可借阅', '计算机');
INSERT INTO `book` VALUES ('66666', '念猪猪', '邦邦', '念念大公司', '99999', '可借阅', '文学');
INSERT INTO `book` VALUES ('88888', '傻逼念念', '邦邦', '念念大侠', '99999', '可借阅', '文学');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外键',
  `student_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `book_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外键',
  `book_name` varchar(30) DEFAULT NULL,
  `record_status` varchar(30) DEFAULT NULL,
  `b_time` varchar(30) DEFAULT NULL,
  `r_time` varchar(30) DEFAULT NULL,
  `deadline` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `student_id` (`student_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('13', '100', '李四', '10000', '小王子', '已还', '2023-05-25', '2023-05-25', '2023-06-24');
INSERT INTO `record` VALUES ('15', '104', '李七', '10004', 'java基础', '已还', '2023-05-27', '2023-09-09', '2023-06-26');
INSERT INTO `record` VALUES ('18', '105', '离离', '10005', 'java基础', '未还', '2023-09-09', null, '2023-10-09');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` varchar(30) NOT NULL,
  `student_name` varchar(30) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `student_mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `student_status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('100', '李四', '', '123645', '', '', null);
INSERT INTO `student` VALUES ('101', '王五', '男', '570282', null, null, '冻结');
INSERT INTO `student` VALUES ('103', '路六', '女', '782145', '523@qq.com', '二区', '正常');
INSERT INTO `student` VALUES ('104', '李七', '男', '123498179', '234@qq.com', '一区', '正常');
INSERT INTO `student` VALUES ('105', '离离', '女', '62359', '6285963@qq.com', '八区', '正常');
INSERT INTO `student` VALUES ('106', '暑促', '男', '9514', '456@qq.com', '', '正常');
INSERT INTO `student` VALUES ('108', '道德', '女', '62333333', 'fjao@qq.com', '', '正常');
INSERT INTO `student` VALUES ('110', '蜀黍', '男', '780247', 'hhiijk@qq.com', '', '正常');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', '李四', '123', '1660961170@qq.com', '2023-05-30 15:09:28');
INSERT INTO `user` VALUES ('8', '王五', '123', '2172613527@qq.com', '2023-05-30 15:15:07');
INSERT INTO `user` VALUES ('10', '123', '79274', '2172613527@qq.com', '2023-06-01 13:59:21');
INSERT INTO `user` VALUES ('11', '4718', 'y9174', '61234h@qq.com', '2023-05-31 17:00:55');
