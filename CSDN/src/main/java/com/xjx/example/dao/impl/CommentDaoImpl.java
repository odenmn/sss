package com.xjx.example.dao.impl;

import com.xjx.example.dao.CommentDao;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;
import com.xjx.example.service.ArticleService;
import com.xjx.example.service.UserService;
import com.xjx.example.service.impl.ArticleServiceImpl;
import com.xjx.example.service.impl.UserServiceImpl;
import com.xjx.example.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public boolean addComment(Comment comment) throws SQLException {
        String sql = "INSERT INTO comment (content, user_id, article_id, publish_time, like_count) VALUES (?,?,?,?,?)";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, comment.getContent());
            pstmt.setInt(2, comment.getUser().getId());
            pstmt.setInt(3, comment.getArticle().getId());
            pstmt.setTimestamp(4, Timestamp.valueOf(comment.getPublishTime()));
            pstmt.setInt(5,0);
            return pstmt.executeUpdate() > 0;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteComment(int commentId) throws SQLException {
        String sql = "DELETE FROM comment WHERE id = ?";
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, commentId);
            return pstmt.executeUpdate() > 0;
        } finally {
            JDBCUtils.closeConnection(connection);
        }
    }

    // 删除文章的全部评论
    @Override
    public boolean deleteAllCommentsByArticleId(int articleId) throws SQLException {
        String sql = "DELETE FROM comment WHERE article_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public Comment getCommentById(int commentId) throws SQLException{
        String sql = "SELECT * FROM comment WHERE id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, commentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Comment comment = new Comment();
                    comment.setId(rs.getInt("id"));
                    comment.setContent(rs.getString("content"));

                    // 获取user对象
                    UserService userService = new UserServiceImpl();
                    int userId = rs.getInt("user_id");
                    comment.setUser(userService.getUserById(userId));

                    // 获取article对象
                    ArticleService articleService = new ArticleServiceImpl();
                    Article article = new Article();
                    int articleId = rs.getInt("article_id");
                    article = articleService.getArticleById(articleId);
                    comment.setArticle(article);

                    Timestamp timestamp = rs.getTimestamp("publish_time");
                    comment.setPublishTime(timestamp.toLocalDateTime());
                    comment.setLikeCount(rs.getInt("like_count"));
                    return comment;
                }
            }
        }
        return null;
    }
    @Override
    public List<Comment> getCommentsByArticle(Article article, String sortType) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        String orderBy = "publish_time DESC";
        if ("like_count".equals(sortType)) {
            orderBy = "like_count DESC";
        }
        String sql = "SELECT * FROM comment WHERE article_id = ? ORDER BY " + orderBy;
        Connection connection = JDBCUtils.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, article.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setContent(rs.getString("content"));

                // 获取user对象
                UserService userService = new UserServiceImpl();
                int userId = rs.getInt("user_id");
                comment.setUser(userService.getUserById(userId));

                comment.setArticle(article);
                Timestamp timestamp = rs.getTimestamp("publish_time");
                comment.setPublishTime(timestamp.toLocalDateTime());
                comment.setLikeCount(rs.getInt("like_count"));
                comments.add(comment);
            }
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return comments;
    }
}
