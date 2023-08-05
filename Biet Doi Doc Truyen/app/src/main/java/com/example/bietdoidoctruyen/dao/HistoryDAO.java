package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.History;
import com.example.bietdoidoctruyen.model.Manga;
import com.example.bietdoidoctruyen.model.MangaContent;

import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {

    DbHelper helper;
    public HistoryDAO (Context context){
        helper = new DbHelper(context);
    }

    public long addMangaToHistory(int userId, int mangaId) {
        // Kiểm tra xem mangaId đã tồn tại trong bảng History hay chưa
        if (isMangaIdExist(userId, mangaId)) {
            return -1; // Trả về -1 để chỉ ra rằng thêm thất bại do mangaId đã tồn tại
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("mangaId", mangaId);
        return db.insert("History", null, values);
    }

    // Hàm kiểm tra xem mangaId đã tồn tại trong bảng History hay chưa
    private boolean isMangaIdExist(int userId, int mangaId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM History WHERE userId = ? AND mangaId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(mangaId)});
        boolean isExist = cursor.getCount() > 0;
        cursor.close();
        return isExist;
    }


    @SuppressLint("Range")
    public List<History> getHistoryByUserId(int userId) {
        List<History> historyList = new ArrayList<>();
        String query = "SELECT * FROM History WHERE userId = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            do {
                History history = new History();
                history.setHistoryId(cursor.getInt(cursor.getColumnIndex("historyId")));
                history.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                history.setMangaId(cursor.getInt(cursor.getColumnIndex("mangaId")));
                historyList.add(history);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return historyList;
    }

}
