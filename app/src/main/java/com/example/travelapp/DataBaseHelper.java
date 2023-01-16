package com.example.travelapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USER(EMAIL TEXT PRIMARY KEY,FIRSTNAME TEXT," +
                " LASTNAME TEXT,PASSWORD TEXT,DESTINATION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertCustomer(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FIRSTNAME", user.getFirstName());
        contentValues.put("LASTNAME", user.getLastName());
        contentValues.put("PASSWORD", user.getPassword());
        contentValues.put("DESTINATION", user.getDestination());
        sqLiteDatabase.insert("USER", null, contentValues);
    }
    public Cursor getAllUsers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USER", null);
    }
}
