package com.greenravolution.gravo.objects;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.util.List;

public class NotificationRepository {
    private NotificationDao notificationDao;
    private LiveData<List<Notification>> allNotifications;

    NotificationRepository(Application application){
        SharedPreferences preferences = application.getApplicationContext().getSharedPreferences("login_status", Context.MODE_PRIVATE);
        String user_id = String.valueOf(preferences.getInt("user_id", 0));
        NotificationRoomDatabase db = NotificationRoomDatabase.getDatabase(application);
        notificationDao = db.notificationDao();
        allNotifications = notificationDao.getAllNotifications(user_id);
    }

    public LiveData<List<Notification>> getAllNotifications(){
        return allNotifications;
    }

    public void insert(Notification notification){
        new insertAsyncTask(notificationDao).execute(notification);
    }

    public void deleteNotification(Notification notification)  {
        new deleteNotificationAsyncTask(notificationDao).execute(notification);
    }

    private static class insertAsyncTask extends AsyncTask<Notification, Void, Void> {
        private NotificationDao mAsyncTaskDao;

        insertAsyncTask(NotificationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Notification... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteNotificationAsyncTask extends AsyncTask<Notification, Void, Void> {
        private NotificationDao mAsyncTaskDao;

        deleteNotificationAsyncTask(NotificationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Notification... params) {
            mAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }
}
