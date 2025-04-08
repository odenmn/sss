package com.xjx.example.dao.impl;

import com.xjx.example.dao.LikeDao;
import com.xjx.example.entity.Following;
import com.xjx.example.entity.Like;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDaoImpl implements LikeDao {
    @Override
    public boolean addLike(int articleId, int userId) throws SQLException {
        String sql = "INSERT INTO `like` (article_id, user_id) VALUES (?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            pstmt.setInt(2, userId);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean hasLiked(int articleId, int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM `like` WHERE article_id = ? AND user_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            pstmt.setInt(2, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 获取查询结果中的计数
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Override
    public boolean cancelLike(int articleId, int userId) throws SQLException {
        String sql = "DELETE FROM `like` WHERE article_id = ? AND user_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            pstmt.setInt(2, userId);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Like> getLikesByUser(User user) throws SQLException {
        List<Like> likes = new ArrayList<>();
        String sql = "SELECT * FROM `like` WHERE user_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, user.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Like like = new Like();
                    like.setId(rs.getInt("id"));
                    like.setUserId(rs.getInt("user_id"));
                    like.setArticleId(rs.getInt("article_id"));
                    likes.add(like);
                }
            }
        }
        return likes;
    }
}
