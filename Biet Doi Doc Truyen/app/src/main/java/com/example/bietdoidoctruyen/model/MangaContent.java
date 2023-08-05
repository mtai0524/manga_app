package com.example.bietdoidoctruyen.model;

import java.io.Serializable;

public class MangaContent implements Serializable {
    private int contentId;
    private int chapterContentId;
    private int imageMangaId;
    private String imgContent;
    private String mangaTxt;

    public String getMangaTxt() {
        return mangaTxt;
    }

    public void setMangaTxt(String mangaTxt) {
        this.mangaTxt = mangaTxt;
    }

    public MangaContent(int contentId, int chapterContentId, String imgContent, String mangaTxt) {
        this.contentId = contentId;
        this.chapterContentId = chapterContentId;
        this.imgContent = imgContent;
        this.mangaTxt = mangaTxt;
    }

    public MangaContent(int contentId, int chapterContentId, String imgContent) {
        this.contentId = contentId;
        this.chapterContentId = chapterContentId;
        this.imgContent = imgContent;
    }
    public MangaContent(){

    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getChapterContentId() {
        return chapterContentId;
    }

    public void setChapterContentId(int chapterContentId) {
        this.chapterContentId = chapterContentId;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    public MangaContent(int imageMangaId) {
        this.imageMangaId = imageMangaId;
    }

    public int getImageMangaId() {
        return imageMangaId;
    }

    public void setImageMangaId(int imageMangaId) {
        this.imageMangaId = imageMangaId;
    }
}
