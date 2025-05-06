

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, 'Java001班');
INSERT INTO `class` VALUES (2, 'C++001班');
INSERT INTO `class` VALUES (3, '前端001班');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'Java');
INSERT INTO `course` VALUES (2, 'C++');
INSERT INTO `course` VALUES (3, 'MySQL');
INSERT INTO `course` VALUES (4, '操作系统');
INSERT INTO `course` VALUES (5, '计算机网络');
INSERT INTO `course` VALUES (6, '数据结构');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salary` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (1, '马云', '老板', 1500000.00);
INSERT INTO `emp` VALUES (2, '马化腾', '老板', 1800000.00);
INSERT INTO `emp` VALUES (3, '鑫哥', '讲师', 10000.00);
INSERT INTO `emp` VALUES (4, '博哥', '讲师', 12000.00);
INSERT INTO `emp` VALUES (5, '平姐', '学管', 9000.00);
INSERT INTO `emp` VALUES (6, '莹姐', '学管', 8000.00);
INSERT INTO `emp` VALUES (7, '孙悟空', '游戏角色', 956.80);
INSERT INTO `emp` VALUES (8, '猪悟能', '游戏角色', 700.50);
INSERT INTO `emp` VALUES (9, '沙和尚', '游戏角色', 333.30);

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` bigint NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `chinese` decimal(4, 1) NULL DEFAULT NULL,
  `math` decimal(4, 1) NULL DEFAULT NULL,
  `english` decimal(4, 1) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1, '唐三藏', 134.0, 98.0, 66.0);
INSERT INTO `exam` VALUES (3, '猪悟能', 176.0, 98.0, 90.0);
INSERT INTO `exam` VALUES (4, '曹孟德', 140.0, 90.0, 67.0);
INSERT INTO `exam` VALUES (5, '刘玄德', 111.0, 145.0, 45.0);
INSERT INTO `exam` VALUES (6, '孙权', 140.0, 73.0, 78.5);
INSERT INTO `exam` VALUES (7, '宋公明', 150.0, 125.0, 30.0);
INSERT INTO `exam` VALUES (8, '张飞', 54.0, 128.0, NULL);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `score` float NULL DEFAULT NULL,
  `student_id` bigint NULL DEFAULT NULL,
  `course_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, 80, 1, 1);
INSERT INTO `score` VALUES (2, 98.5, 1, 3);
INSERT INTO `score` VALUES (3, 33, 1, 5);
INSERT INTO `score` VALUES (4, 98, 1, 6);
INSERT INTO `score` VALUES (5, 60, 2, 1);
INSERT INTO `score` VALUES (6, 59.5, 2, 5);
INSERT INTO `score` VALUES (7, 33, 3, 1);
INSERT INTO `score` VALUES (8, 68, 3, 3);
INSERT INTO `score` VALUES (9, 99, 3, 5);
INSERT INTO `score` VALUES (10, 67, 4, 1);
INSERT INTO `score` VALUES (11, 23, 4, 3);
INSERT INTO `score` VALUES (12, 56, 4, 5);
INSERT INTO `score` VALUES (13, 72, 4, 6);
INSERT INTO `score` VALUES (14, 81, 5, 1);
INSERT INTO `score` VALUES (15, 37, 5, 5);
INSERT INTO `score` VALUES (16, 56, 6, 2);
INSERT INTO `score` VALUES (17, 43, 6, 4);
INSERT INTO `score` VALUES (18, 79, 6, 6);
INSERT INTO `score` VALUES (19, 80, 7, 2);
INSERT INTO `score` VALUES (20, 92, 7, 6);
INSERT INTO `score` VALUES (21, 80, 1, 1);
INSERT INTO `score` VALUES (22, 98.5, 1, 3);
INSERT INTO `score` VALUES (23, 60, 2, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int NULL DEFAULT 18,
  `gender` tinyint(1) NULL DEFAULT NULL,
  `enroll_date` date NULL DEFAULT NULL,
  `class_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `class_id`(`class_id` ASC) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '唐三藏', '100001', 18, 1, '1986-09-01', 1);
INSERT INTO `student` VALUES (2, '孙悟空', '100002', 18, 1, '1986-09-01', 1);
INSERT INTO `student` VALUES (3, '猪悟能', '100003', 18, 1, '1986-09-01', 1);
INSERT INTO `student` VALUES (4, '沙悟净', '100004', 18, 1, '1986-09-01', 1);
INSERT INTO `student` VALUES (5, '宋江', '200001', 18, 1, '2000-09-01', 2);
INSERT INTO `student` VALUES (6, '武松', '200002', 18, 1, '2000-09-01', 2);
INSERT INTO `student` VALUES (7, '李逹', '200003', 18, 1, '2000-09-01', 2);
INSERT INTO `student` VALUES (8, '不想毕业', '200004', 18, 1, '2000-09-01', 2);

SET FOREIGN_KEY_CHECKS = 1;
