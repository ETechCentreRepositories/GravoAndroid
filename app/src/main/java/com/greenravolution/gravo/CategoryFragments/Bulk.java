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
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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

import com.greenravolution.gravo.R;

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
        bulk_take_photo.setOnClickListener(v -> startCamera());
        bulk_image.setOnClickListener(v->bulk_take_photo.performClick());
        bulk_submit.setOnClickListener(v -> addData());
        return view;

    }

    public void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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
                        new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            startActivityForResult(intent, REQUEST_CAMERA);

        }

    }

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

    public void addData() {
        if (null != bulk_image.getDrawable()) {
            if(bulk_description.getText().toString().equals("")){
                Toast.makeText(getContext(), "Please describe your item", Toast.LENGTH_SHORT).show();
            }else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setCancelable(false);
                dialog.setTitle("Submitted");
                dialog.setMessage("Thank you for submitting!\n\nWe will get back to you shortly with a quote.");
                dialog.setPositiveButton("Done", (dialogInterface, i) -> { });
                AlertDialog dialogue = dialog.create();
                dialogue.show();
            }

            //imageview have image
        } else {
            Toast.makeText(getContext(), "Please take a photo of the item you want to recycle", Toast.LENGTH_SHORT).show();
            //imageview have no image
        }
    }
}
