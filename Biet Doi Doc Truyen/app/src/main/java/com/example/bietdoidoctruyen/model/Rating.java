package com.example.bietdoidoctruyen.model;

public class Rating {
    private int ratingId;
    private int userId;
    private int mangaId;
    private float rating;
    private String ratingContent;

    public Rating(int ratingId, int userId, int mangaId, float rating, String ratingContent) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.mangaId = mangaId;
        this.rating = rating;
        this.ratingContent = ratingContent;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getRatingContent() {
        return ratingContent;
    }

    public void setRatingContent(String ratingContent) {
        this.ratingContent = ratingContent;
    }
}
