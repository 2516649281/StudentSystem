package com.chunfeng.controller;

import com.chunfeng.entity.Class;
import com.chunfeng.result.JsonRequest;
import com.chunfeng.result.ServiceController;
import com.chunfeng.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级Controller
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
@RestController
@RequestMapping("/class")
public class ClassController extends ServiceController {

    /**
     * 班级业务层注入
     */
    @Autowired
    private IClassService classService;

    /**
     * 查看所有班级
     *
     * @return JSON
     */
    @GetMapping
    public JsonRequest<List<Class>> lookAllClass() {
        return classService.lookAllClass();
    }

    /**
     * 查看一个班级
     *
     * @param classId 班级Id
     * @return JSON
     */
    @GetMapping("/{classId}")
    public JsonRequest<Class> lookOneClassById(@PathVariable Integer classId) {
        return classService.lookOneClassById(classId);
    }

    /**
     * 存储一条班级信息
     *
     * @param aClass 待添加的班级信息
     * @return JSON
     */
    @PostMapping
    public JsonRequest<Integer> saveOneClass(@RequestBody Class aClass) {
        return classService.saveOneClass(aClass);
    }

    /**
     * 修改一条班级信息
     *
     * @param aClass 待修改的班级信息
     * @return JSON
     */
    @PutMapping
    public JsonRequest<Integer> updateOneClass(@RequestBody Class aClass) {
        return classService.updateOneClass(aClass);
    }

    /**
     * 批量删除多个班级
     *
     * @param classIds 班级Id
     * @return JSON
     */
    @DeleteMapping
    public JsonRequest<Integer> deleteClasses(@RequestBody Integer[] classIds) {
        return classService.deleteClasses(classIds);
    }
}
