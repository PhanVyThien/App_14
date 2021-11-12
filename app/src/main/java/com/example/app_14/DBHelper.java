package com.example.app_14;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    private static String DBname="Thien.db";
    public DBHelper(Context context) {
        super(context, DBname,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table contacts (id interger primary key, name text, phone text, email text, street, place text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists contacts");
        onCreate(sqLiteDatabase);
    }
    public boolean insertConatct(String name,String phone,String email,String street,String place){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        long id=getAllContacts().size();
        cv.put("id",id);
        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("email",email);
        cv.put("street",street);
        cv.put("place",place);

        db.insert("contacts",null,cv);
        return true;
    }
    public Cursor getData(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from contacts where id="+id,null);
        return res;
    }
    public int rowCount(){
        SQLiteDatabase db=this.getReadableDatabase();
        int re=(int) DatabaseUtils.queryNumEntries(db,"name");
        return re;
    }
    public boolean updateContact(Integer id,String name,String phone,String email,String street,String place){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("email",email);
        cv.put("street",street);
        cv.put("place",place);
        int contacts;
        db.update("contacts",cv, "id=?", new String[]{Integer.toString(id)});
        return true;
    }
    public Integer deleteContact(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("contacts","id=?",new String[]{Integer.toString(id)} );
    }
    @SuppressLint("Range")
    public ArrayList getAllContacts(){
        ArrayList al=new ArrayList();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor re=db.rawQuery("select * from contacts",null);
        re.moveToFirst();
        while(!re.isAfterLast()){
            al.add(re.getString(re.getColumnIndex("name")));
            re.moveToNext();
        }
        return al;
    }

}
