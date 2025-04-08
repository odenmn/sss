package com.xjx.example.service;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Column;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface ColumnService {
    // 新建栏目
    boolean addColumn(Column column);
    // 删除栏目
    boolean deleteColumn(int columnId);
    // 根据栏目ID获取栏目
    Column getColumnByColumnId(int columnId);
    // 模糊搜索栏目
    List<Column> searchColumnsByColumnName(String columnName);
    // 获得用户的栏目
    List<Column> getColumnsByUser(User user);
    // 添加文章到栏目中
    boolean addArticleToColumn(int columnId, int articleId);
    // 根据栏目获取文章
    List<Article> getArticlesByColumn(int columnId);
}
