package com.greenravolution.gravo.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.adapters.PageViewerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final String[] TEXTS = {"GRAVO is a platform through which environmental information and educational material can be accessed with ease for all subscribers.", "GRAVO is also a platform through which environmental information and educational material can be accessed with ease for all subscribers.", "GRAVO is also a platform through which environmental information and educational material can be accessed with ease for all subscribers."};
    private static int currentPage = 0;
    Button login, register;
    private ViewPager mPager;
    private ArrayList<String> textArray = new ArrayList<String>();
    RelativeLayout img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        Window w = getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        img = findViewById(R.id.mainLayout);
        AnimationDrawable progressDrawable = (AnimationDrawable) img.getBackground();
        progressDrawable.start();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle("DISCLAIMER");
        dialog.setMessage("The Gravo Recycler Application is still in the midst of development. Do take note that this is just the front end. \n\nIf you encounter any errors, please do not hesitate to contact any of the staff to tell us about it as we are trying to improve it as we go along as well.\n\nThank you for your patience!");
        dialog.setPositiveButton("OK", (dialogInterface, i) -> { });
        AlertDialog dialogue = dialog.create();
        dialogue.show();
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

                progressDrawable.stop();
                startActivityForResult(itr, 1);
                break;
        }
    }

    private void init() {
        textArray.addAll(Arrays.asList(TEXTS));

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new PageViewerAdapter(Login.this, textArray));
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == TEXTS.length) {
                currentPage = 0;
            }
            mPager.setCurrentItem(currentPage++, true);
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 50000);
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
