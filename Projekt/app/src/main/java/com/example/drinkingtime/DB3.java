package com.example.drinkingtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB3 extends SQLiteOpenHelper {
    public DB3( Context context) {
        super(context, "KVypiti.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table KolikVypit(hodnota TEXT, id INT primary key)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("drop Table if exists KolikVypit");
    }
    public Boolean toDrink(String hodnota)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hodnota", hodnota);

        contentValues.put("id", 1);
        long result=DB.insert("KolikVypit", null, contentValues);
        if(result==-1)
        {
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor VypisPiti()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from KolikVypit", null);


        return cursor;
    }
    public Boolean deleteData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from KolikVypit", null);
        if (cursor.getCount() > 0){

            long result = DB.delete("KolikVypit", "id=1", null);
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
    public boolean update(String hodnota){
        deleteData();
        toDrink(hodnota);
        return true;
    }
}
