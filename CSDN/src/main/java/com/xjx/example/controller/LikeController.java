package com.xjx.example.controller;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.User;
import com.xjx.example.service.AdminService;
import com.xjx.example.service.ArticleService;
import com.xjx.example.service.LikeService;
import com.xjx.example.service.impl.ArticleServiceImpl;
import com.xjx.example.service.impl.LikeServiceImpl;

import java.util.List;

public class LikeController {
    private LikeService likeService = new LikeServiceImpl();
    private ArticleService articleService = new ArticleServiceImpl();
    // 添加点赞
    public boolean addLike(int articleId,int userId){
        return likeService.addLike(articleId,userId) && articleService.increaseLikeCount(articleId);
    }

    // 取消点赞
    public boolean cancelLike(int articleId, int userId){
        return likeService.cancelLike(articleId,userId) && articleService.decreaseLikeCount(articleId);
    }

    // 获取用户的点赞文章
    public List<Article> getLikeArticlesByUser(User user){
        return likeService.getLikeArticlesByUser(user);
    }

}
