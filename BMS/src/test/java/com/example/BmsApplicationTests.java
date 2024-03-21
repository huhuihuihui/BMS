package com.example;

import com.example.common.R;
import com.example.entity.Record;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.mapper.RecordMapper;
import com.example.service.SendMailService;
import com.example.service.StudentService;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BmsApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    StudentService studentService;

    @Autowired
    RecordMapper recordMapper;

    @Test
    void contextLoads() {
//        User user = new User();
//        user.setUserName("ab");
////        user.setPassword("123");
////        user.setUserMail("2345@qq.com");
//        R r = userService.deleteUser(user);
//        System.out.println(r.toString());
        Student stu = new Student();
        stu.setStudentId("100");
        stu.setStudentName("李四");
        stu.setSex("女");
        stu.setTelephone("52243");
        R r = studentService.addStudent(stu);
        System.out.println(r.toString());
    }

    @Test
    void mail(){
    }


}
