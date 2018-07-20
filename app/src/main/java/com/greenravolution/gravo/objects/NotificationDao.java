package com.greenravolution.gravo.objects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NotificationDao {

    @Insert
    void insert(Notification notification);

    @Query("DELETE FROM notification_table")
    void deleteAll();

    @Query("SELECT * FROM notification_table")
    LiveData<List<Notification>> getAllNotifications();

}
