package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bietdoidoctruyen.database.DbHelper;

public class RatingDAO {
    DbHelper helper;
    public RatingDAO (Context context){
        helper = new DbHelper(context);
    }



    public void addRating(int userId, int mangaId, float rating, String ratingContent) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("mangaId", mangaId);
        values.put("rating", rating);
        values.put("ratingContent", ratingContent);

        db.insert("Rating", null, values);
    }

    public void insertRating(int userId, int mangaId, float rating, String ratingContent) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("mangaId", mangaId);
        values.put("rating", rating);
        values.put("ratingContent", ratingContent);

        long newRowId = db.insert("Rating", null, values);

        if (newRowId == -1) {
            Log.e("Insert Error", "Failed to insert row into Rating table");
        } else {
            Log.i("Insert Success", "Row inserted with ID: " + newRowId);
        }

        db.close();
    }

    public void insertOrUpdateRating(int userId, int mangaId, float rating, String ratingContent) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("mangaId", mangaId);
        values.put("rating", rating);
        values.put("ratingContent", ratingContent);

        String whereClause = "userId = ? AND mangaId = ?";
        String[] whereArgs = {String.valueOf(userId), String.valueOf(mangaId)};

        Cursor cursor = db.query("Rating", null, whereClause, whereArgs, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            int rowsAffected = db.update("Rating", values, whereClause, whereArgs);
            cursor.close();
            if (rowsAffected <= 0) {
                // Handle update failure
            }
        } else {
            long newRowId = db.insert("Rating", null, values);
        }

        db.close();
    }




    public void addOrUpdateRating(int userId, int mangaId, float rating, String ratingContent) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("mangaId", mangaId);
        values.put("rating", rating);
        values.put("ratingContent", ratingContent);

        String whereClause = "userId = ? AND mangaId = ?";
        String[] whereArgs = {String.valueOf(userId), String.valueOf(mangaId)};

        db.updateWithOnConflict("Rating", values, whereClause, whereArgs, SQLiteDatabase.CONFLICT_REPLACE);

        db.close();
    }

    @SuppressLint("Range")
    public float getRatingForMangaAndUser(int mangaId, int userId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT rating FROM Rating WHERE mangaId = ? AND userId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(mangaId), String.valueOf(userId)});

        float rating = 0; // Giá trị mặc định nếu không tìm thấy dữ liệu
        if (cursor.moveToFirst()) {
            rating = cursor.getFloat(cursor.getColumnIndex("rating"));
        }

        cursor.close();
        return rating;
    }

    @SuppressLint("Range")
    public String getCommentForMangaAndUser(int mangaId, int userId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT ratingContent FROM Rating WHERE mangaId = ? AND userId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(mangaId), String.valueOf(userId)});
        String comment = ""; // Giá trị mặc định nếu không tìm thấy dữ liệu
        if (cursor.moveToFirst()) {
            comment = cursor.getString(cursor.getColumnIndex("ratingContent"));
        }

        cursor.close();
        return comment;
    }

}
