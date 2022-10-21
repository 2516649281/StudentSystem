CREATE DATABASE student_system;

use student_system;

CREATE TABLE `student`
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '学生编号',
    `name`        VARBINARY(32) NOT NULL COMMENT '学生姓名',
    `age`         INT           NOT NULL COMMENT '学生年龄',
    `sex`         INT           NOT NULL COMMENT '学生性别(0女,1男)',
    `address`     VARCHAR(1024) NOT NULL COMMENT '学生地址',
    `class_id`    INT           NOT NULL COMMENT '学生班级',
    `create_user` INT           NOT NULL COMMENT '创建用户',
    `update_user` INT COMMENT '修改用户',
    `create_time` DATETIME      NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '学生表';

CREATE TABLE `class`
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '班级编号',
    `class_name`  VARCHAR(32) NOT NULL COMMENT '班级名称',
    `create_user` INT         NOT NULL COMMENT '创建用户',
    `update_user` INT COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '班级表';

CREATE TABLE `user`
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户编号',
    `username`    VARCHAR(32)  NOT NULL UNIQUE COMMENT '用户名',
    `password`    VARCHAR(512) NOT NULL COMMENT '密码',
    `create_user` INT          NOT NULL COMMENT '创建用户',
    `update_user` INT COMMENT '修改用户',
    `create_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '用户表';