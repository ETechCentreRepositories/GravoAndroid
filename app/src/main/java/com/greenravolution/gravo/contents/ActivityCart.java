package com.greenravolution.gravo.contents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.google.android.gms.tasks.SuccessContinuation;
import com.greenravolution.gravo.R;

public class ActivityCart extends AppCompatActivity {
    Toolbar toolbar;
    Button points, cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());

        points = findViewById(R.id.points);
        points.setOnClickListener(v->{startActivity(new Intent(this, ActivitySuccessfullTransaction.class)); finish();});
        cash = findViewById(R.id.cash);
        cash.setOnClickListener(v->{startActivity(new Intent(this, ActivitySuccessfullTransaction.class)); finish();});

    }
}
