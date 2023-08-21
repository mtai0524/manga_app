package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.Manga;

import java.util.ArrayList;
import java.util.List;

public class MangaDAO {
    DbHelper helper;

    public MangaDAO(Context context) {
        helper = new DbHelper(context);
    }
    @SuppressLint("Range")
    public String getDescriptionByMangaId(int mangaId) {
        String mangaDescription = "";
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = { "description" };
        String selection = "mangaId = ?";
        String[] selectionArgs = { String.valueOf(mangaId) };

        Cursor cursor = db.query("MANGA", projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            mangaDescription = cursor.getString(cursor.getColumnIndex("description"));
        }

        cursor.close();
        db.close();
        return mangaDescription;
    }
    public List<Integer> getAllMangaIds() {
        List<Integer> mangaIds = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT mangaId FROM MANGA";
        Cursor cursor = db.rawQuery(query, null);

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

    public List<String> getAllMangaNames() {
        List<String> mangaNames = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT mangaName FROM MANGA";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String mangaName = cursor.getString(cursor.getColumnIndex("mangaName"));
                mangaNames.add(mangaName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return mangaNames;
    }

    public void updateManga(int mangaId,String newName, String newImage, String newDescription) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mangaName", newName);
        values.put("image", newImage);
        values.put("description", newDescription);

        db.update("MANGA", values, "mangaId = ?", new String[]{String.valueOf(mangaId)});

        db.close();
    }

    // trả về id để xác định id của manga vừa mới được thêm vào sqlite
    public long insertManga(String mangaName, String image, String description) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mangaName", mangaName);
        values.put("image", image);
        values.put("description", description);

        long mangaId = db.insert("MANGA", null, values);

        db.close();

        return mangaId;
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
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                // Create a Manga object and add it to the list
                Manga manga = new Manga(mangaId, mangaName, mangaImage, description);
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
                String description = cs.getString(3);
                Manga c = new Manga(id,name,imgName, description);
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
