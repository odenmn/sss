package com.xjx.example.service.impl;

import com.xjx.example.dao.UserDao;
import com.xjx.example.dao.impl.UserDaoImpl;
import com.xjx.example.entity.Report;
import com.xjx.example.entity.User;
import com.xjx.example.service.UserService;
import com.xjx.example.util.PasswordEncryptUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    public boolean addUser(User user) {
        try {
            return userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByUsername(String username) {
        try {
            return userDao.getUserByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(int userId){
        try {
            return userDao.getUserById(userId);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public List<User> searchUsers(String keyword){
        return userDao.searchUsers(keyword);
    }

    public boolean updateUser(User user) {
        try {
            return userDao.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        try {
            return userDao.deleteUser(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(User user) {
        // 先检查用户名是否已存在
        User existingUser = getUserByUsername(user.getUsername());
        if (existingUser != null) {
            return false; // 用户名已存在，注册失败
        }
        // 生成盐值
        String salt = UUID.randomUUID().toString();
        // 对密码进行加密
        user.setSalt(salt);
        user.setPassword(PasswordEncryptUtils.encryptPassword(user.getPassword() + salt));
        return addUser(user);
    }

    public boolean login(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            String encryptedPassword = PasswordEncryptUtils.encryptPassword(password + user.getSalt());
            return encryptedPassword.equals(user.getPassword());
        }
        return false;
    }

    @Override
    public boolean addReport(Report report) {
        try {
            return userDao.addReport(report);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
