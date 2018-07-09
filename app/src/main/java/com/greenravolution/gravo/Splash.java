package com.greenravolution.gravo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.greenravolution.gravo.functions.GetAsyncRequest;
import com.greenravolution.gravo.login.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class Splash extends AppCompatActivity {
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;
    ImageView ic_splash;
    GetAsyncRequest.OnAsyncResult getRates = (resultCode, message) -> {
        try {
            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            JSONObject results = new JSONObject(message);
            JSONArray rates = results.getJSONArray("result");
            Log.e("rates", rates.toString() + "\n");
            SharedPreferences.Editor editor = sessionManager.edit();
            editor.putString("rates", rates.toString());
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ic_splash = findViewById(R.id.splash_id);
        if(isNetworkAvailable()){
            GetAsyncRequest asyncRequest = new GetAsyncRequest();
            asyncRequest.setOnResultListener(getRates);
            asyncRequest.execute("http://greenravolution.com/API/getCategories.php?type=all");

            int SPLASH_TIME_OUT = 2000;
            new Handler().postDelayed(() -> {
                sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                if (sessionManager.getString(SESSION_ID, null) == null) {
                    Intent i = new Intent(Splash.this, Login.class);
                    Log.w("SESSION_ID:", "not logged in. ID ->" + sessionManager.getString(SESSION_ID, null));
                    startActivity(i);
                    finish();
                } else if (Objects.equals(sessionManager.getString(SESSION_ID, null), "200")) {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    Log.i("SESSION_ID ERROR:", "Logged in. ID ->" + sessionManager.getString(SESSION_ID, null));
                    startActivity(i);
                    finish();
                } else {
                    Log.e("SESSION_ID ERROR:", "Retrieved_ID ->" + sessionManager.getString(SESSION_ID, null));
                }
            }, SPLASH_TIME_OUT);
        }else{
            Toast.makeText(this,"The Gravo App requires Internet. Please on your data and restart the app",Toast.LENGTH_SHORT).show();
        }

    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}