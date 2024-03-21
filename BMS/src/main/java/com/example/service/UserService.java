package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.R;
import com.example.entity.User;

public interface UserService extends IService<User> {
    R getUser(User user);
    R addUser(User user);
    R deleteUser(User user);
    R updateUser(User user);
}
