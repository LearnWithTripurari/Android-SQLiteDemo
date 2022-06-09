package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "School.db";
    private static final int DB_VERSION = 1;

    public SQLiteDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE Users (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name Text, Username Text, Password Text, Mobile Text)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(String name, String username, String password, String mobile) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", name);
        contentValues.put("Username", username);
        contentValues.put("Password", password);
        contentValues.put("Mobile", mobile);

        long result = database.insert("Users", null, contentValues);

        if (result == -1) return false;

        return true;
    }

    public Cursor read() {
        SQLiteDatabase database = getWritableDatabase();

        return database.rawQuery("SELECT * FROM Users", null);
    }

    public boolean update(String name, String username, String password, String mobile) {

        SQLiteDatabase database = getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Password", password);
        contentValues.put("Mobile", mobile);

       Cursor cursor = database.rawQuery("SELECT * FROM Users WHERE Username=?",new String[]{username});

       if (cursor.getCount() > 0) {

           int result = database.update("Users", contentValues,"Username=?",new String[] {username});

           if (result == -1) return false;
       }
       else {
           return false;
       }

       return true;
    }

    public boolean delete(String username) {
        SQLiteDatabase database = getReadableDatabase();

      Cursor cursor  = database.rawQuery("SELECT * FROM Users WHERE Username=?",new String[]{username});

        if (cursor.getCount() > 0) {

            int result = database.delete("Users","Username=?",new String[] {username});

            if (result == -1) return false;
        }
        else {
            return false;
        }

        return true;
    }
}
