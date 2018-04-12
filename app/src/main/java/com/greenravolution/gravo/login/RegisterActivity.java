package com.greenravolution.gravo.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greenravolution.gravo.R;

public class RegisterActivity extends AppCompatActivity {
    EditText email, fname,lname,password,username,address;
    CheckBox ctnc;
    RelativeLayout rl;
    TextView btnc;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        fname = findViewById(R.id.first_name);
        lname = findViewById(R.id.last_name);
        toolbar = findViewById(R.id.toolbar);
        ctnc = findViewById(R.id.ctnc);
        fname.setMaxWidth(fname.getWidth());
        lname.setMaxWidth(lname.getWidth());
        btnc = findViewById(R.id.btnc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> {
            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });
        btnc.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
            LayoutInflater li = LayoutInflater.from(RegisterActivity.this);
            final View gtnc = li.inflate(R.layout.tnc_dialog, null);
            dialog.setCancelable(true);
            dialog.setView(gtnc);
            dialog.setPositiveButton("Accept", (dialogInterface, i) -> ctnc.setChecked(true));
            dialog.setNegativeButton("Later", (dialogInterface, i) -> {
            });
            AlertDialog dialogue = dialog.create();
            dialogue.show();
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ib = new Intent();
        ib.putExtra("type", "0");
        setResult(1, ib);
        finish();
    }
}
