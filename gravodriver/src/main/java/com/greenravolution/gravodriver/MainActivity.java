package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.greenravolution.gravodriver.Objects.OrderDetails;
import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.adapters.OrdersAdapter;
import com.greenravolution.gravodriver.functions.GetAsyncRequest;
import com.greenravolution.gravodriver.functions.HttpReq;
import com.greenravolution.gravodriver.functions.Rates;
import com.greenravolution.gravodriver.loginsignup.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final String SESSION = "login_status";

    LinearLayout llProgress;
    ImageView progressbar;
    ListView orders;
    OrdersAdapter oad;
    ArrayList<Orders> oal;
    ArrayList<OrderDetails> odal;
    TextView collectDate, totalWeight, totalPrice;
    SharedPreferences sessionManager;
    Rates rates = new Rates();
    android.support.v7.widget.Toolbar toolbar;
    GetAsyncRequest.OnAsyncResult asyncResultUpdateTrans = (resultCode, message) -> {

    };
    GetAsyncRequest.OnAsyncResult getRates = (resultCode, message) -> {
        try {
            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
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

        progressDrawable.stop();
        try {
            oal.clear();
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
                    String ad = detail.getString("collection_address");
                    int uid = detail.getInt("recycler_id");
                    int stid = detail.getInt("status_id");
                    String statustype = detail.getString("status_type");
                    Double totalprice = detail.getDouble("total_price");

                    price = price + totalprice;
                    oal.add(new Orders(id, tc, ad, uid, stid));
                }
                DecimalFormat df = new DecimalFormat("####0.00");
                totalPrice.setText(String.format("Total Price: $%s", String.valueOf(df.format(price))));
                totalWeight.setText("");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        oad.notifyDataSetChanged();

    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        this.getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        llProgress = findViewById(R.id.llProgress);
        progressbar = findViewById(R.id.progressBar);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            if (getSupportActionBar().getTitle() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
        oal = new ArrayList<>();
        odal = new ArrayList<>();
        orders = findViewById(R.id.orders);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        collectDate = findViewById(R.id.collectDate);
        totalPrice = findViewById(R.id.totalPrice);
        totalWeight = findViewById(R.id.totalWeight);
        collectDate.setText(String.format("Pickups for Today: %s", date));
        oad = new OrdersAdapter(MainActivity.this, oal);
        orders.setAdapter(oad);
        oad.notifyDataSetChanged();
        // temp
        llProgress.setVisibility(View.GONE);

        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        asyncRequest.setOnResultListener(getRates);
        asyncRequest.execute("https://greenravolution.com/API/getCategories.php?type=all");

    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("Exit");
        dialog.setMessage("Are you sure you want to exit?");
        dialog.setPositiveButton("Yes", (dialogInterface, i) -> finish());
        dialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
        AlertDialog dialogue = dialog.create();

        dialogue.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Logout");
                dialog.setMessage("Are you sure you want to log out?");
                dialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                    sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sessionManager.edit();
                    editor.clear();
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                });
                dialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
                AlertDialog dialogue = dialog.create();
                dialogue.show();

                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        oal.clear();
        getTransactions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        oal.clear();
        getTransactions();

//        int id = 1;
//        String tc = "Transaction #00001";
//        String tt = "1";
//        String ad = "BLK 279 Tampines Street 22 #08-220";
//        String po = "520279";
//        int uid = 3;
//        int sid = 1;
//        int pid = 2;
//        int stid = 1;
//
//        int id2 = 2;
//        String tc2 = "Transaction #00002";
//        String tt2 = "1";
//        String ad2 = "BLK 159 Woodlands Avenue 2 #06-802";
//        String po2 = "730159";
//        int uid2 = 3;
//        int sid2 = 2;
//        int pid2 = 2;
//        int stid2 = 4;
//
//        int id3 = 3;
//        String tc3 = "Transaction #00003";
//        String tt3 = "2";
//        String ad3 = "BLK 629 senja road #20-196";
//        String po3 = "670629";
//        int uid3 = 3;
//        int sid3 = 2;
//        int pid3 = 1;
//        int stid3 = 1;
//
//        oal.add(new Orders(id, tc, tt, ad, po, uid, sid, pid, stid));
//        oal.add(new Orders(id2, tc2, tt2, ad2, po2, uid2, sid2, pid2, stid2));
//        oal.add(new Orders(id3, tc3, tt3, ad3, po3, uid3, sid3, pid3, stid3));

//        getTransactions();
        oad.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        oal.clear();
        getTransactions();
//        int id = 3;
//        String tc = "Transaction #00001";
//        String tt = "1";
//        String ad = "BLK 279 Tampines Street 22 #08-220";
//        String po = "520279";
//        int uid = 3;
//        int sid = 1;
//        int pid = 2;
//        int stid = 1;
//
//        int id2 = 4;
//        String tc2 = "Transaction #00002";
//        String tt2 = "1";
//        String ad2 = "BLK 159 Woodlands Avenue 2 #06-802";
//        String po2 = "730159";
//        int uid2 = 3;
//        int sid2 = 2;
//        int pid2 = 2;
//        int stid2 = 4;
//
//        int id3 = 5;
//        String tc3 = "Transaction #00003";
//        String tt3 = "2";
//        String ad3 = "BLK 629 senja road #20-196";
//        String po3 = "670629";
//        int uid3 = 3;
//        int sid3 = 2;
//        int pid3 = 1;
//        int stid3 = 1;
//
//        oal.add(new Orders(id, tc, tt, ad, po, uid, sid, pid, stid));
//        oal.add(new Orders(id2, tc2, tt2, ad2, po2, uid2, sid2, pid2, stid2));
//        oal.add(new Orders(id3, tc3, tt3, ad3, po3, uid3, sid3, pid3, stid3));
//        getTransactions();
        oad.notifyDataSetChanged();
    }

    public void getTransactions() {     //get list of items
        llProgress.setVisibility(View.VISIBLE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
        progressDrawable.start();
        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        asyncRequest.setOnResultListener(asyncResult);
        asyncRequest.execute("https://www.greenravolution.com/API/gettransaction.php?type=all");
    }

    public static class updatetransaction extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Log.e("id", strings[0]);
            return req.PostRequest("https://www.greenravolution.com/API/updatetransactionstatus.php", "transactionid=" + strings[0] + "&status=3");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("UPDATE TRANSACTIONS:", s + "");
        }
    }

}
