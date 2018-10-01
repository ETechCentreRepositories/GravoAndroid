package com.greenravolution.gravoapp.contents;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.greenravolution.gravoapp.R;
import com.greenravolution.gravoapp.functions.HttpReq;
import com.greenravolution.gravoapp.functions.Utility;
import com.greenravolution.gravoapp.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityEditUser extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout progressbar, getaddresslayout;
    public static final String SESSION = "login_status";
    SharedPreferences sessionManager;
    EditText newFirstName, newLastName, newPhone, etFloor, etUnit;
    TextView newEmail, tvaddress;
    CircleImageView newProfile;
    Button cancel, save, uploadImage;
    String getName, getImage, getAddress, getEmail, getFirstName, getLastName, getPhone, userChosenTask;
    int getId;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    final private int REQUEST_CODE_ASK_PERMISSIONS_CAMERA = 2018;
    final private int REQUEST_CODE_ASK_PERMISSIONS_GALLERY = 2019;

    private PlaceAutocompleteFragment placeAutocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_user);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressbar = findViewById(R.id.progressbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        newFirstName = findViewById(R.id.first_name);
        newLastName = findViewById(R.id.last_name);
        newEmail = findViewById(R.id.newEmail);
        newProfile = findViewById(R.id.newImage);
        newPhone = findViewById(R.id.newPhone);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        etFloor = findViewById(R.id.etfloor);
        etUnit = findViewById(R.id.etunit);

        placeAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        getaddresslayout = findViewById(R.id.getaddresslayout);
        getaddresslayout.setVisibility(View.GONE);

        tvaddress = findViewById(R.id.tvaddress);
        tvaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getaddresslayout.getVisibility() == View.GONE) {
                    getaddresslayout.setVisibility(View.VISIBLE);
                } else {
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

        save.setOnClickListener(v -> {
            if (newFirstName.getText().toString().equals("")
                    && newLastName.getText().toString().equals("")
                    && newPhone.getText().toString().equals("")
                    && tvaddress.getText().toString().equals("Select address...")) {
                Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show();
            } else {
                EditUser editUser = new EditUser();
                ShowProgress();
                editUser.execute();
            }
        });
        uploadImage = findViewById(R.id.uploadImage);
        cancel.setOnClickListener(v -> finish());
        uploadImage.setOnClickListener(v -> selectImage());
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        getId = sessionManager.getInt("user_id", -1);
        getName = sessionManager.getString("user_name", "");
        getFirstName = sessionManager.getString("user_first_name", "");
        getLastName = sessionManager.getString("user_last_name", "");
        getEmail = sessionManager.getString("user_email", "");
        getAddress = sessionManager.getString("user_address", "");
        tvaddress.setText(getAddress);
        getImage = sessionManager.getString("user_image", "");
        getPhone = sessionManager.getString("user_contact", "");

        newFirstName.setText(getFirstName);
        newLastName.setText(getLastName);
        newEmail.setText(getEmail);
        if (sessionManager.getString("user_address_unit", "").equals("") || sessionManager.getString("user_address_unit", "").equals("-")) {
            etFloor.setText("");
            etUnit.setText("");
        } else {
            etFloor.setText(sessionManager.getString("user_address_unit", "").split("-")[0]);
            etUnit.setText(sessionManager.getString("user_address_unit", "").split("-")[1]);
        }

        newPhone.setText(getPhone);
        HideProgress();
        if (getImage.equals("")) {
            newProfile.setImageDrawable(getDrawable(R.drawable.gravo_logo_black));
        } else {
            Glide.with(ActivityEditUser.this).load(getImage).into(newProfile);
        }

    }

    public void selectImage() {
        final CharSequence[] items = {"Take a photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEditUser.this);
        builder.setTitle("Pick Profile Image");
        builder.setItems(items, (dialog, item) -> {
//            boolean result = Utility.checkPermission(ActivityEditUser.this);
            if (items[item].equals("Take a photo")) {
                userChosenTask = "Take a photo";
//                if (result) {
                    cameraIntent();
//                }
            } else if (items[item].equals("Choose from Library")) {
                userChosenTask = "Choose from Library";
//                if (result) {
                    galleryIntent();
//                }
            } else {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void cameraIntent() {
        if (Build.VERSION.SDK_INT >= 23) {
            Log.e("Permission State", ContextCompat.checkSelfPermission(ActivityEditUser.this, Manifest.permission.CAMERA) + "");

            // Here, thisActivity is the current activity
            if (ActivityCompat.checkSelfPermission(ActivityEditUser.this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(ActivityEditUser.this, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS_CAMERA);
            }
        } else {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);
        }


    }

    public void galleryIntent() {
        if (Build.VERSION.SDK_INT >= 23) {
            Log.e("Permission State", ContextCompat.checkSelfPermission(ActivityEditUser.this, Manifest.permission.READ_EXTERNAL_STORAGE) + "");

            // Here, thisActivity is the current activity
            if (ActivityCompat.checkSelfPermission(ActivityEditUser.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select An Image"), SELECT_FILE);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(ActivityEditUser.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS_GALLERY);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select An Image"), SELECT_FILE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Log.e("camera intent:", data.toString());
            super.onActivityResult(requestCode, resultCode, data);
            Log.e("BULK: result Code", resultCode + "");
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == SELECT_FILE) {
                    onSelectFromGalleryResult(data);
                } else if (requestCode == REQUEST_CAMERA) {
                    Log.e("BULK: ", requestCode + "");
                    onCaptureImageResult(data);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;

        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getContentResolver()), data.getData());
                Log.e("BULK: bitmap:", bm.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        newProfile.setImageBitmap(bm);
        UploadPhoto(bm);
        ShowProgress();
//                bulk_image.setImageBitmap(cameraImage);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        assert thumbnail != null;
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        Log.e("BULK: bitmap:", thumbnail.toString());
//        newProfile.setImageBitmap(thumbnail);
        UploadPhoto(thumbnail);
        ShowProgress();

//        bulk_image.setImageBitmap(thumbnail);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS_CAMERA:
                Log.i("permissions", permissions[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    cameraIntent();

                } else {
                    // Permission Denied
                    Toast.makeText(getBaseContext(), "Permissions Denied", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            case REQUEST_CODE_ASK_PERMISSIONS_GALLERY:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    galleryIntent();

                } else {
                    // Permission Denied
                    Toast.makeText(getBaseContext(), "Permissions Denied", Toast.LENGTH_LONG)
                            .show();
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public class EditUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            HttpReq req = new HttpReq();
            API api = new API();
            return req.PostRequest(api.getEditUser()
                    , "userid=" + getId
                            + "&firstname=" + newFirstName.getText().toString()
                            + "&lastname=" + newLastName.getText().toString()
                            + "&email=" + newEmail.getText().toString()
                            + "&contactnumber=" + newPhone.getText().toString()
                            + "&address=" + tvaddress.getText().toString()
                            + "&unit=" + etUnit.getText().toString() + "-" + etFloor.getText().toString());
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
                    editor.apply();
                    Toast.makeText(ActivityEditUser.this, "Profile updated!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(ActivityEditUser.this, "Unable to update details!", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public class EditProfilePic extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            int userid = sessionManager.getInt("user_id", -1);
            API api = new API();
            HttpReq req = new HttpReq();
            return req.PostRequest(api.getUpdateImage(), "userid=" + userid + "&encoded_string=" + strings[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            HideProgress();
            Log.e("EDIT PROFILE", String.valueOf(s));
            HideProgress();
            try {
                JSONObject result = new JSONObject(s);
                int status = result.getInt("status");
                Log.e("EDIT PHOTO STATUS", result.getString("status") + "");
                if (status == 200) {
                    JSONArray profiles = result.getJSONArray("result");
                    JSONObject user = profiles.getJSONObject(0);
                    sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sessionManager.edit();
                    editor.putString("user_image", user.getString("photo"));
                    editor.apply();
                    Glide.with(ActivityEditUser.this).load(user.getString("photo")).into(newProfile);
                    Toast.makeText(ActivityEditUser.this, "Profile Photo Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityEditUser.this, "Unable to update photo", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {

            }
        }
    }

    public void UploadPhoto(Bitmap image) {
        Bitmap icon = image;
        String encodedimage = bitmapToBase64(icon);
        Log.e("BITMAP: ", encodedimage);
        EditProfilePic editProfilePic = new EditProfilePic();
        editProfilePic.execute(encodedimage);
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

    public void HideProgress() {
        save.setEnabled(true);
        cancel.setEnabled(true);
        newFirstName.setEnabled(true);
        newLastName.setEnabled(true);

        newPhone.setEnabled(true);
        uploadImage.setEnabled(true);
        progressbar.setVisibility(View.GONE);
    }

    public void ShowProgress() {
        save.setEnabled(false);
        cancel.setEnabled(false);
        newFirstName.setEnabled(false);
        newLastName.setEnabled(false);

        newPhone.setEnabled(false);
        uploadImage.setEnabled(false);
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

        if (imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
