package com.example.asus.lrucachepracticewithdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 13/12/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Ahdz";
    public static final String TABLE_NAME = "Todo";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        // SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (TITLE TEXT PRIMARY KEY, " +
                                                "DESCRIPTION TEXT);");
        db.delete("Todo", null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertData(String title, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("description",desc);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+TABLE_NAME,null);
        return res;
    }
}
