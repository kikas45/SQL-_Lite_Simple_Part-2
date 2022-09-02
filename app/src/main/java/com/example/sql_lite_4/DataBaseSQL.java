package com.example.sql_lite_4;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class DataBaseSQL extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "OfflineLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_Offlinelibrary";

    private static final String COLUMN_ID = "_key_id";

    private static final String COLUMN_IMAGE_URL = "image_URL";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESC = "descrip";
    private static final String COLUMN_ICON = "icon";
    private static final String COLUMN_NAME = "name";

    DataBaseSQL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IMAGE_URL + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESC +" TEXT, " +
                COLUMN_ICON + " TEXT, " +
                COLUMN_NAME + " TEXT );";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String image_URL, String title, String description, String icon, String name ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_IMAGE_URL, image_URL);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESC, description);
        cv.put(COLUMN_ICON, icon);
        cv.put(COLUMN_NAME, name);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }



    @SuppressLint("Range")
    public ArrayList<BookModel> getAllBooks() {
        ArrayList<BookModel> bookModelArrayList = new ArrayList<BookModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                BookModel bookModel = new BookModel();
                bookModel.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
                bookModel.setImage(c.getString(c.getColumnIndex(COLUMN_IMAGE_URL)));
                bookModel.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                bookModel.setDesc(c.getString(c.getColumnIndex(COLUMN_DESC)));
                bookModel.setName(c.getString(c.getColumnIndex(COLUMN_ICON)));
                bookModel.setIcon(c.getString(c.getColumnIndex(COLUMN_NAME)));
                // adding to list
                bookModelArrayList.add(bookModel);
            } while (c.moveToNext());
        }
        return bookModelArrayList;
    }




    void updateData(String row_id, String image_URL, String title, String description, String icon, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IMAGE_URL, image_URL);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESC, description);
        cv.put(COLUMN_ICON, icon);
        cv.put(COLUMN_NAME, name);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }


    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


    public  void  deleteEntry(long row){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, COLUMN_ID + " = " + row, null);
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }

    }


}
