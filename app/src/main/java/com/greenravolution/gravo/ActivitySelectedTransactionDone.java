package com.greenravolution.gravo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.greenravolution.gravo.contents.ActivityHelp;

public class ActivitySelectedTransactionDone extends AppCompatActivity {
    Toolbar toolbar;
    TextView title, needHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_transaction_done);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        title = findViewById(R.id.transaction_title);
        needHelp = findViewById(R.id.needHelp);
        needHelp.setOnClickListener(v -> startActivity(new Intent(this, ActivityHelp.class)));
    }
}
