package com.batchmates.android.sqllightproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Android on 6/26/2017.
 */

public class SQLlightHandler extends SQLiteOpenHelper{

    private String name;
    private static final String DATABASE_NAME="databasenow";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "the_table";
    private static final String TAG = "The Database";
    private static final String CONTACT_NAME = "name";
    private static final String CONTACT_PHONE = "phone";
    private static final String BITHDAY = "birthday";
    private static final String NUM = "ID";

    private static final String IMAGE = "IMAGE";



    public SQLlightHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "onCreate: Creating Table");
        String CREATE_TABLE= "CREATE TABLE " + TABLE_NAME +"(" +
                CONTACT_NAME +" TEXT,"+
                CONTACT_PHONE+" TEXT,"+
                BITHDAY + " TEXT," +
                IMAGE + " BLOB," +
                NUM + " INTEGER PRIMARY KEY"+")";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);

        onCreate(db);

    }

    public void saveContact(Person person)
    {
        SQLiteDatabase database=getWritableDatabase();

        ContentValues contentValues= new ContentValues();

        contentValues.put(CONTACT_NAME,person.getName());
        contentValues.put(CONTACT_PHONE,person.getPhone());
        contentValues.put(BITHDAY,person.getBirthday());
        contentValues.put(IMAGE,person.getPicture());


        database.insert(TABLE_NAME,null,contentValues);
    }

    public void deleteContact(Person person)
    {
        //String query= "Delete" + "From" +TABLE_NAME;

        SQLiteDatabase database = this.getWritableDatabase();


        //removing contact by name
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + CONTACT_NAME+ "= '" + person.getName() + "'");


        database.close();

    }

    public void updateContact(Person person)
    {

        String query =CONTACT_NAME+ "= '" +person.getName()+"'";
        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        //contentValues.put(CONTACT_NAME,person.getName());
        contentValues.put(CONTACT_PHONE,person.getPhone());
        contentValues.put(BITHDAY,person.getBirthday());


        database.update(TABLE_NAME,contentValues,query,null);
        database.close();
    }




    public void displayContacts()
    {
        String query = "Select * From " + TABLE_NAME;

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst())
        {
            do {
                Log.d(TAG, "printContacts: Name: " + cursor.getString(0) + " Phone: " + cursor.getString(1) + " Birthday: " + cursor.getString(2) + " PIC: " + cursor.getBlob(3)+ " ID: " + cursor.getString(4));

            } while (cursor.moveToNext());
        }
    }


//    public void saveImage(byte[] thing)
//    {
//        String query = "Select * From " + TABLE_NAME;
//        SQLiteDatabase database = this.getWritableDatabase();
//        //Cursor cursor = database.rawQuery(query, null);
//        ContentValues contentValues=new ContentValues();
//
//        contentValues.put(IMAGE,thing);
//
//        Log.d(TAG, "saveImage: "+thing);
//        database.insert(TABLE_NAME,null,contentValues);
//
//        //Log.d(TAG, "saveImage: "+ cursor.getBlob(3));
//    }

}
