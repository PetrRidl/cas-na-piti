package com.example.drinkingtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Uzivatel(vaha TEXT/*,pohlavi TEXT*/, id INT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Uzivatel");

    }
    public Boolean insertUser(String vaha/*, String pohlavi*/)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vaha", vaha);
        //contentValues.put("pohlavi", pohlavi);
        contentValues.put("id", 1);
        long result=DB.insert("Uzivatel", null, contentValues);
        if(result==-1)
        {
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updateUser(String vaha/*, String pohlavi*/)
    {
        deleteData();
        insertUser(vaha/*, pohlavi*/);
        return true;

    }
    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Uzivatel", null);
        return cursor;
    }
    public Boolean deleteData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Uzivatel", null);
        if (cursor.getCount() > 0){

            long result = DB.delete("Uzivatel", "id=1", null);
            if (result == -1)
            {
                return false;
            }
            else{
                return true;
            }
        }
        else
        {
            return false;
        }
    }




}

