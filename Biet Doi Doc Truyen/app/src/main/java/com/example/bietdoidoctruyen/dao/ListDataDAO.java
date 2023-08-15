package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.ListData;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.ArrayList;
import java.util.List;

public class ListDataDAO {
    DbHelper helper;

    public ListDataDAO(Context context) {
        helper = new DbHelper(context);
    }
    public void deleteCategoryById(int categoryId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("Category", "categoryId = ?", new String[]{String.valueOf(categoryId)});
        db.close();
    }
    public List<String> getAllCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT categoryName FROM Category";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String categoryName = cursor.getString(cursor.getColumnIndex("categoryName"));
                categoryNames.add(categoryName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categoryNames;
    }

    public void updateCategoryName(int categoryId, String newCategoryName) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("categoryName", newCategoryName);

        db.update("Category", values, "categoryId = ?", new String[]{String.valueOf(categoryId)});

        db.close();
    }

    public long insertCategory(String categoryName, int type) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("categoryName", categoryName);
        values.put("type", type);
        long categoryId = db.replace("Category", null, values);
        db.close();
        return categoryId;
    }

    public List<ListData> getAllCategories() {
        List<ListData> categories = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;

        try {
            String selectQuery = "SELECT * FROM Category";
            cursor = db.rawQuery(selectQuery, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int categoryId = cursor.getInt(cursor.getColumnIndex("categoryId"));
                    @SuppressLint("Range") String categoryName = cursor.getString(cursor.getColumnIndex("categoryName"));
                    @SuppressLint("Range") int categoryType = cursor.getInt(cursor.getColumnIndex("type"));

                    ListData category = new ListData(categoryId, categoryName, categoryType);
                    categories.add(category);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return categories;
    }


    public List<String> getCategoryNamesByMangaId(int mangaId) {
        List<String> categoryNames = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT c.categoryName FROM Category_Manga cm " +
                "JOIN Category c ON cm.categoryId = c.categoryId " +
                "WHERE cm.mangaId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(mangaId)});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String categoryName = cursor.getString(cursor.getColumnIndex("categoryName"));
                categoryNames.add(categoryName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categoryNames;
    }

    @SuppressLint("Range")
    public int getCategoryIdByName(String categoryName) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int categoryId = -1;

        String selectQuery = "SELECT categoryId FROM Category WHERE categoryName = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{categoryName});

        if (cursor != null && cursor.moveToFirst()) {
            categoryId = cursor.getInt(cursor.getColumnIndex("categoryId"));
            cursor.close();
        }

        db.close();
        return categoryId;
    }

    public List<ListData> getCategoryName() {
        List<ListData> listCategoryName = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            // SELECT all rows from the MANGA table where categoryId matches the given value
            String selectQuery = "SELECT * FROM Category";
            cursor = db.rawQuery(selectQuery, null);

            // Loop through the cursor to create Manga objects and add them to the list
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int categoryId = cursor.getInt(cursor.getColumnIndex("categoryId"));
                    @SuppressLint("Range") String categoryName = cursor.getString(cursor.getColumnIndex("categoryName"));

                    // Create a Manga object and add it to the list
                    ListData listCate = new ListData(categoryId, categoryName);
                    listCategoryName.add(listCate);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the cursor and database
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return listCategoryName;
    }

    public List<Manga> getMangaByCategoryId(int categoryId) {
        List<Manga> mangaList = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            // SELECT all rows from the MANGA table where categoryId matches the given value
            String selectQuery = "SELECT * FROM MANGA WHERE categoryId = ?";
            cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(categoryId)});

            // Loop through the cursor to create Manga objects and add them to the list
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int mangaId = cursor.getInt(cursor.getColumnIndex("mangaId"));
                    @SuppressLint("Range") String mangaName = cursor.getString(cursor.getColumnIndex("mangaName"));
                    @SuppressLint("Range") String mangaImage = cursor.getString(cursor.getColumnIndex("image"));

                    // Create a Manga object and add it to the list
                    Manga manga = new Manga(mangaId, mangaName, mangaImage);
                    mangaList.add(manga);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the cursor and database
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return mangaList;
    }

    @SuppressLint("Range")
    public String getCategoryNameById(int categoryId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String categoryName = "";

        String selectQuery = "SELECT categoryName FROM Category WHERE categoryId = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(categoryId)});

        if (cursor != null && cursor.moveToFirst()) {
            categoryName = cursor.getString(cursor.getColumnIndex("categoryName"));
            cursor.close();
        }

        return categoryName;
    }
}
