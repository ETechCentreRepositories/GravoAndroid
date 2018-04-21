package com.greenravolution.gravo.MainFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivitySelectedTransaction;

/**
 * A simple {@link Fragment} subclass.
 */
public class Calendar extends Fragment {
    LinearLayout collect1, collect2;

    public Calendar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        collect1 = view.findViewById(R.id.collect1);
        collect2 = view.findViewById(R.id.collect2);
        collect1.setOnClickListener(v->getActivity().startActivity(new Intent(getContext(), ActivitySelectedTransaction.class)));
        collect2.setOnClickListener(v->getActivity().startActivity(new Intent(getContext(), ActivitySelectedTransaction.class)));

        return view;
    }

}
