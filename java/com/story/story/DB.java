package com.story.story;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    public static final String DBNAME="databaseList.db";
    public static final String DBLOCATION = Environment.getDataDirectory ()+"/data/com.story.story/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public DB(Context context) {
        super (context, DBNAME, null, 1);
        this.mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDatabase() {
        String dbPath = mContext.getDatabasePath (DBNAME).getPath ();
        if (mDatabase != null && mDatabase.isOpen ()) {

            return;

        }
        mDatabase = SQLiteDatabase.openDatabase (dbPath, null, SQLiteDatabase.OPEN_READWRITE);


    }
    public  void  closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close ();
        }
    }

    public ArrayList get_All_Titles() {
        ArrayList arrayList = new ArrayList ();
        openDatabase ();
        Cursor res = mDatabase.rawQuery ("select * from tablo", null);
        res.moveToFirst ();
        while (res.isAfterLast () == false) {
            arrayList.add (res.getString (res.getColumnIndex ("title")));
            res.moveToNext ();


        }
        res.close ();
        closeDatabase ();
        return arrayList;
    }
    public  String get_full_story(String title){
        String full_story;
        openDatabase ();
        Cursor res =mDatabase.rawQuery ("select * from tablo where title like'" + title+"'",null);
        res.moveToFirst ();
        full_story =res.getString (res.getColumnIndex ("story"));
        res.close ();
        return full_story;

    }
}
