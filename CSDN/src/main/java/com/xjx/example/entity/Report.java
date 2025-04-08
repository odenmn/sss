package com.xjx.example.entity;

import java.time.LocalDateTime;

public class Report {
    private int id;
    private User reporter;
    private Comment reportedComment;
    private Article reportedArticle;
    private String reason;
    private String status;
    private LocalDateTime reportTime;

    public Report() {
    }

    public Report(int id, User reporter, Comment comment, Article reportedArticle, String reason, String status, LocalDateTime reportTime) {
        this.id = id;
        this.reporter = reporter;
        this.reportedComment = comment;
        this.reportedArticle = reportedArticle;
        this.reason = reason;
        this.status = status;
        this.reportTime = reportTime;
    }

    public Comment getReportedComment() {
        return reportedComment;
    }

    public void setReportedComment(Comment reportedComment) {
        this.reportedComment = reportedComment;
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

    public LocalDateTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalDateTime reportTime) {
        this.reportTime = reportTime;
    }
}    