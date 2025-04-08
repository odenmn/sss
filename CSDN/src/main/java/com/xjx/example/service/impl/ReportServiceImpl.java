package com.xjx.example.service.impl;

import com.xjx.example.dao.ReportDao;
import com.xjx.example.dao.impl.ReportDaoImpl;
import com.xjx.example.entity.Report;
import com.xjx.example.service.ReportService;

import java.sql.SQLException;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    private ReportDao reportDao = new ReportDaoImpl();
    @Override
    public boolean addReport(Report report) {
        try {
            return reportDao.addReport(report);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Report getPendingReportByReportId(int reportId) {
        try {
            return reportDao.getPendingReportByReportId(reportId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean processReport(int reportId, String result) {
        try {
            return reportDao.processReport(reportId,result);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Report> getPendingReports() {
        try {
            return reportDao.getPendingReports();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
