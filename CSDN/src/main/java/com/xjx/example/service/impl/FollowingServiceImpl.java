package com.xjx.example.service.impl;

import com.xjx.example.dao.FollowingDao;
import com.xjx.example.dao.impl.FollowingDaoImpl;
import com.xjx.example.entity.Following;
import com.xjx.example.entity.User;
import com.xjx.example.service.FollowingService;
import com.xjx.example.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowingServiceImpl implements FollowingService {

    private FollowingDao followingDao = new FollowingDaoImpl();

    @Override
    public boolean addFollowing(Following following) {
        try {
            // 判断是否已经关注
            if (followingDao.hasFollowing(following.getFollowerId(),following.getFollowedId())) {
                System.out.println("你已经关注了该博主");
                return false;
            }
            return followingDao.addFollowing(following);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelFollowing(int followerId, int followedId) {
        try {
            // 判断是否已经关注
            if (!followingDao.hasFollowing(followerId,followedId)) {
                System.out.println("你还没有关注，无法取消");
                return false;
            }
            return followingDao.cancelFollowing(followerId,followedId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getFollowingUsersByUser(User user) {
        try {
            List<User> followingUsers = new ArrayList<>();
            List<Following> followings = followingDao.getFollowingsByUser(user);
            for (Following following : followings) {
                // 获取当前用户关注的用户User对象
                UserService userService = new UserServiceImpl();
                User followingUser = userService.getUserById(following.getFollowedId());
                followingUsers.add(followingUser);
            }
            return followingUsers;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
