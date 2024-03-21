package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book {
    @TableId(type = IdType.ASSIGN_ID)
    private String bookId;
    private String bookName;
    private String author;
    private String press;
    private double price;
    private String bookStatus;
    private String type;
}
