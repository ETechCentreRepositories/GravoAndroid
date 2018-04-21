package com.greenravolution.gravo.contents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.R;

import java.util.Objects;

public class ActivitySuccessfullTransaction extends AppCompatActivity {
    Button toCalendar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfull_transaction);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        toCalendar = findViewById(R.id.toCalendar);
        toCalendar.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivity.class).putExtra("fromSuccess", "1"));
            finish();
        });
    }
}
