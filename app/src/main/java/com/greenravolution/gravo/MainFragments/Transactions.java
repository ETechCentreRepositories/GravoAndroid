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

public class Transactions extends Fragment {
    //items are used for temporary purposes. Replace with Listview.
    LinearLayout item1, item2;

    public Transactions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        item1 = view.findViewById(R.id.item1);
        item2 = view.findViewById(R.id.item2);
        item1.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivitySelectedTransaction.class).putExtra("transaction_id","TRANSACTION #10091983294823")));
        item2.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivitySelectedTransaction.class).putExtra("transaction_id","TRANSACTION #10091983294823")));

        return view;
    }

}
