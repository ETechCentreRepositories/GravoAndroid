package com.greenravolution.gravodriver.functions;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 14/3/2018.
 */

public class Convert {

    public Convert() {

    }

    public String GetAddressFromLocation(Context context, LatLng latLng) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;

        String retrievedAddress = null;
        try {
            address = coder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            retrievedAddress = address.get(0).getAddressLine(0).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retrievedAddress;
    }

    public LatLng GetLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng currentLocation = null;
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            currentLocation = new LatLng(location.getLatitude(),
                    location.getLongitude());
            Log.e("current Locale", String.valueOf(currentLocation));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentLocation;
    }
}
