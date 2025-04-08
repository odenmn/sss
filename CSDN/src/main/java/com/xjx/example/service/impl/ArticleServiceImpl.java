package com.xjx.example.service.impl;

import com.xjx.example.dao.ArticleDao;
import com.xjx.example.dao.impl.ArticleDaoImpl;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Column;
import com.xjx.example.entity.Tag;
import com.xjx.example.entity.User;
import com.xjx.example.service.ArticleService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = new ArticleDaoImpl();

    @Override
    public boolean publishArticle(Article article) {
        LocalDateTime currentTime = LocalDateTime.now();
        article.setPublishTime(currentTime);
        try {
            return articleDao.publishArticle(article);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editArticle(Article article) {
        try {
            return articleDao.editArticle(article);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteArticle(int articleId) {
        try {
            return articleDao.deleteArticle(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Article> searchArticles(String keyword) {
        try {
            return articleDao.searchArticles(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Article> getArticlesByUser(User user) {
        try {
            return articleDao.getArticlesByUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Article getArticleByTitle(String title) {
        try {
            return articleDao.getArticleByTitle(title);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Article getArticleById(int articleId) {
        try {
            return articleDao.getArticleById(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Article> getHotArticles(int limit) {
        try {
            return articleDao.getHotArticles(limit);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean setArticleTop(int articleId,List<Article> articlesByColumn) {
        try {
            // 查看是否已有置顶文章
            for (Article articleInColumn : articlesByColumn) {
                if (articleInColumn.isTop()){
                    // 取消原来的置顶
                    if (cancelArticleTop(articleInColumn.getId())){
                        System.out.println("取消原来的置顶成功！");
                    }
                }
            }
            // 设置新的置顶
            return articleDao.setArticleTop(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelArticleTop(int articleId) {
        try {
            return articleDao.cancelArticleTop(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean increaseLikeCount(int articleId) {
        try {
            return articleDao.increaseLikeCount(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean decreaseLikeCount(int articleId) {
        try {
            return articleDao.decreaseLikeCount(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
