package com.greenravolution.gravo.CategoryFragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.HttpReq;
import com.greenravolution.gravo.functions.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Bulk extends Fragment {

    ImageView bulk_image;
    Button bulk_submit, bulk_take_photo;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    TextView bulk_description;
    String userChosenTask;
    LinearLayout bulklist;

    public static final String SESSION = "login_status";

    final private int REQUEST_CODE_ASK_PERMISSIONS = 1962;

    public Bulk() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bulk, container, false);
        bulk_image = view.findViewById(R.id.display_bulk_img);
        bulk_submit = view.findViewById(R.id.bulk_submit);
        bulk_take_photo = view.findViewById(R.id.takephoto);
        bulk_description = view.findViewById(R.id.bulk_description);
        bulklist = view.findViewById(R.id.bulktransactions);
        bulk_image.setVisibility(View.GONE);
        bulk_take_photo.setOnClickListener(v -> selectImage());
        bulk_image.setOnClickListener(v -> bulk_take_photo.performClick());
        bulk_submit.setOnClickListener(v -> addData());
        GetBulk getBulk = new GetBulk();
        getBulk.execute();
        return view;

    }

    public void addData() {
        if (null != bulk_image.getDrawable()) {
            if (bulk_description.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Please describe your item", Toast.LENGTH_SHORT).show();
            } else {
                Drawable getImage = bulk_image.getDrawable();
                Bitmap bitmap = ((BitmapDrawable) getImage).getBitmap();
                String encoded_image = bitmapToBase64(bitmap);
                AddBulk addBulk = new AddBulk();
                addBulk.execute(encoded_image, bulk_description.getText().toString());
// convert bitmap to drawable
            }
        } else {
            Toast.makeText(getContext(), "Please take a photo of the item you want to recycle", Toast.LENGTH_SHORT).show();
        }

    }

    private byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public void selectImage() {
        final CharSequence[] items = {"Take a photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Pick Profile Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getContext());
                if (items[item].equals("Take a photo")) {
                    userChosenTask = "Take a photo";
                    if (result) {
                        cameraIntent();
                    }
                } else if (items[item].equals("Choose from Library")) {
                    userChosenTask = "Choose from Library";
                    if (result) {
                        galleryIntent();
                    }
                } else {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void cameraIntent() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            int hasCameraPermission = getContext().checkSelfPermission(Manifest.permission.CAMERA);
            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                Log.i("Test 19/6", "Requesting permissions");
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    }

    public void galleryIntent() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            int hasCameraPermission = getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                Log.i("Test", "Requesting permissions");
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select An Image"), SELECT_FILE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("BULK: result Code", resultCode + "");
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                Log.e("BULK: request code", requestCode + "");
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                Log.e("BULK: ", requestCode + "");
                onCaptureImageResult(data);
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;

        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), data.getData());
                Log.e("BULK: bitmap:", bm.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bulk_image.setVisibility(View.VISIBLE);
        bulk_take_photo.setVisibility(View.GONE);
//                bulk_image.setImageBitmap(cameraImage);
        Glide.with(Objects.requireNonNull(getContext())).load(bitmapToByte(Objects.requireNonNull(bm))).into(bulk_image);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        assert thumbnail != null;
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("BULK: bitmap:", thumbnail.toString());
//        bulk_image.setImageBitmap(thumbnail);
        bulk_image.setVisibility(View.VISIBLE);
        bulk_take_photo.setVisibility(View.GONE);
//                bulk_image.setImageBitmap(cameraImage);
        Glide.with(Objects.requireNonNull(getContext())).load(bitmapToByte(Objects.requireNonNull(thumbnail))).into(bulk_image);
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

    public class AddBulk extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            SharedPreferences sessionManager = getContext().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            int id = sessionManager.getInt("user_id", -1);
            return req.PostRequest("http://ehostingcentre.com/gravo/addbulkitem.php", "image=" + strings[0] + "&description=" + strings[1] + "&id=" + String.valueOf(id));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            bulklist.removeAllViews();
            Log.e("Add Bulk", s);
            try {
                JSONObject results = new JSONObject(s);
                int status = results.getInt("status");
                if (status == 200) {
                    Log.e("STATUS", "Success");
                    JSONArray result = results.getJSONArray("result");
                    for(int i = 0; i < result.length();i++){
                        JSONObject item = result.getJSONObject(i);
                        int id = item.getInt("id");
                        String quote = item.getString("price_quote");
                        String link = item.getString("image");
                        String description = item.getString("description");
                        String code = item.getString("transaction_id_key");
                        bulklist.addView(initView(id,code,description,quote,link));
                    }
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setCancelable(false);
                    dialog.setTitle("Submitted");
                    dialog.setMessage("Thank you for submitting!\n\nWe will get back to you shortly with a quote.");
                    dialog.setPositiveButton("Done", (dialogInterface, i) -> {
                        bulk_image.setImageDrawable(null);
                        bulk_take_photo.setVisibility(View.VISIBLE);
                    });
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();
                } else if (status == 404) {
                    Log.e("STATUS", "No transactions");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class GetBulk extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            SharedPreferences sessionManager = getContext().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            int id = sessionManager.getInt("user_id", -1);
            return req.GetRequest(("http://ehostingcentre.com/gravo/getbulkitems.php?type=user&userid=" + String.valueOf(id)));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            bulklist.removeAllViews();
            Log.e("Add Bulk", s);
            try {
                JSONObject results = new JSONObject(s);
                int status = results.getInt("status");
                if (status == 200) {
                    Log.e("STATUS", "Success");
                    JSONArray result = results.getJSONArray("result");
                    for(int i = 0; i < result.length();i++){
                        JSONObject item = result.getJSONObject(i);
                        int id = item.getInt("id");
                        String quote = item.getString("price_quote");
                        String link = item.getString("image");
                        String description = item.getString("description");
                        String code = item.getString("transaction_id_key");
                        bulklist.addView(initView(id,code,description,quote,link));


                    }
                } else if (status == 404) {
                    Log.e("STATUS", "No transactions");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public View initView(int id, String getCode, String getDescription, String getQuote,String link) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {
            View view;
            view = inflater.inflate(R.layout.bulk_page_items, null);
            TextView code, description, price_quote;
            ImageView image;
            code = view.findViewById(R.id.code);
            description = view.findViewById(R.id.description);
            price_quote = view.findViewById(R.id.price_quote);
            image = view.findViewById(R.id.image);
            code.setText(getCode);
            description.setText(getDescription);
            price_quote.setText(getQuote);
            Glide.with(getContext()).load(link).into(image);
            return view;
        } else {
            return null;
        }
    }

}
