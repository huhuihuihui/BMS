package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {
    @TableId
    private Integer userId;
    private String userName;
    private String password;
    private String userMail;
    private LocalDateTime registerTime;
}
