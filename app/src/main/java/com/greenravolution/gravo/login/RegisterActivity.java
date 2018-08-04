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
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravo.Firebase.FirebaseInstanceIDService;
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
    EditText email, fname, lname, password,number;
    EditText address_unit,address_block,address_street,address_postal;
    ImageButton pwvisibility;
    CheckBox ctnc;
    RelativeLayout rl;
    TextView btnc;
    Toolbar toolbar;
    String address;
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
        register = findViewById(R.id.register);

        toolbar = findViewById(R.id.toolbar);
        ctnc = findViewById(R.id.ctnc);
        fname.setMaxWidth(fname.getWidth());
        lname.setMaxWidth(lname.getWidth());
        btnc = findViewById(R.id.btnc);

        address_block = findViewById(R.id.address_blk);
        address_unit = findViewById(R.id.address_unit);
        address_street = findViewById(R.id.address_street);
        address_postal = findViewById(R.id.address_postal);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        HideProgress();

        register.setOnClickListener(v -> {
            if(address_unit.getText().toString().equals("")){
                address = address_street.getText().toString()+" Singapore "+address_postal.getText().toString();
            }else if(address_block.getText().toString().equals("")){
                address = address_street.getText().toString()+" Singapore "+address_postal.getText().toString();
            }else if(address_block.getText().toString().equals("") && address_unit.getText().toString().equals("")){
                address = address_street.getText().toString()+" Singapore "+address_postal.getText().toString();
            }else{
                address = "Blk " + address_block.getText().toString() + " #" + address_unit.getText().toString() + ", " + address_street.getText().toString() + " Singapore " + address_postal.getText().toString();
            }
            if (checkNetworks()) {
                if(ctnc.isChecked()){
                    if(email.getText().toString().equalsIgnoreCase("")||password.getText().toString().equalsIgnoreCase("")){
                        Toast.makeText(this, "Please enter your details", Toast.LENGTH_LONG).show();
                    }else{
                        if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()
                                && !email.getText().toString().equalsIgnoreCase("")
                                && !fname.getText().toString().equalsIgnoreCase("")
                                && !lname.getText().toString().equalsIgnoreCase("")
                                && !password.getText().toString().equalsIgnoreCase("")
                                && !number.getText().toString().equalsIgnoreCase("")
                                && !address_street.getText().toString().equalsIgnoreCase("")
                                && !address_postal.getText().toString().equalsIgnoreCase("")){
                            ShowProgress();
                            Register doregister = new Register();
                            doregister.execute(getlinkrequest.getRegister());
                        }else{
                            Toast.makeText(this, "Please enter all your details", Toast.LENGTH_LONG).show();
                        }
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Please read and accept our terms and conditions", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(RegisterActivity.this, "You are not connected to the internet", Toast.LENGTH_LONG).show();
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
            String results = registerRequest.PostRequest(strings[0]
                    , "firstname=" + fname.getText().toString()
                            + "&lastname=" + lname.getText().toString()
                            + "&email=" + email.getText().toString()
                            + "&password=" + password.getText().toString()
                            + "&contactnumber=" + number.getText().toString()
                            + "&address=" + address
                            + "&block=" + address_block.getText().toString()
                            + "&unit=" + address_unit.getText().toString()
                            + "&street=" + address_street.getText().toString()
                            + "&postal=" + address_postal.getText().toString());

            try{
                JSONObject resultObject = new JSONObject(results);
                int status = resultObject.getInt("status");
                if (status == 200) {
                    JSONArray users = resultObject.getJSONArray("result");
                    JSONObject user = users.getJSONObject(0);
                    String userID = ""+user.getInt("id");
                    FirebaseInstanceIDService registerToken = new FirebaseInstanceIDService();
                    Log.i("registerToken",registerToken.toString());
                    registerToken.callTokenRefresh(userID,"register");
                }
            } catch (JSONException e){
                e.printStackTrace();
            }

            return results;
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
                    LayoutInflater li = LayoutInflater.from(RegisterActivity.this);
                    final View gtnc = li.inflate(R.layout.dialog_userhasregistered, null);
                    dialog.setCancelable(true);
                    dialog.setView(gtnc);
                    dialog.setPositiveButton("Log in now", (dialogInterface, i) ->  startActivity(new Intent(RegisterActivity.this,LoginActivity.class)));
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();
                } else if (status == 200) {
                    sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sessionManager.edit();
                    editor.putString(SESSION_ID, String.valueOf(status));

                    JSONArray users = result.getJSONArray("result");
                    JSONObject user = users.getJSONObject(0);
                    editor.putInt("user_id", user.getInt("id"));
                    editor.putString("user_image", user.getString("photo"));
                    editor.putString("user_first_name", user.getString("first_name"));
                    Log.e("FIRSTNAME",user.getString("first_name"));
                    editor.putString("user_last_name", user.getString("last_name"));
                    Log.e("LASTNAME",user.getString("last_name"));
                    editor.putString("user_name", user.getString("first_name") + " " + user.getString("last_name"));
                    editor.putString("user_email", user.getString("email"));
                    editor.putString("user_full_name", user.getString("full_name"));
                    editor.putString("user_contact", user.getString("contact_number"));
                    editor.putString("user_address", user.getString("address"));
                    editor.putString("user_address_block", user.getString("block"));
                    editor.putString("user_address_unit", user.getString("unit"));
                    editor.putString("user_address_street", user.getString("street"));
                    editor.putString("user_address_postal", user.getString("postal"));
                    editor.putInt("user_total_points", user.getInt("total_points"));
                    editor.putString("user_rank", user.getString("rank_name"));
                    editor.putString("login_type", "normal");
                    editor.apply();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
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
        fname.setEnabled(true);
        lname.setEnabled(true);
        email.setEnabled(true);
        password.setEnabled(true);
        pwvisibility.setEnabled(true);
        number.setEnabled(true);
        register.setEnabled(true);
        ctnc.setEnabled(true);
        fname.setEnabled(true);
        lname.setEnabled(true);
        btnc.setEnabled(true);

        address_block.setEnabled(true);
        address_unit.setEnabled(true);
        address_street.setEnabled(true);
        address_postal.setEnabled(true);

        progressbar.setVisibility(View.GONE);
    }
    public void ShowProgress() {
        fname.setEnabled(false);
        lname.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        pwvisibility.setEnabled(false);
        number.setEnabled(false);

        register.setEnabled(false);
        ctnc.setEnabled(false);
        fname.setEnabled(false);
        lname.setEnabled(false);
        btnc.setEnabled(false);

        address_block.setEnabled(false);
        address_unit.setEnabled(false);
        address_street.setEnabled(false);
        address_postal.setEnabled(false);
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideSoftKeyBoard();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideSoftKeyBoard();
    }

    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
