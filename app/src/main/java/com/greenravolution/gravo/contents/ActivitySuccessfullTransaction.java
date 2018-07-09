package com.greenravolution.gravo.contents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.greenravolution.gravo.R;

import java.util.Objects;

public class ActivitySuccessfullTransaction extends AppCompatActivity {

    Toolbar toolbar;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfull_transaction);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        date = findViewById(R.id.date);
        Intent intent = getIntent();
        date.setText(String.format("Your items are scheduled for collection on %s", intent.getStringExtra("date")));
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}
