package com.greenravolution.gravodriver.loginsignup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.MainActivity;
import com.greenravolution.gravodriver.R;
import com.greenravolution.gravodriver.functions.HttpReq;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";

    Toolbar toolbar;
    EditText ete, etp;
    Button bl;
    CheckBox ctnc;
    TextView re;
    SharedPreferences sessionManager;
    LinearLayout llProgress;


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
        llProgress = findViewById(R.id.avi);
        llProgress.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        boolean networkState = checkNetwork();
        if(!networkState){
            Toast.makeText(this, "Please Switch your data on", Toast.LENGTH_SHORT).show();
        }
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
                boolean networkState = checkNetwork();
                if(!networkState){
                    Toast.makeText(LoginActivity.this, "Please Switch your data on", Toast.LENGTH_SHORT).show();
                }else{
                    if (ete.getText().toString().isEmpty() || etp.getText().toString().isEmpty()) {
                        re.setText(R.string.invalid_login);
                    } else {
                        llProgress.setVisibility(View.VISIBLE);
                        Login login = new Login();
                        login.execute("http://bryanlowsk.com/UHoo/API/login.php");
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
        setResult(1, ib);
        finish();
    }

    private class Login extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq request = new HttpReq();
            return request.PostRequest("http://bryanlowsk.com/UHoo/API/login.php", "email="+ete.getText().toString()+"&password="+etp.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            llProgress.setVisibility(View.GONE);
            if (ctnc.isChecked()) {
                try {
                    JSONObject loginDetails = new JSONObject(s);
                    int status = loginDetails.getInt("status");
                    if (status == 200) {
                        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sessionManager.edit();
                        editor.putString(SESSION_ID, String.valueOf(status));
                        editor.apply();
                        Intent itmchk = new Intent(LoginActivity.this, MainActivity.class);
                        itmchk.putExtra("message", "Welcome Back!");
                        Intent ib = new Intent();
                        ib.putExtra("type", "1");
                        setResult(1, ib);
                        finish();
                        startActivity(itmchk);
                    } else {
                        re.setText(R.string.invalid_login);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {

                try {
                    JSONObject loginDetails = new JSONObject(s);
                    int status = loginDetails.getInt("status");
                    if (status == 200) {
                        Intent itmnochk = new Intent(LoginActivity.this, MainActivity.class);
                        itmnochk.putExtra("message", "Welcome Back!");
                        Intent ib = new Intent();
                        ib.putExtra("type", "1");
                        setResult(1, ib);
                        finish();
                        startActivity(itmnochk);
                    } else {
                        re.setText(R.string.invalid_login);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public boolean checkNetwork(){
        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {

            // notify user you are online
            return true;

        }
        else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            // notify user you are not online
            Toast.makeText(this, "Please Switch your data on", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

}
