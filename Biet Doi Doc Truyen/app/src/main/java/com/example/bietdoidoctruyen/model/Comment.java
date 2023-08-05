package com.example.bietdoidoctruyen.model;

public class Comment {
    private int cmtId;
    private int userId;
    private int mangaId;
    private String comment;

    public Comment() {
    }

    public Comment(int cmtId, int userId, int mangaId, String comment) {
        this.cmtId = cmtId;
        this.userId = userId;
        this.mangaId = mangaId;
        this.comment = comment;
    }

    public Comment(int userId, int mangaId, String comment) {
        this.userId = userId;
        this.mangaId = mangaId;
        this.comment = comment;
    }

    public int getCmtId() {
        return cmtId;
    }

    public void setCmtId(int cmtId) {
        this.cmtId = cmtId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMangaId() {
        return mangaId;
    }

    public void setMangaId(int mangaId) {
        this.mangaId = mangaId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
