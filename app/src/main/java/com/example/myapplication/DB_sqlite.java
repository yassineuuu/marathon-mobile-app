package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class DB_sqlite extends SQLiteOpenHelper {

    public static final String DB_Name = "data.db";

    public DB_sqlite(Context context) {
        super(context, DB_Name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE `runners` (id integer primary key AUTOINCREMENT, name text, email varchar(255) NOT NULL, phone integer, sexe text, birthday date )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists runners");
        onCreate(db);

    }

    public boolean insertData(String name, String email, int phone, String sexe, String birthday){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("sexe", sexe);
        contentValues.put("birthday", birthday);

        long result = db.insert("runners", null, contentValues);

        if (result == -1){
            return false;}
        else{
            return true;}
    }

    public ArrayList allRecords(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM runners", null);

        res.moveToFirst();

        while (res.isAfterLast() == false){

            String id = res.getString(0);
            String name = res.getString(1);
            String email = res.getString(2);
            String phone = res.getString(3);

            arrayList.add(id+": "+name + "\n  " + email+"\n   "+ phone +"\n   ");

            res.moveToNext();
        }

        return arrayList;
    }

    public boolean updateRunner(String id , String name, String email, int phone, String sexe, String birthday){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("sexe", sexe);
        contentValues.put("birthday", birthday);

        db.update("runners", contentValues, "id = ?", new String[]{id});

        return true;

    }



    public Integer deleteRunner(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("runners", "id = ?", new String[]{id});

    }
}
