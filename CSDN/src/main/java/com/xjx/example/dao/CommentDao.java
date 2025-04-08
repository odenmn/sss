package com.xjx.example.dao;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    // 添加评论
    boolean addComment(Comment comment) throws SQLException;
    // 删除评论
    boolean deleteComment(int commentId) throws SQLException;
    // 根据评论ID获取评论
    Comment getCommentById(int commentId) throws SQLException;
    // 根据文章获取评论
    List<Comment> getCommentsByArticle(Article article, String sortType) throws SQLException;
}
