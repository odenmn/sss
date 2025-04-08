package com.xjx.example.dao.impl;

import com.xjx.example.dao.FollowingDao;
import com.xjx.example.entity.Following;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FollowingDaoImpl implements FollowingDao {
    @Override
    public boolean addFollowing(Following following) throws SQLException {
        String sql = "INSERT INTO following (follower_id, followed_id) VALUES (?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, following.getFollowerId());
            pstmt.setLong(2, following.getFollowedId());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean hasFollowing(int followerId, int followedId) throws SQLException {
        // 使用了 COUNT(*) 聚合函数来统计符合 WHERE 的记录数量
        String sql = "SELECT COUNT(*) FROM following WHERE follower_id = ? AND followed_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, followerId);
            pstmt.setInt(2, followedId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Override
    public boolean cancelFollowing(int followerId, int followedId) throws SQLException {
        String sql = "DELETE FROM following WHERE follower_id = ? AND followed_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, followerId);
            pstmt.setInt(2, followedId);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Following> getFollowingsByUser(User user) throws SQLException {
        List<Following> followings = new ArrayList<>();
        String sql = "SELECT * FROM following WHERE follower_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, user.getId());
             try (ResultSet rs = pstmt.executeQuery()) {
                 while (rs.next()) {
                    Following following = new Following();
                    following.setId(rs.getInt("id"));
                    following.setFollowerId(rs.getInt("follower_id"));
                    following.setFollowedId(rs.getInt("followed_id"));
                    followings.add(following);
                }
            }
        }
        return followings;
    }

}
