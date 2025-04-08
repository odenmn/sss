package com.xjx.example.controller;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Comment;
import com.xjx.example.entity.Tag;
import com.xjx.example.entity.User;
import com.xjx.example.service.ArticleService;
import com.xjx.example.service.CommentService;
import com.xjx.example.service.impl.ArticleServiceImpl;
import com.xjx.example.service.impl.CommentServiceImpl;

import java.util.List;

public class ArticleController {
    private ArticleService articleService = new ArticleServiceImpl();

    // 发表文章
    public boolean publishArticle(Article article) {
        return articleService.publishArticle(article);
    }

    // 编辑文章
    public boolean editArticle(Article article) {
        return articleService.editArticle(article);
    }

    // 删除文章
    public boolean deleteArticle(int articleId) {
        return articleService.deleteArticle(articleId);
    }

    // 根据关键词搜索文章
    public List<Article> searchArticles(String keyword) {
        return articleService.searchArticles(keyword);
    }

    // 根据用户获取文章
    public List<Article> getArticlesByUser(User user) {
        return articleService.getArticlesByUser(user);
    }

    // 根据文章标题获取文章
    public Article getArticleByTitle(String title){
        return articleService.getArticleByTitle(title);
    }

    // 根据文章ID获取文章
    public Article getArticleById(int articleId){
        return articleService.getArticleById(articleId);
    }

    // 置顶文章
    public boolean setArticleTop(int articleId,List<Article> articlesByColumn){
        return articleService.setArticleTop(articleId,articlesByColumn);
    }
    // 取消文章置顶
    public boolean cancelArticleTop(int articleId){
        return articleService.cancelArticleTop(articleId);
    }

    // 根据点赞数获取热门文章
    public List<Article> getHotArticles() {
        int limit = 8;
        return articleService.getHotArticles(limit);
    }

}
