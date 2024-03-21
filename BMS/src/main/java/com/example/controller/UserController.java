package com.example.controller;

import com.example.common.R;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public R getUser(@RequestBody User user){
        return userService.getUser(user);
    }

    @PostMapping("/sign")
    public R addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/delete")
    public R deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }

    @PostMapping("/update")
    public R updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
