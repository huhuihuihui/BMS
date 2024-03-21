package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.R;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;
//
//    @Autowired
//    SendMailService sendMailService;

    @Override
    public R getUser(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, user.getUserName());
        User u = userMapper.selectOne(lqw);
        if (u == null)
            return R.fail().message("该用户不存在");
        else {
            if (u.getPassword().equals(user.getPassword()))
                return R.ok().message("登录成功").data("user", u);
            else
                return R.fail().message("密码错误");
        }
    }

    @Override
    public R addUser(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName,user.getUserName());
        User user1 = userMapper.selectOne(lqw);
        if(user1 != null){
            return R.fail().message("该用户名已用，请重新想个吧");
        }
        LocalDateTime ldt = LocalDateTime.now();
        user.setRegisterTime(ldt);
        int result = userMapper.insert(user);
        if (result > 0) {
            return R.ok().message("注册成功");
        } else
            return R.fail().message("注册失败");
    }

    @Override
    public R deleteUser(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, user.getUserName());
        userMapper.delete(lqw);
        return R.ok().message("用户已删除");
    }

    @Override
    public R updateUser(User user) {
        LambdaUpdateWrapper<User> luw = new LambdaUpdateWrapper<>();
        luw.set(User::getUserName, user.getUserName());
        luw.set(user.getPassword() != null, User::getPassword, user.getPassword());
        luw.eq(User::getUserId, user.getUserId());
        Boolean bool = this.update(user, luw);
        if (bool)
            return R.ok().message("管理员信息更新成功").data("user",user);
        else
            return R.fail().message("更新失败");
    }

//    @Override
//    public R activationAccount(String confirmCode) {
//        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(User::getConfirmCode,confirmCode);
//        User user = userMapper.selectOne(lqw);
//        boolean after = LocalDateTime.now().isAfter(user.getActivationTime());
//        if(after){
//            userMapper.deleteById(user.getUserId());
//            return R.fail().message("链接已失效，请重新注册");
//        }
//        else{
//            LambdaUpdateWrapper<User> luw = new LambdaUpdateWrapper<>();
//            luw.set(User::getIsValid, 1);
//            luw.eq(User::getUserId,user.getUserId());
//            int result = userMapper.update(user,luw);
//            if(result > 0)
//                return R.ok().message("激活成功");
//            else{
//                userMapper.deleteById(user.getUserId());
//                return R.fail().message("激活失败,请重新注册");
//            }
//        }
//    }
}
