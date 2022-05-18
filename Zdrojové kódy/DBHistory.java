package com.example.drinkingtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DBHistory  extends SQLiteOpenHelper {
    public DBHistory( Context context) {
        super(context, "HistoriePrace.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Historie(vypito TEXT, id INT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Historie");

    }

    public Boolean insertData(String stat, int id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vypito", stat);
        contentValues.put("id", id);
        long result=DB.insert("Historie", null, contentValues);
        if(result==-1)
        {
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor vypisHistorie()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from Historie", null);


        return cursor;
    }

    public Cursor vypisID()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select id from Historie ORDER BY id DESC LIMIT 1", null);
        //Cursor cursor = sqLiteDatabase.rawQuery("select id from Historie", null);


        return cursor;
    }

}
