package com.example.bietdoidoctruyen.model;

import java.util.List;

public class ListData {


    private int categoryId;

    private int type;
    private List<Manga> listManga;
    private String categoryName;


    public ListData (String categoryName){
        this.categoryName = categoryName;
    }

    public ListData(int categoryId,String categoryName,  int type) {
        this.categoryId = categoryId;
        this.type = type;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName;
    }

    public ListData(int categoryId, String catagoryName) {
        this.categoryId = categoryId;
        this.categoryName = catagoryName;
    }

    public ListData(){

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

  
    public String getCatagoryName() {
        return categoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.categoryName = catagoryName;
    }

}
