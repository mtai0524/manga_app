package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.Comment;
import com.example.bietdoidoctruyen.model.CommentWithUserInfo;
import com.example.bietdoidoctruyen.model.History;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    DbHelper helper;
    public CommentDAO (Context context){
        helper = new DbHelper(context);
    }


    public List<CommentWithUserInfo> getCommentsWithUserInfo(int mangaId) {
        List<CommentWithUserInfo> commentList = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT Comment.comment, Comment.userId, REGISTER.avatar, REGISTER.Username " +
                "FROM Comment " +
                "INNER JOIN REGISTER ON Comment.userId = REGISTER.userId " +
                "WHERE Comment.mangaId = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(mangaId)});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex("userId"));
                @SuppressLint("Range") String avatar = cursor.getString(cursor.getColumnIndex("avatar"));
                @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex("Username"));
                @SuppressLint("Range") String comment = cursor.getString(cursor.getColumnIndex("comment"));

                CommentWithUserInfo commentWithUserInfo = new CommentWithUserInfo(userId, avatar, userName, comment);
                commentList.add(commentWithUserInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return commentList;
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
