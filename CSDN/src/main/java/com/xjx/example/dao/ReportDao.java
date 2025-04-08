package com.xjx.example.dao;

import com.xjx.example.entity.Report;

import java.sql.SQLException;
import java.util.List;

public interface ReportDao {
    //添加举报
    boolean addReport(Report report) throws SQLException;
    // 通过Report的ID获取举报
    Report getPendingReportByReportId(int reportId) throws SQLException;
    // 处理举报的方法，传入举报ID和处理结果
    boolean processReport(int reportId, String result) throws SQLException;
    // 获取待处理举报列表的方法，返回待处理举报的列表
    List<Report> getPendingReports() throws SQLException;
}
