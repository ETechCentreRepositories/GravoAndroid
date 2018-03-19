package com.greenravolution.gravodriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.greenravolution.gravodriver.loginsignup.Login;

import java.util.Objects;

public class Splash extends AppCompatActivity {
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;
    ImageView ic_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ic_splash = findViewById(R.id.splash_id);
        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
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
            }

        }, SPLASH_TIME_OUT);

    }


}