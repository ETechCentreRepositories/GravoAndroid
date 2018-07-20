package com.greenravolution.gravo.objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "notification_table")
public class Notification {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int messageId;
    private String userId;
    private String message;
    private Long time;

    public Notification(@NonNull String userId, @NonNull String message, @NonNull Long time) {this.userId = userId; this.message = message; this.time = time;}

    public String getUserId(){return this.userId;}

    public void setUserId(String userId){this.userId = userId;}

    public String getMessage(){return this.message;}

    public Long getTime(){return this.time;}

    public void setTime(Long time){this.time = time;}

    public int getMessageId(){ return this.messageId;}

    public void setMessageId(int id){
        this.messageId = id;
    }

}
