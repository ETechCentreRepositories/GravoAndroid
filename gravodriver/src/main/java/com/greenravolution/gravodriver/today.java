package com.greenravolution.gravodriver;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.Objects.OrderDetails;
import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.adapters.FragmentPageAdapter;
import com.greenravolution.gravodriver.functions.GetAsyncRequest;
import com.greenravolution.gravodriver.functions.HttpReq;
import com.greenravolution.gravodriver.loginsignup.Login;
import com.greenravolution.gravodriver.loginsignup.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class today extends Fragment {

    public static final String SESSION = "login_status";
    LinearLayout llProgress;
    ImageView progressbar;
    SwipeRefreshLayout refreshLayout;

    ArrayList<Orders> oal;
    ArrayList<OrderDetails> odal;
    TextView collectDate;
    SharedPreferences sessionManager;
    LinearLayout list;
    android.support.v7.widget.Toolbar toolbar;

    CardView cardView;
    LinearLayout llotw, llarr, llContent;
    Button botw, barr, bmap;
    TextView tt, ta, tpc, tst,tName;
    Boolean allowRefresh = false;

    GetAsyncRequest.OnAsyncResult getRates = (resultCode, message) -> {
        try {
            sessionManager = getActivity().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            JSONObject results = new JSONObject(message);
            JSONArray rates = results.getJSONArray("result");
            Log.e("rates", rates.toString() + "\n");
            SharedPreferences.Editor editor = sessionManager.edit();
            editor.putString("rates", rates.toString());
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    };
    GetAsyncRequest.OnAsyncResult asyncResult = (resultCode, message) -> {

        llProgress.setVisibility(View.GONE);
        Log.e("GET TRANSACTION ALL: ", message);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();

        SharedPreferences.Editor editor = sessionManager.edit();
        editor.putString("alltransactionsObject",message);
        editor.commit();


        progressDrawable.stop();
        try {
            oal.clear();
            list.removeAllViews();
            odal.clear();
            Double price = 0.00;
            JSONObject object = new JSONObject(message);
            int status = object.getInt("status");

            if (status == 200) {
                refreshLayout.setRefreshing(false);
                JSONArray results = object.getJSONArray("result");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject detail = results.getJSONObject(i);
                    int id = detail.getInt("id");
                    String tc = detail.getString("transaction_id_key");
                    String cDate = detail.getString("collection_date");
                    String ad = detail.getString("collection_address");
                    String cName = detail.getString("collection_user");
                    int uid = detail.getInt("recycler_id");
                    int stid = detail.getInt("status_id");
                    String statustype = detail.getString("status_type");
                    Double totalprice = detail.getDouble("total_price");

                    price = price + totalprice;
                    Orders neworder = new Orders(id, tc, ad, uid, stid,cName);
                    if(stid !=4){
                        Calendar cal = Calendar.getInstance();
                        String todayDate = cal.get(Calendar.YEAR) + "";
                        if (cal.get(Calendar.MONTH) < 10) {
                            todayDate = todayDate + "-0" + (cal.get(Calendar.MONTH)+1);
                        } else {
                            todayDate = todayDate + "-" + (cal.get(Calendar.MONTH)+1);
                        }

                        if(cal.get(Calendar.DAY_OF_MONTH) < 10){
                            todayDate = todayDate + "-0" + cal.get(Calendar.DAY_OF_MONTH);
                        } else {
                            todayDate = todayDate + "-" + cal.get(Calendar.DAY_OF_MONTH);
                        }
                        Log.i("today's Date",todayDate + ", " + results.length());
                        if(cDate.equalsIgnoreCase(todayDate)){
                            list.addView(initview(neworder, i + 1));
                        }
                    }

                }
            } else {

                Toast.makeText(getActivity(),"Failed to load data",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    };

    public today() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);


        llProgress = view.findViewById(R.id.llProgress);
        progressbar = view.findViewById(R.id.progressBar);
        list = view.findViewById(R.id.list);


        oal = new ArrayList<>();
        odal = new ArrayList<>();
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        collectDate = view.findViewById(R.id.collectDate);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        collectDate.setText(String.format("Pickups for Today: %s", date));

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(hasNetworkConnectivity()){
                    getTransactions();
                } else {
                    refreshLayout.setRefreshing(false);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    LayoutInflater li = LayoutInflater.from(getActivity());
                    final View gtnc = li.inflate(R.layout.dialog_noconnectivity, null);
                    dialog.setCancelable(true);
                    dialog.setView(gtnc);
                    dialog.setPositiveButton("Ok", (dialogInterface, i) ->  startActivity(new Intent(getActivity(),Login.class)));
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();
                }
            }
        });

        //getLocalTransactions();
