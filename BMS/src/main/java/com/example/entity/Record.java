package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Record {
    @TableId
    private Long recordId;
    private String studentId;
    private String studentName;
    private String bookId;
    private String bookName;
    private String recordStatus;
    private String bTime;
    private String rTime;
    private String deadline;
}
