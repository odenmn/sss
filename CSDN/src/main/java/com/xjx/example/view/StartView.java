package com.xjx.example.view;

import com.xjx.example.controller.AdminController;
import com.xjx.example.controller.ArticleController;
import com.xjx.example.controller.RegisterAndLoginController;
import com.xjx.example.controller.UserController;
import com.xjx.example.entity.User;

import java.util.Scanner;

public class StartView {
    AdminController adminController = new AdminController();
    ArticleController articleController = new ArticleController();
    RegisterAndLoginController registerAndLoginController = new RegisterAndLoginController();
    UserController userController = new UserController();
    UserView userView = new UserView();
    AdminView adminView = new AdminView();
    Scanner sc = new Scanner(System.in);
    public void showMenu(){

        while (true) {
            System.out.println("=================");
            System.out.println("欢迎来到CSDN博客系统");
            System.out.println("=================");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("3. 退出");
            System.out.println("请选择操作（输入 1-3）：");
            String command = sc.nextLine();
            switch (command) {
                case "1":
                    // 登录
                    User user = new RegisterAndLoginController().Login();
                    if (user == null){
                        break;
                    }
                    if (user.getRole().equals("普通用户")){
                        // 身份为普通用户
                        userView.showUserMenu(user);
                    }else {
                        // 身份为管理员
                        adminView.showAdminMenu();
                    }
                    break;
                case "2":
                    // 注册用户
                    registerAndLoginController.register();
                    break;
                case "3":
                    //退出系统
                    System.out.println("退出系统成功！");
                    return;
                default:
                    System.out.println("请输入正确的命令！");
            }
        }

    }
}


