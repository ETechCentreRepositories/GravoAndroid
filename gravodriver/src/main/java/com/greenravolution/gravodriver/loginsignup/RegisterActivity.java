package com.greenravolution.gravodriver.loginsignup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.greenravolution.gravodriver.MainActivity;
import com.greenravolution.gravodriver.R;
import com.greenravolution.gravodriver.WebView;
import com.greenravolution.gravodriver.functions.HttpReq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText ete, etfn, etln, etnum, etic, etpw, etli, etvl;
    Button bca;
    CheckBox ctnc;

    RelativeLayout rl;
    TextView tvaddress;
    TextView btnc;
    LinearLayout llProgress, getaddresslayout;
    ImageView progressBar;
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session_collector";

    SharedPreferences sessionManager;

    private PlaceAutocompleteFragment placeAutocompleteFragment;
    int userstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);


        toolbar = findViewById(R.id.toolbar);
        ete = findViewById(R.id.getEmail);
        etfn = findViewById(R.id.getFirstName);
        etln = findViewById(R.id.getLastName);
        etnum = findViewById(R.id.getNumber);

        etic = findViewById(R.id.getNRIC);
        etpw = findViewById(R.id.getPassword);
        etli = findViewById(R.id.getLiscenseNo);
        etvl = findViewById(R.id.getVehicleNumber);


        llProgress = findViewById(R.id.avi);
        progressBar = findViewById(R.id.progressBar);
        HideProgress();
        bca = findViewById(R.id.signup);
        ctnc = findViewById(R.id.ctnc);
        rl = findViewById(R.id.rl);
        btnc = findViewById(R.id.btnc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> {
            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });
        placeAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        getaddresslayout = findViewById(R.id.getaddresslayout);
        getaddresslayout.setVisibility(View.GONE);

        tvaddress = findViewById(R.id.tvaddress);
        tvaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getaddresslayout.getVisibility()==View.GONE){
                    getaddresslayout.setVisibility(View.VISIBLE);
                }else{
                    getaddresslayout.setVisibility(View.GONE);
                }
            }
        });
        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder().setCountry("SG").build();
        placeAutocompleteFragment.setFilter(autocompleteFilter);
        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                tvaddress.setText(place.getAddress().toString());
                placeAutocompleteFragment.setText("");
                getaddresslayout.setVisibility(View.GONE);

            }

            @Override
            public void onError(Status status) {
            }
        });
        bca.setOnClickListener(v -> {
            if (ete.getText().toString().isEmpty()
                    && etfn.getText().toString().isEmpty()
                    && etln.getText().toString().isEmpty()
                    && etnum.getText().toString().isEmpty()
                    && etic.getText().toString().isEmpty()
                    && etpw.getText().toString().isEmpty()
                    && etli.getText().toString().isEmpty()
                    && tvaddress.getText().toString().equals("select address...")
                    && etvl.getText().toString().isEmpty()) {

                Snackbar.make(rl, "Please fill in all fields!", Snackbar.LENGTH_LONG).show();

            } else {

                if (ctnc.isChecked()) {
                    ShowProgress();
                    Register register = new Register();
                    register.execute("http://ehostingcentre.com/gravo/collectorsignup.php");
                } else {
                    Snackbar.make(rl, "Please accept our Terms and Conditions", Snackbar.LENGTH_LONG).show();
                }

            }
        });
        btnc.setOnClickListener(v -> {
            startActivity(new Intent(this, WebView.class).putExtra("link", "http://ehostingcentre.com/gravo/content/termsandconditions.php"));
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
            HttpReq req = new HttpReq();
            return req.PostRequest(strings[0], "firstname=" + etfn.getText().toString()
                    + "&lastname=" + etln.getText().toString()
                    + "&email=" + ete.getText().toString()
                    + "&password=" + etpw.getText().toString()
                    + "&contactnumber=" + etnum.getText().toString()
                    + "&nric=" + etic.getText().toString()
                    + "&address=" + tvaddress.getText().toString()
                    + "&liscencenumber=" + etli.getText().toString()
                    + "&vehiclenumber=" + etvl.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            HideProgress();
            Log.e("ONPOSTEX SIGNUP: ", s);
            try {
                String userName = "";
                String userEmail = "";
                String userNumber = "";


                JSONObject result = new JSONObject(s);
                int status = result.getInt("status");
                if (status == 200) {
                    JSONArray getUser = result.getJSONArray("users");
                    for (int i = 0; i < getUser.length(); i++) {
                        JSONObject user = getUser.getJSONObject(i);
                        userstatus = user.getInt("status");
                    }
                    if (userstatus == 1) {
                        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sessionManager.edit();
                        editor.putString(SESSION_ID, String.valueOf(status));
                        editor.putString("name", userName);
                        editor.putString("email", userEmail);
                        editor.putString("number", userNumber);

                        editor.apply();
                        Intent itmchk = new Intent(RegisterActivity.this, MainActivity.class);
                        Intent ib = new Intent();
                        ib.putExtra("type", "1");
                        setResult(1, ib);
                        finish();
                        startActivity(itmchk);

                    } else if (userstatus == 0) {

                        AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                        LayoutInflater li = LayoutInflater.from(RegisterActivity.this);
                        final View gtnc = li.inflate(R.layout.acceptancedialog, null);
                        dialog.setCancelable(true);
                        dialog.setView(gtnc);
                        dialog.setPositiveButton("Ok", (dialogInterface, i) -> startActivity(new Intent(RegisterActivity.this, Login.class)));
                        AlertDialog dialogue = dialog.create();
                        dialogue.show();

                        //Toast.makeText(RegisterActivity.this,"You have not been approved to drive with Gravo yet! We will get back to you shortly.\n\nThank you for your patience!",Toast.LENGTH_SHORT).show();

                    } else if (userstatus == 2) {

                        AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                        LayoutInflater li = LayoutInflater.from(RegisterActivity.this);
                        final View gtnc = li.inflate(R.layout.dialog_rejecteduser, null);
                        dialog.setCancelable(true);
                        dialog.setView(gtnc);
                        dialog.setPositiveButton("I understand.", (dialogInterface, i) -> startActivity(new Intent(RegisterActivity.this, Login.class)));
                        AlertDialog dialogue = dialog.create();
                        dialogue.show();
                        //Toast.makeText(RegisterActivity.this,"Unfortunately, You do not fit the requirements to be a collector. We apologize for the inconvenience!",Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                        LayoutInflater li = LayoutInflater.from(RegisterActivity.this);
                        final View gtnc = li.inflate(R.layout.acceptancedialog, null);
                        dialog.setCancelable(true);
                        dialog.setView(gtnc);
                        dialog.setPositiveButton("Ok", (dialogInterface, i) -> startActivity(new Intent(RegisterActivity.this, Login.class)));
                        AlertDialog dialogue = dialog.create();
                        dialogue.show();
                        //Toast.makeText(RegisterActivity.this,"An unexpected error has occurred. We apologize for the inconvenience!",Toast.LENGTH_SHORT).show();
                    }
                } else if (status == 404) {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                    LayoutInflater li = LayoutInflater.from(RegisterActivity.this);
                    final View gtnc = li.inflate(R.layout.dialog_userhasregistered, null);
                    dialog.setCancelable(true);
                    dialog.setView(gtnc);
                    dialog.setPositiveButton("Log in now", (dialogInterface, i) -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();

                } else if (status == 400) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
                    LayoutInflater li = LayoutInflater.from(RegisterActivity.this);
                    final View gtnc = li.inflate(R.layout.dialog_unexpectederror, null);
                    dialog.setCancelable(true);
                    dialog.setView(gtnc);
                    dialog.setPositiveButton("I understand", (dialogInterface, i) -> startActivity(new Intent(RegisterActivity.this, Login.class)));
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();
                    //Toast.makeText(RegisterActivity.this,"An unexpected error has occurred. We apologize for the inconvenience!",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void ShowProgress() {
        llProgress.setVisibility(View.VISIBLE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressBar.getDrawable();
        progressDrawable.start();
    }

    public void HideProgress() {
        llProgress.setVisibility(View.GONE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressBar.getDrawable();
        progressDrawable.stop();
    }

}

