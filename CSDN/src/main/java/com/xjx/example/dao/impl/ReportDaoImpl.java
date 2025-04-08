package com.xjx.example.dao.impl;

import com.xjx.example.dao.ArticleDao;
import com.xjx.example.dao.ReportDao;
import com.xjx.example.dao.UserDao;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Report;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl implements ReportDao {
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

    @Override
    public Report getPendingReportByReportId(int reportId) throws SQLException {
        String sql = "SELECT * FROM report WHERE id = ? AND status = 'pending'";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, reportId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
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
                    return report;
                }
            }
        }
        return null;
    }

    // 处理举报
    @Override
    public boolean processReport(int reportId, String result) throws SQLException {
        String sql = "UPDATE report SET status = ?, process_time = ? WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, result);
            LocalDateTime currentTime = LocalDateTime.now();
            pstmt.setTimestamp(2, Timestamp.valueOf(currentTime));
            pstmt.setInt(3, reportId);
            return pstmt.executeUpdate() > 0;
        }
    }

    // 查看待处理的举报
    @Override
    public List<Report> getPendingReports() throws SQLException {
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
        }
        return reports;
    }
}
