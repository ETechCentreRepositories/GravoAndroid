package com.greenravolution.gravodriver;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.greenravolution.gravodriver.Objects.API;
import com.greenravolution.gravodriver.functions.HttpReq;
import com.greenravolution.gravodriver.functions.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileEdit extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout progressbar,getaddresslayout;
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session_collector";
    SharedPreferences sessionManager;
    EditText newFirstName, newLastName, newPhone, newLicenseNo, newVehicleNo;
    TextView tvaddress;
    private PlaceAutocompleteFragment placeAutocompleteFragment;
    TextView newEmail;
    //CircleImageView newProfile;
    Button cancel, save;
    String getEmail, getFirstName, getLastName, getPhone, getLicenseNo, getVehicleNo, getAddress;
    String getId;
    //private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    //final private int REQUEST_CODE_ASK_PERMISSIONS = 1962018;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressbar = findViewById(R.id.progressbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        newFirstName = findViewById(R.id.first_name);
        newLastName = findViewById(R.id.last_name);
        newEmail = findViewById(R.id.newEmail);
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
        //newAddress = findViewById(R.id.newAddress);

        //newProfile = findViewById(R.id.newImage);
        newPhone = findViewById(R.id.newPhone);
        newLicenseNo = findViewById(R.id.newLicenseNo);
        newVehicleNo = findViewById(R.id.newVehicleNo);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        save.setOnClickListener(v -> {
            EditUser editUser = new EditUser();
            ShowProgress();
            editUser.execute();
        });
        //uploadImage = findViewById(R.id.uploadImage);
        cancel.setOnClickListener(v -> finish());
        //uploadImage.setOnClickListener(v -> selectImage());
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        getId = sessionManager.getString("id", "");
        Log.i("user id is ", getId + "");
        getFirstName = sessionManager.getString("firstname", "");
        getLastName = sessionManager.getString("lastname", "");
        getEmail = sessionManager.getString("email", "");

        getAddress = sessionManager.getString("address", "");
        getPhone = sessionManager.getString("number", "");
        getLicenseNo = sessionManager.getString("license","");
        getVehicleNo = sessionManager.getString("vehicle","");

        newFirstName.setText(getFirstName);
        newLastName.setText(getLastName);
        newEmail.setText(getEmail);

        newPhone.setText(getPhone);
        tvaddress.setText(getAddress);
        newLicenseNo.setText(getLicenseNo);
        newVehicleNo.setText(getVehicleNo);

        HideProgress();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Log.e("camera intent:", data.toString());
            super.onActivityResult(requestCode, resultCode, data);
            Log.e("BULK: result Code", resultCode + "");

        }
    }

    public class EditUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            API api = new API();

            return req.PostRequest(api.getEditDriver()
                    , "userid=" + getId
                            + "&firstname=" + newFirstName.getText().toString()
                            + "&lastname=" + newLastName.getText().toString()
                            + "&email=" + newEmail.getText().toString()
                            + "&contactnumber=" + newPhone.getText().toString()
                            +"&address="+tvaddress.getText().toString()
                            +"&licensenumber="+newLicenseNo.getText().toString()
                            +"&vehiclenumber="+newVehicleNo.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            HideProgress();
            Log.i("RETURN CODE = ", s);
            try {
                JSONObject result = new JSONObject(s);
                int status = result.getInt("status");
                if (status == 200) {
                    JSONArray profiles = result.getJSONArray("result");
                    JSONObject user = profiles.getJSONObject(0);
                    sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sessionManager.edit();
                    //editor.putString("user_image", user.getString("photo"));
                    editor.putString("firstname", user.getString("first_name"));
                    editor.putString("lastname", user.getString("last_name"));
                    editor.putString("name", user.getString("first_name") + " " + user.getString("last_name"));
                    editor.putString("email", user.getString("email"));
                    editor.putString("contact", user.getString("phone"));
                    editor.putString("address", user.getString("address"));

                    editor.putString("license", user.getString("liscence_number"));
                    editor.putString("vehicle", user.getString("vehicle_number"));
                    editor.putString("address",user.getString("address"));
                    editor.apply();
                    Toast.makeText(profileEdit.this, "Profile updated!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(profileEdit.this, "Unable to update details!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void HideProgress() {
        save.setEnabled(true);
        cancel.setEnabled(true);
        newFirstName.setEnabled(true);
        newLastName.setEnabled(true);
        //newAddress.setEnabled(true);
        tvaddress.setEnabled(true);
        newPhone.setEnabled(true);
        //uploadImage.setEnabled(true);
        progressbar.setVisibility(View.GONE);
    }

    public void ShowProgress() {
        save.setEnabled(false);
        cancel.setEnabled(false);
        newFirstName.setEnabled(false);
        newLastName.setEnabled(false);
        //newAddress.setEnabled(false);
        tvaddress.setEnabled(false);
        newPhone.setEnabled(false);
        //uploadImage.setEnabled(false);
        progressbar.setVisibility(View.VISIBLE);
    }
}
