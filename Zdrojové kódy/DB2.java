package com.example.drinkingtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.util.Log;

public class DB2 extends SQLiteOpenHelper {
    public DB2( Context context) {
        super(context, "Userdata1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table KolikVypito(hodnota TEXT, KolikVypit TEXT, id INT primary key)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("drop Table if exists KolikVypito");
    }
    public Boolean drinked(String hodnota)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hodnota", hodnota);

        contentValues.put("id", 1);
        long result=DB.insert("KolikVypito", null, contentValues);
        if(result==-1)
        {
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor VypisDat()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from KolikVypito", null);


        return cursor;
    }
    public boolean update(String hodnota){
        deleteData();
        drinked(hodnota);
        return true;
    }
    public Boolean deleteData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from KolikVypito", null);
        if (cursor.getCount() > 0){

            long result = DB.delete("KolikVypito", "id=1", null);
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