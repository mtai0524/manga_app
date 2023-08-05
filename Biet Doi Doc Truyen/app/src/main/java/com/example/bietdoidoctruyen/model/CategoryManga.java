package com.example.bietdoidoctruyen.model;

public class CategoryManga {
    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private int mangaId;



    public int getMangaId() {
        return mangaId;
    }

    public void setMangaId(int mangaId) {
        this.mangaId = mangaId;
    }

    public CategoryManga(int categoryId, int mangaId) {
        this.categoryId = categoryId;
        this.mangaId = mangaId;
    }

    public CategoryManga() {
    }
}
