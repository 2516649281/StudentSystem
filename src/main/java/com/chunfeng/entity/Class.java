package com.chunfeng.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * 班级表
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
@Data
@NoArgsConstructor
public class Class implements Serializable {
    /**
     * 班级Id
     */
    private Integer id;
    /**
     * 班级名称
     */
    private String className;
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
