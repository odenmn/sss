package com.xjx.example.service;

import com.xjx.example.dao.CommentDao;
import com.xjx.example.dao.impl.CommentDaoImpl;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public class CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    public boolean addComment(Comment comment) {
        try {
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

    public List<Comment> getCommentsByArticle(Article article, String sortType) {
        try {
            return commentDao.getCommentsByArticle(article, sortType);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
