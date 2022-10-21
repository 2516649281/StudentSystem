/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8.0
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : student_system

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 21/10/2022 17:55:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`
(
    `id`          int(0)                                                       NOT NULL AUTO_INCREMENT COMMENT '班级编号',
    `class_name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名称',
    `create_user` int(0)                                                       NOT NULL COMMENT '创建用户',
    `update_user` int(0)                                                       NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime(0)                                                  NOT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                                  NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '班级表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class`
VALUES (1, '计算机01', 2, 2, '2022-10-21 10:47:29', '2022-10-21 10:47:34');
INSERT INTO `class`
VALUES (2, '计算机02', 2, 2, '2022-10-21 13:36:19', '2022-10-21 13:36:20');
INSERT INTO `class`
VALUES (6, '计算机03', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (7, '计算机04', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (8, '计算机05', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (9, '软件01', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (10, '软件02', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (11, '软件03', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (12, '软件04', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (13, '软件05', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (14, '护理01', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (15, '护理02', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (16, '护理03', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (17, '护理04', 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `class`
VALUES (18, '护理05', 2, NULL, '2022-10-21 00:00:00', NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`          int(0)                                                         NOT NULL AUTO_INCREMENT COMMENT '学生编号',
    `name`        varbinary(32)                                                  NOT NULL COMMENT '学生姓名',
    `age`         int(0)                                                         NOT NULL COMMENT '学生年龄',
    `sex`         int(0)                                                         NOT NULL COMMENT '学生性别(0女,1男)',
    `address`     varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生地址',
    `class_id`    int(0)                                                         NOT NULL COMMENT '学生班级',
    `create_user` int(0)                                                         NOT NULL COMMENT '创建用户',
    `update_user` int(0)                                                         NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime(0)                                                    NOT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                                    NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student`
VALUES (1, 0xEFBBBFE5B08FE6988E, 18, 1, '北京', 1, 2, 2, '2022-10-21 09:39:47', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (2, 0xE5B08FE7BAA2, 18, 0, '上海', 9, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (3, 0xE5B08FE58D8E, 19, 1, '武汉', 8, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (8, 0xE69D8EE58D8E, 18, 1, '北京', 1, 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `student`
VALUES (9, 0x73747564656E7431, 18, 0, '上海', 9, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (10, 0x73747564656E7432, 18, 0, '上海', 1, 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `student`
VALUES (11, 0x73747564656E7433, 18, 0, '北京', 1, 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `student`
VALUES (12, 0x73747564656E7434, 18, 1, '上海', 2, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (13, 0x73747564656E7435, 18, 0, '福建', 14, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (14, 0x73747564656E7436, 18, 0, '广州', 1, 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `student`
VALUES (15, 0x73747564656E7437, 18, 0, '深圳', 1, 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `student`
VALUES (16, 0x73747564656E7438, 18, 1, '深圳', 1, 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `student`
VALUES (17, 0x73747564656E7439, 18, 0, '深圳', 15, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (18, 0x73747564656E743130, 18, 1, '福建', 9, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (19, 0x73747564656E743131, 18, 0, '四川', 14, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (20, 0x73747564656E743132, 18, 0, '深圳', 8, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (21, 0x73747564656E743133, 18, 0, '广东', 1, 2, NULL, '2022-10-21 00:00:00', NULL);
INSERT INTO `student`
VALUES (22, 0x73747564656E743134, 18, 1, '浙江', 9, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (23, 0x73747564656E743135, 19, 1, '恩施', 1, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (24, 0x73747564656E743136, 18, 0, '重庆', 9, 2, 2, '2022-10-21 00:00:00', '2022-10-21 00:00:00');
INSERT INTO `student`
VALUES (25, 0x73747564656E743137, 18, 0, '安徽', 1, 2, NULL, '2022-10-21 00:00:00', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int(0)                                                        NOT NULL AUTO_INCREMENT COMMENT '用户编号',
    `username`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '用户名',
    `password`    varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
    `create_user` int(0)                                                        NOT NULL COMMENT '创建用户',
    `update_user` int(0)                                                        NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime(0)                                                   NOT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_username_uindex` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (2, 'root', '137870BA-3D89-4FA2-9F7D-D0C2540F619A$7C7892B6440490DDE6FDAB057B4F352D', 0, 2, '2022-10-20 00:00:00',
        '2022-10-21 00:00:00');
INSERT INTO `user`
VALUES (3, 'admin', '8367F4FC-F2A5-42F7-9960-C4C6B1A9078C$150692EA25B9E02370ED65862FC9FFFA', 0, NULL,
        '2022-10-21 00:00:00', NULL);

SET FOREIGN_KEY_CHECKS = 1;
