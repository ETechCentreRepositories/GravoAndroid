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
public class history extends Fragment {

    public static final String SESSION = "login_status";
    LinearLayout llProgress;
    ImageView progressbar;
    SwipeRefreshLayout refreshLayout;

    ArrayList<Orders> oal;
    ArrayList<OrderDetails> odal;
    SharedPreferences sessionManager;
    LinearLayout list;
    android.support.v7.widget.Toolbar toolbar;

    CardView cardView;
    LinearLayout llotw, llarr, llContent;
    Button botw, barr, bmap;
    TextView tt, ta, tpc, tst,tName;

//    GetAsyncRequest.OnAsyncResult getRates = (resultCode, message) -> {
//        try {
//            sessionManager = getActivity().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
//            JSONObject results = new JSONObject(message);
//            JSONArray rates = results.getJSONArray("result");
//            Log.e("rates", rates.toString() + "\n");
//            SharedPreferences.Editor editor = sessionManager.edit();
//            editor.putString("rates", rates.toString());
//            editor.commit();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    };
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
                    String ad = detail.getString("collection_address");
                    String cName = detail.getString("collection_user");
                    int uid = detail.getInt("recycler_id");
                    int stid = detail.getInt("status_id");
                    String statustype = detail.getString("status_type");
                    Double totalprice = detail.getDouble("total_price");

                    price = price + totalprice;
                    Orders neworder = new Orders(id, tc, ad, uid, stid,cName);
                    if(stid == 4){
                        list.addView(initview(neworder, i + 1));
                    }

                }
            } else {
                refreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(),"Failed to load data",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    };

    public history() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);


        Log.i("Called","history oncreate");
        llProgress = view.findViewById(R.id.llProgress);
        progressbar = view.findViewById(R.id.progressBar);
        list = view.findViewById(R.id.list);


        oal = new ArrayList<>();
        odal = new ArrayList<>();
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");

        refreshLayout = view.findViewById(R.id.refreshLayout);

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

        getLocalTransactions();
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
//                    refreshLayout.setRefreshing(false);
//                    JSONArray results = object.getJSONArray("result");
//                    for (int i = 0; i < results.length(); i++) {
//                        JSONObject detail = results.getJSONObject(i);
//                        int id = detail.getInt("id");
//                        String tc = detail.getString("transaction_id_key");
//                        String ad = detail.getString("collection_address");
//                        String cName = detail.getString("collection_user");
//                        int uid = detail.getInt("recycler_id");
//                        int stid = detail.getInt("status_id");
//                        String statustype = detail.getString("status_type");
//                        Double totalprice = detail.getDouble("total_price");
//
//                        price = price + totalprice;
//                        Orders neworder = new Orders(id, tc, ad, uid, stid,cName);
//                        if(stid == 4){
//                            list.addView(initview(neworder, i + 1));
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
                    refreshLayout.setRefreshing(false);
                    JSONArray results = object.getJSONArray("result");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject detail = results.getJSONObject(i);
                        int id = detail.getInt("id");
                        String tc = detail.getString("transaction_id_key");
                        String ad = detail.getString("collection_address");
                        String cName = detail.getString("collection_user");
                        int uid = detail.getInt("recycler_id");
                        int stid = detail.getInt("status_id");
                        String statustype = detail.getString("status_type");
                        Double totalprice = detail.getDouble("total_price");

                        price = price + totalprice;
                        Orders neworder = new Orders(id, tc, ad, uid, stid,cName);
                        if(stid == 4){
                            list.addView(initview(neworder, i + 1));
                        }
                    }
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
        llContent = view.findViewById(R.id.llContent);


        if (order.getStatus_id() == 4) {

            botw.setVisibility(View.GONE);
            barr.setVisibility(View.GONE);
            bmap.setVisibility(View.GONE);
            tName.setTextColor(getResources().getColor(R.color.white));
            tName.setText(order.getCollecter_name());
            String title = "Pickup " + String.valueOf(position) + " (Collected)";
            tt.setTextColor(getResources().getColor(R.color.white));
            tt.setText(title);
            ta.setText("Address: " + order.getAddress());
            ta.setTextColor(getResources().getColor(R.color.white));
            tpc.setText(String.format("Transaction Code: %s", String.valueOf(order.getTransaction_code())));
            tpc.setTextColor(getResources().getColor(R.color.white));
            cardView.setBackgroundColor(getResources().getColor(R.color.grey));
            llContent.setBackgroundColor(getResources().getColor(R.color.grey));

        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasNetworkConnectivity()){
                    Intent intent = new Intent(getContext(), history_details.class);
                    Orders orders = order;
                    intent.putExtra("address", orders.getAddress());
                    intent.putExtra("transaction_id", orders.getTransaction_code());
                    intent.putExtra("id", orders.getId());
                    startActivityForResult(intent, 1);
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
        Log.i("Called","history onResume");
        getLocalTransactions();

    }
}
