package com.greenravolution.gravo.Firebase;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.R;

import org.json.JSONObject;

import java.util.Map;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{
    private static final String TAG =  "FirebaseMessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i("FirebaseMessage 19/7","Receive Message");

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("FirebaseMessage 19/7", "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("FirebaseMessage 19/7", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        Map<String, String> params = remoteMessage.getData();
        JSONObject object = new JSONObject(params);
        Log.e("JSON_OBJECT", object.toString());

        String message = remoteMessage.getData().get("message");


        showNotification(message);
    }

    private void showNotification(String message) {
        Log.i("FirebaseMessage 19/7","Message: "+message);

        Intent i = new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        //NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"M_CH_ID")
                //getString(R.string.default_notification_channel_id))
                .setAutoCancel(true)
                .setContentTitle("Gravo")
                .setContentText("Alert: " + message)
                .setSmallIcon(R.drawable.gravo_logo_black_full)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0,builder.build());
    }


}