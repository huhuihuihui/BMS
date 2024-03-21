package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {
    @TableId(type = IdType.ASSIGN_ID)
    private String studentId;
    private String studentName;
    private String sex;
    private String telephone;
    private String studentMail;
    private String address;
    private String studentStatus;
}
