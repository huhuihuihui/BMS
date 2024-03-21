package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.R;
import com.example.entity.Book;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public R addBook(Book book) {
        if(book.getBookId()!=null&&book.getBookName()!=null&&book.getPrice()!=0){
            Book selectBook = bookMapper.selectById(book.getBookId());
            if(selectBook != null)
                return R.fail().message("书籍编号重复了！");
            book.setBookStatus("可借阅");
            if(bookMapper.insert(book) != 0)
                return R.ok().message("书籍添加成功！");
            else
                return R.fail().message("添加失败！");
        }
        else
            return R.fail().message("书籍编号/书籍名称/书籍价格是必要的");
    }

    @Override
    public R showBook(int currentPage) {
        IPage page = new Page(currentPage,6);
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.orderBy(true,true,Book::getBookId);
        bookMapper.selectPage(page,lqw);
        return R.ok().data("books",page.getRecords()).data("total",page.getTotal());
    }

    @Override
    public R selectBook(Book book, int currentPage) {
        String bookId = book.getBookId();
        String bookName = book.getBookName();
        String author = book.getAuthor();
        String press = book.getPress();
        String type = book.getType();
        String bookStatus = book.getBookStatus();
        IPage page = new Page(currentPage,6);
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(bookId!=null,Book::getBookId,bookId);
        lqw.like(bookName!=null,Book::getBookName,bookName);
        lqw.like(author!=null,Book::getAuthor,author);
        lqw.like(press!=null,Book::getPress,press);
        lqw.like(type!=null,Book::getType,type);
        lqw.eq(bookStatus!=null,Book::getBookStatus,bookStatus);
        bookMapper.selectPage(page,lqw);
        return R.ok().data("books",page.getRecords()).data("total",page.getTotal());
    }

    @Override
    public R updateBook(Book book) {
        System.out.println(book);
        Book b = bookMapper.selectById(book.getBookId());
        if(book.getBookName()!="") b.setBookName(book.getBookName());
        if(book.getAuthor()!="") b.setAuthor(book.getAuthor());
        if(book.getPress()!="") b.setPress(book.getPress());
        if(book.getType()!="") b.setType(book.getType());
        if(book.getPrice()!=0) b.setPrice(book.getPrice());
        int i = bookMapper.updateById(b);
        if(i > 0){
            return R.ok().message("书籍信息更新成功").data("book",b);
        }
        else
            return R.fail().message("书籍信息更新失败");
    }

    @Override
    public R deleteBook(Book book) {
        bookMapper.deleteById(book.getBookId());
        return R.ok().message("该书籍已删除");
    }
}
