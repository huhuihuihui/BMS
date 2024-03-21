package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.R;
import com.example.entity.Book;
import com.example.entity.Record;
import com.example.entity.Student;
import com.example.mapper.BookMapper;
import com.example.mapper.RecordMapper;
import com.example.mapper.StudentMapper;
import com.example.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    StudentMapper studentMapper;

    @Override
    public R addRecord(String bookId, String studentId) {
        if(bookId!=null && studentId!=null){
            LocalDate currentDate = LocalDate.now();  //获取现在时间
            LocalDate deadline = currentDate.plusDays(30);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Record record = new Record();
            //通过书籍id查书籍名
            Book book = bookMapper.selectById(bookId);
            if(book.getBookStatus().equals("已借出"))
                return R.fail().message("该书籍已经借出，不可借用");
            record.setBookName(book.getBookName());
            //通过学生id查学生姓名
            Student stu = studentMapper.selectById(studentId);
            if(stu.getStudentStatus().equals("冻结"))
                return R.fail().message("该用户已冻结");
            record.setStudentName(stu.getStudentName());

            LambdaQueryWrapper<Record> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Record::getStudentId,studentId);
            Record eixstRecord = recordMapper.selectOne(lqw);
            //是否存在超过截止日期未归还的情况
            if(eixstRecord!=null && eixstRecord.getRTime()==null && eixstRecord.getDeadline().compareTo(currentDate.format(formatter)) < 0){
                LambdaUpdateWrapper<Student> luwstu = new LambdaUpdateWrapper<>();
                luwstu.eq(Student::getStudentId,studentId);
                luwstu.set(Student::getStudentStatus,"冻结"); //将学生表状态改成冻结
                studentMapper.update(stu,luwstu);
                return R.fail().message("该用户已冻结");
            }

            record.setBookId(bookId);
            record.setStudentId(studentId);
            record.setRecordStatus("未还");

            record.setBTime(currentDate.format(formatter));  //设置借书时间
            record.setDeadline(deadline.format(formatter)); //截至加30天

            if(recordMapper.insert(record)!=0){
                LambdaUpdateWrapper<Book> luw = new LambdaUpdateWrapper<>();
                luw.eq(Book::getBookId,bookId);
                luw.set(Book::getBookStatus,"已借出");
                int update = bookMapper.update(book, luw);
                if(update!=0)
                    return R.ok().message("借阅成功");
                else
                    return R.fail().message("借阅失败");
            }
            else
                return R.fail().message("借阅失败");
        }else
            return R.fail().message("信息不全！无法保存");
    }

    @Override
    public R showRecord(int currentPage) {
        IPage page = new Page(currentPage,6);
        LambdaQueryWrapper<Record> lqw = new LambdaQueryWrapper<>();
        lqw.orderBy(true,true,Record::getRecordId);
        recordMapper.selectPage(page,lqw);
        return R.ok().data("records",page.getRecords()).data("total",page.getTotal());
    }

    @Override
    public R selectRecord(Record record,int currentPage) {
        System.out.println(record);
        IPage page = new Page(currentPage,6);
        LambdaQueryWrapper<Record> lqw = new LambdaQueryWrapper<>();
        lqw.likeRight(record.getBookId()!=null,Record::getBookId,record.getBookId());
        lqw.like(record.getBookName()!=null,Record::getBookName,record.getBookName());
        lqw.like(record.getStudentName()!=null,Record::getStudentName,record.getStudentName());
        lqw.likeRight(record.getStudentId()!=null,Record::getStudentId,record.getStudentId());
        lqw.eq(record.getRecordStatus()!=null,Record::getRecordStatus,record.getRecordStatus());
        lqw.orderBy(true,true,Record::getRecordId);
        recordMapper.selectPage(page,lqw);
        return R.ok().data("records",page.getRecords()).data("total",page.getTotal());
    }

    @Override
    public R updateRecord(Record record) {
        System.out.println(record);
        Record re = recordMapper.selectById(record.getRecordId());
        if(record.getRecordStatus().equals("已还"))
            return R.fail().message("该学生已归还");
        LocalDate currentDate = LocalDate.now();  //获取归还时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LambdaUpdateWrapper<Record> luw = new LambdaUpdateWrapper<>();
        luw.eq(Record::getRecordId,record.getRecordId());
        luw.set(Record::getRecordStatus,"已还");  //设置归还状态
        luw.set(Record::getRTime,currentDate.format(formatter)); //设置归还时间

        LambdaUpdateWrapper<Book> luwbook = new LambdaUpdateWrapper<>();
        Book book = bookMapper.selectById(re.getBookId()); //获取数据库里对应bookId
        luwbook.eq(Book::getBookId,re.getBookId());
        luwbook.set(Book::getBookStatus,"可借阅");
        if(bookMapper.update(book,luwbook)==0)
            return R.fail().message("归还失败");

        //如果该学生是冻结状态就恢复正常
        Student stu = studentMapper.selectById(re.getStudentId());
        if(stu.getStudentStatus().equals("冻结")){
            LambdaUpdateWrapper<Student> luwstu = new LambdaUpdateWrapper<>();
            luwstu.eq(Student::getStudentId,stu.getStudentId());
            luwstu.set(Student::getStudentStatus,"正常");
            studentMapper.update(stu, luwstu);
        }

        if(this.update(re,luw))
            return R.ok().message("归还成功");
        else
            return R.fail().message("归还失败");
    }

    @Override
    public R deleteRecord(Record record) {
        int i = recordMapper.deleteById(record.getRecordId());
        if(i!=0)
            return R.ok().message("删除成功");
        else
            return R.fail().message("删除失败");
    }
}
