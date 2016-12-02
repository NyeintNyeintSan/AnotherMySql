package com.nyeintnyeint.mysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nyeintnyeint on 12/1/16.
 */
public class MyDBHandler extends SQLiteOpenHelper {
    public static final int DB_version=1;
    public static final String Database_name="product.db";
    public static final String Table_name="product";
    public static final String Column_id="_id";
    public static final String Column_name="_productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Database_name, factory, DB_version);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
       String query="CREATE TABLE "+Table_name+"("+
               Column_id+" INTEGER PRIMARY KEY AUTOINCREMENT "+
                     Column_name+" TEXT"+");";
        database.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(database);
    }

    public void addProduct(String product)
    {
        ContentValues values=new ContentValues();
        //values.put(Column_name,product.get_productname());
        values.put(Column_name,product);
        SQLiteDatabase db=getWritableDatabase();
        db.insert(Table_name,null,values);
        db.close();
    }

    public void deleteProduct(String product)
    {
        SQLiteDatabase db=getWritableDatabase();
        //db.execSQL("DELETE FROM "+Table_name+" WHERE "+Column_name+"=\""+product.get_productname()+"\";");
        db.execSQL("DELETE FROM "+Table_name+" WHERE "+Column_name+"=\""+product+"\";");
    }

    public String dbtoString(){
        String s=" ";
        SQLiteDatabase db1=getWritableDatabase();
        String query="SELECT * FROM "+Table_name+" WHERE 1";

        Cursor c=db1.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("productname"))!=null){
                s+=c.getString(c.getColumnIndex("productname"));
                s+="\n";
            }
        }
        db1.close();
        return s;
    }
}
