package com.xjx.example.entity;

import java.util.Date;

public class Comment {
    private int id;
    private String content;
    private User user;
    private Article article;
    private Date publishTime;
    private int likeCount;

    public Comment() {
    }

    public Comment(int id, String content, User user, Article article, Date publishTime, int likeCount) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.article = article;
        this.publishTime = publishTime;
        this.likeCount = likeCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}