package com.example.ridzi.helpteens1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;







/**
 * Created by hp pc on 7/11/2016.
 */
public class DBPost extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BookBox2.db";
    private static final String TABLE_NAME = "Dbpost";
    private static final String BOOK_NAME = "bookname";
    private static final String PUBLICATION = "publication";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final int DATABASE_VERSION = 1;

    //create query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
              BOOK_NAME + " TEXT , " + PUBLICATION + " TEXT ," + DESCRIPTION + " TEXT ," + PRICE +
            " TEXT);";

    public DBPost(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public DBPost(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //method to add details
    public long submitdetail(BookBoxModel2 book) {
        SQLiteDatabase db = this.getWritableDatabase();
        //creating content values
        ContentValues values = new ContentValues();
        values.put(BOOK_NAME, book.bookname);
        values.put(DESCRIPTION, book.description);
        values.put(PUBLICATION, book.publication);
        values.put(PRICE, book.price);

        //insert row in table
        return db.insert(TABLE_NAME, null, values);
    }

    public List<BookBoxModel2> getAllStudentsList() {
        List<BookBoxModel2> studentArrayList = new ArrayList<BookBoxModel2>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                BookBoxModel2 book = new BookBoxModel2();
                book.bookname = c.getString(c.getColumnIndex(BOOK_NAME));
                book.publication = c.getString(c.getColumnIndex(PUBLICATION));
                book.description = c.getString(c.getColumnIndex(DESCRIPTION));
                book.price = c.getString(c.getColumnIndex(PRICE));

                studentArrayList.add(book);
            } while (c.moveToNext());
        }
        Log.e("list", studentArrayList.toString());
        return studentArrayList;
    }



  /*  String getEmail(String username) {
        String email = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "email=?", new String[]{username}, null, null, null, null);
            email = cursor.getString(cursor.getColumnIndex(EMAIL));
            cursor.close();

        return email;
    }
*/}



