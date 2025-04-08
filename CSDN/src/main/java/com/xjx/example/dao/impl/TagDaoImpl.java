package com.xjx.example.dao.impl;

import com.xjx.example.dao.TagDao;
import com.xjx.example.entity.Tag;
import com.xjx.example.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImpl implements TagDao {
    @Override
    public int addTag(String tagName) throws SQLException {
        String sql = "INSERT INTO tag (tag_name) VALUES (?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, tagName);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    @Override
    public Tag getTagByTagName(String tagName) throws SQLException {
        String sql = "SELECT * FROM tag WHERE tag_name = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tagName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Tag tag = new Tag();
                    tag.setId(rs.getInt("id"));
                    tag.setTagName(rs.getString("tag_name"));
                    return tag;
                }
            }
        }
        return null;
    }

    @Override
    public boolean addArticleTag(int articleId, int tagId) throws SQLException {
        String sql = "INSERT INTO article_tag (article_id, tag_id) VALUES (?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            pstmt.setInt(2, tagId);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteTagById(int tagId) throws SQLException {
        String sql = "DELETE FROM tag WHERE id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tagId);
            return pstmt.executeUpdate() > 0;
        }
    }
    @Override
    public boolean deleteArticleTag(int tagId) throws SQLException {
        String sql = "DELETE FROM article_tag WHERE tag_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tagId);
            return pstmt.executeUpdate() > 0;
        }
    }
    @Override
    public List<Tag> getTagsByArticleId(int articleId) throws SQLException {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM tag" +
                "JOIN article_tag ON tag.id = article_tag.tag_id " +
                "WHERE article_tag.article_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, articleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Tag tag = new Tag();
                    tag.setId(rs.getInt("id"));
                    tag.setTagName(rs.getString("tag_name"));
                    tags.add(tag);
                }
            }
        }
        return tags;
    }

    @Override
    public List<Integer> getArticleIdsByTagKeyword(String tagKeyword) throws SQLException {
        List<Integer> articleIds = new ArrayList<>();
        String sql = "SELECT at.article_id FROM tag t " +
                "JOIN article_tag at ON t.id = at.tag_id " +
                "WHERE t.tag_name LIKE ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + tagKeyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    articleIds.add(rs.getInt("article_id"));
                }
            }
        }
        return articleIds;
    }
}
