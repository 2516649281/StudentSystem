package com.chunfeng;

import com.chunfeng.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentSystemApplicationTests {

    @Autowired
    private IStudentService studentService;

    @Test
    void contextLoads() {
        System.out.println(studentService.lookAllUser());
    }

}
