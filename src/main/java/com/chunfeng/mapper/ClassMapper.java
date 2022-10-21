package com.chunfeng.mapper;

import com.chunfeng.entity.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级Mapper
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
public interface ClassMapper {
    /**
     * 查询所有班级
     *
     * @return 班级列表
     */
    List<Class> selectAllClass();

    /**
     * 根据班级Id查询
     *
     * @param classIds 班级Id
     * @return 班级列表
     */
    List<Class> selectClassById(@Param("classIds") Integer[] classIds);

    /**
     * 添加一条班级信息
     *
     * @param aClass 班级信息
     * @return 影响行数
     */
    Integer insertClass(Class aClass);

    /**
     * 根据班级Id修改一条班级信息
     *
     * @param aClass 班级信息
     * @return 影响行数
     */
    Integer updateClassById(Class aClass);

    /**
     * 根据班级Id批量删除班级
     *
     * @param classIds 班级Id
     * @return 影响行数
     */
    Integer deleteClassById(@Param("classIds") Integer[] classIds);

    /**
     * 根据Id值记录班级个数
     *
     * @param classIds 班级Id
     * @return 班级个数
     */
    Integer countById(@Param("classIds") Integer[] classIds);
}
