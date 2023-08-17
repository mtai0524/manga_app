package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {
    DbHelper helper;
    public ChapterDAO(Context context){
        helper = new DbHelper(context);
    }

    // Constructor
    @SuppressLint("Range")
    public List<Integer> getChapterIdsByMangaId(int mangaId) {
        List<Integer> chapterIds = new ArrayList<>();
        String query = "SELECT chapterId FROM Chapter WHERE mangaId = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(mangaId)});

        if (cursor.moveToFirst()) {
            do {
                int chapterId = cursor.getInt(cursor.getColumnIndex("chapterId"));
                chapterIds.add(chapterId);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return chapterIds;
    }


    @SuppressLint("Range")
    public List<String> getChapterNamesForManga(int mangaId) {
        List<String> chapterNames = new ArrayList<>();
        String query = "SELECT chapterName FROM Chapter WHERE mangaId = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(mangaId)});

        if (cursor.moveToFirst()) {
            do {
                String chapterName = cursor.getString(cursor.getColumnIndex("chapterName"));
                chapterNames.add(chapterName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return chapterNames;
    }
    public void updateChapterByChapterId(int chapterId, String chapterName) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("chapterName", chapterName);

        db.update("Chapter", values, "chapterId = ?", new String[]{String.valueOf(chapterId)});

        db.close();
    }

    // Phương thức để lấy danh sách các Chapter thuộc về một Manga cụ thể dựa vào mangaId

    @SuppressLint("Range")
    public List<Chapter> getChaptersForManga(int mangaId) {
        List<Chapter> chapters = new ArrayList<>();
        String query = "SELECT * FROM Chapter WHERE mangaId = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(mangaId)});

        if (cursor.moveToFirst()) {
            do {
                Chapter chapter = new Chapter();
                chapter.setChapterId(cursor.getInt(cursor.getColumnIndex("chapterId")));
                chapter.setMangaId(cursor.getInt(cursor.getColumnIndex("mangaId")));
                chapter.setChapterName(cursor.getString(cursor.getColumnIndex("chapterName")));
                // Thiết lập các thông tin khác của đối tượng Chapter (nếu có)
                chapters.add(chapter);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return chapters;
    }

    public void addChapter(int mangaId, String chapterName) {
        ContentValues values = new ContentValues();
        values.put("mangaId", mangaId);
        values.put("chapterName", chapterName);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.replace("Chapter", null, values);
    }

}
