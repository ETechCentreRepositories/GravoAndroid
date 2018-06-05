package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.login.Login;

import java.util.Objects;

public class ActivityUser extends AppCompatActivity {
    Toolbar toolbar;
    RelativeLayout gravosPage;
    TextView logout;

    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        gravosPage = findViewById(R.id.gravosPage);
        gravosPage.setOnClickListener(v->startActivity(new Intent(this,ActivityLeaderboard.class)));
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(v->{
            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sessionManager.edit();
            editor.putString(SESSION_ID, null);
            editor.apply();
            Intent in = new Intent(ActivityUser.this,Login.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
            finish();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Log.e("res: ", String.valueOf(requestCode));
            if(data != null){
                if(data.getStringExtra("type")!=null){
                    if(Objects.equals(data.getStringExtra("type"), "0")){
                        Log.e("type", "back button");
                    }else if (Objects.equals(data.getStringExtra("type"), "1")){
                        finish();
                    }
                }else {
                    Log.e("type", "null");
                }
                Log.e("data", "null");
            }

        }else{
            Log.e("res: ", String.valueOf(requestCode));
        }
    }
}
