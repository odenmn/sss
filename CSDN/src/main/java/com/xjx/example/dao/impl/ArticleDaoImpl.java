package com.xjx.example.dao.impl;

import com.xjx.example.dao.ArticleDao;
import com.xjx.example.dao.UserDao;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Tag;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    @Override
    public boolean publishArticle(Article article) {
        String sql = "INSERT INTO articles (title, content, user_id, publish_time) VALUES (?,?,?,?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setInt(3, article.getAuthor().getId());
            pstmt.setTimestamp(4, Timestamp.valueOf(article.getPublishTime()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean editArticle(Article article) {
        String sql = "UPDATE articles SET title = ?, content = ? WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, article.getTitle());
            pstmt.setString(2, article.getContent());
            pstmt.setInt(3, article.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteArticle(int articleId) {
        String sql = "DELETE FROM articles WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Article> searchArticles(String keyword) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM articles WHERE title LIKE ? OR content LIKE ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                User author = new User();
                author.setId(rs.getInt("user_id"));
                article.setAuthor(author);
                Timestamp timestamp = rs.getTimestamp("publish_time");
                if (timestamp != null) {
                    article.setPublishTime(timestamp.toLocalDateTime());
                }
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public List<Article> getArticlesByUser(User user) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM articles WHERE user_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Article article = new Article();
                    article.setId(rs.getInt("id"));
                    article.setTitle(rs.getString("title"));
                    article.setContent(rs.getString("content"));
                    article.setAuthor(user);
                    Timestamp timestamp = rs.getTimestamp("publish_time");
                    if (timestamp != null) {
                        article.setPublishTime(timestamp.toLocalDateTime());
                    }
                    articles.add(article);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public Article getArticleById(int articleId){
        Article article = new Article();
        String sql = "SELECT * FROM articles WHERE article_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    article.setId(rs.getInt("id"));
                    article.setTitle(rs.getString("title"));
                    article.setContent(rs.getString("content"));
                    UserDao userDao = new UserDaoImpl();
                    article.setAuthor(userDao.getUserById(rs.getInt("user_id")));
                    Timestamp timestamp = rs.getTimestamp("publish_time");
                    if (timestamp != null) {
                        article.setPublishTime(timestamp.toLocalDateTime());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }
    @Override
    public List<Article> getHotArticles() {
        return null;
    }

    @Override
    public boolean addTagsToArticle(int articleId, List<Tag> tags) {
        String sql = "INSERT INTO article_tags (article_id, tag_id) VALUES (?,?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Tag tag : tags) {
                pstmt.setInt(1, articleId);
                pstmt.setInt(2, tag.getId());
                pstmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Tag> getTagsByArticle(int articleId) {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT tags.* FROM tags " +
                "JOIN article_tags ON tags.id = article_tags.tag_id " +
                "WHERE article_tags.article_id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Tag tag = new Tag();
                    tag.setId(rs.getInt("id"));
                    tag.setName(rs.getString("name"));
                    tags.add(tag);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }
    @Override
    public boolean updateArticleLikeCount(int articleId, int likeCount){
        String sql = "UPDATE articles SET like_count = ? WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, likeCount);
            pstmt.setInt(2, articleId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

