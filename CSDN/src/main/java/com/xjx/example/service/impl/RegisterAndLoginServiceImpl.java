package com.xjx.example.service.impl;

import com.xjx.example.entity.User;
import com.xjx.example.service.RegisterAndLoginService;
import com.xjx.example.service.UserService;

import java.util.Scanner;

public class RegisterAndLoginServiceImpl implements RegisterAndLoginService {
    Scanner sc = new Scanner(System.in);
    UserService userService = new UserServiceImpl();

    //注册
    public void register(){
        System.out.println("请输入用户名：");
        String username = sc.next();
        String password;
        while (true) {
            System.out.println("请输入密码：");
            password = sc.next();
            System.out.println("请确认密码：");
            String okPassword = sc.next();
            if (!password.equals(okPassword)) {
                System.out.println("您输入的两次密码不一致，请重新输入！");
            } else {
                break;
            }
        }
        System.out.println("请选择角色（输入 1 代表普通用户，2 代表管理员）：");
        int roleInput = sc.nextInt();
        String role ;
        if (roleInput == 1) {
            role = "普通用户";
        }else {
            role = "管理员";
        }
        User user = new User(username,password,role);
        if (userService.register(user)) {
            System.out.println("注册成功！您的用户名是：" + username + ",密码是："
                    + password + "，身份是：" + role + " 请返回主界面登录~~");
        }else {
            System.out.println("注册失败，该用户名已存在~");
        }
    }


    //登录
    public User Login(){
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        if (userService.login(username,password)){
            System.out.println("登录成功！");
            return userService.getUserByUsername(username);
        }else{
            System.out.println("登录失败，用户名或密码错误");
            return null;
        }
    }
}
