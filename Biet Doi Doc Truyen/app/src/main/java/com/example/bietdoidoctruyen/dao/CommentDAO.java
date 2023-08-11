package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.Comment;
import com.example.bietdoidoctruyen.model.History;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    DbHelper helper;
    public CommentDAO (Context context){
        helper = new DbHelper(context);
    }


    public void addComment(int userId, int mangaId, String commentText) {
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("mangaId", mangaId);
        values.put("comment", commentText);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.insert("Comment", null, values);
    }

    @SuppressLint("Range")
    public List<Comment> getCommentByMangaId(int mangaId) {
        List<Comment> commentList = new ArrayList<>();
        String query = "SELECT * FROM Comment WHERE mangaId = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(mangaId)});

        if (cursor.moveToFirst()) {
            do {
                Comment comment = new Comment();
                comment.setCmtId(cursor.getInt(cursor.getColumnIndex("cmtId")));
                comment.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                comment.setMangaId(cursor.getInt(cursor.getColumnIndex("mangaId")));
                comment.setComment(cursor.getString(cursor.getColumnIndex("comment")));
                commentList.add(comment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return commentList;
    }

    @SuppressLint("Range")
    public List<Comment> getCommentByUserId(int userId, int mangaId) {
        List<Comment> commentList = new ArrayList<>();
        String query = "SELECT * FROM Comment WHERE userId = ? AND mangaId = ?";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(mangaId)});

        if (cursor.moveToFirst()) {
            do {
                Comment comment = new Comment();
                comment.setCmtId(cursor.getInt(cursor.getColumnIndex("cmtId")));
                comment.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                comment.setMangaId(cursor.getInt(cursor.getColumnIndex("mangaId")));
                comment.setComment(cursor.getString(cursor.getColumnIndex("comment")));
               commentList.add(comment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return commentList;
    }
}
