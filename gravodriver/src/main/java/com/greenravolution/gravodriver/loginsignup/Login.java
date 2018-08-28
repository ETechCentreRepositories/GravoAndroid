package com.greenravolution.gravodriver.loginsignup;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.greenravolution.gravodriver.R;

import java.util.Objects;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button login, register;
    RelativeLayout img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        Window w = getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        img = findViewById(R.id.mainLayout);
        AnimationDrawable progressDrawable = (AnimationDrawable) img.getBackground();
        progressDrawable.start();

    }

    @Override
    public void onClick(View v) {
        AnimationDrawable progressDrawable = (AnimationDrawable) img.getBackground();


        switch (v.getId()) {
            case R.id.login:
                Intent itl = new Intent(Login.this, LoginActivity.class);

                progressDrawable.stop();
                startActivityForResult(itl, 1);
                break;
            case R.id.register:
                Intent itr = new Intent(Login.this, RegisterActivity.class);
                //Intent itr = new Intent(Login.this, RegisterActivity_fix.class);
                progressDrawable.stop();
                startActivityForResult(itr, 1);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.e("res: ", String.valueOf(requestCode));
            if (data != null) {
                if (data.getStringExtra("type") != null) {
                    if (Objects.equals(data.getStringExtra("type"), "0")) {
                        Log.e("type", "back button");
                    } else if (Objects.equals(data.getStringExtra("type"), "1")) {
                        finish();
                    }
                } else {
                    Log.e("type", "null");
                }
                Log.e("data", "null");
            }

        } else {
            Log.e("res: ", String.valueOf(requestCode));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AnimationDrawable progressDrawable = (AnimationDrawable) img.getBackground();
        progressDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        AnimationDrawable progressDrawable = (AnimationDrawable) img.getBackground();
        progressDrawable.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AnimationDrawable progressDrawable = (AnimationDrawable) img.getBackground();
        progressDrawable.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AnimationDrawable progressDrawable = (AnimationDrawable) img.getBackground();
        progressDrawable.start();
    }
}
