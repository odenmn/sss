package com.xjx.example.controller;

import com.xjx.example.entity.Following;
import com.xjx.example.entity.User;
import com.xjx.example.service.FollowingService;
import com.xjx.example.service.impl.FollowingServiceImpl;

import java.util.List;

public class FollowingController {
    private FollowingService followingService = new FollowingServiceImpl();

    // 添加关注
    public boolean addFollowing(Following following){
        return followingService.addFollowing(following);
    }

    // 取消关注
    public boolean cancelFollowing(int followerId, int followedId){
        return followingService.cancelFollowing(followerId,followedId);
    }

    // 获取当前用户关注的用户
    public List<User> getFollowingUsersByUser(User user){
        return followingService.getFollowingUsersByUser(user);
    }
}
