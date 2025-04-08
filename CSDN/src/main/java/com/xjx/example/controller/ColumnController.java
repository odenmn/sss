package com.xjx.example.controller;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Column;
import com.xjx.example.entity.User;
import com.xjx.example.service.ColumnService;
import com.xjx.example.service.impl.ColumnServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class ColumnController {
    private ColumnService columnService = new ColumnServiceImpl();

    public boolean addColumn(Column column){
        return columnService.addColumn(column);
    }
    // 删除栏目
    public boolean deleteColumn(int columnId){
        return columnService.deleteColumn(columnId);
    }
    // 根据栏目ID获取栏目
    public Column getColumnByColumnId(int columnId){
        return columnService.getColumnByColumnId(columnId);
    }
    // 模糊搜索栏目
    public List<Column> searchColumnsByColumnName(String columnName){
        return columnService.searchColumnsByColumnName(columnName);
    }
    // 获得用户的栏目
    public List<Column> getColumnsByUser(User user){
        return columnService.getColumnsByUser(user);
    }
    // 添加文章到栏目中
    public boolean addArticleToColumn(int columnId, int articleId){
        return columnService.addArticleToColumn(columnId,articleId);
    }
    // 将文章移除出专栏
    public void removeArticleFromColumn(int articleId){
        columnService.removeArticleFromColumn(articleId);
    }
    // 根据栏目获取文章
    public List<Article> getArticlesByColumn(int columnId){
        return columnService.getArticlesByColumn(columnId);
    }
}
