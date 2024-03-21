package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.R;
import com.example.entity.Student;

public interface StudentService extends IService<Student> {
    R addStudent(Student stu);
    R showStudents(int currentPage);
    R selectStudent(Student stu,int currentPage);
    R updateStudent(Student stu);
    R deleteStudent(Student stu);
}
