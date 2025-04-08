package com.xjx.example.service;

import com.xjx.example.entity.Report;

import java.util.List;

public interface AdminService {
    boolean deleteArticle(int articleId);
    boolean deleteComment(int commentId);
    boolean processReport(int reportId, String result);
    List<Report> getPendingReports();
}
