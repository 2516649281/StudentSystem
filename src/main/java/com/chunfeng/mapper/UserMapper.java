package com.chunfeng.mapper;

import com.chunfeng.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
public interface UserMapper {
    /**
     * 根据用户名查询一个用户
     *
     * @param name 用户名
     * @return 用户信息
     */
    User selectOneUserByName(String username);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    List<User> selectAllUser();

    /**
     * 根据用户Id查询一个用户
     *
     * @param userId 用户Id
     * @return 用户信息
     */
    User selectUserById(Integer userId);

    /**
     * 添加一条用户信息
     *
     * @param user 用户信息
     * @return 影响行数
     */
    Integer insertUser(User user);

    /**
     * 根据用户Id修改一条用户信息
     *
     * @param user 用户信息
     * @return 影响行数
     */
    Integer updateUserById(User user);

    /**
     * 批量删除用户
     *
     * @param userIds 用户Id
     * @return 影响行数
     */
    Integer deleteUserById(@Param("userIds") Integer[] userIds);

    /**
     * 根据Id获取数据库中的用户个数
     *
     * @param userIds 用户Id
     * @return 个数
     */
    Integer countById(@Param("userIds") Integer[] userIds);
}
