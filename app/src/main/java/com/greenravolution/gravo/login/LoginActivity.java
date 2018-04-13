package com.greenravolution.gravo.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.R;

public class LoginActivity extends AppCompatActivity {
    Button login, fbLogin;
    EditText email,password;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}
