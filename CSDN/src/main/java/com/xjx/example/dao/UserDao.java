package com.xjx.example.dao;

import com.xjx.example.entity.User;

import java.sql.SQLException;


public interface UserDao {
    boolean addUser(User user) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    User getUserById(int userId) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    boolean deleteUser(int userId) throws SQLException;

}
