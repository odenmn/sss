package com.xjx.example.entity;

import java.util.Date;

public class Report {
    private int id;
    private User reporter;
    private Article reportedArticle;
    private String reason;
    private String status;
    private Date reportTime;

    public Report() {
    }

    public Report(int id, User reporter, Article reportedArticle, String reason, String status, Date reportTime) {
        this.id = id;
        this.reporter = reporter;
        this.reportedArticle = reportedArticle;
        this.reason = reason;
        this.status = status;
        this.reportTime = reportTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Article getReportedArticle() {
        return reportedArticle;
    }

    public void setReportedArticle(Article reportedArticle) {
        this.reportedArticle = reportedArticle;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
}    