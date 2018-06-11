package com.greenravolution.gravo.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravo.MainActivity;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.HttpReq;
import com.greenravolution.gravo.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class RegisterActivity extends AppCompatActivity {
    EditText email, fname, lname, password, cfmpassword, number, address;
    ImageButton pwvisibility;
    CheckBox ctnc;
    RelativeLayout rl;
    TextView btnc;
    Toolbar toolbar;
    Button register;
    LinearLayout progressbar;

    HttpReq registerRequest = new HttpReq();
    API getlinkrequest = new API();

    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);


        progressbar = findViewById(R.id.progressbar);
        HideProgress();

        fname = findViewById(R.id.first_name);
        lname = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        pwvisibility = findViewById(R.id.pwvisibility);

        pwvisibility.setOnClickListener(v-> {
            int inputtype = password.getInputType();
            Log.e("INPUT TYPE: ", password.getInputType()+"");
            if(inputtype == 129){
                password.setInputType(144);
                pwvisibility.setImageDrawable(getDrawable(R.drawable.ic_visibility_off_24dp));
            }else if(inputtype == 144){
                password.setInputType(129);
                pwvisibility.setImageDrawable(getDrawable(R.drawable.ic_visibility_24dp));
            }
        });

        number = findViewById(R.id.number);
        address = findViewById(R.id.address);
        register = findViewById(R.id.register);

        toolbar = findViewById(R.id.toolbar);
        ctnc = findViewById(R.id.ctnc);
        fname.setMaxWidth(fname.getWidth());
        lname.setMaxWidth(lname.getWidth());
        btnc = findViewById(R.id.btnc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        register.setOnClickListener(v -> {
            if (checkNetworks()) {
                ShowProgress();
                Register doregister = new Register();
                doregister.execute(getlinkrequest.getRegister());
            } else {
                Toast.makeText(RegisterActivity.this, "You are not connected to the internet", Toast.LENGTH_SHORT).show();
            }

        });

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
    public class Register extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return registerRequest.PostRequest(strings[0]
                    , "firstname=" + fname.getText().toString()
                            + "&lastname=" + lname.getText().toString()
                            + "&email=" + email.getText().toString()
                            + "&password=" + password.getText().toString()
                            + "&contactnumber=" + number.getText().toString()
                            + "&address=" + address.getText().toString()
                            + "&encoded_string=" + UploadPhoto());
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            HideProgress();
            Log.e("SIGNUP POST EXECUTE: ", s);
            try {
                JSONObject result = new JSONObject(s);
                int status = result.getInt("status");
                if (status == 404) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                    dialog.setCancelable(false);
                    dialog.setMessage("You have already registered!\nPlease Login via our login page");
                    dialog.setPositiveButton("OK", (dialogInterface, i) -> {
                    });
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();
                } else if (status == 200) {
                    sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sessionManager.edit();
                    editor.putString(SESSION_ID, String.valueOf(status));

                    JSONArray users = result.getJSONArray("users");
                    JSONObject user = users.getJSONObject(0);
                    editor.putInt("user_id", user.getInt("id"));
                    editor.putString("user_image", user.getString("photo"));
                    editor.putString("user_name", user.getString("first_name") + " " + user.getString("last_name"));
                    editor.putString("user_email", user.getString("email"));
                    editor.putString("user_contact", user.getString("contact_number"));
                    editor.putString("user_address", user.getString("address"));
                    editor.putInt("user_total_points", user.getInt("total_points"));
                    editor.apply();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }else{
                    Log.e("STATUS CODE: ",status+"");
                    Log.e("STATUS MESSAGE: ", result.getString("message"));
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
    public void HideProgress() {
        progressbar.setVisibility(View.GONE);
    }
    public void ShowProgress() {
        progressbar.setVisibility(View.VISIBLE);
    }
    public String UploadPhoto() {
        Bitmap icon = BitmapFactory.decodeResource(RegisterActivity.this.getResources(),
                R.drawable.gravo_logo_black);
        String encodedimage = bitmapToBase64(icon);
        Log.e("BITMAP: ", encodedimage);
        return encodedimage;
    }
    private String bitmapToBase64(Bitmap bitmap) {
        String encodedImage = "";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            encodedImage += URLEncoder.encode(Base64.encodeToString(byteArray, Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedImage;
    }
}
