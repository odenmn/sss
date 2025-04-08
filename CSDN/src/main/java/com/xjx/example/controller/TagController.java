package com.xjx.example.controller;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Tag;
import com.xjx.example.service.TagService;
import com.xjx.example.service.impl.TagServiceImpl;

import java.util.List;

public class TagController {
    private TagService tagService = new TagServiceImpl();
    public boolean addTagToArticle(int articleId, String tagName){
        return tagService.addTagToArticle(articleId,tagName);
    }
    public boolean deleteTagFromArticle(int tagId){
        return tagService.deleteTagFromArticle(tagId);
    }
    public boolean deleteTagByName(String tagName){
        return tagService.deleteTagByName(tagName);
    }
    public List<Tag> getTagsByArticleId(int articleId){
        return tagService.getTagsByArticleId(articleId);
    }

    public List<Article> getArticlesByTagKeyword(String tagKeyword){
        return tagService.getArticlesByTagKeyword(tagKeyword);
    }

}
