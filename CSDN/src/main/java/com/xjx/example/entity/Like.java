package com.xjx.example.entity;

public class Like {
    private int id;
    private int articleId;
    private int userId;

    public Like() {
    }

    public Like(int id, int articleId, int userId) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
    }

    public Like(int articleId, int userId) {
        this.articleId = articleId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
