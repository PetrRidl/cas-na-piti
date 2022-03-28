package com.example.drinkingtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB4  extends SQLiteOpenHelper {
    public DB4( Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table Pohlavi(pohlavi TEXT, id INT primary key)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("drop Table if exists Pohlavi");
    }
    public Boolean saveGender(String hodnota)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pohlavi", hodnota);

        contentValues.put("id", 1);
        long result=DB.insert("Pohlavi", null, contentValues);
        if(result==-1)
        {
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor VypisPohlavi()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from Pohlavi where id = 1", null);


        return cursor;
    }
    public Boolean deleteData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Pohlavi", null);
        if (cursor.getCount() > 0){

            long result = DB.delete("Pohlavi", "id=1", null);
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
        saveGender(hodnota);
        return true;
    }
}
