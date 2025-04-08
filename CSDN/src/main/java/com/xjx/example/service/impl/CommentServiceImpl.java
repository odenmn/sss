package com.xjx.example.service.impl;

import com.xjx.example.dao.CommentDao;
import com.xjx.example.dao.impl.CommentDaoImpl;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;
import com.xjx.example.entity.User;
import com.xjx.example.service.CommentService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    public boolean addComment(Comment comment) {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            comment.setPublishTime(currentTime);
            return commentDao.addComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteComment(int commentId) {
        try {
            return commentDao.deleteComment(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Comment getCommentById(int commentId){
        try {
            return commentDao.getCommentById(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Comment> getCommentsByArticle(Article article, String sortType) {
        try {
            return commentDao.getCommentsByArticle(article, sortType);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comment> viewMyCommentsAndCommentIdByArticle(Article article, User loginUser) {
        // 获取文章的评论集合
        List<Comment> comments = getCommentsByArticle(article,"");
        // 该文章我的评论集合
        List<Comment> myComments = new ArrayList<>();
        for (Comment myComment : comments) {
            if (myComment.getUser().getId() == loginUser.getId()) {
                myComments.add(myComment);
            }
        }
        return myComments;
    }
}
