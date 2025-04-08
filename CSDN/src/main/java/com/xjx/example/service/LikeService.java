package com.xjx.example.service;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Like;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface LikeService {

    // 添加点赞
    boolean addLike(int articleId,int userId);

    // 取消点赞
    boolean cancelLike(int articleId, int userId);

    // 获取用户的点赞文章
    List<Article> getLikeArticlesByUser(User user);
}
