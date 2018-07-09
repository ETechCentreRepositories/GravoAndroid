package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.HttpReq;
import com.greenravolution.gravo.objects.API;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityHelp extends AppCompatActivity {
    TextView name, email, phone, message;
    Toolbar toolbar;
    LinearLayout progressbar;
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_help);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);

        name = findViewById(R.id.name);
        name.setText(sessionManager.getString("user_full_name", ""));
        email = findViewById(R.id.email);
        email.setText(sessionManager.getString("user_email", ""));
        phone = findViewById(R.id.phone);
        message = findViewById(R.id.message);
        progressbar = findViewById(R.id.progressbar);
        HideProgress();
        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendHelp sendHelp = new SendHelp();
                ShowProgress();
                sendHelp.execute();

            }
        });
    }

    public class SendHelp extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            API api = new API();
            return req.PostRequest(api.getFAQ(), "name=" + name.getText().toString() + "&email=" + email.getText().toString() + "&number=" + phone.getText().toString() + "&message=" + message.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            HideProgress();
            Log.e("RESULT", s);
            try {
                JSONObject result = new JSONObject(s);
                int status = result.getInt("status");
                if (status == 200) {
                    Toast.makeText(ActivityHelp.this, "An email has been sent to you. please do check it out!", Toast.LENGTH_LONG).show();
                } else if (status == 400) {
                    Toast.makeText(ActivityHelp.this, "We are unable to send an email from our servers. please try again.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ActivityHelp.this, "An unexpected error has occurred! we apologize for the inconvenience!", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {


            }

        }
    }

    public void HideProgress() {
        progressbar.setVisibility(View.GONE);
    }

    public void ShowProgress() {
        progressbar.setVisibility(View.VISIBLE);
    }
}
