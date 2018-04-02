package com.example.android.antiragging;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chait on 7/5/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


     private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "emergencycontacts";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "CONTACTS";
    private static final String COL1 = "name";
    private static final String COL2 = "number";

    public  DatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String createTable = "CREATE TABLE " + TABLE_NAME +" ( "+ COL2 + " varchar  PRIMARY KEY, " + COL1 +" varchar )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
              onCreate(db);
    }

    public boolean addData(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, contact.getName());
        contentValues.put(COL2, contact.getNumber());

        Log.d(TAG, "addData: Adding" + contact.getName() +"-" + contact.getNumber() + "to" + TABLE_NAME);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if (result > 0) {
            return true;
        }else {
            return false;
        }
        }

        public List<Contact> getData(){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor data = db.rawQuery(query,null);
            List<Contact> contacts = new ArrayList<>();
            if(data!=null){
                if(data.getCount()<=0){
                    data.close();
                    return null;
                }else{
                    data.moveToFirst();
                    do{
                        Contact contact = new Contact();
                        contact.setName(data.getString(data.getColumnIndex(COL1)));
                        contact.setNumber(data.getString(data.getColumnIndex(COL2)));
                        contacts.add(contact);
                    }while(data.moveToNext());
                }
            }
            return contacts;
        }

        public Contact getItemId(String name){
        SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT " + "* FROM " + TABLE_NAME + " WHERE " + COL1 +" = '" +name+ "'";
            Cursor data = db.rawQuery(query,null);
            Contact contact = new Contact();

            if(data!=null) {
                if (data.getCount() <= 0) {
                    data.close();
                    return null;
                } else {
                    data.moveToFirst();
                                        contact.setName(data.getString(data.getColumnIndex(COL1)));
                    contact.setNumber(data.getString(data.getColumnIndex(COL2)));

                }
            }
            return contact;

        }

        public void updatenamenumber(String newName, String newNumber, String oldName, String oldNumber){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "UPDATE "+ TABLE_NAME+ " SET " + COL1+ " = " + newName+ COL2+ " = "+ newNumber+ " WHERE " + COL1+ " = ' "+ oldName+" ' " + " OR "+COL2+ " = ' "+ oldNumber+" ' ";
            ContentValues values = new ContentValues();
            values.put(COL1,newName);
            values.put(COL2,newNumber);
            Log.d(TAG, "updateNameAndNumber: query" + query);
            Log.d(TAG,"updateNameAndNumber: setting name and no to" + newName + newNumber);
            db.execSQL(query);

        }

        public void deletenamenumber(String number, String name){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + number+ "'" + " AND " + COL1 + " = '"+ name+"'";
            Log.d(TAG, "deletingname: query" +query);
            Log.d(TAG, " deletingname: deleting" + name + number +"from database");
            db.execSQL(query);

        }
    }

