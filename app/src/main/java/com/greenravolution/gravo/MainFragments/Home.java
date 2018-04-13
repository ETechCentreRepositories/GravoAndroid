package com.greenravolution.gravo.MainFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivityCategories;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    CardView paper, metals, ewaste, bulk;



    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        paper = view.findViewById(R.id.paper);
        metals = view.findViewById(R.id.metals);
        ewaste = view.findViewById(R.id.ewaste);
        bulk = view.findViewById(R.id.bulk);
        paper.setOnClickListener(v->startActivity(new Intent(getContext(), ActivityCategories.class).putExtra("category", 0)));
        metals.setOnClickListener(v->startActivity(new Intent(getContext(), ActivityCategories.class).putExtra("category", 2)));
        ewaste.setOnClickListener(v->startActivity(new Intent(getContext(), ActivityCategories.class).putExtra("category", 1)));
        bulk.setOnClickListener(v->startActivity(new Intent(getContext(), ActivityCategories.class).putExtra("category", 3)));

        return view;
    }
}
