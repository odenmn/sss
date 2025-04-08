package com.xjx.example.controller;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;
import com.xjx.example.entity.User;
import com.xjx.example.service.CommentService;
import com.xjx.example.service.impl.CommentServiceImpl;

import java.util.List;

public class CommentController {
    private CommentService commentService = new CommentServiceImpl();
    // 发表评论
    public boolean addComment(Comment comment){
        return commentService.addComment(comment);
    }

    // 删除评论
    public boolean deleteComment(int commentId){
        return commentService.deleteComment(commentId);
    }

    // 根据评论ID获取评论
    public Comment getCommentById(int commentId){
        return commentService.getCommentById(commentId);
    }

    // 根据文章获取评论
    public List<Comment> getCommentsByArticle(Article article){
        // 默认按评论的发布时间来排序评论，可以改为按评论的点赞数排序
        String sortType = "";
        return commentService.getCommentsByArticle(article,sortType);
    }

    // 进入文章后展示自己的评论和评论ID,在删除评论时使用，方便使用ID删除
    public List<Comment> viewMyCommentsAndCommentIdByArticle(Article article, User loginUser){
        return commentService.viewMyCommentsAndCommentIdByArticle(article,loginUser);
    }
}
