package com.greenravolution.gravoapp.contents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

import com.greenravolution.gravoapp.R;

public class ActivitySettingsItems extends AppCompatActivity {
    Toolbar toolbar;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_items);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());

        TextView activityTitle = findViewById(R.id.title);
        text = findViewById(R.id.text);
        Intent intent = getIntent();
        String item = intent.getStringExtra("tnc");
        String title = intent.getStringExtra("type");

        text.setText(Html.fromHtml(item));
        activityTitle.setText(title);
    }
}
