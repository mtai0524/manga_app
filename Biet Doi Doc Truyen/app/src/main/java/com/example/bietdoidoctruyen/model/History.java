package com.example.bietdoidoctruyen.model;

public class History {
    private int historyId;
    private int userId;
    private int mangaId;

    public History() {
    }

    public History(int historyId, int userId, int mangaId) {
        this.historyId = historyId;
        this.userId = userId;
        this.mangaId = mangaId;
    }

    public History(int userId, int mangaId) {
        this.userId = userId;
        this.mangaId = mangaId;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
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
}
