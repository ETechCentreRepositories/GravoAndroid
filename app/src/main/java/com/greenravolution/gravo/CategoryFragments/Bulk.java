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
<<<<<<< HEAD
import android.app.Activity;
=======
import android.app.Dialog;
import android.content.ContentResolver;
>>>>>>> e3c7c8c2c14c73807a5206dfe181656259ef4348
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
import com.greenravolution.gravo.functions.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    String userChoosenTask;

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

    public void startCamera() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
           Log.e("BULK: PERMISSION", "permission granted");
            selectImage();

        }

    }

<<<<<<< HEAD
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
=======
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            if (data == null) {
                Log.i("BULK ", "Image not taken");
            } else {
                Bitmap cameraImage = null;
                if(data.getData()==null){
                    cameraImage = (Bitmap)data.getExtras().get("data");
                }else{
                    try {
                        cameraImage = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Log.d("testing", "onActivityResult: " + data);

                bulk_image.setVisibility(View.VISIBLE);
                bulk_take_photo.setVisibility(View.GONE);
                bulk_image.setImageBitmap(cameraImage);
            }
        }
    }
>>>>>>> e3c7c8c2c14c73807a5206dfe181656259ef4348

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

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo");
        builder.setItems(items, (dialog, item) -> {
            boolean result = Utility.checkPermission(getActivity());
            if (items[item].equals("Take Photo")) {
                userChoosenTask = "Take Photo";
                if (result)
                    Log.e("BULK: IMAGE TYPE:", "Take Photo");
                cameraIntent();
            } else if (items[item].equals("Choose from Library")) {
                userChoosenTask = "Choose from Library";
                if (result)
                    Log.e("BULK: IMAGE TYPE:", "Choose your Library");
                galleryIntent();
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
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


}
