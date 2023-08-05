package com.example.bietdoidoctruyen.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Manga implements Serializable {


    private int idManga;
    private String mangaName;
    private String image;
    private int categoryId;
    private String description;

    public Manga(int idManga, String mangaName, String image, String description) {
        this.idManga = idManga;
        this.mangaName = mangaName;
        this.image = image;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Manga(int idManga, String mangaName, String image, int categoryId, String description) {
        this.idManga = idManga;
        this.mangaName = mangaName;
        this.image = image;
        this.categoryId = categoryId;
        this.description = description;
    }

    public Manga(int idManga, String mangaName, String image, int categoryId) {
        this.idManga = idManga;
        this.mangaName = mangaName;
        this.image = image;
        this.categoryId = categoryId;

    }

    private List<Chapter> chapterList;

    public Manga(int idManga, String mangaName, String image, List<Chapter> chapterList) {
        this.idManga = idManga;
        this.mangaName = mangaName;
        this.image = image;
        this.chapterList = chapterList;
    }

    public Manga(String mangaName, String image) {
        this.mangaName = mangaName;
        this.image = image;
    }
    public Manga(int id , String mangaName, String image) {
        this.idManga = id;
        this.mangaName = mangaName;
        this.image = image;
    }
    public Manga() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getIdManga() {
        return idManga;
    }

    public void setIdManga(int idManga) {
        this.idManga = idManga;
    }

    public String getMangaName() {
        return mangaName;
    }

    public void setMangaName(String mangaName) {
        this.mangaName = mangaName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Manga other = (Manga) obj;
        return this.idManga == other.idManga; // Xác định hai Manga giống nhau nếu cùng có ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(idManga); // Sử dụng ID để tính hashCode
    }
}
