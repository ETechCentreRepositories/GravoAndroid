package com.greenravolution.gravo.CategoryFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenravolution.gravo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Metals extends Fragment {


    public Metals() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_metals, container, false);
    }

}
