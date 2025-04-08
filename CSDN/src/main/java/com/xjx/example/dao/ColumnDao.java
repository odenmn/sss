package com.xjx.example.dao;

import com.xjx.example.entity.Article;
import com.xjx.example.entity.Column;
import com.xjx.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface ColumnDao {
    boolean addColumn(Column column) throws SQLException;
    boolean deleteColumn(int columnId) throws SQLException;
    Column getColumnByColumnId(int columnId) throws SQLException;
    List<Article> getArticlesByColumn(int columnId) throws SQLException;
    List<Column> searchColumnsByColumnName(String columnName) throws SQLException;
    List<Column> getColumnsByUser(User user) throws SQLException;
    boolean addArticleToColumn(int columnId, int articleId) throws SQLException;
    void removeArticleFromColumn(int articleId) throws SQLException;
}
