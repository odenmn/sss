package com.xjx.example.service;

import com.xjx.example.dao.AdminDao;
import com.xjx.example.dao.impl.AdminDaoImpl;
import com.xjx.example.entity.Report;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    public boolean deleteArticle(int articleId) {
        return adminDao.deleteArticle(articleId);
    }

    public boolean deleteComment(int commentId) {
        return adminDao.deleteComment(commentId);
    }

    public boolean processReport(int reportId, String result) {
        return adminDao.processReport(reportId, result);
    }

    public List<Report> getPendingReports() {
        return adminDao.getPendingReports();
    }
}
