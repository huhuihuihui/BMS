package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.R;
import com.example.entity.Record;

public interface RecordService extends IService<Record> {
    R addRecord(String bookId, String studentId); //找到书，输入学生信息
    R showRecord(int currentPage);
    R selectRecord(Record record, int currentPage);   //书籍信息/学生信息查询借阅记录
    R updateRecord(Record record);  //已还就不可更改
    R deleteRecord(Record record);
}