//        try {
//
//            sessionManager = getActivity().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
//            String message = sessionManager.getString("alltransactionsObject",null);
//            if(message != null){
//
//                oal.clear();
//                list.removeAllViews();
//                odal.clear();
//                Double price = 0.00;
//                JSONObject object = new JSONObject(message);
//                int status = object.getInt("status");
//                if (status == 200) {
//                    JSONArray results = object.getJSONArray("result");
//                    for (int i = 0; i < results.length(); i++) {
//                        JSONObject detail = results.getJSONObject(i);
//                        int id = detail.getInt("id");
//                        String tc = detail.getString("transaction_id_key");
//                        String cDate = detail.getString("collection_date");
//                        String ad = detail.getString("collection_address");
//                        String cName = detail.getString("collection_user");
//                        int uid = detail.getInt("recycler_id");
//                        int stid = detail.getInt("status_id");
//                        String statustype = detail.getString("status_type");
//                        Double totalprice = detail.getDouble("total_price");
//
//                        price = price + totalprice;
//                        Orders neworder = new Orders(id, tc, ad, uid, stid,cName);
//                        if(stid !=4){
//                            Calendar cal = Calendar.getInstance();
//                            String todayDate = cal.get(Calendar.YEAR) + "";
//                            if (cal.get(Calendar.MONTH) < 10) {
//                                todayDate = todayDate + "-0" + (cal.get(Calendar.MONTH)+1);
//                            } else {
//                                todayDate = todayDate + "-" + (cal.get(Calendar.MONTH)+1);
//                            }
//
//                            if(cal.get(Calendar.DAY_OF_MONTH) < 10){
//                                todayDate = todayDate + "-0" + cal.get(Calendar.DAY_OF_MONTH);
//                            } else {
//                                todayDate = todayDate + "-" + cal.get(Calendar.DAY_OF_MONTH);
//                            }
//
//                            Log.i("today's Date",todayDate + ", " + results.length());
//                            if(cDate.equalsIgnoreCase(todayDate)){
//                                list.addView(initview(neworder, i + 1));
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }



        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.e("res: ", String.valueOf(requestCode));
            if (data != null) {
                if (data.getStringExtra("type") != null) {
                    if (Objects.equals(data.getStringExtra("type"), "0")) {
                        Log.e("type", "back button");
                    } else if (Objects.equals(data.getStringExtra("type"), "1")) {
                        Log.e("type", "transaction done button");
                    }
                } else {
                    Log.e("type", "phone backpress");
                }
                Log.e("data", "null");
            }

        } else {
            Log.e("res: ", String.valueOf(requestCode));
        }
    }

    public void getLocalTransactions(){
//        llProgress.setVisibility(View.VISIBLE);
//        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
//        progressDrawable.start();

        try {

            sessionManager = getActivity().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            String message = sessionManager.getString("alltransactionsObject",null);
            if(message != null){

                oal.clear();
                list.removeAllViews();
                odal.clear();
                Double price = 0.00;
                JSONObject object = new JSONObject(message);
                int status = object.getInt("status");
                if (status == 200) {
                    JSONArray results = object.getJSONArray("result");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject detail = results.getJSONObject(i);
                        int id = detail.getInt("id");
                        String tc = detail.getString("transaction_id_key");
                        String cDate = detail.getString("collection_date");
                        String ad = detail.getString("collection_address");
                        String cName = detail.getString("collection_user");
                        int uid = detail.getInt("recycler_id");
                        int stid = detail.getInt("status_id");
                        String statustype = detail.getString("status_type");
                        Double totalprice = detail.getDouble("total_price");

                        price = price + totalprice;
                        Orders neworder = new Orders(id, tc, ad, uid, stid,cName);
                        if(stid !=4){
                            Calendar cal = Calendar.getInstance();
                            String todayDate = cal.get(Calendar.YEAR) + "";
                            if (cal.get(Calendar.MONTH) < 10) {
                                todayDate = todayDate + "-0" + (cal.get(Calendar.MONTH)+1);
                            } else {
                                todayDate = todayDate + "-" + (cal.get(Calendar.MONTH)+1);
                            }

                            if(cal.get(Calendar.DAY_OF_MONTH) < 10){
                                todayDate = todayDate + "-0" + cal.get(Calendar.DAY_OF_MONTH);
                            } else {
                                todayDate = todayDate + "-" + cal.get(Calendar.DAY_OF_MONTH);
                            }

                            Log.i("today's Date",todayDate + ", " + results.length());
                            if(cDate.equalsIgnoreCase(todayDate)){
                                list.addView(initview(neworder, i + 1));
                            }
                        }
                    }

//                    llProgress.setVisibility(View.GONE);
//                    progressDrawable.stop();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getTransactions() {     //get list of items
        llProgress.setVisibility(View.VISIBLE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
        progressDrawable.start();
        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        asyncRequest.setOnResultListener(asyncResult);
        sessionManager = getActivity().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        String collectorid = sessionManager.getString("id","");
        asyncRequest.execute("http://ehostingcentre.com/gravo/gettransaction.php?type=withcollectorid&id="+collectorid);
    }

    public static class updatetransaction extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Log.e("id", strings[0]);
            @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("h:mm a");
            String date = df.format(Calendar.getInstance().getTime());
            String reqResult = req.PostRequest("http://ehostingcentre.com/gravo/updatetransactionstatus.php", "transactionid=" + strings[0] + "&status="+strings[1]+"&arrivaltime=" + date + "");
            Log.e("reqResult", reqResult);
            try {
                JSONObject resultObject = new JSONObject(reqResult);
                if (resultObject.getInt("status") == 200) {
                    JSONArray resultArray = resultObject.getJSONArray("result");
                    JSONObject transactionObject = resultArray.getJSONObject(0);
                    String recyclerID = transactionObject.getString("recycler_id");
                    if(strings[2].equalsIgnoreCase("send")) {
                        String reqNotification = req.PostRequest("http://ehostingcentre.com/gravo/sendNotification.php", "userID=" + recyclerID);
                        Log.e("reqNotification", reqNotification);
                    }
                    JSONObject items = resultObject.getJSONArray("result").getJSONObject(0);
                    int transactionID = items.getInt("id");
                }else if(resultObject.getInt("status") == 201){
                    JSONArray resultArray = resultObject.getJSONArray("result");
                    JSONObject transactionObject = resultArray.getJSONObject(0);
                    String recyclerID = transactionObject.getString("recycler_id");
                    if(strings[2].equalsIgnoreCase("send")) {
                        String reqNotification = req.PostRequest("http://ehostingcentre.com/gravo/sendNotificationOtw.php", "userID=" + recyclerID);
                        Log.e("reqNotification", reqNotification);
                    }
                    JSONObject items = resultObject.getJSONArray("result").getJSONObject(0);
                    int transactionID = items.getInt("id");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return reqResult;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject object = new JSONObject(s);
                int status = object.getInt("status");
                if (status == 200) {
                    JSONObject items = object.getJSONArray("result").getJSONObject(0);
                    int transactionID = items.getInt("id");
                    today.UpdateStatusMessages updateStatusMessages = new today.UpdateStatusMessages();
                    updateStatusMessages.execute(String.valueOf(transactionID));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class UpdateStatusMessages extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            int transactionID = Integer.parseInt(strings[0]);
            Date d = new Date();
            HttpReq req = new HttpReq();
            CharSequence date = android.text.format.DateFormat.format("MMMM d, yyyy ", d.getTime());
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
            String strDate = mdformat.format(calendar.getTime());
            String addmessage = req.PostRequest("http://ehostingcentre.com/gravo/addtransactionhistory.php", "transactionid=" + transactionID + "&message=Driver arrived on " + date + " at " + strDate);
            Log.e("HISTORY", addmessage);
            try {
                JSONObject results = new JSONObject(addmessage);
                int statusid = results.getInt("status");
                if (statusid != 200) {
                    Log.e("ERROR", "ERROR OCCURRED");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public View initview(Orders order, int position) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.schedule_item, null);

        cardView = view.findViewById(R.id.cardView);
        tName = view.findViewById(R.id.pickupName);
        tt = view.findViewById(R.id.pickupTitle);
        ta = view.findViewById(R.id.pickupAddress);
        tpc = view.findViewById(R.id.pickupPostal);
        tst = view.findViewById(R.id.pickupTiming);
        botw = view.findViewById(R.id.botw);
        barr = view.findViewById(R.id.barr);
        bmap = view.findViewById(R.id.bmap);
        //llarr = view.view.findViewById(R.id.llarr);
        //llotw = view.view.findViewById(R.id.llotw);
        llContent = view.findViewById(R.id.llContent);


       if (order.getStatus_id() == 3) {

            botw.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_pink_round_disabled));
            botw.setEnabled(false);
            barr.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_green_round));
            barr.setEnabled(true);

            tName.setTextColor(getResources().getColor(R.color.white));
            tName.setText(order.getCollecter_name());
            String title = "Pickup " + String.valueOf(position) + " (Arrived)";
            tt.setTextColor(getResources().getColor(R.color.white));
            tt.setText(title);
            ta.setText("Address: " + order.getAddress());
            ta.setTextColor(getResources().getColor(R.color.white));
            tpc.setText(String.format("Transaction Code: %s", String.valueOf(order.getTransaction_code())));
            tpc.setTextColor(getResources().getColor(R.color.white));
//                holder.tst.setText(String.valueOf(order.getSession_id()));
//                holder.tst.setTextColor(context.getResources().getColor(R.color.white));
            cardView.setBackgroundColor(getResources().getColor(R.color.brand_pink));
            llContent.setBackgroundColor(getResources().getColor(R.color.brand_pink));

        } else if (order.getStatus_id() == 2){
            botw.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_pink_round_disabled));
            botw.setEnabled(false);
            barr.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_green_round));
            barr.setEnabled(true);

            tName.setTextColor(getResources().getColor(R.color.white));
            tName.setText(order.getCollecter_name());
            String title = "Pickup " + String.valueOf(position) + " (On The Way)";
            tt.setTextColor(getResources().getColor(R.color.white));
            tt.setText(title);
            ta.setText("Address: " + order.getAddress());
            ta.setTextColor(getResources().getColor(R.color.white));
            tpc.setText(String.format("Transaction Code: %s", String.valueOf(order.getTransaction_code())));
            tpc.setTextColor(getResources().getColor(R.color.white));
