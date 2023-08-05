package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.CategoryManga;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.ArrayList;
import java.util.List;

public class CategoryMangaDAO {
    DbHelper helper;

    public CategoryMangaDAO(Context context) {
        helper = new DbHelper(context);
    }

    public List<Integer> getMangaIdsByCategoryId(int categoryId) {
        List<Integer> mangaIds = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        // Truy vấn dữ liệu từ bảng Category_Manga dựa vào categoryId
        String query = "SELECT mangaId FROM Category_Manga WHERE categoryId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(categoryId)});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int mangaId = cursor.getInt(cursor.getColumnIndex("mangaId"));
                mangaIds.add(mangaId);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return mangaIds;
    }

    // gọi hàm này truyền categoryId
    public List<Manga> getMangaByCategoryId(int categoryId) {
        List<Manga> mangaList = new ArrayList<>();
        List<Integer> mangaIds = getMangaIdsByCategoryId(categoryId); // gọi hàm trên sau đó trả về List mangaId

        SQLiteDatabase db = helper.getReadableDatabase();

        for (int mangaId : mangaIds) {
            Cursor cursor = null;
            try {
                // SELECT manga by mangaId
                String selectQuery = "SELECT * FROM MANGA WHERE mangaId = ?";
                cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(mangaId)});

                // Loop through the cursor to create Manga objects and add them to the list
                if (cursor != null && cursor.moveToFirst()) {
                    @SuppressLint("Range") String mangaName = cursor.getString(cursor.getColumnIndex("mangaName"));
                    @SuppressLint("Range") String mangaImage = cursor.getString(cursor.getColumnIndex("image"));

                    // Create a Manga object and add it to the list
                    Manga manga = new Manga(mangaId, mangaName, mangaImage, categoryId);
                    mangaList.add(manga);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Close the cursor
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        db.close();
        return mangaList;
    }
}
