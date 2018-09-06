package com.greenravolution.gravo.MainFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivitySelectedTransaction;
import com.greenravolution.gravo.functions.AsyncGetCompleteTransaction;
import com.greenravolution.gravo.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Transactions extends Fragment {
    //items are used for temporary purposes. Replace with Listview.
    LinearLayout transactionLayout, progressbar;
    SwipeRefreshLayout refreshLayout;
    NestedScrollView scrollView;
    AsyncGetCompleteTransaction.OnAsyncResult getDates = (int resultCode, String message) -> {
        StopLoading();
        refreshLayout.setRefreshing(false);
        try {
            LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getContext()).getSystemService(LAYOUT_INFLATER_SERVICE);
            JSONObject result = new JSONObject(message);
            int status = result.getInt("status");
            if (status == 200) {
                transactionLayout.removeAllViews();
                JSONArray getTransactionArray = result.getJSONArray("result");
                for (int i = 0; i < getTransactionArray.length(); i++) {
                    JSONObject transactionObject = getTransactionArray.getJSONObject(i);
                    String transactionID = transactionObject.getString("id");
                    String transactionDate = transactionObject.getString("collection_date");
                    String transactionIDKey = transactionObject.getString("transaction_id_key");
                    String transactionTotalPrice = transactionObject.getString("total_price");

                    String transactionTotalWeight = transactionObject.getString("total_weight");
                    String day = transactionDate.substring(transactionDate.lastIndexOf('-') + 1);
                    String month = transactionDate.substring(transactionDate.indexOf('-') + 1, transactionDate.lastIndexOf('-'));
                    String year = transactionDate.substring(0, transactionDate.indexOf('-'));

                    String fixedDate = day + " " + getmonth(month) + " " + year;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");



                    View fragmentTransaction;
                    String transactionStatus = transactionObject.getString("status_type");
                    String transactionTime = transactionObject.getString("collection_date_timing");

                    if (transactionStatus.equals("Transaction Complete")||transactionStatus.equals("Collected and Paid")||transactionStatus.equalsIgnoreCase("transaction cancelled")) {
                        assert inflater != null;
                        fragmentTransaction = inflater.inflate(R.layout.transaction_page_items_complete, null);
                    } else {
                        assert inflater != null;
                        fragmentTransaction = inflater.inflate(R.layout.transaction_page_items, null);
                    }
                    CardView item = fragmentTransaction.findViewById(R.id.cardview_item);
                    TextView tvTransactionID = fragmentTransaction.findViewById(R.id.tvTransactionID);
                    TextView tvWeight = fragmentTransaction.findViewById(R.id.tvWeight);
                    TextView tvPrice = fragmentTransaction.findViewById(R.id.tvPrice);
                    TextView tvStatus = fragmentTransaction.findViewById(R.id.tvStatus);



                    fragmentTransaction.setTag(transactionID);

                    tvTransactionID.setText(String.format("#%s", transactionIDKey)+" | "+transactionTime+",\n"+fixedDate);
                    tvWeight.setText(transactionTotalWeight);
                    tvPrice.setText(String.format("$%s", transactionTotalPrice));
                    if(transactionStatus.equalsIgnoreCase("transaction cancelled")){
                        item.setCardBackgroundColor(getResources().getColor(R.color.grey));
                    }

                    tvStatus.setText(transactionStatus.toUpperCase());

                    fragmentTransaction.setOnClickListener(v -> {
                        Intent intent = new Intent(getContext(), ActivitySelectedTransaction.class);
                        int chosenID = Integer.parseInt(fragmentTransaction.getTag().toString());
                        Log.i("getTag", chosenID + "");
                        intent.putExtra("intChosenID", chosenID);
                        startActivity(intent);

                    });
                    transactionLayout.addView(fragmentTransaction);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    };

    public Transactions() {
        // Required empty public constructor
    }

    public void StopLoading() {
        progressbar.setVisibility(View.GONE);
    }

    public void StartLoading() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        transactionLayout = view.findViewById(R.id.transactionDetails);
        progressbar = view.findViewById(R.id.progressbar);
        refreshLayout = view.findViewById(R.id.refreshlayout);
        scrollView = view.findViewById(R.id.scrollview);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTransactions();
            }
        });
        StopLoading();
        getTransactions();
        return view;
    }

    public void getTransactions() {
        SharedPreferences preferences;
        API links = new API();
        AsyncGetCompleteTransaction getTransactions = new AsyncGetCompleteTransaction();
        getTransactions.setOnResultListener(getDates);
        preferences = getActivity().getSharedPreferences("login_status", Context.MODE_PRIVATE);
        String id = String.valueOf(preferences.getInt("user_id", 0));
        String url = links.getTransaction() + "?type=userid&userid=" + id;
        StartLoading();
        getTransactions.execute(url);
    }

    public String getmonth(String month){
        if(month.equals("1")||month.equals("01")){
            return "January";
        }else if(month.equals("2")||month.equals("02")){
            return "February";
        }else if(month.equals("3")||month.equals("03")){
            return "March";
        }else if(month.equals("4")||month.equals("04")){
            return "April";
        }else if(month.equals("5")||month.equals("05")){
            return "May";
        }else if(month.equals("6")||month.equals("06")){
            return "June";
        }else if(month.equals("7")||month.equals("07")){
            return "July";
        }else if(month.equals("8")||month.equals("08")){
            return "August";
        }else if(month.equals("9")||month.equals("09")){
            return "September";
        }else if(month.equals("10")||month.equals("10")){
            return "October";
        }else if(month.equals("11")||month.equals("11")){
            return "November";
        }else if(month.equals("12")||month.equals("12")){
            return "December";
        }else{
            return "Month Unavailable";
        }
    }

}
