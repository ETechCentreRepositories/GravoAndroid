package com.greenravolution.gravo.objects;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NotificationRepository {
    private NotificationDao notificationDao;
    private LiveData<List<Notification>> allNotifications;

    NotificationRepository(Application application){
        NotificationRoomDatabase db = NotificationRoomDatabase.getDatabase(application);
        notificationDao = db.notificationDao();
        allNotifications = notificationDao.getAllNotifications();
    }

    LiveData<List<Notification>> getAllNotifications(){
        return allNotifications;
    }

    public void insert(Notification notification){
        new insertAsyncTask(notificationDao).execute(notification);
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
}
