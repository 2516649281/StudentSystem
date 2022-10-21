package com.chunfeng.service;

import com.chunfeng.entity.Class;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 班级业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
public interface IClassService {

    /**
     * 查看所有班级
     *
     * @return JSON
     */
    JsonRequest<List<Class>> lookAllClass();

    /**
     * 查看一个班级
     *
     * @param classId 班级Id
     * @return JSON
     */
    JsonRequest<Class> lookOneClassById(Integer classId);

    /**
     * 存储一条班级信息
     *
     * @param aClass 待添加的班级信息
     * @return JSON
     */
    JsonRequest<Integer> saveOneClass(Class aClass);

    /**
     * 修改一条班级信息
     *
     * @param aClass 待修改的班级信息
     * @return JSON
     */
    JsonRequest<Integer> updateOneClass(Class aClass);

    /**
     * 批量删除多个班级
     *
     * @param classIds 班级Id
     * @return JSON
     */
    JsonRequest<Integer> deleteClasses(Integer[] classIds);
}
