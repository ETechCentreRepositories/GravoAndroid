package com.greenravolution.gravodriver.loginsignup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.MainActivity;
import com.greenravolution.gravodriver.R;

public class LoginActivity extends AppCompatActivity {
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    Toolbar toolbar;
    EditText ete, etp;
    Button bl;
    CheckBox ctnc;
    TextView re;
    SharedPreferences sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        toolbar = findViewById(R.id.toolbar);
        ete = findViewById(R.id.getEmail);
        etp = findViewById(R.id.getPassword);
        ctnc = findViewById(R.id.rbmMe);
        bl = findViewById(R.id.login);
        re = findViewById(R.id.resultError);
        re.setTextColor(getResources().getColor(R.color.brand_pink));
        re.setTextSize(15);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ib = new Intent();
                ib.putExtra("type", "0");
                setResult(1, ib);
                finish();

            }
        });
        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ete.getText().toString().isEmpty() || etp.getText().toString().isEmpty()) {
                    re.setText(R.string.invalid_login);
                } else {
                    if (ctnc.isChecked()) {
                        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sessionManager.edit();
                        editor.putString(SESSION_ID, "200");
                        editor.apply();
                        Intent itmchk = new Intent(LoginActivity.this, MainActivity.class);
                        itmchk.putExtra("message", "Welcome Back!");
                        Intent ib = new Intent();
                        ib.putExtra("type", "1");
                        setResult(1,ib);
                        finish();
                        startActivity(itmchk);

                    } else {
                        Intent itmnochk = new Intent(LoginActivity.this, MainActivity.class);
                        itmnochk.putExtra("message", "Welcome Back!");
                        Intent ib = new Intent();
                        ib.putExtra("type", "1");
                        setResult(1,ib);
                        finish();
                        startActivity(itmnochk);

                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ib = new Intent();
        ib.putExtra("type", "0");
        setResult(1,ib);
        finish();
    }
}
