package com.xjx.example.service;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    // 添加评论
    boolean addComment(Comment comment);
    // 删除评论
    boolean deleteComment(int commentId);
    // 删除文章的全部评论
    boolean deleteAllCommentsByArticleId(int articleId) throws SQLException;
    // 根据评论ID获取评论
    Comment getCommentById(int commentId);
    // 根据文章获取评论
    List<Comment> getCommentsByArticle(Article article, String sortType);
    // 查看我对文章的评论
    List<Comment> viewMyCommentsAndCommentIdByArticle(Article article, User loginUser);
}
