package com.xjx.example.controller;

import com.xjx.example.entity.Report;
import com.xjx.example.entity.User;
import com.xjx.example.service.UserService;
import com.xjx.example.service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    private UserService userService = new UserServiceImpl();

    // 搜索用户
    public List<User> foundUsers(String keyword){
        return userService.searchUsers(keyword);
    }

    // 根据用户名获取用户
    public User getUserByUsername(String username){
        return userService.getUserByUsername(username);
    }

    // 删除用户
    public boolean deleteUser(int userId){
        return userService.deleteUser(userId);
    }

    // 添加举报
    public boolean addReport(Report report){
        return userService.addReport(report);
    }

}
