package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.Utility;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityEditUser extends AppCompatActivity {
    Toolbar toolbar;

    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    SharedPreferences sessionManager;
    TextView newName, newEmail, newAddress;
    CircleImageView newProfile;
    Button cancel, save, uploadImage;
    String getName, getImage, getAddress, getEmail;

    private String userChosenTask;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_user);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());

        newName = findViewById(R.id.newName);
        newEmail = findViewById(R.id.newEmail);
        newAddress = findViewById(R.id.newAddress);
        newProfile = findViewById(R.id.newImage);

        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        uploadImage = findViewById(R.id.uploadImage);
        cancel.setOnClickListener(v-> finish());
        uploadImage.setOnClickListener(v-> selectImage());

        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        getName = sessionManager.getString("user_name","");
        getEmail = sessionManager.getString("user_email", "");
        getAddress = sessionManager.getString("user_address", "");
        getImage = sessionManager.getString("user_image", "");

        newName.setText(getName);
        newEmail.setText(getEmail);
        newAddress.setText(getAddress);
        if(getImage.equals("")){
            newProfile.setImageDrawable(getDrawable(R.drawable.gravo_logo_black));
        }else{
            Glide.with(ActivityEditUser.this).load(getImage).into(newProfile);
        }
    }
    public void selectImage(){
        final CharSequence[] items = {"Take a photo","Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEditUser.this);
        builder.setTitle("Pick Profile Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(ActivityEditUser.this);
                if(items[item].equals("Take a photo")){
                    userChosenTask = "Take a photo";
                    if(result){
                        cameraIntent();
                    }
                }else if(items[item].equals("Choose from Library")){
                    userChosenTask = "Choose from Library";
                    if(result){
                        galleryIntent();
                    }
                }else{
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }
    public void cameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }
    public void galleryIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select An Image"),SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
