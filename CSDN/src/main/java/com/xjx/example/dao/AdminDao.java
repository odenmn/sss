package com.xjx.example.dao;

import com.xjx.example.entity.Report;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    // 删除文章的方法，传入文章ID
    boolean deleteArticle(int articleId);
    // 删除评论的方法，传入评论ID
    boolean deleteComment(int commentId);
    // 处理举报的方法，传入举报ID和处理结果
    boolean processReport(int reportId, String result);
    // 获取待处理举报列表的方法，返回待处理举报的列表
    List<Report> getPendingReports();
}
