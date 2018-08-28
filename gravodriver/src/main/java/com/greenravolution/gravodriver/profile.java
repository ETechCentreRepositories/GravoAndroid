package com.greenravolution.gravodriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    Toolbar toolbar;

    TextView name, myEmail, myName, myNumber, myAddress, myLicenseNo, myVehicleNo;
    String getName, getImage, getAddress, getEmail, getNumber, getLicenseNo, getVehicleNo;
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session_collector";
    SharedPreferences sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        myName = findViewById(R.id.myName);
        myAddress = findViewById(R.id.myAddress);
        myEmail = findViewById(R.id.myEmail);
        myNumber = findViewById(R.id.myNumber);
        myLicenseNo = findViewById(R.id.myLicenseNo);
        myVehicleNo = findViewById(R.id.myVehicleNo);

        getName = sessionManager.getString("firstname", "") + " " + sessionManager.getString("lastname", "");
        getEmail = sessionManager.getString("email", "");
        getAddress = sessionManager.getString("address", "");

        getNumber = sessionManager.getString("number", "");
        getLicenseNo = sessionManager.getString("license", "");
        getVehicleNo = sessionManager.getString("vehicle", "");
        getImage = sessionManager.getString("user_image", "");

        myName.setText(getName);
        myEmail.setText(getEmail);
        myNumber.setText(getNumber);
        myLicenseNo.setText(getLicenseNo);
        myVehicleNo.setText(getVehicleNo);
        myAddress.setText(getAddress);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    public void updateprofile() {
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        getName = sessionManager.getString("firstname", "") + " " + sessionManager.getString("lastname", "");
        getEmail = sessionManager.getString("email", "");
        getNumber = sessionManager.getString("number", "");
        getLicenseNo = sessionManager.getString("license", "");
        getVehicleNo = sessionManager.getString("vehicle", "");
        getImage = sessionManager.getString("user_image", "");
        getAddress = sessionManager.getString("address", "");
        myName.setText(getName);
        myEmail.setText(getEmail);
        myNumber.setText(getNumber);
        myLicenseNo.setText(getLicenseNo);
        myVehicleNo.setText(getVehicleNo);
        myAddress.setText(getAddress);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_account_menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            // Android home
            case R.id.edit_account:
                startActivityForResult(new Intent(this, profileEdit.class), 1);
                return true;

        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateprofile();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateprofile();
    }
}
