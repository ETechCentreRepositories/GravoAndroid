package com.greenravolution.gravodriver;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.functions.GetAsyncRequest;
import com.greenravolution.gravodriver.functions.HttpReq;
import com.greenravolution.gravodriver.loginsignup.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class bigitems extends Fragment {
    TextView collectDate;
    LinearLayout bigItemsList;
    SharedPreferences sessionManager;
    SwipeRefreshLayout refreshLayout;

    GetAsyncRequest.OnAsyncResult asyncResult = (resultCode, message) -> {
        refreshLayout.setRefreshing(false);
        SharedPreferences.Editor editor = sessionManager.edit();
        editor.putString("alltransactionsObject", message);
        editor.commit();
        try {
            bigItemsList.removeAllViews();
            JSONObject result = new JSONObject(message);
            JSONArray bulk_items = result.getJSONArray("bulk_items");
            Log.e("Bulk array",bulk_items.toString());
            for (int i =0; i< bulk_items.length();i++){
                int status = bulk_items.getJSONObject(i).getInt("bulk_transaction_status_id");
                if(status == 1 || status == 2 || status == 7 || status == 12){
//                    bigItemsList.addView(initView(bulk_items.getJSONObject(i)));
                }else{
                    bigItemsList.addView(initView(bulk_items.getJSONObject(i)));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    };

    public bigitems() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bigitems, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        refreshLayout = view.findViewById(R.id.refreshLayout);
        collectDate = view.findViewById(R.id.collectDate);
        collectDate.setText(String.format("Pickups for Today: %s", date));
        bigItemsList = view.findViewById(R.id.bigitemslist);
        sessionManager = getContext().getSharedPreferences("login_status", Context.MODE_PRIVATE);
        String bulkItems = sessionManager.getString("alltransactionsObject","");
        try {
            JSONObject result = new JSONObject(bulkItems);
            JSONArray bulk_items = result.getJSONArray("bulk_items");
            Log.e("Bulk array",bulk_items.toString());
            for (int i =0; i< bulk_items.length();i++){
                int status = bulk_items.getJSONObject(i).getInt("bulk_transaction_status_id");
                if(status == 1 || status == 2 || status == 8 || status == 12){
//                    bigItemsList.addView(initView(bulk_items.getJSONObject(i)));
                }else{
                    bigItemsList.addView(initView(bulk_items.getJSONObject(i)));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTransactions();
            }
        });

    }
    public void getTransactions() {     //get list of items
        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        asyncRequest.setOnResultListener(asyncResult);
        sessionManager = getActivity().getSharedPreferences("login_status", Context.MODE_PRIVATE);
        String collectorid = sessionManager.getString("id", "");
        asyncRequest.execute("http://ehostingcentre.com/gravo/gettransaction.php?type=withcollectorid&id=" + collectorid);
    }
    public View initView(JSONObject j){
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bulk_list_item,null,false);
        TextView name = view.findViewById(R.id.pickupName);
        TextView title = view.findViewById(R.id.pickupTitle);
        TextView address = view.findViewById(R.id.pickupAddress);
        TextView datetime = view.findViewById(R.id.pickupDatetime);
        TextView postal = view.findViewById(R.id.pickupPostal);
        CardView cardView = view.findViewById(R.id.cardView);
        LinearLayout llContent = view.findViewById(R.id.llContent);
        Button botw = view.findViewById(R.id.botw);
        Button barr = view.findViewById(R.id.barr);
        Button bmap = view.findViewById(R.id.bmap);

        try {
            int id = j.getInt("bulk_transaction_status_id");
            if(id==5){
                botw.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_pink_round_disabled));
                botw.setEnabled(false);
                barr.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_green_round));
                barr.setEnabled(true);

                name.setTextColor(getResources().getColor(R.color.white));
                title.setTextColor(getResources().getColor(R.color.white));
                address.setTextColor(getResources().getColor(R.color.white));
                postal.setTextColor(getResources().getColor(R.color.white));
                datetime.setTextColor(getResources().getColor(R.color.white));
                cardView.setCardBackgroundColor(getResources().getColor(R.color.brand_pink));
                llContent.setBackgroundColor(getResources().getColor(R.color.brand_pink));
            }else if(id==4){
                botw.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_pink_round_disabled));
                botw.setEnabled(false);
                barr.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_green_round));
                barr.setEnabled(true);
                name.setTextColor(getResources().getColor(R.color.white));
                title.setTextColor(getResources().getColor(R.color.white));
                address.setTextColor(getResources().getColor(R.color.white));
                postal.setTextColor(getResources().getColor(R.color.white));
                datetime.setTextColor(getResources().getColor(R.color.white));
                cardView.setCardBackgroundColor(getResources().getColor(R.color.brand_pink));
                llContent.setBackgroundColor(getResources().getColor(R.color.brand_pink));
            }else if(id==6){
                botw.setVisibility(View.GONE);
                barr.setVisibility(View.GONE);
                bmap.setVisibility(View.GONE);
                name.setTextColor(getResources().getColor(R.color.white));
                title.setTextColor(getResources().getColor(R.color.white));
                address.setTextColor(getResources().getColor(R.color.white));
                postal.setTextColor(getResources().getColor(R.color.white));
                datetime.setTextColor(getResources().getColor(R.color.white));
                cardView.setCardBackgroundColor(getResources().getColor(R.color.grey));
                llContent.setBackgroundColor(getResources().getColor(R.color.grey));
            }
            name.setText(j.getString("full_name"));
            title.setText(j.getString("status"));;
            address.setText(j.getString("address"));
            postal.setText(j.getString("collection_date"));
            datetime.setText(j.getString("collection_date_timing"));
            botw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int id = j.getInt("id");
                        String updatestatus = new UpdateBulkStatusOtw().execute(String.valueOf(id)).get();
                        Log.e("status", updatestatus);
                        JSONObject result = new JSONObject(updatestatus);
                        int status = result.getInt("status");
                        if(status==200){
                            String t_code = j.getString("transaction_id_key");
                            String r_id = j.getString("recycler_id");
                            JSONObject object = new JSONObject(new SendNotificationOtw().execute(t_code, r_id).get());
                            int status2 = object.getInt("success");
                            if(status2==1){
                                getTransactions();
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
            barr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int id = j.getInt("id");
                        String updatestatus = new UpdateBulkStatusArrived().execute(String.valueOf(id)).get();
                        if(new JSONObject(updatestatus).getInt("status")==200){
                            String t_code = j.getString("transaction_id_key");
                            String r_id = j.getString("recycler_id");
                            JSONObject object = new JSONObject(new SendNotificationArrived().execute(t_code, r_id).get());
                            int status2 = object.getInt("success");
                            if(status2==1){
                                startActivity(new Intent(getContext(),BulkTransactionDetails.class).putExtra("id",id));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
            bmap.setOnClickListener(v -> {
                // Create a Uri from an intent string. Use the result to create an Intent.
                String url = null;
                try {
                    url = "https://www.google.com/maps/dir/?api=1&destination=" + j.getString("address") + "&travelmode=driving";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");
                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            });

            return view;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
    public class UpdateBulkStatusOtw extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("http://www.ehostingcentre.com/gravo/updatebulkstatusotw.php","id="+strings[0]);
        }
    }
    public class UpdateBulkStatusArrived extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("http://www.ehostingcentre.com/gravo/updatebulkstatusarrived.php","id="+strings[0]);
        }
    }
    public class SendNotificationOtw extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("http://ehostingcentre.com/gravo/sendnotificationbulkotw.php", "userID=" + strings[1] + "&transaction_code=" + strings[0]);
        }
    }
    public class SendNotificationArrived extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("http://ehostingcentre.com/gravo/sendnotificationbulkarrived.php", "userID=" + strings[1] + "&transaction_code=" + strings[0]);
        }
    }



}