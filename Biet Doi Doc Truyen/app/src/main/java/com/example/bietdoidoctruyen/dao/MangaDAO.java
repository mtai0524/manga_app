package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.StringUtils;
import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.ArrayList;
import java.util.List;

public class MangaDAO {
    DbHelper helper;

    public MangaDAO(Context context) {
        helper = new DbHelper(context);
    }

    public List<Manga> getMangaByName(String searchName) {
        List<Manga> mangaList = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        // Truy vấn dữ liệu từ bảng MANGA dựa vào tên truyện
        String query = "SELECT * FROM MANGA WHERE mangaName LIKE ?";
        Cursor cursor = db.rawQuery(query, new String[]{"%" + searchName + "%"});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int mangaId = cursor.getInt(cursor.getColumnIndex("mangaId"));
                @SuppressLint("Range") String mangaName = cursor.getString(cursor.getColumnIndex("mangaName"));
                @SuppressLint("Range") String mangaImage = cursor.getString(cursor.getColumnIndex("image"));
                @SuppressLint("Range") int categoryId = cursor.getInt(cursor.getColumnIndex("categoryId"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                // Create a Manga object and add it to the list
                Manga manga = new Manga(mangaId, mangaName, mangaImage, categoryId, description);
                mangaList.add(manga);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return mangaList;
    }

    public ArrayList<Manga> getAll() {
        ArrayList<Manga> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM MANGA", null);
        if (cs.moveToFirst()) {
            do {
                int id = cs.getInt(0);
                String name = cs.getString(1);
                String imgName = cs.getString(2);
                int categoryId = cs.getInt(3);
                String description = cs.getString(4);
                Manga c = new Manga(id,name,imgName, categoryId, description);
                list.add(c);
            } while (cs.moveToNext());
        }
        cs.close();
        db.close();
        return list;
    }

    public Manga getCategoryById(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Manga manga = null;

        Cursor cursor = db.query("MANGA", null, "mangaId = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("mangaName"));
            @SuppressLint("Range") String imgName = cursor.getString(cursor.getColumnIndex("image"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
            manga = new Manga(id, name, imgName, description);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return manga;
    }
}
