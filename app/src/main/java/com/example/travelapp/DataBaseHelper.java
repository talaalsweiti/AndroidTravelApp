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
//        context.deleteDatabase("TRAVEL_APP")
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USERS(EMAIL TEXT PRIMARY KEY,FIRSTNAME TEXT," +
                " LASTNAME TEXT,PASSWORD TEXT,DESTINATION TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE DESTINATIONS(CITY TEXT PRIMARY KEY,COUNTRY TEXT," +
                " CONTINENT TEXT,LONGITUDE REAL,LATITUDE REAL ,COST REAL,IMG TEXT,DESCRIPTION TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE FAV_DESTINATIONS(CITY TEXT PRIMARY KEY," +
                " COUNTRY TEXT,EMAIL TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FIRSTNAME", user.getFirstName());
        contentValues.put("LASTNAME", user.getLastName());
        contentValues.put("PASSWORD", user.getPassword());
        contentValues.put("DESTINATION", user.getDestination());
        sqLiteDatabase.insert("USERS", null, contentValues);
    }

    public Cursor getAllUsers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS", null);
    }

    public Cursor searchUser(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE EMAIL=?", new String[]{email}, null);
    }

    public void updateInformation(ContentValues contentValues, String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update("USERS",contentValues,"EMAIL=?",new String[]{email});

    }

    public void insertDestination(Destination destination) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CITY", destination.getCity());
        contentValues.put("COUNTRY", destination.getCountry());
        contentValues.put("CONTINENT", destination.getContinent());
        contentValues.put("LONGITUDE", destination.getLongitude());
        contentValues.put("LATITUDE", destination.getLatitude());
        contentValues.put("COST", destination.getCost());
        contentValues.put("IMG", destination.getImg());
        contentValues.put("DESCRIPTION", destination.getDescription());
        sqLiteDatabase.insertWithOnConflict("DESTINATIONS", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public Cursor getRandomDestination(String continent) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM DESTINATIONS WHERE CONTINENT=?", new String[]{continent}, null);
    }

    public Cursor selectDestinations() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT CONTINENT,COUNTRY,CITY FROM DESTINATIONS ", null);
    }
    public Cursor searchFavDestinations(String city) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT COUNT (*) FROM FAV_DESTINATIONS WHERE CITY=? ", new String[]{city}, null);

    }


    public Cursor numberOfDestinations(String continent) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM DESTINATIONS WHERE CONTINENT=?", new String[]{continent}, null);
    }

    public Cursor sortDestinations(String sortMethod) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        if (sortMethod.equals("asc"))
            return sqLiteDatabase.rawQuery("SELECT CITY,COST FROM DESTINATIONS ORDER BY COST ASC", null);
        else
            return sqLiteDatabase.rawQuery("SELECT CITY,COST FROM DESTINATIONS ORDER BY COST DESC", null);
    }

    public Cursor selectOneDestination(String city) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM DESTINATIONS WHERE CITY=?", new String[]{city}, null);
    }

    public void insertFavorite(String email, String city, String country) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CITY", city);
        contentValues.put("COUNTRY", country);
        contentValues.put("EMAIL", email);
        sqLiteDatabase.insertWithOnConflict("FAV_DESTINATIONS", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public Cursor getFavorites(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM FAV_DESTINATIONS WHERE EMAIL=? " , new String[]{email}, null);
    }
    public boolean deleteFavorite(String city) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
       return sqLiteDatabase.delete("FAV_DESTINATIONS","city=? ",new String[]{city}) >0;
    }
}
