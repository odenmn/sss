package com.xjx.example.entity;

import java.util.List;

public class Column {
    private int id;
    private String columnName;
    private User user;
    private String description;
    private List<Article> articles;

    public Column() {
    }

    public Column(int id, String columnName, User user , String description) {
        this.id = id;
        this.columnName = columnName;
        this.user = user;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
