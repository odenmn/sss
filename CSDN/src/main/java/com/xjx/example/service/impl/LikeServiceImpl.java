package com.xjx.example.service.impl;

import com.xjx.example.dao.LikeDao;
import com.xjx.example.dao.impl.LikeDaoImpl;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Like;
import com.xjx.example.entity.User;
import com.xjx.example.service.ArticleService;
import com.xjx.example.service.LikeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeServiceImpl implements LikeService {
    private LikeDao likeDao = new LikeDaoImpl();
    @Override
    public boolean addLike(int articleId, int userId){
        try {
            if (likeDao.hasLiked(articleId, userId)) {
                System.out.println("你已经点过赞了");
                return false;
            }
            return likeDao.addLike(articleId,userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean cancelLike(int articleId, int userId) {
        try {
            if (!likeDao.hasLiked(articleId, userId)) {
                System.out.println("你还没有点赞，无法取消");
                return false;
            }
            return likeDao.cancelLike(articleId, userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Article> getLikeArticlesByUser(User user) {
        try {
            List<Like> likes = likeDao.getLikesByUser(user);
            List<Article> likeArticles = new ArrayList<>();
            for (Like like : likes) {
                // 获取当前用户点赞的Article对象
                ArticleService articleService = new ArticleServiceImpl();
                Article article = articleService.getArticleById(like.getArticleId());
                likeArticles.add(article);
            }
            return likeArticles;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
