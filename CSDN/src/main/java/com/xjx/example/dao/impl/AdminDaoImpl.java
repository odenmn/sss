package com.xjx.example.dao.impl;

import com.xjx.example.dao.AdminDao;
import com.xjx.example.dao.ArticleDao;
import com.xjx.example.dao.UserDao;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Report;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    // 删除文章
    @Override
    public boolean deleteArticle(int articleId) {
        String sql = "DELETE FROM article WHERE article_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            return pstmt.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除评论
    @Override
    public boolean deleteComment(int commentId){
        String sql = "DELETE FROM comment WHERE id = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, commentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 处理举报
    @Override
    public boolean processReport(int reportId, String result) {
        String sql = "UPDATE report SET status = ?, process_time = ? WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, result);
            LocalDateTime currentTime = LocalDateTime.now();
            pstmt.setTimestamp(2,Timestamp.valueOf(currentTime));
            pstmt.setInt(3, reportId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 查看待处理的举报
    @Override
    public List<Report> getPendingReports() {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM report WHERE status = 'pending'";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Report report = new Report();
                    report.setId(rs.getInt("id"));
                    // 获取举报人信息
                    int reporterId = rs.getInt("user_id");
                    UserDao userDao = new UserDaoImpl();
                    User reporter = userDao.getUserById(reporterId);
                    report.setReporter(reporter);
                    // 获取被举报文章信息
                    int articleId = rs.getInt("article_id");
                    ArticleDao articleDao = new ArticleDaoImpl();
                    Article reportedArticle = articleDao.getArticleById(articleId);
                    report.setReportedArticle(reportedArticle);
                    report.setStatus(rs.getString("status"));
                    report.setReason(rs.getString("reason"));
                    reports.add(report);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}
