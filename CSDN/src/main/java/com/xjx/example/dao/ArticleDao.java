package com.xjx.example.dao;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Tag;
import com.xjx.example.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {
    boolean publishArticle(Article article);
    boolean editArticle(Article article);
    boolean deleteArticle(int articleId);
    List<Article> searchArticles(String keyword);
    List<Article> getArticlesByUser(User user);
    Article getArticleById(int articleId);
    List<Article> getHotArticles();
    boolean addTagsToArticle(int articleId, List<Tag> tags);
    List<Tag> getTagsByArticle(int articleId);
    boolean updateArticleLikeCount(int articleId, int likeCount);

}
