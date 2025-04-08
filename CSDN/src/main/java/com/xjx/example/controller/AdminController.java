package com.xjx.example.controller;

import com.xjx.example.entity.Report;
import com.xjx.example.service.impl.AdminServiceImpl;

import java.util.List;

public class AdminController {
    private AdminServiceImpl adminService = new AdminServiceImpl();

    // 删除文章
    public boolean deleteArticle(int articleId) {
        return adminService.deleteArticle(articleId);
    }

    // 删除评论
    public boolean deleteComment(int commentId) {
        return adminService.deleteComment(commentId);
    }

    // 处理举报
    public boolean processReport(int reportId, String result) {
        return adminService.processReport(reportId, result);
    }

    // 获取带处理的举报
    public List<Report> getPendingReports() {
        return adminService.getPendingReports();
    }
}
