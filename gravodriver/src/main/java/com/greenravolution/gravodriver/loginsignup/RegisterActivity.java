package com.greenravolution.gravodriver.loginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greenravolution.gravodriver.MainActivity;
import com.greenravolution.gravodriver.R;

public class RegisterActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText ete, etfn, etln, etnum, etic, etpw, etli;
    Button bca;
    CheckBox ctnc;
    RelativeLayout rl;
    TextView btnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        toolbar = findViewById(R.id.toolbar);
        ete = findViewById(R.id.getEmail);
        etfn = findViewById(R.id.getFirstName);
        etln = findViewById(R.id.getLastName);
        etnum = findViewById(R.id.getNumber);
        etic = findViewById(R.id.getNRIC);
        etpw = findViewById(R.id.getPassword);
        etli = findViewById(R.id.getLiscense);
        bca = findViewById(R.id.signup);
        ctnc = findViewById(R.id.ctnc);
        rl = findViewById(R.id.rl);
        btnc = findViewById(R.id.btnc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        toolbar.setNavigationOnClickListener(v -> {
            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });
        bca.setOnClickListener(v -> {
            if (ete.getText().toString().isEmpty()
                    || etfn.getText().toString().isEmpty()
                    || etln.getText().toString().isEmpty()
                    || etnum.getText().toString().isEmpty()
                    || etic.getText().toString().isEmpty()
                    || etpw.getText().toString().isEmpty()
                    || etli.getText().toString().isEmpty()) {

                Snackbar.make(rl, "Please fill in all fields!", Snackbar.LENGTH_LONG).show();

            } else {

                if (ctnc.isChecked()) {
                    Intent itmn = new Intent(RegisterActivity.this, MainActivity.class);
                    itmn.putExtra("message", "Welcome!");
                    Intent ib = new Intent();
                    ib.putExtra("type", "1");
                    setResult(1, ib);
                    finish();
                    startActivity(itmn);

                } else {
                    Snackbar.make(rl, "Please accept our Terms and Conditions", Snackbar.LENGTH_LONG).show();
                }

            }
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
