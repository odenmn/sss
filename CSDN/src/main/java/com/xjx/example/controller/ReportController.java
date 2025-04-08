package com.xjx.example.controller;

import com.xjx.example.entity.Report;
import com.xjx.example.service.ReportService;
import com.xjx.example.service.impl.ReportServiceImpl;

import java.util.List;

public class ReportController {
    private ReportService reportService = new ReportServiceImpl();

    //添加举报
    public boolean addReport(Report report){
        return reportService.addReport(report);
    }

    // 通过Report的ID获取待处理的举报
    public Report getPendingReportByReportId(int reportId){
        return reportService.getPendingReportByReportId(reportId);
    }

    // 处理举报的方法，传入举报ID和处理结果
    public boolean processReport(int reportId, String result){
        return reportService.processReport(reportId,result);
    }

    // 获取待处理举报列表的方法，返回待处理举报的列表
    public List<Report> getPendingReports(){
        return reportService.getPendingReports();
    }
}
