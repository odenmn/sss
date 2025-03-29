package com.xjx.example.entity;

public class User {
    private int id;
    private String username;
    private String password;
    private String salt;
    private boolean isBanned;

    public User() {
    }

    public User(int id, String username, String password, String salt, boolean isBanned) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.isBanned = isBanned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }
}
