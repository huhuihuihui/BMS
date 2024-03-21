package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.R;
import com.example.entity.Book;

public interface BookService extends IService<Book> {
    R addBook(Book book);
    R showBook(int currentPage);
    R selectBook(Book book ,int currentPage);
    R updateBook(Book book);
    R deleteBook(Book book);
}
