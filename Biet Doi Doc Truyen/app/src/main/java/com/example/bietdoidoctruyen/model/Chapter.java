package com.example.bietdoidoctruyen.model;

import java.io.Serializable;

public class Chapter implements Serializable {

    private int chapterId;
    private int mangaId;
    String chapterName;

    public Chapter() {
    }

    public Chapter(int chapterId, int mangaId, String chapterName) {
        this.chapterId = chapterId;
        this.mangaId = mangaId;
        this.chapterName = chapterName;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getMangaId() {
        return mangaId;
    }

    public void setMangaId(int mangaId) {
        this.mangaId = mangaId;
    }

    public Chapter(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
}
