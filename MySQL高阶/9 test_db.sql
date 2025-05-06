SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `test_db`;
CREATE DATABASE `test_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `test_db`;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES (1, '计算机系2019级1班', '学习了计算机原理、C和Java语言、数据结构和算法');
INSERT INTO `classes` VALUES (2, '中文系2019级3班', '学习了中国传统文学');
INSERT INTO `classes` VALUES (3, '自动化2019级5班', '学习了机械自动化');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'Java');
INSERT INTO `course` VALUES (2, '中国传统文化');
INSERT INTO `course` VALUES (3, '计算机原理');
INSERT INTO `course` VALUES (4, '语文');
INSERT INTO `course` VALUES (5, '高阶数学');
INSERT INTO `course` VALUES (6, '英文');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `score` decimal(3, 1) NULL DEFAULT NULL,
  `student_id` int(11) NULL DEFAULT NULL,
  `course_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (70.5, 1, 1);
INSERT INTO `score` VALUES (98.5, 1, 3);
INSERT INTO `score` VALUES (33.0, 1, 5);
INSERT INTO `score` VALUES (98.0, 1, 6);
INSERT INTO `score` VALUES (60.0, 2, 1);
INSERT INTO `score` VALUES (59.5, 2, 5);
INSERT INTO `score` VALUES (33.0, 3, 1);
INSERT INTO `score` VALUES (68.0, 3, 3);
INSERT INTO `score` VALUES (99.0, 3, 5);
INSERT INTO `score` VALUES (67.0, 4, 1);
INSERT INTO `score` VALUES (23.0, 4, 3);
INSERT INTO `score` VALUES (56.0, 4, 5);
INSERT INTO `score` VALUES (72.0, 4, 6);
INSERT INTO `score` VALUES (81.0, 5, 1);
INSERT INTO `score` VALUES (37.0, 5, 5);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sn` int(11) NOT NULL COMMENT '学号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `mail` varchar(20) COMMENT 'QQ邮箱'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 50001, '张三', 'zs@bit.com');
INSERT INTO `student` VALUES (2, 50002, '李四', 'ls@bit.com');
INSERT INTO `student` VALUES (3, 50003, '王五', 'ww@bit.com');
INSERT INTO `student` VALUES (4, 50004, '赵六', 'zl@bit.com');
INSERT INTO `student` VALUES (5, 50005, '钱七', 'qq@bit.com');

SET FOREIGN_KEY_CHECKS = 1;
