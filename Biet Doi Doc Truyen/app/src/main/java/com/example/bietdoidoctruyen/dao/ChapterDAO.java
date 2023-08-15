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
