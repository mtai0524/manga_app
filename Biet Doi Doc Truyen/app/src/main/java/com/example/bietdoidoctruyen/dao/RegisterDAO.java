package com.example.bietdoidoctruyen.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bietdoidoctruyen.database.DbHelper;
import com.example.bietdoidoctruyen.model.Register;

import java.util.ArrayList;
import java.util.List;

public class RegisterDAO {
    DbHelper helper;




    public RegisterDAO(Context context) {
        helper = new DbHelper(context);

    }
    public void deleteUserById(int userId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("REGISTER", "userId = ?", new String[]{String.valueOf(userId)});
        db.close();
    }
    public void updateRoleByUserId(int userId, String newRole) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("role", newRole);

        db.update("REGISTER", values, "userId = ?", new String[]{String.valueOf(userId)});

        db.close();
    }

    public List<Register> getAllRegisters2() {
        List<Register> registerList = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT * FROM REGISTER";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex("userId"));

                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("Username"));
                @SuppressLint("Range") String avatarUri = cursor.getString(cursor.getColumnIndex("avatar"));
                @SuppressLint("Range") String role = cursor.getString(cursor.getColumnIndex("role"));

                Register register = new Register(userId,username, avatarUri, role);
                registerList.add(register);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return registerList;
    }

    @SuppressLint("Range")
    public String getRoleByUserId(int userId) {
        String role = null;
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT role FROM REGISTER WHERE userId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            role = cursor.getString(cursor.getColumnIndex("role"));
        }

        cursor.close();
        db.close();

        return role;
    }
    @SuppressLint("Range")
    public String getAvatarUriByUserId(int userId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String avatarUri = null;

        String query = "SELECT avatar FROM REGISTER WHERE userId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            avatarUri = cursor.getString(cursor.getColumnIndex("avatar"));
        }

        cursor.close();
        db.close();

        return avatarUri;
    }

    public boolean updateAvatarUri(int userId, String avatarUri) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("avatar", avatarUri);
        int numRowsUpdated = db.update("REGISTER", values, "userId = ?", new String[]{String.valueOf(userId)});
        db.close();
        return numRowsUpdated > 0;
    }

//    public ArrayList<Register> getAll() {
//        ArrayList<Register> list = new ArrayList<>();
//        SQLiteDatabase database = helper.getReadableDatabase();
//        String sql = "SELECT * FROM REGISTER";
//        Cursor cs = database.rawQuery(sql, null);
//        cs.moveToFirst();
//        while (!cs.isAfterLast()) {
//            int id = cs.getInt(0);
//            String username = cs.getString(1);
//            String password = cs.getString(2);
//            Register reg = new Register(id ,username, password);
//            list.add(reg);
//            cs.moveToNext();
//        }
//        cs.close();
//        database.close();
//        return list;
//    }
    @SuppressLint("Range")
    public String getUsernameByUserId(int userId) {
        String username = null;
        SQLiteDatabase db = helper.getReadableDatabase();

        // Specify the columns you want to retrieve (in this case, only the "Username" column)
        String[] projection = {"Username"};

        // Specify the selection criteria
        String selection = "userId = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        // Perform the query
        Cursor cursor = db.query("REGISTER", projection, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // Retrieve the username from the cursor
            username = cursor.getString(cursor.getColumnIndex("Username"));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return username;
    }


    @SuppressLint("Range")
    public int getUserIdByUserName(String userName) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int userId = -1; // Giá trị mặc định nếu không tìm thấy user name

        String query = "SELECT userId FROM REGISTER WHERE Username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userName});

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex("userId"));
        }

        cursor.close();
        db.close();

        return userId;
    }

    public boolean checkUserName(String username) {
        SQLiteDatabase MyDB = helper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from REGISTER where Username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkUserNamePassword(String username, String password){
        SQLiteDatabase MyDB = helper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from REGISTER where Username = ? and Password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean insert(Register register){
        SQLiteDatabase MyDB = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", register.getUsername());
        contentValues.put("Password", register.getPassword());
        contentValues.put("role", register.getRole());
        long row = MyDB.insert("REGISTER", null, contentValues);
        MyDB.close(); // phai dong ket noi
        return row > 0; // neu row > 0 thi tra true , con k thi false
    };
}
