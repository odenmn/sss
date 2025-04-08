package com.xjx.example.dao;

import com.xjx.example.entity.Like;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface LikeDao {
    boolean addLike(int articleId,int userId) throws SQLException ;

    boolean hasLiked(int articleId, int userId) throws SQLException ;

    boolean cancelLike(int articleId, int userId) throws SQLException ;

    List<Like> getLikesByUser(User user) throws SQLException;
}
