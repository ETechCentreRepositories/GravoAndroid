package com.greenravolution.gravoapp.objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "notification_table")
public class Notification {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private int messageId;
    private String userId;
    private String message;
    private String title;
    private String time;

    @NonNull
    public int getId() {
        return id;
    }

    public String TABLE_NAME() {
        return "notifications";
    }

    public String COLUMN_ID() {
        return "id";
    }
    public String COLUMN_USER_ID() {
        return "recycler_id";
    }

    public String COLUMN_MESSAGE() {
        return "message";
    }
    public String COLUMN_TITLE() {
        return "title";
    }

    public String COLUMN_TIME() {
        return "time";
    }

    public Notification(){}
    public Notification(int id, String userId,String title, String message,String time) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId(){return this.userId;}

    public void setUserId(String userId){this.userId = userId;}

    public String getMessage(){return this.message;}

    public String getTime(){return this.time;}

    public void setTime(String time){this.time = time;}

    public int getMessageId(){ return this.messageId;}

    public void setMessageId(int id){
        this.messageId = id;
    }

    // Create table SQL query
    public final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME()+ "("
                    + COLUMN_ID() + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USER_ID() + " INTEGER,"
                    + COLUMN_TITLE() + " TEXT,"
                    + COLUMN_MESSAGE() + " TEXT,"
                    + COLUMN_TIME() + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ");";

}
