package com.xjx.example.dao;

import com.xjx.example.entity.Report;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    boolean deleteArticle(int articleId);
    boolean deleteComment(int commentId);
    boolean processReport(int reportId, String result);
    List<Report> getPendingReports();
}
