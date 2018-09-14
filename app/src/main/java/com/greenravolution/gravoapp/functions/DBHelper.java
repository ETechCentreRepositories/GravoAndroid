package com.greenravolution.gravoapp.functions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.greenravolution.gravoapp.objects.Notification;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSON = 1;
    Notification notification = new Notification();
    private static final String DATABASE_NAME = "gravo_db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(notification.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+notification.TABLE_NAME());
        onCreate(db);
    }
    public long AddNotification(String message,String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(notification.COLUMN_MESSAGE(), message);
        values.put(notification.COLUMN_TITLE(), title);
        long id = db.insert(notification.TABLE_NAME(), null, values);
        db.close();
        Log.e("ADDED NOTIFICATIONS","'");
        return id;
    }
    public Notification getNotification(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(notification.TABLE_NAME(),
                new String[]{notification.COLUMN_ID(),notification.COLUMN_USER_ID(), notification.COLUMN_TITLE(), notification.COLUMN_MESSAGE(), notification.COLUMN_TIME()},
                notification.COLUMN_ID() + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Notification note = new Notification(
                cursor.getInt(cursor.getColumnIndex(notification.COLUMN_ID()))
                ,cursor.getString(cursor.getColumnIndex(notification.COLUMN_USER_ID()))
                ,cursor.getString(cursor.getColumnIndex(notification.COLUMN_TITLE()))
                ,cursor.getString(cursor.getColumnIndex(notification.COLUMN_MESSAGE()))
                ,cursor.getLong(cursor.getColumnIndex(notification.COLUMN_TIME())));


        // close the db connection
        cursor.close();

        return note;
    }

    public ArrayList<Notification> getAllNotifications() {
        ArrayList<Notification> notif= new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + notification.TABLE_NAME() + " ORDER BY " +
                notification.COLUMN_TIME() + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Notification note = new Notification(
                        cursor.getInt(cursor.getColumnIndex(notification.COLUMN_ID()))
                        ,cursor.getString(cursor.getColumnIndex(notification.COLUMN_USER_ID()))
                        ,cursor.getString(cursor.getColumnIndex(notification.COLUMN_TITLE()))
                        ,cursor.getString(cursor.getColumnIndex(notification.COLUMN_MESSAGE()))
                        ,cursor.getLong(cursor.getColumnIndex(notification.COLUMN_TIME())));

                notif.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notif;
    }

    public int getNotificationCount() {
        String countQuery = "SELECT  * FROM " + notification.TABLE_NAME();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }
    public void deleteNotification(Notification notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(notification.TABLE_NAME(), notification.COLUMN_ID() + " = ?",
                new String[]{String.valueOf(notification.getId())});
        db.close();
    }

}
