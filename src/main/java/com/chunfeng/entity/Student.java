package com.chunfeng.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * 学生表
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/19
 */
@Data
@NoArgsConstructor
public class Student implements Serializable {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 地址
     */
    private String address;
    /**
     * 班级Id
     */
    private Integer classId;
    /**
     * 创建用户
     */
    private Integer createUser;
    /**
     * 修改用户
     */
    private Integer updateUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
