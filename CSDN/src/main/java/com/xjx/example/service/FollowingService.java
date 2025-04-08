package com.xjx.example.service;

import com.xjx.example.entity.Following;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface FollowingService {

    // 添加关注
    boolean addFollowing(Following following);

    // 取消关注
    boolean cancelFollowing(int followerId, int followedId);

    // 获取当前用户关注的用户
    List<User> getFollowingUsersByUser(User user);

}
