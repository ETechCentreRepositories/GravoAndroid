package com.greenravolution.gravo.functions;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.MainFragments.Calendar;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivitySelectedTransaction;
import com.greenravolution.gravo.objects.NotificationDao;
import com.greenravolution.gravo.objects.NotificationRoomDatabase;

public class PickUpDayReminder extends BroadcastReceiver{
    public void onReceive(Context context, Intent i){
        Log.i("OnReceive","I'm almost in");
        class addNotificationThread implements Runnable {
            public void run() {
                addNotification(context);
            }
        }
        Thread t = new Thread(new addNotificationThread());
        t.start();
        showNotification(context);
    }

    private void showNotification(Context context){
        Log.i("OnReceive","I'm in");
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0,
                intent, 0);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Gravo");
        builder.setContentText("Pick Up Day");
        builder.setSmallIcon(R.drawable.gravo_logo_black_full);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);
        Notification n = builder.build();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, n);
    }

    public void addNotification(Context context){
        SharedPreferences preferences = context.getSharedPreferences("login_status", Context.MODE_PRIVATE);
        String user_id = String.valueOf(preferences.getInt("user_id", 0));
        Log.i("Notificaion",user_id);

        java.util.Calendar c = java.util.Calendar.getInstance();
        long time = c.getTimeInMillis();
        NotificationRoomDatabase db = Room.databaseBuilder(context,
                NotificationRoomDatabase.class, "notification_database").build();
        NotificationDao dao = db.notificationDao();
        com.greenravolution.gravo.objects.Notification noti = new com.greenravolution.gravo.objects.Notification(user_id,"Pick Up Day",time);
        Log.i("Notificaion",noti.getUserId() + " " + noti.getMessage());
        //com.greenravolution.gravo.objects.Notification noti = new com.greenravolution.gravo.objects.Notification("Pick Up Day");
        dao.insert(noti);
    }
}
