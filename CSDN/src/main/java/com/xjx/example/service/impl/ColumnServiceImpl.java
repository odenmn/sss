package com.xjx.example.service.impl;

import com.xjx.example.dao.AdminDao;
import com.xjx.example.dao.ArticleDao;
import com.xjx.example.dao.ColumnDao;
import com.xjx.example.dao.UserDao;
import com.xjx.example.dao.impl.ArticleDaoImpl;
import com.xjx.example.dao.impl.ColumnDaoImpl;
import com.xjx.example.dao.impl.UserDaoImpl;
import com.xjx.example.entity.Article;
import com.xjx.example.entity.Column;
import com.xjx.example.entity.User;
import com.xjx.example.service.ColumnService;
import com.xjx.example.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColumnServiceImpl implements ColumnService {
    ColumnDao columnDao = new ColumnDaoImpl();

    @Override
    public boolean addColumn(Column column) {
        try {
            return columnDao.addColumn(column);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteColumn(int columnId) {
        try {
            return columnDao.deleteColumn(columnId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Column getColumnByColumnId(int columnId) {
        try {
            return  columnDao.getColumnByColumnId(columnId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Column> searchColumnsByColumnName(String columnName) {
        try {
            return columnDao.searchColumnsByColumnName(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Column> getColumnsByUser(User user) {
        try {
            return columnDao.getColumnsByUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addArticleToColumn(int columnId, int articleId) {
        try {
            return columnDao.addArticleToColumn(columnId,articleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Article> getArticlesByColumn(int columnId) {
        try {
            return columnDao.getArticlesByColumn(columnId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeArticleFromColumn(int articleId) {
        try {
            columnDao.removeArticleFromColumn(articleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
