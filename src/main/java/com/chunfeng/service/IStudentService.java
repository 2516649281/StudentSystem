package com.chunfeng.service;

import com.chunfeng.entity.Student;
import com.chunfeng.result.JsonRequest;

import java.util.List;

/**
 * 学生业务层接口
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
public interface IStudentService {
    /**
     * 查看所有学生信息
     *
     * @return JSON
     */
    JsonRequest<List<Student>> lookAllUser();

    /**
     * 查看一个学生信息
     *
     * @param studentId 学生Id
     * @return JSON
     */
    JsonRequest<Student> lookOneStudent(Integer studentId);

    /**
     * 根据班级Id查询学生
     *
     * @param classId 班级Id
     * @return JSON
     */
    JsonRequest<List<Student>> lookStudentByClass(Integer classId);

    /**
     * 储存一条学生信息
     *
     * @param student 待添加的学生信息
     * @return JSON
     */
    JsonRequest<Integer> saveOneStudent(Student student);

    /**
     * 更新一条学生信息
     *
     * @param student 待修改的学生信息
     * @return JSON
     */
    JsonRequest<Integer> updateOneStudentById(Student student);

    /**
     * 批量删除学生
     *
     * @param ids 学生信息
     * @return JSON
     */
    JsonRequest<Integer> deleteStudents(Integer[] ids);
}
