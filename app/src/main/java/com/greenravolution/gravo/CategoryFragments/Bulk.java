package com.greenravolution.gravo.CategoryFragments;

/*
 * Copyright (c) 2014 Rex St. John on behalf of AirPair.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import android.Manifest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivityEditUser;
import com.greenravolution.gravo.functions.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

import java.io.IOException;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Bulk extends Fragment {

    ImageView bulk_image;
    Button bulk_submit, bulk_take_photo;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    TextView bulk_description;
    Uri imageUri;
    String userChosenTask;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 1962;

    public Bulk() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bulk, container, false);

        bulk_image = view.findViewById(R.id.display_bulk_img);
        bulk_submit = view.findViewById(R.id.bulk_submit);
        bulk_take_photo = view.findViewById(R.id.takephoto);
        bulk_description = view.findViewById(R.id.bulk_description);
        bulk_image.setVisibility(View.GONE);
        bulk_take_photo.setOnClickListener(v -> selectImage());
        bulk_image.setOnClickListener(v -> bulk_take_photo.performClick());
        bulk_submit.setOnClickListener(v -> addData());
        return view;

    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CAMERA) {
//            if (data == null) {
//                Log.i("BULK ", "Image not taken");
//            } else {
//                Bitmap cameraImage = null;
//                if (data.getData() == null) {
//                    cameraImage = (Bitmap) data.getExtras().get("data");
//                } else {
//                    try {
//                        cameraImage = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                bulk_image.setVisibility(View.VISIBLE);
//                bulk_take_photo.setVisibility(View.GONE);
////                bulk_image.setImageBitmap(cameraImage);
//                Glide.with(Objects.requireNonNull(getContext())).load(bitmapToByte(Objects.requireNonNull(cameraImage))).into(bulk_image);
//
//            }
//        }
//    }


    public void addData() {
        if (null != bulk_image.getDrawable()) {
            if (bulk_description.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Please describe your item", Toast.LENGTH_SHORT).show();
            } else {
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
            }

            //imageview have image
        } else {
            Toast.makeText(getContext(), "Please take a photo of the item you want to recycle", Toast.LENGTH_SHORT).show();
            //imageview have no image
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


}
