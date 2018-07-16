package com.greenravolution.gravo.Firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    public static final String SESSION = "login_status";
    SharedPreferences sessionManager;
    Context context;
    String userID;
    String purpose;

    public void callTokenRefresh(String userID, String purpose){
        this.userID = userID;
        this.purpose = purpose;
        Log.i("callTokenRefresh",userID+" "+purpose);
        if(purpose != null && userID != null){
            onTokenRefresh();
        }

    }

    @Override
    public void onTokenRefresh() {
        //sessionManager = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        //String userID = ""+sessionManager.getInt("user_id",0);

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i("token generated",token);
        registerToken(token, userID);
    }

    private void registerToken(String token, String userID) {
        Log.d(TAG, "registerToken: "+ token);
        String url;
        if(purpose.equalsIgnoreCase("register")){
            url = "http://ehostingcentre.com/gravo/registerToken.php";
        } else {
            url = "http://ehostingcentre.com/gravo/updateToken.php";
        }

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token",token)
                .add("userid",userID)
                .build();
        Request request = new Request.Builder()
                //.url("http://192.168.1.71/fcm/register.php")
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            Log.i("registerToken results", response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}