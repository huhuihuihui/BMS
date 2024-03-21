package com.example.controller;

import com.example.common.R;
import com.example.entity.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public R addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/{currentPage}")
    public R showBook(@PathVariable int currentPage){
        return bookService.showBook(currentPage);
    }

    @PostMapping("/select/{currentPage}")
    public R selectBook(@RequestBody Book book,@PathVariable int currentPage){
        return bookService.selectBook(book,currentPage);
    }

    @PostMapping("/update")
    public R updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping("/delete")
    public R deleteBook(@RequestBody Book book){
        return bookService.deleteBook(book);
    }
}
