package com.xjx.example.dao;

import com.xjx.example.entity.Report;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;


public interface UserDao {
    // 添加用户
    boolean addUser(User user) throws SQLException;

    // 根据用户名获取用户
    User getUserByUsername(String username) throws SQLException;

    User getUserByUserId(int userId) throws SQLException;

    // 根据用户ID获取用户
    User getUserById(int userId) throws SQLException;

    // 搜索用户
    List<User> searchUsers(String keyword);

    //更新用户信息
    boolean updateUser(User user) throws SQLException;

    //删除用户
    boolean deleteUser(int userId) throws SQLException;

    //添加举报
    boolean addReport(Report report) throws SQLException;
}
