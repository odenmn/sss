package com.xjx.example.dao.impl;

import com.xjx.example.dao.UserDao;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, salt, is_banned) VALUES (?,?,?,?)";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getSalt());
            pstmt.setBoolean(4, user.isBanned());
            return pstmt.executeUpdate() > 0;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                user.setBanned(rs.getBoolean("is_banned"));
                return user;
            }
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                user.setBanned(rs.getBoolean("is_banned"));
                return user;
            }
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, salt = ?, is_banned = ? WHERE id = ?";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getSalt());
            pstmt.setBoolean(4, user.isBanned());
            pstmt.setInt(5, user.getId());
            return pstmt.executeUpdate() > 0;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate() > 0;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }
}
