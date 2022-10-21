package com.chunfeng.mapper;

import com.chunfeng.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生Mapper
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
public interface StudentMapper {
    /**
     * 查看所有学生
     *
     * @return 学生列表
     */
    List<Student> selectAllStudent();

    /**
     * 根据学生Id查询单个学生
     *
     * @param studentId 学生Id
     * @return 学生信息
     */
    Student selectStudentById(Integer studentId);

    /**
     * 根据班级Id查询学生
     *
     * @param classIds 班级Id
     * @return JSON
     */
    List<Student> selectStudentByClassId(@Param("classIds") Integer[] classIds);

    /**
     * 添加一条学生信息
     *
     * @param student 待添加的学生信息
     * @return 影响行数
     */
    Integer insertStudent(Student student);

    /**
     * 修改一条学生信息
     *
     * @param student 待修改的学生信息
     * @return 影响行数
     */
    Integer updateStudentById(Student student);

    /**
     * 批量删除学生信息
     *
     * @param studentIds 学生编号
     * @return 影响行数
     */
    Integer deleteStudentById(@Param("studentIds") Integer[] studentIds);

    /**
     * 根据Id记录学生个数
     *
     * @param studentIds 学生Id
     * @return 学生个数
     */
    Integer countStudentById(@Param("studentIds") Integer[] studentIds);
}
