package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.MangaContent;

import java.util.ArrayList;
import java.util.List;

public class MangaContentDAO {
    DbHelper helper;
    public MangaContentDAO(Context context){
        helper = new DbHelper(context);
    }

    public void addOrUpdateContentByChapterId(int chapterContentId, String imgContent, String mangaTxt) {
        SQLiteDatabase db = helper.getWritableDatabase();

        // Xóa dữ liệu cũ trước khi thêm mới hoặc cập nhật
        db.delete("Content", "chapterContentId = ?", new String[]{String.valueOf(chapterContentId)});

        ContentValues values = new ContentValues();
        values.put("chapterContentId", chapterContentId);
        values.put("imgContent", imgContent);
        values.put("mangaTxt", mangaTxt);

        db.insert("Content", null, values);

        db.close();
    }
//    public void addOrUpdateContentByChapterId(int chapterContentId, String imgContent, String mangaTxt) {
//        ContentValues values = new ContentValues();
//        values.put("chapterContentId", chapterContentId);
//        values.put("imgContent", imgContent);
//        values.put("mangaTxt", mangaTxt);
//
//        SQLiteDatabase db = helper.getWritableDatabase();
//
//        long result = db.insertWithOnConflict("Content", null, values, SQLiteDatabase.CONFLICT_REPLACE);
//
//        db.close();
//    }
    public void updateContentByChapterContentId(int chapterContentId, String imgContent, String mangaTxt) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imgContent", imgContent);
        values.put("mangaTxt", mangaTxt);
        db.update("Content", values, "chapterContentId = ?", new String[]{String.valueOf(chapterContentId)});
        db.close();
    }

    public void addImgContentByChapterId(int chapterContentId, String imgContent, String mangaTxt) {
        ContentValues values = new ContentValues();
        values.put("chapterContentId", chapterContentId);
        values.put("imgContent", imgContent);
        values.put("mangaTxt", mangaTxt);

        SQLiteDatabase db = helper.getWritableDatabase();

        db.replace("Content", null, values);
    }

    @SuppressLint("Range")
    public String getTxtContentByChapterContentId(int chapterContentId) {
        String mangaTxt = null;
        String query = "SELECT mangaTxt FROM Content WHERE chapterContentId = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(chapterContentId)});

        if (cursor.moveToFirst()) {
            mangaTxt = cursor.getString(cursor.getColumnIndex("mangaTxt"));
        }

        cursor.close();
        return mangaTxt;
    }
    @SuppressLint("Range")
    public String getImgContentByChapterContentId(int chapterContentId) {
        String imgContent = null;
        String query = "SELECT imgContent FROM Content WHERE chapterContentId = ? LIMIT 1";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(chapterContentId)});

        if (cursor.moveToFirst()) {
            imgContent = cursor.getString(cursor.getColumnIndex("imgContent"));
        }

        cursor.close();
        return imgContent;
    }
    @SuppressLint("Range")
    public List<Integer> getChapterContentIdsByChapterId(int chapterId) {
        List<Integer> chapterContentIds = new ArrayList<>();
        String query = "SELECT DISTINCT chapterContentId FROM Content WHERE chapterContentId = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(chapterId)});

        if (cursor.moveToFirst()) {
            do {
                int chapterContentId = cursor.getInt(cursor.getColumnIndex("chapterContentId"));
                chapterContentIds.add(chapterContentId);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return chapterContentIds;
    }

    @SuppressLint("Range")
    public ArrayList<MangaContent> getAll() {
        ArrayList<MangaContent> contents = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Content", null);
        if (cursor.moveToFirst()) {
            do {
                MangaContent content = new MangaContent();
                content.setContentId(cursor.getInt(cursor.getColumnIndex("contentId")));
                content.setChapterContentId(cursor.getInt(cursor.getColumnIndex("chapterContentId")));
                content.setImgContent(cursor.getString(cursor.getColumnIndex("imgContent")));
                contents.add(content);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contents;
    }
    @SuppressLint("Range")
    public List<MangaContent> getContentByChapterId(int chapterId) {
        List<MangaContent> contents = new ArrayList<>();
        String query = "SELECT * FROM Content WHERE chapterContentId = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(chapterId)});

        if (cursor.moveToFirst()) {
            do {
                MangaContent content = new MangaContent();
                content.setContentId(cursor.getInt(cursor.getColumnIndex("contentId")));
                content.setChapterContentId(cursor.getInt(cursor.getColumnIndex("chapterContentId")));
                content.setImgContent(cursor.getString(cursor.getColumnIndex("imgContent")));
                content.setMangaTxt(cursor.getString(cursor.getColumnIndex("mangaTxt")));
                contents.add(content);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contents;
    }

}
