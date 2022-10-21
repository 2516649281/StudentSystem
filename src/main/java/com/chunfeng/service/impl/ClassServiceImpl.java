package com.chunfeng.service.impl;

import com.chunfeng.entity.Class;
import com.chunfeng.entity.Student;
import com.chunfeng.mapper.ClassMapper;
import com.chunfeng.mapper.StudentMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.ServiceEnum;
import com.chunfeng.service.IClassService;
import com.chunfeng.utils.SqlDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * 班级业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
@Service
@Slf4j
@Transactional
public class ClassServiceImpl implements IClassService {

    /**
     * 获取当前时间
     */
    private final Date date = SqlDateUtils.date;

    /**
     * 班级mapper注入
     */
    @Autowired
    private ClassMapper classMapper;

    /**
     * 学生mapper注入
     */
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 当前用户注入
     */
    @Autowired
    private SqlDateUtils sqlDateUtils;

    /**
     * 查看所有班级
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "class")
    public JsonRequest<List<Class>> lookAllClass() {
        List<Class> classes = classMapper.selectAllClass();
        if (classes.isEmpty()) {
            log.warn("未查询到任何班级信息!");
            return JsonRequest.error(ServiceEnum.NOT_FOUND);
        }
        log.info("已查询到{}条班级信息!", classes.size());
        return JsonRequest.success(classes);
    }

    /**
     * 查看一个班级
     *
     * @param classId 班级Id
     * @return JSON
     */
    @Override
    @Cacheable(value = "class", key = "#classId")
    public JsonRequest<Class> lookOneClassById(Integer classId) {
        Class aClass = classMapper.selectClassById(new Integer[]{classId}).get(0);
        if (Objects.isNull(aClass)) {
            log.warn("未查到班级Id为{}的信息!", classId);
            return JsonRequest.error(ServiceEnum.NOT_FOUND);
        }
        log.info("已查到班级Id为{}的信息!", classId);
        return JsonRequest.success(aClass);
    }

    /**
     * 存储一条班级信息
     *
     * @param aClass 待添加的班级信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "class", allEntries = true)
    public JsonRequest<Integer> saveOneClass(Class aClass) {
        //注入
        aClass.setCreateUser(sqlDateUtils.getCurrentUserId());
        aClass.setCreateTime(date);
        Integer column = classMapper.insertClass(aClass);
        //添加逻辑
        if (column < 1) {
            log.error("添加名为{}的班级失败!返回值{}", aClass.getClassName(), column);
            return JsonRequest.error(ServiceEnum.INSERT_ERROR);
        }
        log.info("添加名为{}的班级成功!返回值{}", aClass.getClassName(), column);
        return JsonRequest.success(column);
    }

    /**
     * 修改一条班级信息
     *
     * @param aClass 待修改的班级信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "class", allEntries = true)
    public JsonRequest<Integer> updateOneClass(Class aClass) {
        //判断班级是否存在
        Class aClass1 = classMapper.selectClassById(new Integer[]{aClass.getId()}).get(0);
        if (Objects.isNull(aClass1)) {
            log.warn("Id为{}的班级不存在!", aClass.getId());
            return JsonRequest.error(ServiceEnum.NOT_FOUND);
        }
        //注入
        aClass.setUpdateTime(date);
        aClass.setUpdateUser(sqlDateUtils.getCurrentUserId());
        //修改逻辑
        Integer column = classMapper.updateClassById(aClass);
        if (column < 1) {
            log.error("Id为{}的班级修改失败!返回值{}", aClass.getId(), column);
            return JsonRequest.error(ServiceEnum.UPDATE_ERROR);
        }
        log.info("Id为{}的班级修改成功!返回值{}", aClass.getId(), column);
        return JsonRequest.success(column);
    }

    /**
     * 批量删除多个班级
     *
     * @param classIds 班级Id
     * @return JSON
     */
    @Override
    @CacheEvict(value = "class", allEntries = true)
    public JsonRequest<Integer> deleteClasses(Integer[] classIds) {
        //获取个数
        Integer count = classMapper.countById(classIds);
        if (count < classIds.length) {
            log.warn("待删除的班级个数与数据库已存在的班级个数不符!数据库实际存在{}个,需要{}个", count, classIds.length);
            return JsonRequest.error(ServiceEnum.NOT_FOUND);
        }
        //判断班级内是否存在学生
        List<Student> students = studentMapper.selectStudentByClassId(classIds);
        if (!students.isEmpty()) {
            //如果存在,则抛出异常停止删除!
            log.info("待删除的班级内存在学生!存在:{}位学生!", students.size());
            return JsonRequest.error(ServiceEnum.DELETE_ERROR);
        }
        //删除逻辑
        Integer column = classMapper.deleteClassById(classIds);
        if (column < 1) {
            log.error("删除用班级遇到错误!返回值{}", column);
            return JsonRequest.error(ServiceEnum.DELETE_ERROR);
        }
        log.info("已删除{}条班级信息!返回值{}", count, column);
        return JsonRequest.success(column);
    }
}
