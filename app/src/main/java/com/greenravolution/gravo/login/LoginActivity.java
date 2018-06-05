package com.greenravolution.gravo.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.HttpReq;
import com.greenravolution.gravo.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;
    LinearLayout progressbar;
    Button login, fbLogin;
    EditText email, password;
    Toolbar toolbar;
    HttpReq loginRequest = new HttpReq();
    API getlinkrequest = new API();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        progressbar = findViewById(R.id.progressbar);
        HideProgress();
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login.setOnClickListener(v -> {
            if (checkNetworks()) {
                ShowProgress();
                Login login = new Login();
                login.execute(getlinkrequest.getLogin(), email.getText().toString(), password.getText().toString());
            } else {
                Toast.makeText(LoginActivity.this, "You are not connected to the internet", Toast.LENGTH_SHORT).show();
            }

        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());


    }

    @SuppressLint("StaticFieldLeak")
    public class Login extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            return loginRequest.PostRequest(strings[0], "email=" + strings[1] + "&password=" + strings[2]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            HideProgress();
            Log.e("LOGIN POST EXECUTE: ", s);
            try {
                JSONObject result = new JSONObject(s);
                int status = result.getInt("status");
                if (status == 200) {
                    sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sessionManager.edit();
                    editor.putString(SESSION_ID, String.valueOf(status));

                    JSONArray users = result.getJSONArray("users");
                    JSONObject user = users.getJSONObject(0);
                    editor.putInt("user_id", user.getInt("id"));
                    editor.putString("user_image", user.getString("photo"));
                    editor.putString("user_name", user.getString("first_name")+" "+user.getString("last_name"));
                    editor.putString("user_email", user.getString("email"));
                    editor.putString("user_contact", user.getString("contact_number"));
                    editor.putString("user_address", user.getString("address"));
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                } else if (status == 404) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                    dialog.setCancelable(false);
                    dialog.setMessage("You have not registered yet!\nPlease register to be able to enjoy the services which Gravo provides!");
                    dialog.setPositiveButton("OK", (dialogInterface, i) -> {
                    });
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();
//                    String message = result.getString("message");
//                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                } else {
                    String message = result.getString("message");
                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkNetworks() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void HideProgress(){
        progressbar.setVisibility(View.GONE);
    }
    public void ShowProgress(){
        progressbar.setVisibility(View.VISIBLE);
    }
}
