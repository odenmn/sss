package com.xjx.example.service;

import com.xjx.example.dao.AdminDao;
import com.xjx.example.dao.ArticleDao;
import com.xjx.example.dao.impl.AdminDaoImpl;
import com.xjx.example.dao.impl.ArticleDaoImpl;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Report;
import com.xjx.example.entity.Tag;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public class ArticleService {
    private ArticleDao articleDao = new ArticleDaoImpl();

    public boolean publishArticle(Article article) {
        return articleDao.publishArticle(article);
    }

    public boolean editArticle(Article article) {
        return articleDao.editArticle(article);
    }

    public boolean deleteArticle(int articleId) {
        return articleDao.deleteArticle(articleId);
    }

    public List<Article> searchArticles(String keyword) {
        return articleDao.searchArticles(keyword);
    }

    public List<Article> getArticlesByUser(User user) {
        return articleDao.getArticlesByUser(user);
    }

    public List<Article> getHotArticles() {
        return articleDao.getHotArticles();
    }

    public boolean addTagsToArticle(int articleId, List<Tag> tags) {
        return articleDao.addTagsToArticle(articleId, tags);
    }

    public List<Tag> getTagsByArticle(int articleId) {
        return articleDao.getTagsByArticle(articleId);
    }

    public boolean updateArticleLikeCount(int articleId, int likeCount){
        return articleDao.updateArticleLikeCount(articleId,likeCount);
    }
}
