package com.example.bietdoidoctruyen.model;

public class CommentWithUserInfo {


    private int userId;

    private String userName;
    private String comment;
    private String avatar;


    public CommentWithUserInfo(int userId ,String avatar, String userName, String comment) {
        this.userId = userId;
        this.avatar = avatar;
        this.userName = userName;
        this.comment = comment;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getAvatar() {
        return avatar;
    }

    public String getUserName() {
        return userName;
    }

    public String getComment() {
        return comment;
    }
}