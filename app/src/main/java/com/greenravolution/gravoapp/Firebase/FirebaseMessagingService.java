package com.greenravolution.gravoapp.Firebase;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.greenravolution.gravoapp.MainActivity;
import com.greenravolution.gravoapp.R;
import com.greenravolution.gravoapp.functions.DBHelper;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("FirebaseMessage 19/7", "Receive Message");
        Log.e("FirebaseMessage GETFROM", remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e("FirebaseMessage 19/7", "Message data payload: " + remoteMessage.getData());
        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e("FirebaseMessage 19/7", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.e("FirebaseMessage 19/7", "Message Notification Title: " + remoteMessage.getNotification().getTitle());
        }

        String message = remoteMessage.getNotification().getBody();
        String title = remoteMessage.getNotification().getTitle();

        showNotification(title, message);

    }

    private void showNotification(String title, String message) {
        Log.i("FirebaseMessage 19/7", "Message: " + message);
        DBHelper helper = new DBHelper(this.getApplicationContext());
        helper.AddNotification(message,title);

        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        //NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                     //getString(R.string.default_notification_channel_id))
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(android.app.Notification.PRIORITY_MAX)
                .setSmallIcon(R.drawable.gravo_logo_main)
                .setBadgeIconType(R.drawable.gravo_logo_main)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }

}