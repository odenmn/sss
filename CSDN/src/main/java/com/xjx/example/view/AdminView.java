package com.xjx.example.view;

import com.xjx.example.controller.*;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;
import com.xjx.example.entity.Report;
import com.xjx.example.entity.User;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    private AdminController adminController = new AdminController();
    private UserController userController = new UserController();
    private ReportController reportController = new ReportController();
    private ArticleController articleController = new ArticleController();
    private CommentController commentController = new CommentController();
    Scanner scanner = new Scanner(System.in);
    public void showAdminMenu() {
        while (true) {
            System.out.println("-----------------");
            System.out.println("1. 处理举报");
            System.out.println("2. 退出");
            String command = scanner.next();
            switch (command) {
                case "1":
                    while (true) {
                        try {
                            // 查看待处理的举报
                            if (!viewPendingReports()){
                                break;
                            }
                            System.out.println("请输入要处理的举报ID（输入0返回）:");
                            int reportId = scanner.nextInt();
                            scanner.nextLine();
                            // 返回
                            if (reportId == 0){
                                System.out.println("返回成功！");
                                break;
                            }
                            Report report = reportController.getPendingReportByReportId(reportId);
                            // 该举报是否存在且待处理
                            if (report == null){
                                System.out.println("未找到这个待处理举报~~");
                                continue;
                            }
                            // 处理举报
                            while (true) {
                                System.out.println("-----操作-----");
                                System.out.println("1. 删除违规文章");
                                System.out.println("2. 删除违规评论");
                                System.out.println("3. 封禁用户");
                                System.out.println("4. 返回");
                                System.out.println("请选择操作编号：");
                                String choice = scanner.next();
                                switch (choice) {
                                    case "1":
                                        deleteArticle(report);
                                        break;
                                    case "2":
                                        deleteComment(report);
                                        break;
                                    case "3":
                                        banUser(report);
                                        break;
                                    case "4":
                                        System.out.println("返回成功");
                                        break;
                                    default:
                                        System.out.println("请您输入有效的操作编号！");
                                        break;
                                }
                                break;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("输入无效，请输入一个有效的整数");
                            scanner.nextLine();
                        }
                    }
                    break;
                case "2":
                    System.out.println("退出管理员界面");
                    return;
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }

    private void banUser(Report report) {
        // 文章违规的用户
        User reportedUserAboutArticle = report.getReportedArticle().getAuthor();
        // 评论违规的用户
        User reportedUserAboutComment = report.getReportedComment().getUser();
        String command;
        // 是对文章作者的封禁
        if (reportedUserAboutComment.getId() == 0){
            System.out.println("是否确认封禁作者（Y/N）：" + reportedUserAboutArticle.getUsername());
            command = scanner.next();
            if (command.equals("Y")){
                if (userController.banUser(reportedUserAboutArticle.getId())) {
                    System.out.println("封禁成功！");
                }else {
                    System.out.println("封禁失败~~");
                }
            }else if (command.equals("N")){
                System.out.println("返回成功！");
                return;
            }else {
                System.out.println("输入有误，请重新输入");
            }
        }
        // 是对发布评论用户的封禁
        if (reportedUserAboutArticle.getId() == 0){
            System.out.println("是否确认封禁用户（Y/N）：" + reportedUserAboutComment.getUsername());
            command = scanner.next();
            if (command.equals("Y")){
                if (userController.banUser(reportedUserAboutComment.getId())){
                    System.out.println("封禁成功！");
                }else {
                    System.out.println("封禁失败~~");
                }
            }else if (command.equals("N")){
                System.out.println("返回成功！");
                return;
            }else {
                System.out.println("输入有误，请重新输入");
            }
        }
    }

    private void reportResult(int reportId) {
        System.out.println("请输入处理结果（approved/rejected）:");
        String result = scanner.next();
        if (reportController.processReport(reportId, result)) {
            System.out.println("举报处理成功");
        } else {
            System.out.println("举报处理失败");
        }
    }

    private void deleteArticle(Report report){
        while (true) {
            try {
                System.out.println("请输入要删除的文章ID（输入0返回）:");
                int articleId = scanner.nextInt();
                scanner.nextLine();
                if (articleId == 0){
                    System.out.println("返回成功！");
                    return;
                }
                if (report.getReportedArticle().getId() != articleId){
                    System.out.println("输入有误，请重新输入！");
                    continue;
                }
                if (articleController.deleteArticle(articleId)) {
                    System.out.println("文章删除成功");
                } else {
                    System.out.println("文章删除失败");
                }
                reportResult(report.getId());
                break;
            } catch (Exception e) {
                System.out.println("输入无效，请输入一个有效的整数");
            }
        }
    }

    private void deleteComment(Report report){
        while (true) {
            try {
                System.out.println("请输入要删除的评论ID（输入0返回）:");
                String commentID = scanner.next();
                int commentId = Integer.parseInt(commentID);
                if (commentId == 0){
                    System.out.println("返回成功！");
                    return;
                }
                else if (report.getReportedComment().getId() != commentId){
                    System.out.println("输入有误，请重新输入！");
                    continue;
                }
                if (commentController.deleteComment(commentId)) {
                    System.out.println("评论删除成功");
                } else {
                    System.out.println("评论删除失败");
                }
                reportResult(report.getId());
                break;
            } catch (NumberFormatException e) {
                System.out.println("输入无效，请jc");
                scanner.nextLine();
            }
        }
    }

    private boolean viewPendingReports() {
        List<Report> reports = reportController.getPendingReports();
        System.out.println("----------------------");
        if (reports.isEmpty()){
            System.out.println("暂无举报");
            return false;
        }
        for (Report report : reports) {
            Article reportedArticle = report.getReportedArticle();
            Comment reportedComment = report.getReportedComment();
            System.out.println("举报 ID: " + report.getId() + ", 原因: " + report.getReason());
            if (reportedArticle != null){
                System.out.println("被举报的文章ID：" + reportedArticle.getId() + "  标题：" + reportedArticle.getTitle() + "  作者：" + reportedArticle.getAuthor().getUsername());
                System.out.println("文章内容：" + reportedArticle.getContent());
            }
            if (reportedComment != null){
                System.out.println("被举报的评论ID：" + reportedComment.getId() + "  用户：" + reportedComment.getUser().getUsername());
                System.out.println("评论内容：" + reportedComment.getContent());
            }
        }
        return true;
        }

    }

