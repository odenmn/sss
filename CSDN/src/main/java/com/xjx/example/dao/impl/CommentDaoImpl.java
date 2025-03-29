package com.xjx.example.dao.impl;

import com.xjx.example.dao.CommentDao;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;
import com.xjx.example.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public boolean addComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments (content, user_id, article_id, publish_time, like_count) VALUES (?,?,?,?,?)";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, comment.getContent());
            pstmt.setInt(2, comment.getUser().getId());
            pstmt.setInt(3, comment.getArticle().getId());
            pstmt.setTimestamp(4, new java.sql.Timestamp(comment.getPublishTime().getTime()));
            pstmt.setInt(5, comment.getLikeCount());
            return pstmt.executeUpdate() > 0;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteComment(int commentId) throws SQLException {
        String sql = "DELETE FROM comments WHERE id = ?";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, commentId);
            return pstmt.executeUpdate() > 0;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public List<Comment> getCommentsByArticle(Article article, String sortType) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String orderBy = "publish_time DESC";
        if ("like_count".equals(sortType)) {
            orderBy = "like_count DESC";
        }
        String sql = "SELECT * FROM comments WHERE article_id = ? ORDER BY " + orderBy;
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, article.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setContent(rs.getString("content"));
                // 这里需要进一步完善获取 user 的逻辑
                comment.setArticle(article);
                comment.setPublishTime(rs.getTimestamp("publish_time"));
                comment.setLikeCount(rs.getInt("like_count"));
                comments.add(comment);
            }
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return comments;
    }
}
