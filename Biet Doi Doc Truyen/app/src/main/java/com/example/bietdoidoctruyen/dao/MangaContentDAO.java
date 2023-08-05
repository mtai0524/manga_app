package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
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
