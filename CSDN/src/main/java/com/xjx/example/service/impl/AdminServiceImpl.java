package com.xjx.example.service.impl;

import com.xjx.example.dao.AdminDao;
import com.xjx.example.dao.impl.AdminDaoImpl;
import com.xjx.example.entity.Report;
import com.xjx.example.service.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    public boolean deleteArticle(int articleId) {
        try {
            return adminDao.deleteArticle(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteComment(int commentId) {
        try {
            return adminDao.deleteComment(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean processReport(int reportId, String result) {
        try {
            return adminDao.processReport(reportId, result);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Report> getPendingReports() {
        try {
            return adminDao.getPendingReports();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
