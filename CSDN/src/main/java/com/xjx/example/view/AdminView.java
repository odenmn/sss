package com.xjx.example.view;

import com.xjx.example.controller.AdminController;
import com.xjx.example.controller.ArticleController;
import com.xjx.example.entity.Report;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    private AdminController adminController = new AdminController();
    private ArticleController articleController = new ArticleController();
    Scanner scanner = new Scanner(System.in);
    public void showAdminMenu() {
        while (true) {
            System.out.println("-----------------");
            System.out.println("1. 删除违规文章");
            System.out.println("2. 删除违规评论");
            System.out.println("3. 处理举报");
            System.out.println("4. 退出");
            String command = scanner.next();
            switch (command) {
                case "1":
                    deleteArticle();
                    break;
                case "2":
                    deleteComment();
                    break;
                case "3":
                    while (true) {
                        try {
                            // 查看待处理的举报
                            if (!viewPendingReports()){
                                break;
                            }
                            System.out.println("请输入要处理的举报 ID:");
                            int reportId = scanner.nextInt();
                            scanner.nextLine();
                            // 处理举报
                            // 展示举报信息

                            //articleController.getArticleById();
                            while (true) {
                                System.out.println("-----操作-----");
                                System.out.println("1. 删除违规文章");
                                System.out.println("2. 删除违规评论");
                                System.out.println("3. 拒绝处理");
                                System.out.println("请选择操作编号");
                                String choice = scanner.next();
                                switch (choice) {
                                    case "1":
                                        deleteArticle();
                                        break;
                                    case "2":
                                        deleteComment();
                                        break;
                                    case "3":
                                        break;
                                    default:
                                        System.out.println("请您输入有效的操作编号！");
                                        break;
                                }
                                break;
                            }
                            System.out.println("请输入处理结果（approved/rejected）:");
                            String result = scanner.next();
                            if (adminController.processReport(reportId, result)) {
                                System.out.println("举报处理成功");
                            } else {
                                System.out.println("举报处理失败");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("输入无效，请输入一个有效的整数");
                        }
                    }
                    break;
                case "4":
                    System.out.println("退出管理员界面");
                    return;
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }

    private void deleteArticle(){
        while (true) {
            try {
                System.out.println("请输入要删除的文章 ID:");
                int articleId = scanner.nextInt();
                scanner.nextLine();

                if (adminController.deleteArticle(articleId)) {
                    System.out.println("文章删除成功");
                } else {
                    System.out.println("文章删除失败");
                }
                break;
            } catch (Exception e) {
                System.out.println("输入无效，请输入一个有效的整数");
            }
        }
    }

    private void deleteComment(){
        while (true) {
            try {
                System.out.println("请输入要删除的评论 ID:");
                int commentId = scanner.nextInt();
                scanner.nextLine();
                if (adminController.deleteComment(commentId)) {
                    System.out.println("评论删除成功");
                } else {
                    System.out.println("评论删除失败");
                }
                break;
            } catch (Exception e) {
                System.out.println("输入无效，请输入一个有效的整数");
            }
        }
    }

    private boolean viewPendingReports() {
        List<Report> reports = adminController.getPendingReports();
        if (reports.isEmpty()){
            System.out.println("暂无举报");
            return false;
        }
        for (Report report : reports) {
                System.out.println("举报 ID: " + report.getId() + ", 原因: " + report.getReason());
                report.getReportedArticle();
            }
        return true;
        }

    }

