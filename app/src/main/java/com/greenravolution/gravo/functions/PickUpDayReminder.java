package com.greenravolution.gravo.functions;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivitySelectedTransaction;

public class PickUpDayReminder extends BroadcastReceiver{
    public void onReceive(Context context, Intent i){
        Log.i("OnReceive","I'm almost in");
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
}
