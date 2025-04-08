package com.xjx.example.service.impl;

import com.xjx.example.dao.ArticleDao;
import com.xjx.example.dao.TagDao;
import com.xjx.example.dao.impl.TagDaoImpl;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Tag;
import com.xjx.example.service.ArticleService;
import com.xjx.example.service.TagService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagServiceImpl implements TagService {
    private TagDao tagDao = new TagDaoImpl();
    private ArticleService articleService = new ArticleServiceImpl();

    @Override
    public boolean addTagToArticle(int articleId, String tagName) {
        try {
            Tag tag = tagDao.getTagByTagName(tagName);
            if (tag == null) {
                int tagId = tagDao.addTag(tagName);
                if (tagId == -1) {
                    return false;
                }
                tag = new Tag(tagId,tagName);
            }
            return tagDao.addArticleTag(articleId, tag.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteTagFromArticle(int tagId){
        try {
            return tagDao.deleteTagById(tagId) && tagDao.deleteArticleTag(tagId);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteTagByName(String tagName){
        try {
            Tag tag = tagDao.getTagByTagName(tagName);
            return tagDao.deleteArticleTag(tag.getId()) && tagDao.deleteTagById(tag.getId());
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Tag> getTagsByArticleId(int articleId) {
        try {
            return tagDao.getTagsByArticleId(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Article> getArticlesByTagKeyword(String tagKeyword) {
        try {
            List<Integer> articleIds = tagDao.getArticleIdsByTagKeyword(tagKeyword);
            List<Article> articles = new ArrayList<>();
            for (Integer articleId : articleIds) {
                Article article = articleService.getArticleById(articleId);
                articles.add(article);
            }
            return articles;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
