package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityLeaderboard extends AppCompatActivity {
    Toolbar toolbar;
    TextView points, rank, name;
    SharedPreferences sessionManager;
    public static final String SESSION = "login_status";
    TextView share, invite;
    CircleImageView profilpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_leaderboard);

        profilpic = findViewById(R.id.profile_image);
        name = findViewById(R.id.name);
        rank = findViewById(R.id.rank);
        points = findViewById(R.id.points);
        share = findViewById(R.id.share);
        invite = findViewById(R.id.invite);


        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        Glide.with(ActivityLeaderboard.this).load(sessionManager.getString("user_image", "https://www.greenravolution.com/API/uploads/291d5076443149a4273f0199fea9db39a3ab4884.png")).into(profilpic);
        points.setText(String.valueOf("Points: " + sessionManager.getInt("user_total_points", -1)));
        name.setText(sessionManager.getString("user_full_name", ""));
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                String shareBody = "I have " + String.valueOf(sessionManager.getInt("user_total_points", -1)) + " Points in the gravo app doing recycling!\n you can join me too!\n\nhttps://www.greenravolution.com/\n\nCome and Join me now!";
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                String shareBody = "I have " + String.valueOf(sessionManager.getInt("user_total_points", -1)) + " Points in the gravo app doing recycling!\n you can join me too!\n\nhttps://www.greenravolution.com/\n\nCome and Join me now!";
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());

    }

}

