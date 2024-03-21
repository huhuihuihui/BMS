package com.example.controller;

import com.example.common.R;
import com.example.entity.Record;
import com.example.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @GetMapping("/add/{bookId}/{studentId}")
    public R addRecord(@PathVariable String bookId,@PathVariable String studentId){
        return recordService.addRecord(bookId,studentId);
    }

    @GetMapping("/{currentPage}")
    public R showRecord(@PathVariable int currentPage){
        return recordService.showRecord(currentPage);
    }

    @PostMapping("/select/{currentPage}")
    public R selectRecord(@RequestBody Record record, @PathVariable int currentPage){
        return recordService.selectRecord(record,currentPage);
    }

    @PostMapping("/update")
    public R updateRecord(@RequestBody Record record){
        return recordService.updateRecord(record);
    }

    @DeleteMapping("/delete")
    public R deleteRecord(@RequestBody Record record){
        return recordService.deleteRecord(record);
    }
}
