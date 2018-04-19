package com.greenravolution.gravo.contents;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.greenravolution.gravo.R;

import java.util.Objects;

public class ActivityUser extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
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
