package com.example.bietdoidoctruyen.model;

public class CategoryManga {
    private int categoryId;
    private int mangaId;

    public int getMangaId() {
        return mangaId;
    }

    public void setMangaId(int mangaId) {
        this.mangaId = mangaId;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public CategoryManga(int categoryId, int mangaId) {
        this.categoryId = categoryId;
        this.mangaId = mangaId;
    }

    public CategoryManga() {
    }
}
