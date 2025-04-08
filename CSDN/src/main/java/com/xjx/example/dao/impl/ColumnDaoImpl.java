package com.xjx.example.dao.impl;

import com.xjx.example.dao.AdminDao;
import com.xjx.example.dao.ArticleDao;
import com.xjx.example.dao.ColumnDao;
import com.xjx.example.dao.UserDao;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Column;
import com.xjx.example.entity.User;
import com.xjx.example.util.JDBCUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ColumnDaoImpl implements ColumnDao {

    @Override
    public boolean addColumn(Column column) throws SQLException {
        String sql = "INSERT INTO `column` (column_name, user_id, description) VALUES (?, ?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, column.getColumnName());
            pstmt.setInt(2, column.getUser().getId());
            pstmt.setString(3, column.getDescription());
            return pstmt.executeUpdate() > 0;
        }
    }
    @Override
    public boolean deleteColumn(int columnId) throws SQLException {
        try (Connection conn = JDBCUtils.getConnection()) {
            // 先删除关联表中的数据
            String deleteRelationSql = "DELETE FROM column_article WHERE column_id = ?";
            try (PreparedStatement relationPstmt = conn.prepareStatement(deleteRelationSql)) {
                relationPstmt.setInt(1, columnId);
                relationPstmt.executeUpdate();
            }
            // 再删除栏目表中的数据
            String deleteColumnSql = "DELETE FROM `column` WHERE id = ?";
            try (PreparedStatement columnPstmt = conn.prepareStatement(deleteColumnSql)) {
                columnPstmt.setInt(1, columnId);
                return columnPstmt.executeUpdate() > 0;
            }
        }
    }

    @Override
    public Column getColumnByColumnId(int columnId) throws SQLException {
        String sql = "SELECT * FROM `column` WHERE id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, columnId);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    Column column = new Column();
                    column.setId(rs.getInt("id"));
                    // 获得user
                    UserDao userDao = new UserDaoImpl();
                    User user = userDao.getUserById(rs.getInt("user_id"));
                    column.setUser(user);
                    column.setColumnName(rs.getString("column_name"));
                    column.setDescription(rs.getString("description"));
                    // 获取该栏目下的所有文章
                    List<Article> articles = getArticlesByColumn(columnId);
                    column.setArticles(articles);
                    return column;
                }
            }
        }
        return null;
    }

    @Override
    public List<Article> getArticlesByColumn(int columnId) throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT a.* FROM article a " +
                "JOIN column_article ca ON a.article_id = ca.article_id " +
                "WHERE ca.column_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, columnId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Article article = new Article();
                    article.setId(rs.getInt("article_id"));
                    UserDao userDao = new UserDaoImpl();
                    User user = userDao.getUserById(rs.getInt("user_id"));
                    article.setAuthor(user);
                    article.setTitle(rs.getString("title"));
                    article.setContent(rs.getString("content"));
                    article.setPublishTime(rs.getTimestamp("publish_time").toLocalDateTime());
                    article.setTop(rs.getBoolean("is_top"));
                    article.setLikeCount(rs.getInt("like_count"));
                    articles.add(article);
                }
            }
        }
        return articles;
    }

    @Override
    public List<Column> searchColumnsByColumnName(String columnName) throws SQLException {
        List<Column> columns = new ArrayList<>();
        String sql = "SELECT * FROM `column` WHERE column_name LIKE ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 模糊查询条件
            pstmt.setString(1, "%" + columnName + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Column column = new Column();
                    column.setId(rs.getInt("id"));
                    // 获得user
                    UserDao userDao = new UserDaoImpl();
                    User user = userDao.getUserById(rs.getInt("user_id"));
                    column.setUser(user);
                    column.setColumnName(rs.getString("column_name"));
                    column.setDescription(rs.getString("description"));
                    // 获取该栏目下的所有文章
                    List<Article> articles = getArticlesByColumn(column.getId());
                    column.setArticles(articles);
                    columns.add(column);
                }
            }
        }
        return columns;
    }

    @Override
    public List<Column> getColumnsByUser(User user) throws SQLException {
        List<Column> columns = new ArrayList<>();
        String sql = "SELECT * FROM `column` WHERE user_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Column column = new Column();
                    column.setId(rs.getInt("id"));
                    column.setUser(user);
                    column.setColumnName(rs.getString("column_name"));
                    column.setDescription(rs.getString("description"));


                    columns.add(column);
                }
            }
        }
        return columns;
    }

    @Override
    public boolean addArticleToColumn(int columnId, int articleId) throws SQLException {
        String sql = "INSERT INTO `column_article` (column_id, article_id) VALUES (?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, columnId);
            pstmt.setInt(2, articleId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
