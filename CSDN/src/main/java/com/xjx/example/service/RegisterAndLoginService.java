package com.xjx.example.service;

import com.xjx.example.entity.User;
import com.xjx.example.service.impl.UserServiceImpl;

import java.util.Scanner;

public interface RegisterAndLoginService {
    void register();
    User Login();
}
