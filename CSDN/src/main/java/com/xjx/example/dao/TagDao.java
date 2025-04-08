package com.xjx.example.dao;

import com.xjx.example.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagDao {

    // 添加标签到tag表中,并返回该标签的自增主键id
    int addTag(String tagName) throws SQLException;
    //根据标签名获取标签对象
    Tag getTagByTagName(String tagName) throws SQLException;
    // 添加文章标签，绑定articleId和tagId
    boolean addArticleTag(int articleId, int tagId) throws SQLException;
    // 根据标签ID删除标签
    boolean deleteTagById(int tagId) throws SQLException;
    // 删除文章与标签的关联
    boolean deleteArticleTag(int tagId) throws SQLException;
    // 根据文章id获取标签
    List<Tag> getTagsByArticleId(int articleId) throws SQLException;
    // 通过模糊搜索标签找到文章ID
    List<Integer> getArticleIdsByTagKeyword(String tagKeyword) throws SQLException;
    // 根据标签ID删除标签
}
