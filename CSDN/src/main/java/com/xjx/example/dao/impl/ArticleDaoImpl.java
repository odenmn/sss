package com.xjx.example.dao.impl;

import com.xjx.example.dao.ArticleDao;
import com.xjx.example.dao.UserDao;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 许俊曦
 */
public class ArticleDaoImpl implements ArticleDao {
    @Override
    public boolean publishArticle(Article article) throws SQLException {
        String sql = "INSERT INTO article (title, content, user_id, publish_time,like_count) VALUES (?,?,?,?,?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setInt(3, article.getAuthor().getId());
            pstmt.setTimestamp(4, Timestamp.valueOf(article.getPublishTime()));
            pstmt.setInt(5, 0);
            return pstmt.executeUpdate() > 0;
        }
    }
    @Override
    public boolean editArticle(Article article) throws SQLException {
        String sql = "UPDATE article SET title = ?, content = ? WHERE article_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setInt(3, article.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteArticle(int articleId) throws SQLException {
        String sql = "DELETE FROM article WHERE article_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Article> searchArticles(String keyword) throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM article WHERE title LIKE ? OR content LIKE ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("article_id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                User author = new User();
                author.setId(rs.getInt("user_id"));
                article.setAuthor(author);
                Timestamp timestamp = rs.getTimestamp("publish_time");
                article.setPublishTime(timestamp.toLocalDateTime());
                article.setTop(rs.getBoolean("is_top"));
                article.setLikeCount(rs.getInt("like_count"));
                articles.add(article);
            }
        }
        return articles;
    }

    @Override
    public List<Article> getArticlesByUser(User user) throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM article WHERE user_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Article article = new Article();
                    article.setId(rs.getInt("article_id"));
                    article.setTitle(rs.getString("title"));
                    article.setContent(rs.getString("content"));
                    article.setAuthor(user);
                    Timestamp timestamp = rs.getTimestamp("publish_time");
                    article.setPublishTime(timestamp.toLocalDateTime());
                    article.setTop(rs.getBoolean("is_top"));
                    article.setLikeCount(rs.getInt("like_count"));
                    articles.add(article);
                }
            }
        }
        return articles;
    }

    @Override
    public Article getArticleById(int articleId) throws SQLException {
        Article article = new Article();
        String sql = "SELECT * FROM article WHERE article_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    article.setId(rs.getInt("article_id"));
                    article.setTitle(rs.getString("title"));
                    article.setContent(rs.getString("content"));
                    UserDao userDao = new UserDaoImpl();
                    article.setAuthor(userDao.getUserById(rs.getInt("user_id")));
                    Timestamp timestamp = rs.getTimestamp("publish_time");
                    article.setPublishTime(timestamp.toLocalDateTime());
                    article.setTop(rs.getBoolean("is_top"));
                    article.setLikeCount(rs.getInt("like_count"));
                    return article;
                }
            }
        }
        return null;
    }

    @Override
    public Article getArticleByTitle(String title) throws SQLException {
        Article article = new Article();
        String sql = "SELECT * FROM article WHERE title = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1,title);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    article.setId(rs.getInt("article_id"));
                    article.setTitle(rs.getString("title"));
                    article.setContent(rs.getString("content"));
                    UserDao userDao = new UserDaoImpl();
                    article.setAuthor(userDao.getUserById(rs.getInt("user_id")));
                    Timestamp timestamp = rs.getTimestamp("publish_time");
                    article.setPublishTime(timestamp.toLocalDateTime());
                    article.setTop(rs.getBoolean("is_top"));
                    article.setLikeCount(rs.getInt("like_count"));
                }
            }
            return article;
        }
    }

    //
    @Override
    public List<Article> getAllArticles() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM article";//查询
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement prtmt = conn.prepareStatement(sql);
             ResultSet rs = prtmt.executeQuery()){
            while (rs.next()){
                Article article = new Article();
                article.setId(rs.getInt("article_id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                UserDao userDao = new UserDaoImpl();
                article.setAuthor(userDao.getUserById(rs.getInt("user_id")));
                Timestamp timestamp = rs.getTimestamp("publish_time");
                article.setPublishTime(timestamp.toLocalDateTime());
                article.setTop(rs.getBoolean("is_top"));
                article.setLikeCount(rs.getInt("like_count"));
                articles.add(article);
            }
        }
        return articles;
    }

    @Override
    public List<Article> getHotArticles(int limit) throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM article ORDER BY like_count DESC LIMIT ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Article article = new Article();
                    article.setId(rs.getInt("article_id"));
                    article.setTitle(rs.getString("title"));
                    article.setContent(rs.getString("content"));
                    User author = new User();
                    author.setId(rs.getInt("user_id"));
                    article.setAuthor(author);
                    Timestamp timestamp = rs.getTimestamp("publish_time");
                    article.setPublishTime(timestamp.toLocalDateTime());
                    article.setTop(rs.getBoolean("is_top"));
                    article.setLikeCount(rs.getInt("like_count"));
                    articles.add(article);
                }
            }
        }
        return articles;
    }

    // 置顶文章
    @Override
    public boolean setArticleTop(int articleId) throws SQLException {
        String sql = "UPDATE article SET is_top = true WHERE article_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            return pstmt.executeUpdate() > 0;
        }
    }

    // 取消文章置顶
    @Override
    public boolean cancelArticleTop(int articleId) throws SQLException {
        String sql = "UPDATE article SET is_top = false WHERE article_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            return pstmt.executeUpdate() > 0;
        }
    }
    @Override
    public boolean increaseLikeCount(int articleId) throws SQLException {
        String sql = "UPDATE article SET like_count = like_count + 1 WHERE article_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean decreaseLikeCount(int articleId) throws SQLException {
        String sql = "UPDATE article SET like_count = like_count - 1 WHERE article_id = ? AND like_count >= 1";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            return pstmt.executeUpdate() > 0;
        }
    }

}

