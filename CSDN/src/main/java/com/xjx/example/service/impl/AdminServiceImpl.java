package com.xjx.example.service.impl;

import com.xjx.example.dao.AdminDao;
import com.xjx.example.dao.impl.AdminDaoImpl;
import com.xjx.example.entity.Report;
import com.xjx.example.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
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
