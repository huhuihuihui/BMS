package com.example.controller;

import com.example.common.R;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public R addStudent(@RequestBody Student stu){
        return studentService.addStudent(stu);
    }

    @GetMapping("/{currentPage}")
    public R showStudents(@PathVariable int currentPage){
        return studentService.showStudents(currentPage);
    }

    @PostMapping("/select/{currentPage}")
    public R selectById(@RequestBody Student stu,@PathVariable int currentPage){
        return studentService.selectStudent(stu,currentPage);
    }

    @PostMapping("/update")
    public R updateStudent(@RequestBody Student stu){
        return studentService.updateStudent(stu);
    }

    @DeleteMapping("/delete")
    public R deleteStudent(@RequestBody Student stu){
        return studentService.deleteStudent(stu);
    }
}
