package com.xjx.example.service;

import com.xjx.example.entity.Report;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    boolean addUser(User user);
    User getUserByUsername(String username);
    User getUserById(int userId) throws SQLException;
    List<User> searchUsers(String keyword);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
    boolean register(User user);
    boolean login(String username, String password);
    // 封禁用户
    boolean banUser(int userId);
}
