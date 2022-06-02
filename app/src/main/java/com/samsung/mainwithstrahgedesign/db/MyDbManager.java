package com.samsung.mainwithstrahgedesign.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.SaveToPrefMap;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private Context context;
    private static MyDbHelper myDbHelper;
    private static SQLiteDatabase db;
    private SharedPreferences prfs;
    private SharedPreferences pref;
    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper= new MyDbHelper(context);
        prfs = context.getSharedPreferences(BtConsts.COUNT, Context.MODE_PRIVATE);

    }
    public void removeAll()
    {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        db.delete(MyConstants.TABLE_NAME, null, null);
}
    public void openDb(){
        db= myDbHelper.getWritableDatabase();
    }
    public Boolean insertToDb(String tittle){
        SharedPreferences.Editor edit= prfs.edit();
        edit.putInt(MyConstants.COUNT_NUMBER,(prfs.getInt(MyConstants.COUNT_NUMBER,71))+1);
        edit.apply();
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyConstants.TITLE, tittle);
        long newRowId = db.insert(MyConstants.TABLE_NAME, null, values);
        if(newRowId==-1){
            return false;
        }else{
            return true;
        }
    }
    public static String getFromDb(int id){
        String query = "SELECT " +MyConstants.TITLE+ " FROM " + MyConstants.TABLE_NAME+ " WHERE "
                + MyConstants._ID+ " = " + id;
        db= myDbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        String title_name = "";

        if(c.getCount()>0){
            c.moveToFirst();
            do{
                title_name = c.getString(c.getColumnIndexOrThrow(MyConstants.TITLE));
            } while (c.moveToNext());
                c.close();
        }
        return title_name;
        }
        public  void closeDb(){
        myDbHelper.close();
        }

    }

