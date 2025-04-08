package com.xjx.example.service;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Tag;
import com.xjx.example.entity.User;

import java.util.List;

public interface ArticleService {

    // 发布文章
    boolean publishArticle(Article article);
    // 编辑文章
    boolean editArticle(Article article);
    // 删除文章
    boolean deleteArticle(int articleId);
    // 根据关键字搜索文章
    List<Article> searchArticles(String keyword);
    // 根据用户获取文章列表
    List<Article> getArticlesByUser(User user);
    // 根据文章标题获取文章
    Article getArticleByTitle(String title);
    // 根据文章Id获取文章
    Article getArticleById(int articleId);
    // 获取热门文章列表
    List<Article> getHotArticles(int limit);
    // 置顶文章
    boolean setArticleTop(int articleId,List<Article> articlesByColumn);
    // 取消文章置顶
    boolean cancelArticleTop(int articleId);
    // 添加点赞数
    boolean increaseLikeCount(int articleId);
    // 减少点赞数
    boolean decreaseLikeCount(int articleId);
}
