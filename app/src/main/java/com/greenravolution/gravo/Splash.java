package com.greenravolution.gravo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.greenravolution.gravo.functions.GetAsyncRequest;
import com.greenravolution.gravo.functions.HttpReq;
import com.greenravolution.gravo.login.FacebookAddDetailsActivity;
import com.greenravolution.gravo.login.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class Splash extends AppCompatActivity {
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;
    ImageView ic_splash;
    GetAsyncRequest.OnAsyncResult getRates = (resultCode, message) -> {
        try {
            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            JSONObject results = new JSONObject(message);
            JSONArray rates = results.getJSONArray("result");
            Log.e("rates", rates.toString() + "\n");
            SharedPreferences.Editor editor = sessionManager.edit();
            editor.putString("rates", rates.toString());
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ic_splash = findViewById(R.id.splash_id);
        if (isNetworkAvailable()) {
            GetAsyncRequest asyncRequest = new GetAsyncRequest();
            asyncRequest.setOnResultListener(getRates);
            asyncRequest.execute("http://ehostingcentre.com/gravo/getCategories.php?type=all");

            int SPLASH_TIME_OUT = 2000;
            new Handler().postDelayed(() -> {
                sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                if (sessionManager.getString(SESSION_ID, null) == null) {
                    Intent i = new Intent(Splash.this, Login.class);
                    Log.w("SESSION_ID:", "not logged in. ID ->" + sessionManager.getString(SESSION_ID, null));
                    startActivity(i);
                    finish();
                } else if (Objects.equals(sessionManager.getString(SESSION_ID, null), "200")) {
                    Log.i("SESSION_ID ERROR:", "Logged in. ID ->" + sessionManager.getString(SESSION_ID, null));
                    String email = sessionManager.getString("user_email", "");
                    getUser getUser = new getUser();
                    getUser.execute(email);
                } else if (Objects.equals(sessionManager.getString(SESSION_ID, null), "201")) {
                    Log.i("SESSION_ID ERROR:", "Logged in. ID ->" + sessionManager.getString(SESSION_ID, null));
                    String email = sessionManager.getString("user_email", "");
                    getUser getUser = new getUser();
                    getUser.execute(email);
                } else {
                    Log.e("SESSION_ID ERROR:", "Retrieved_ID ->" + sessionManager.getString(SESSION_ID, null));
                }
            }, SPLASH_TIME_OUT);
        } else {
            Toast.makeText(this, "The Gravo App requires Internet. Please on your data and restart the app", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class getUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.GetRequest("http://www.ehostingcentre.com/gravo/getUsers.php?email=" + strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Toast.makeText(Splash.this, "An unexpected Error has occured", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            } else {
                try {
                    JSONObject result = new JSONObject(s);
                    Log.e("GET DETAILS", s);
                    int status = result.getInt("status");
                    if (status == 200) {
                        Log.e("Get User Status", String.valueOf(status));
                        SharedPreferences.Editor editor = sessionManager.edit();
                        editor.putString(SESSION_ID, String.valueOf(status));
                        JSONArray users = result.getJSONArray("users");
                        JSONObject user = users.getJSONObject(0);
                        editor.putInt("user_id", user.getInt("id"));
                        editor.putString("user_facebook_id", user.getString("facebook_id"));
                        editor.putString("user_image", user.getString("photo"));
                        editor.putString("user_first_name", user.getString("first_name"));
                        editor.putString("user_last_name", user.getString("last_name"));
                        editor.putString("user_name", user.getString("first_name") + " " + user.getString("last_name"));
                        editor.putString("user_full_name", user.getString("full_name"));
                        editor.putString("user_email", user.getString("email"));
                        editor.putString("user_contact", user.getString("contact_number"));
                        editor.putString("user_address", user.getString("address"));
                        editor.putString("user_address_block", user.getString("block"));
                        editor.putString("user_address_unit", user.getString("unit"));
                        editor.putString("user_address_street", user.getString("street"));
                        editor.putString("user_address_postal", user.getString("postal"));
                        editor.putInt("user_total_points", user.getInt("total_points"));
                        editor.putString("user_rank", user.getString("rank_name"));
                        editor.apply();
                        if (user.getString("facebook_id")!=null) {
                            if (user.getString("first_name").equalsIgnoreCase("") || user.getString("last_name").equalsIgnoreCase("")) {
                                Intent i = new Intent(Splash.this, FacebookAddDetailsActivity.class);
                                Log.e("ACTIVITY", "SPLASH TO DETAILS FACEBOOK");
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(Splash.this, MainActivity.class);
                                Log.e("ACTIVITY", "SPLASH TO MAIN FACEBOOK");
                                startActivity(i);
                                finish();
                            }
                        } else {
                            Intent i = new Intent(Splash.this, MainActivity.class);
                            Log.e("ACTIVITY", "SPLASH TO MAIN NORMAL");
                            startActivity(i);
                            finish();
                        }

                    } else if (status == 201) {
                        Log.e("Get User Status", String.valueOf(status));
                        SharedPreferences.Editor editor = sessionManager.edit();
                        editor.putString(SESSION_ID, String.valueOf(status));
                        JSONArray users = result.getJSONArray("users");
                        JSONObject user = users.getJSONObject(0);
                        editor.putInt("user_id", user.getInt("id"));
                        editor.putString("user_facebook_id", user.getString("facebook_id"));
                        editor.putString("user_image", user.getString("photo"));
                        editor.putString("user_first_name", user.getString("first_name"));
                        editor.putString("user_last_name", user.getString("last_name"));
                        editor.putString("user_name", user.getString("first_name") + " " + user.getString("last_name"));
                        editor.putString("user_full_name", user.getString("full_name"));
                        editor.putString("user_email", user.getString("email"));
                        editor.putString("user_contact", user.getString("contact_number"));
                        editor.putString("user_address", user.getString("address"));
                        editor.putString("user_address_block", user.getString("block"));
                        editor.putString("user_address_unit", user.getString("unit"));
                        editor.putString("user_address_street", user.getString("street"));
                        editor.putString("user_address_postal", user.getString("postal"));
                        editor.putInt("user_total_points", user.getInt("total_points"));
                        editor.putString("user_rank", user.getString("rank_name"));
                        editor.apply();
                        if (user.getString("facebook_id")!=null) {
                            if (user.getString("first_name").equalsIgnoreCase("") || user.getString("last_name").equalsIgnoreCase("")) {
                                Intent i = new Intent(Splash.this, FacebookAddDetailsActivity.class);
                                Log.e("ACTIVITY", "SPLASH TO DETAILS FACEBOOK");
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(Splash.this, MainActivity.class);
                                Log.e("ACTIVITY", "SPLASH TO MAIN FACEBOOK");
                                startActivity(i);
                                finish();
                            }
                        } else {
                            Intent i = new Intent(Splash.this, MainActivity.class);
                            Log.e("ACTIVITY", "SPLASH TO MAIN NORMAL");
                            startActivity(i);
                            finish();
                        }


                    } else if (status == 404) {
                        Intent i = new Intent(Splash.this, Login.class);
                        Log.w("SESSION_ID:", "not logged in. ID ->" + sessionManager.getString(SESSION_ID, null));
                        startActivity(i);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}