package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.R;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public R addStudent(Student stu) {
        stu.setStudentStatus("正常");
        Student s = studentMapper.selectById(stu.getStudentId());
        if(s == null){
            Boolean bool = this.save(stu);
            if(bool)
                return R.ok().message("添加学生信息成功");
            else
                return R.fail().message("学生信息添加失败");
        } else
            return R.fail().message("要添加的学生已经存在");
    }

    @Override
    public R showStudents(int currentPage){
        IPage page = new Page(currentPage,6);
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.orderBy(true,true,Student::getStudentId);
        studentMapper.selectPage(page,lqw);
        return R.ok().data("students",page.getRecords()).data("total",page.getTotal());
    }

    @Override
    public R selectStudent(Student stu,int currentPage) {
        System.out.println(stu);
        String stuId = stu.getStudentId();
        String stuName = stu.getStudentName();
        String address = stu.getAddress();
        String stuStatus = stu.getStudentStatus();
        IPage page = new Page(currentPage,6);
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.likeRight(stuId!=null,Student::getStudentId,stuId);
        lqw.like(stuName!=null,Student::getStudentName,stuName);
        lqw.like(address!=null,Student::getAddress,address);
        lqw.eq(stuStatus!=null,Student::getStudentStatus,stuStatus);
        studentMapper.selectPage(page,lqw);
        return R.ok().data("students",page.getRecords()).data("total",page.getTotal());
    }

    @Override
    public R updateStudent(Student stu) {
        System.out.println(stu);
        LambdaUpdateWrapper<Student> luw = new LambdaUpdateWrapper<>();
        luw.eq(Student::getStudentId, stu.getStudentId());
        luw.set(stu.getStudentName()!="", Student::getStudentName, stu.getStudentName());
        luw.set(stu.getStudentMail()!="", Student::getStudentMail, stu.getStudentMail());
        luw.set(stu.getSex()!="", Student::getSex, stu.getSex());
        luw.set(StringUtils.isNotBlank(stu.getTelephone()), Student::getTelephone, stu.getTelephone());
        luw.set(stu.getStudentStatus()!="", Student::getStudentStatus, stu.getStudentStatus());
        Boolean bool = this.update(stu, luw);
        if(bool){
            Student student = studentMapper.selectById(stu.getStudentId());
            return R.ok().message("学生信息更新成功").data("student",student);
        }
        else
            return R.fail().message("学生信息更新失败");
    }

    @Override
    public R deleteStudent(Student stu) {
        studentMapper.deleteById(stu.getStudentId());
        return R.ok().message("该学生已删除");
    }
}
