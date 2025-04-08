package com.xjx.example.service;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * 为文章添加标签
     * @param articleId 文章 ID
     * @param tagName 标签名
     * @return 若添加成功返回 true，否则返回 false
     */
    boolean addTagToArticle(int articleId, String tagName);

    // 删除标签和文章和标签的关联
    boolean deleteTagFromArticle(int tagId);

    // 根据标签名删除标签
    boolean deleteTagByName(String tagName);
    /**
     * 获取文章的所有标签
     * @param articleId 文章 ID
     * @return 标签列表
     */
    List<Tag> getTagsByArticleId(int articleId);

    // 通过标签模糊搜索文章
    List<Article> getArticlesByTagKeyword(String tagKeyword);
}
