package com.chunfeng.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * 用户表
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
@Data
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户Id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
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
