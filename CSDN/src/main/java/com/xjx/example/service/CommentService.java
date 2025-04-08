package com.xjx.example.service;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    boolean addComment(Comment comment);
    boolean deleteComment(int commentId);
    Comment getCommentById(int commentId);
    List<Comment> getCommentsByArticle(Article article, String sortType);
    List<Comment> viewMyCommentsAndCommentIdByArticle(Article article, User loginUser);
}
