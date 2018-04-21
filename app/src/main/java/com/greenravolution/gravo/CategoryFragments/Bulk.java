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

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.greenravolution.gravo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Bulk extends Fragment {
    ImageView bulk_image;
    Button bulk_submit, bulk_take_photo;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
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
        bulk_image.setVisibility(View.GONE);
        bulk_take_photo.setOnClickListener(v -> startCamera());
        return view;

    }

    public void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            if (data == null) {
                Log.i("BULK ", "Image not taken");
            } else {
                Log.i("BULK IMAGE ", data.getExtras().get("data")+"");
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                bulk_image.setVisibility(View.VISIBLE);
                bulk_image.setImageBitmap(cameraImage);
            }
        }
    }
    public void addData(){

    }
}