//                holder.tst.setText(String.valueOf(order.getSession_id()));
//                holder.tst.setTextColor(context.getResources().getColor(R.color.white));
            cardView.setBackgroundColor(getResources().getColor(R.color.brand_pink));
            llContent.setBackgroundColor(getResources().getColor(R.color.brand_pink));
        } else {
            //normal pickup
            tName.setText(order.getCollecter_name());
            String title = "Pickup " + String.valueOf(position);
            tt.setText(title);
            ta.setText(String.format("Address: %s", order.getAddress()));
            tpc.setText(String.format("Transaction Code: %s", String.valueOf(order.getTransaction_code())));
        }

        botw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hasNetworkConnectivity()){
                    botw.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_pink_round_disabled));
                    botw.setEnabled(false);
                    barr.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_green_round));
                    barr.setEnabled(true);

                    Orders orders = order;

                    today.updatetransaction updatetransaction = new today.updatetransaction();
                    updatetransaction.execute(String.valueOf(orders.getId()),"2","send");
                    Toast.makeText(getContext(),"clicked on otw " + position + " order id = " + order.getId() ,Toast.LENGTH_SHORT).show();

                    getTransactions();

                } else {
                    refreshLayout.setRefreshing(false);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    LayoutInflater li = LayoutInflater.from(getActivity());
                    final View gtnc = li.inflate(R.layout.dialog_noconnectivity, null);
                    dialog.setCancelable(true);
                    dialog.setView(gtnc);
                    dialog.setPositiveButton("Ok", (dialogInterface, i) ->  startActivity(new Intent(getActivity(),Login.class)));
                    AlertDialog dialogue = dialog.create();
                    dialogue.show();
                }
            }
        });

        barr.setOnClickListener(v -> {
            if(hasNetworkConnectivity()){

                Intent intent = new Intent(getContext(), TransactionDetails.class);
                Orders orders = order;
                intent.putExtra("address", orders.getAddress());
                intent.putExtra("transaction_id", orders.getTransaction_code());
                intent.putExtra("id", orders.getId());

                if(String.valueOf(orders.getStatus_id()) != null){
                    Log.i("clicked","checking for transactionCode");
                    if(orders.getStatus_id() == 2) {
                        botw.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_pink_round_disabled));
                        botw.setEnabled(false);
                        barr.setBackground(getActivity().getResources().getDrawable(R.drawable.btn_brand_green_round));
                        barr.setEnabled(true);

                        today.updatetransaction updatetransaction = new today.updatetransaction();
                        updatetransaction.execute(String.valueOf(orders.getId()), "3", "send");
                        startActivityForResult(intent, 1);

                    } else if(orders.getStatus_id() == 3){

                        today.updatetransaction updatetransaction = new today.updatetransaction();
                        updatetransaction.execute(String.valueOf(orders.getId()),"3","dontsend");
                        startActivityForResult(intent, 1);
                    } else {
                        Toast.makeText(getContext(),"An error has occured1",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(),"An error has occured2",Toast.LENGTH_SHORT).show();
                }

            } else {
                refreshLayout.setRefreshing(false);
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                LayoutInflater li = LayoutInflater.from(getActivity());
                final View gtnc = li.inflate(R.layout.dialog_noconnectivity, null);
                dialog.setCancelable(true);
                dialog.setView(gtnc);
                dialog.setPositiveButton("Ok", (dialogInterface, i) ->  startActivity(new Intent(getActivity(),Login.class)));
                AlertDialog dialogue = dialog.create();
                dialogue.show();
            }
            /// TODO: 14/3/2018 intent to transaction page add in details
//                if (order.getTransaction_type().equals("1")) {

//                } else if (order.getTransaction_type().equals("2")) {
//                    Intent intent = new Intent(context, BulkTransactionDetails.class);
//                    Orders orders = getItem(position);
//                    intent.putExtra("address", orders.getAddress());
//                    intent.putExtra("transaction_id", orders.getTransaction_code());
//                    intent.putExtra("id", orders.getId());
//
//                    ((MainActivity) context).startActivityForResult(intent, 1);
//                } else {
//                    //TODO completed page summary;
//                }

        });

        bmap.setOnClickListener(v -> {
            // Create a Uri from an intent string. Use the result to create an Intent.
            String url = "https://www.google.com/maps/dir/?api=1&destination=" + order.getAddress() + "&travelmode=driving";
            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");
            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent);
        });

        return view;
    }

    public Boolean hasNetworkConnectivity(){
        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


    @Override
    public  void onResume(){
        super.onResume();
        Log.i("Called","today onResume");
        getLocalTransactions();
    }


}
