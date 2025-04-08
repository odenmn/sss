package com.xjx.example.dao;

import com.xjx.example.entity.Following;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface FollowingDao {
    boolean addFollowing(Following following) throws SQLException;
    boolean hasFollowing(int followerId, int followingId) throws SQLException;

    boolean cancelFollowing(int followerId, int followedId) throws SQLException;

    List<Following> getFollowingsByUser(User user) throws SQLException;

}
