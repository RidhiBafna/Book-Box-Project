package com.example.ridzi.helpteens1;

import java.util.List;

/**
 * Created by Ridzi on 14-07-2016.
 */


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.DatabaseErrorHandler;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by hp pc on 7/11/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BookBox.db";
    private static final String TABLE_NAME = "LoginDetail";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String GENDER = "gender";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final int DATABASE_VERSION = 1;

    //create query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT , " + GENDER + " TEXT ," + PHONE + " TEXT ," + EMAIL +
            " TEXT ," + ADDRESS + " TEXT ," + CITY + " TEXT , " + PASSWORD + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    //method to add details
    public long addDetails(BookBoxModel book) {
        SQLiteDatabase db = this.getWritableDatabase();
        //creating content values
        ContentValues values = new ContentValues();
        values.put(NAME, book.name);
        values.put(PHONE, book.phone);
        values.put(CITY, book.city);
        values.put(PASSWORD, book.password);
        values.put(ADDRESS, book.address);
        values.put(EMAIL, book.email);
        values.put(GENDER, book.gender);
        //insert row in table
        return db.insert(TABLE_NAME, null, values);

    }

    public List<BookBoxModel> getAllStudentsList() {
        List<BookBoxModel> studentArrayList = new ArrayList<BookBoxModel>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                BookBoxModel book = new BookBoxModel();
                book.id = c.getInt(c.getColumnIndex(ID));
                book.name = c.getString(c.getColumnIndex(NAME));
                book.phone = c.getString(c.getColumnIndex(PHONE));
                book.city = c.getString(c.getColumnIndex(CITY));
                book.password = c.getString(c.getColumnIndex(PASSWORD));
                book.address = c.getString(c.getColumnIndex(ADDRESS));
                book.email = c.getString(c.getColumnIndex(EMAIL));
                book.gender = c.getString(c.getColumnIndex(GENDER));
                studentArrayList.add(book);
            } while (c.moveToNext());
        }
        return studentArrayList;
    }

    public void deleteEntry(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, PHONE + " =? ", new String[]{String.valueOf(name)});
    }

    String getRegister(String username) {
        String password = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "email=?", new String[]{username}, null, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "Not Exist";
        } else if (cursor.getCount() >= 1 && cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            cursor.close();
        }
        return password;
    }

  /*  String getEmail(String username) {
        String email = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "email=?", new String[]{username}, null, null, null, null);
            email = cursor.getString(cursor.getColumnIndex(EMAIL));
            cursor.close();

        return email;
    }
*/


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

