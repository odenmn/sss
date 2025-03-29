package com.xjx.example.entity;

import java.time.LocalDateTime;

public class Article {
    private int id;
    private String title;
    private String content;
    private User author;
    private LocalDateTime publishTime;
    private int likeCount;

    public Article() {
    }

    public Article(int id, String title, String content, User author, LocalDateTime publishTime, int likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishTime = publishTime;
        this.likeCount = likeCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }
}
