package com.chunfeng.service.impl;

import com.chunfeng.entity.Student;
import com.chunfeng.mapper.StudentMapper;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.ServiceEnum;
import com.chunfeng.service.IStudentService;
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
 * 学生业务层实现类
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements IStudentService {

    /**
     * 获取当前时间
     */
    private final Date date = SqlDateUtils.date;
    /**
     * 学生Mapper注入
     */
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 注入当前用户
     */
    @Autowired
    private SqlDateUtils sqlDateUtils;

    /**
     * 查看所有学生信息
     *
     * @return JSON
     */
    @Override
    @Cacheable(value = "student")
    public JsonRequest<List<Student>> lookAllUser() {
        List<Student> students = studentMapper.selectAllStudent();
        if (students.isEmpty()) {
            log.warn("没有查询到任何学生信息!");
            return JsonRequest.error(ServiceEnum.NOT_FOUND);
        }
        log.info("已查出{}条学生信息", students.size());
        return JsonRequest.success(students);
    }

    /**
     * 查看一个学生信息
     *
     * @param studentId 学生Id
     * @return JSON
     */
    @Override
    @Cacheable(value = "student", key = "#studentId")
    public JsonRequest<Student> lookOneStudent(Integer studentId) {
        Student student = studentMapper.selectStudentById(studentId);
        if (Objects.isNull(student)) {
            log.warn("没有查询到任何学生信息!");
            return JsonRequest.error(ServiceEnum.NOT_FOUND);
        }
        log.info("已查出学生姓名为{}的学生信息", student.getName());
        return JsonRequest.success(student);
    }

    /**
     * 根据班级Id查询学生
     *
     * @param classId 班级Id
     * @return JSON
     */
    @Override
    @Cacheable(value = "student", key = "#classId")
    public JsonRequest<List<Student>> lookStudentByClass(Integer classId) {
        //查询逻辑
        List<Student> students = studentMapper.selectStudentByClassId(new Integer[]{classId});
        if (students.isEmpty()) {
            log.warn("未查询到任何学生信息!");
            return JsonRequest.error(ServiceEnum.NOT_FOUND);
        }
        log.info("已查询到{}条学生信息", students.size());
        return JsonRequest.success(students);
    }

    /**
     * 储存一条学生信息
     *
     * @param student 待添加的学生信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "student", allEntries = true)
    public JsonRequest<Integer> saveOneStudent(Student student) {
        student.setCreateUser(sqlDateUtils.getCurrentUserId());
        student.setCreateTime(date);
        Integer column = studentMapper.insertStudent(student);
        if (column < 1) {
            log.error("储存学生姓名为{}的操作失败!返回值{}", student.getName(), column);
            return JsonRequest.error(ServiceEnum.INSERT_ERROR);
        }
        log.info("储存学生姓名为{}的操作成功!返回值{}", student.getName(), column);
        return JsonRequest.success(column);
    }

    /**
     * 更新一条学生信息
     *
     * @param student 待修改的学生信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "student", allEntries = true)
    public JsonRequest<Integer> updateOneStudentById(Student student) {
        //判断该学生是否存在
        Student studentById = studentMapper.selectStudentById(student.getId());
        if (Objects.isNull(studentById)) {
            log.warn("没有查到学生Id为{}的学生", student.getId());
            return JsonRequest.error(ServiceEnum.NOT_FOUND);
        }
        student.setUpdateUser(sqlDateUtils.getCurrentUserId());
        student.setUpdateTime(date);
        //执行修改逻辑
        Integer column = studentMapper.updateStudentById(student);
        if (column < 1) {
            log.error("修改学生Id为{}的操作失败!返回值{}", student.getId(), column);
            return JsonRequest.error(ServiceEnum.UPDATE_ERROR);
        }
        log.info("修改学生Id为{}的操作成功!返回值{}", student.getId(), column);
        return JsonRequest.success(column);
    }

    /**
     * 批量删除学生
     *
     * @param ids 学生信息
     * @return JSON
     */
    @Override
    @CacheEvict(value = "student", allEntries = true)
    public JsonRequest<Integer> deleteStudents(Integer[] ids) {
        //判断学生个数
        Integer count = studentMapper.countStudentById(ids);
        if (count < ids.length) {
            log.warn("待删除的学生个数与数据库已存在的学生个数不符!数据库实际存在{}个,需要{}个", count, ids.length);
            return JsonRequest.error(ServiceEnum.NOT_FOUND);
        }
        //执行删除逻辑
        Integer column = studentMapper.deleteStudentById(ids);
        if (column < 1) {
            log.error("删除学生失败!返回值{}", column);
            return JsonRequest.error(ServiceEnum.DELETE_ERROR);
        }
        log.info("已删除{}条学生信息!返回值{}", count, column);
        return JsonRequest.success(column);
    }
}
