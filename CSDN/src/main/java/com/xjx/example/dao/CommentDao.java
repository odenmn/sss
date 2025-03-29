package com.xjx.example.dao;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    boolean addComment(Comment comment) throws SQLException;
    boolean deleteComment(int commentId) throws SQLException;
    List<Comment> getCommentsByArticle(Article article, String sortType) throws SQLException;
}
