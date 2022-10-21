package com.chunfeng.controller;

import com.chunfeng.entity.Student;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.ServiceController;
import com.chunfeng.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生Controller
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
@RestController
@RequestMapping("/student")
public class StudentController extends ServiceController {

    /**
     * 业务层注入
     */
    @Autowired
    private IStudentService studentService;

    /**
     * 查看所有学生信息
     *
     * @return JSON
     */
    @GetMapping
    public JsonRequest<List<Student>> lookAllUser() {
        return studentService.lookAllUser();
    }

    /**
     * 查看一个学生信息
     *
     * @param studentId 学生Id
     * @return JSON
     */
    @GetMapping("/{studentId}")
    public JsonRequest<Student> lookOneStudent(@PathVariable Integer studentId) {
        return studentService.lookOneStudent(studentId);
    }

    /**
     * 根据班级Id查询学生
     *
     * @param classId 班级Id
     * @return JSON
     */
    @GetMapping("/class/{classId}")
    public JsonRequest<List<Student>> lookStudentByClass(@PathVariable Integer classId) {
        return studentService.lookStudentByClass(classId);
    }

    /**
     * 储存一条学生信息
     *
     * @param student 待添加的学生信息
     * @return JSON
     */
    @PostMapping
    public JsonRequest<Integer> saveOneStudent(@RequestBody Student student) {
        return studentService.saveOneStudent(student);
    }

    /**
     * 更新一条学生信息
     *
     * @param student 待修改的学生信息
     * @return JSON
     */
    @PutMapping
    public JsonRequest<Integer> updateOneStudentById(@RequestBody Student student) {
        return studentService.updateOneStudentById(student);
    }

    /**
     * 批量删除学生
     *
     * @param ids 学生信息
     * @return JSON
     */
    @DeleteMapping
    public JsonRequest<Integer> deleteStudents(@RequestBody Integer[] ids) {
        return studentService.deleteStudents(ids);
    }
}
