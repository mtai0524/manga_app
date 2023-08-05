package com.example.bietdoidoctruyen.model;

import java.util.List;

public class ListData {


    private int categoryId;



    private int type;
    private List<Manga> listManga;
    private List<User> listUser;
    private String catagoryName;

    public ListData(int categoryId, int type, List<Manga> listManga, List<User> listUser, String catagoryName) {
        this.categoryId = categoryId;
        this.type = type;
        this.listManga = listManga;
        this.listUser = listUser;
        this.catagoryName = catagoryName;
    }

    public ListData(int categoryId, String catagoryName) {
        this.categoryId = categoryId;
        this.catagoryName = catagoryName;
    }

    public ListData(){

    }


    public ListData(int type, List<Manga> listManga, List<User> listUser, String catagoryName) {
        this.type = type;
        this.listManga = listManga;
        this.listUser = listUser;
        this.catagoryName = catagoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Manga> getListCategory() {
        return listManga;
    }

    public void setListCategory(List<Manga> listManga) {
        this.listManga = listManga;
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

}
