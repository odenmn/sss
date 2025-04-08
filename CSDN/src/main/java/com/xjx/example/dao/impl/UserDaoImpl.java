package com.xjx.example.dao.impl;

import com.xjx.example.dao.UserDao;
import com.xjx.example.entity.Report;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, salt, is_banned, role) VALUES (?,?,?,?,?)";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getSalt());
            pstmt.setBoolean(4, user.isBanned());
            pstmt.setString(5,user.getRole());
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
                user.setRole(rs.getString("role"));
                return user;
            }
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public User getUserByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
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
                user.setRole(rs.getString("role"));
                return user;
            }
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return null;
    }
    @Override
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
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
                user.setRole(rs.getString("role"));
                return user;
            }
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return null;
    }
    @Override
    public List<User> searchUsers(String keyword) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE username LIKE ? ";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
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

    @Override
    public boolean addReport(Report report) throws SQLException {
        String sql = "INSERT INTO report (user_id, article_id, comment_id, reason, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, report.getReporter().getId());
            // 判断举报的是文章还是评论
            if (report.getReportedComment() == null) {
                pstmt.setInt(2, report.getReportedArticle().getId());
                pstmt.setInt(3, 0);
            }else {
                pstmt.setInt(2, 0);
                pstmt.setInt(3, report.getReportedComment().getId());
            }
            pstmt.setString(4, report.getReason());


            pstmt.setString(5, "pending");

            return pstmt.executeUpdate() > 0;
        }
    }

}
