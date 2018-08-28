package com.greenravolution.gravodriver.loginsignup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.MainActivity;
import com.greenravolution.gravodriver.R;
import com.greenravolution.gravodriver.functions.HttpReq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session_collector";

    Toolbar toolbar;
    EditText ete, etp;
    Button bl, forgetpassword;
    //    CheckBox ctnc;
    TextView re;
    SharedPreferences sessionManager;
    LinearLayout llProgress;
    ImageView progressBar;

    String userId;
    String userFirstName;
    String userLastName;
    String userEmail;
    String userNumber;
    String userAddress;
    String userLicenseNo;
    String userVehicleNo;

    int userstatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);


        toolbar = findViewById(R.id.toolbar);
        ete = findViewById(R.id.getEmail);
        etp = findViewById(R.id.getPassword);
        forgetpassword = findViewById(R.id.forgetpassword);
        forgetpassword.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgotPassword.class)));

//        ctnc = findViewById(R.id.rbmMe);
        bl = findViewById(R.id.login);
        re = findViewById(R.id.resultError);
        re.setTextColor(getResources().getColor(R.color.brand_pink));
        re.setTextSize(15);
        llProgress = findViewById(R.id.avi);
        llProgress.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progressBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        boolean networkState = checkNetwork();
        if (!networkState) {
            Toast.makeText(this, "Please Switch your data on", Toast.LENGTH_SHORT).show();
        }
        toolbar.setNavigationOnClickListener(v -> {
            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();

        });
        bl.setOnClickListener(v -> {

            boolean networkState1 = checkNetwork();
            if (!networkState1) {
                Toast.makeText(LoginActivity.this, "Please Switch your data on", Toast.LENGTH_SHORT).show();
            } else {
                bl.setEnabled(false);
                if (ete.getText().toString().isEmpty() || etp.getText().toString().isEmpty()) {
                    bl.setEnabled(true);
                    re.setText(R.string.invalid_login);
                } else {
                    llProgress.setVisibility(View.VISIBLE);
                    AnimationDrawable progressDrawable = (AnimationDrawable) progressBar.getDrawable();
                    progressDrawable.start();
                    Login login = new Login();
                    login.execute("http://ehostingcentre.com/gravo/collectorlogin.php");
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

    public boolean checkNetwork() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            // notify user you are online
            return true;

        } else if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            // notify user you are not online
            Toast.makeText(this, "Please Switch your data on", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    private class Login extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq request = new HttpReq();
            return request.PostRequest("http://ehostingcentre.com/gravo/collectorlogin.php", "email=" + ete.getText().toString() + "&password=" + etp.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            re.setText("");
            Log.e("RESULT LOGIN", s + "");
            llProgress.setVisibility(View.GONE);
            AnimationDrawable progressDrawable = (AnimationDrawable) progressBar.getDrawable();
            progressDrawable.stop();

            try {


                JSONObject loginDetails = new JSONObject(s);
                int status = loginDetails.getInt("status");
                if (status == 200) {
                    JSONArray getUser = loginDetails.getJSONArray("users");
                    JSONObject user = getUser.getJSONObject(0);
                    userId = String.valueOf(user.getInt("id"));
                    userFirstName = user.getString("first_name");
                    userLastName = user.getString("last_name");
                    userEmail = user.getString("email");
                    userAddress = user.getString("address");
                    userNumber = user.getString("phone");
                    userLicenseNo = user.getString("liscence_number");
                    userVehicleNo = user.getString("vehicle_number");
                    userstatus = user.getInt("status");

                    if (userstatus == 1) {
                        re.setText("");
                        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sessionManager.edit();
                        editor.putString(SESSION_ID, String.valueOf(status));
                        editor.putString("id", userId);
                        editor.putString("firstname", userFirstName);
                        editor.putString("lastname", userLastName);
                        editor.putString("email", userEmail);
                        editor.putString("number", userNumber);
                        editor.putString("address", userAddress);
                        editor.putString("license", userLicenseNo);
                        editor.putString("vehicle", userVehicleNo);

                        editor.apply();
                        Intent itmchk = new Intent(LoginActivity.this, MainActivity.class);
                        itmchk.putExtra("message", "Welcome Back " + userFirstName + "!");
                        Intent ib = new Intent();
                        ib.putExtra("type", "1");
                        setResult(1, ib);
                        finish();
                        startActivity(itmchk);
                        bl.setEnabled(true);
                    } else if (userstatus == 0) {
                        //Toast.makeText(LoginActivity.this, "You have not been approved to drive with gravo yet! We will get back to you shortly.\n\nThank you for your patience!", Toast.LENGTH_SHORT).show();
                        bl.setEnabled(true);
                        re.setText("");
                        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                        LayoutInflater li = LayoutInflater.from(LoginActivity.this);
                        final View gtnc = li.inflate(R.layout.acceptancedialog, null);
                        dialog.setCancelable(true);
                        dialog.setView(gtnc);
                        dialog.setPositiveButton("Ok", (dialogInterface, i) -> startActivity(new Intent(LoginActivity.this, com.greenravolution.gravodriver.loginsignup.Login.class)));
                        AlertDialog dialogue = dialog.create();
                        dialogue.show();

                    } else if (userstatus == 2) {
                        re.setText("");
                        bl.setEnabled(true);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                        LayoutInflater li = LayoutInflater.from(LoginActivity.this);
                        final View gtnc = li.inflate(R.layout.dialog_rejecteduser, null);
                        dialog.setCancelable(true);
                        dialog.setView(gtnc);
                        dialog.setPositiveButton("I understand.", (dialogInterface, i) -> startActivity(new Intent(LoginActivity.this, com.greenravolution.gravodriver.loginsignup.Login.class)));
                        AlertDialog dialogue = dialog.create();
                        dialogue.show();
                    } else {
                        re.setText("");
                        bl.setEnabled(true);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                        LayoutInflater li = LayoutInflater.from(LoginActivity.this);
                        final View gtnc = li.inflate(R.layout.dialog_unexpectederror, null);
                        dialog.setCancelable(true);
                        dialog.setView(gtnc);
                        dialog.setPositiveButton("Ok", (dialogInterface, i) -> startActivity(new Intent(LoginActivity.this, com.greenravolution.gravodriver.loginsignup.Login.class)));
                        AlertDialog dialogue = dialog.create();
                        dialogue.show();
                    }
                } else if (status == 403) {
                    re.setText("");
                    Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                    bl.setEnabled(true);

                } else if (status == 404) {
                    bl.setEnabled(true);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                    LayoutInflater li = LayoutInflater.from(LoginActivity.this);
                    final View gtnc = li.inflate(R.layout.dialog_userhasnotregistered, null);
                    dialog.setCancelable(true);
                    dialog.setView(gtnc);
                    dialog.setPositiveButton("I would like to Register", (dialogInterface, i) -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
                    dialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();
                } else {
                    re.setText("");
                    //Toast.makeText(LoginActivity.this, "An unexpected error has occurred. We apologize for the inconvenience!", Toast.LENGTH_SHORT).show();
                    bl.setEnabled(true);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                    LayoutInflater li = LayoutInflater.from(LoginActivity.this);
                    final View gtnc = li.inflate(R.layout.acceptancedialog, null);
                    dialog.setCancelable(true);
                    dialog.setView(gtnc);
                    dialog.setPositiveButton("Ok", (dialogInterface, i) -> startActivity(new Intent(LoginActivity.this, com.greenravolution.gravodriver.loginsignup.Login.class)));
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
