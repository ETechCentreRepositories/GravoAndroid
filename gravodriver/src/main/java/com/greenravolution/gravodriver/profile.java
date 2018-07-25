package com.greenravolution.gravodriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    Toolbar toolbar;


    CircleImageView profile_img;
    TextView name, myEmail, myName, myNumber,myAddress, myBlock, myStreet, myUnit, myPostal, myLicenseNo, myVehicleNo;
    String getName, getImage, getAddress, getEmail, getNumber, getBlock, getStreet, getUnit, getPostal, getLicenseNo, getVehicleNo;
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        profile_img = findViewById(R.id.profile_img);
        name = findViewById(R.id.name);
        myName = findViewById(R.id.myName);
        myAddress = findViewById(R.id.myAddress);
        //myUnit = findViewById(R.id.myUnit);
       // myPostal = findViewById(R.id.myPostal);
        //myStreet = findViewById(R.id.myStreet);
        myEmail = findViewById(R.id.myEmail);
        //myBlock = findViewById(R.id.myBlock);
        myNumber = findViewById(R.id.myNumber);
        myLicenseNo = findViewById(R.id.myLicenseNo);
        myVehicleNo = findViewById(R.id.myVehicleNo);
        profile_img = findViewById(R.id.profile_img);

        getName = sessionManager.getString("firstname","") + " " + sessionManager.getString("lastname","");
        getEmail = sessionManager.getString("email", "");
        //getAddress = sessionManager.getString("address", "");
        getUnit = sessionManager.getString("unit", "");
        getStreet = sessionManager.getString("street", "");
        getPostal = sessionManager.getString("postal", "");
        getBlock = sessionManager.getString("block", "");
        getNumber = sessionManager.getString("number","");
        getLicenseNo = sessionManager.getString("license","");
        getVehicleNo = sessionManager.getString("vehicle","");
        getImage = sessionManager.getString("user_image", "");

        name.setText(getName);
        myName.setText(getName);
        myEmail.setText(getEmail);
        myNumber.setText(getNumber);
        myLicenseNo.setText(getLicenseNo);
        myVehicleNo.setText(getVehicleNo);
        myAddress.setText("Block " + getBlock + " " + getUnit + " " + getStreet + " Singapore " + getPostal);

//        myBlock.setText(getBlock);
//        myUnit.setText(getUnit);
//        myPostal.setText(getPostal);
//        myStreet.setText(getStreet);

        if(getImage.equals("")){
            profile_img.setImageDrawable(getDrawable(R.drawable.gravo_logo_black));
        }else{
            Glide.with(profile.this).load(getImage).into(profile_img);
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    public void updateprofile(){
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        getName = sessionManager.getString("firstname","") + " " + sessionManager.getString("lastname","");
        getEmail = sessionManager.getString("email", "");
        getBlock = sessionManager.getString("block", "");
        getPostal = sessionManager.getString("postal", "");
        getStreet = sessionManager.getString("street", "");
        getUnit = sessionManager.getString("unit", "");
        //getAddress = sessionManager.getString("address", "");
        getNumber = sessionManager.getString("number","");
        getLicenseNo = sessionManager.getString("license","");
        getVehicleNo = sessionManager.getString("vehicle","");
        getImage = sessionManager.getString("user_image", "");
        if(getImage.equals("")){
            profile_img.setImageDrawable(getDrawable(R.drawable.gravo_logo_black));
        }else{
            Glide.with(profile.this).load(getImage).into(profile_img);
        }
        name.setText(getName);
        myName.setText(getName);
        myEmail.setText(getEmail);
        myNumber.setText(getNumber);
        myLicenseNo.setText(getLicenseNo);
        myVehicleNo.setText(getVehicleNo);
        myAddress.setText("Block " + getBlock + " " + getUnit + " " + getStreet + " Singapore " + getPostal);
//        myBlock.setText(getBlock);
//        myStreet.setText(getStreet);
//        myPostal.setText(getPostal);
//        myUnit.setText(getUnit);
        //myAddress.setText(getAddress);
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
