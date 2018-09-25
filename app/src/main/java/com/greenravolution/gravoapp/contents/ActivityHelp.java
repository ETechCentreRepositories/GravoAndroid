package com.greenravolution.gravoapp.contents;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravoapp.R;
import com.greenravolution.gravoapp.functions.HttpReq;
import com.greenravolution.gravoapp.objects.API;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityHelp extends AppCompatActivity {
    TextView name, email, phone, message;
    Toolbar toolbar;
    WebView webView;
    LinearLayout progressbar;
    public static final String SESSION = "login_status";
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
        send.setOnClickListener(v -> {
            SendHelp sendHelp = new SendHelp();
            ShowProgress();
            sendHelp.execute();

        });

        webView = findViewById(R.id.webview);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
//        int width = (size.x);
        int width = 100;
        int height = (size.x)-100;
//        String path = "<div style=\"text-align:center;\"><iframe width=\""+String.valueOf(width)+"%\" height=\""+String.valueOf(height)+"\" src=\"https://www.youtube-nocookie.com/embed/ksbl_Yues4A?rel=0&amp;controls=0&amp;showinfo=0\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe></div>";
        String path = "<div style=\"text-align:center;\"><iframe width=\""+String.valueOf(width)+"%\" height=\""+String.valueOf(height)+"\" src=\"https://www.youtube-nocookie.com/embed/LrkMH_EnRas?controls=0&amp;showinfo=0\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe></div>";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadData(path, "text/html", "UTF-8");
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
                    Toast.makeText(ActivityHelp.this, "An email has been sent to you. Please do check it out!", Toast.LENGTH_LONG).show();
                } else if (status == 400) {
                    Toast.makeText(ActivityHelp.this, "We are unable to send an email from our servers. Please try again.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ActivityHelp.this, "An unexpected error has occurred! We apologize for the inconvenience!", Toast.LENGTH_LONG).show();
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
