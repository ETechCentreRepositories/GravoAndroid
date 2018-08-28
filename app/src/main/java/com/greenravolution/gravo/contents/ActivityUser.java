package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.login.Login;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityUser extends AppCompatActivity {
    Toolbar toolbar;
    RelativeLayout gravosPage;
    TextView logout, points;
    CircleImageView profile_img;
    TextView name, myEmail, myName, myAddress;
    String getName, getImage, getAddress, getEmail;

    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;

    public void updateprofile() {
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        getName = sessionManager.getString("user_full_name", "");
        getEmail = sessionManager.getString("user_email", "");
        getAddress = sessionManager.getString("user_address", "");
        getImage = sessionManager.getString("user_image", "");
        if (getImage.equals("")) {
            profile_img.setImageDrawable(getDrawable(R.drawable.gravo_logo_black));
        } else {
            Glide.with(ActivityUser.this).load(getImage).into(profile_img);
        }
        name.setText(getName);
        myName.setText(getName);
        myEmail.setText(getEmail);
        if (getAddress.equalsIgnoreCase("")) {
            myAddress.setText("");
        } else {
            myAddress.setText(getAddress);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        profile_img = findViewById(R.id.profile_img);
        name = findViewById(R.id.name);
        myName = findViewById(R.id.myName);
        myEmail = findViewById(R.id.myEmail);
        myAddress = findViewById(R.id.myAddress);
        profile_img = findViewById(R.id.profile_img);
        points = findViewById(R.id.points);
        getName = sessionManager.getString("user_full_name", "");
        getEmail = sessionManager.getString("user_email", "");
        getAddress = sessionManager.getString("user_address", "");
        getImage = sessionManager.getString("user_image", "");
        points.setText(String.valueOf(sessionManager.getInt("user_total_points",-1))+" points");

        name.setText(getName);
        myName.setText(getName);
        myEmail.setText(getEmail);
        myAddress.setText(getAddress);

        if (getImage.equals("")) {
            profile_img.setImageDrawable(getDrawable(R.drawable.gravo_logo_black));
        } else {
            Glide.with(ActivityUser.this).load(getImage).into(profile_img);
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        gravosPage = findViewById(R.id.gravosPage);
        gravosPage.setOnClickListener(v -> startActivity(new Intent(this, ActivityLeaderboard.class)));
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityUser.this);
            dialog.setCancelable(true);
            dialog.setTitle("Log Out");
            dialog.setMessage("Are you sure you want to log out?");
            dialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sessionManager.edit();
                editor.putString(SESSION_ID, null);
                editor.apply();
                Intent in = new Intent(ActivityUser.this, Login.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            });
            dialog.setNeutralButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialogue = dialog.create();
            dialogue.show();
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_account_menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            // Android home
            case R.id.edit_account:
                startActivityForResult(new Intent(this, ActivityEditUser.class), 1);
                return true;

        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateprofile();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateprofile();
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
}
