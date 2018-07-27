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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.functions.GetAsyncRequest;
import com.greenravolution.gravodriver.functions.HttpReq;
import com.greenravolution.gravodriver.functions.Rates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class history_details extends AppCompatActivity {
    public static final String SESSION = "login_status";
    Toolbar toolbar;
    TextView taddress, ttiming;
    LinearLayout items;
    LinearLayout llProgress;
    ImageView progressbar;
    TextView totalPrice, totalWeight;

    Rates getRates = new Rates();
    GetAsyncRequest.OnAsyncResult asyncResult = (resultCode, message) -> {
        StopLoading();
        try {
            SharedPreferences sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            JSONObject object = new JSONObject(message);
            Log.e("MESSAGE", message);
            int status = object.getInt("status");
            if (status == 200) {

                JSONArray results = object.getJSONArray("result");
                JSONObject transaction = results.getJSONObject(0);
                String getTotalWeight = transaction.getString("total_weight");
                String getTotalPrice = String.valueOf(transaction.getDouble("total_price"));
                totalPrice.setText(String.format("$%s", getTotalPrice));
                totalWeight.setText(getTotalWeight);
                JSONArray details = object.getJSONArray("details");
                for (int i = 0; i < details.length(); i++) {
                    JSONObject detail = details.getJSONObject(i);
                    String detail_id = String.valueOf(detail.getInt("id"));
                    String detail_item = detail.getString("item");
                    String detail_price = String.valueOf(detail.getDouble("price"));
                    String detail_weight = String.valueOf(detail.getDouble("weight"));
                    String detail_rate = detail.getString("rate");
                    String[] itemArray = {detail_item, detail_price, detail_weight, detail_rate, detail_id};
                    items.addView(initView(itemArray));
                }
                llProgress.setVisibility(View.GONE);
                AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
                progressDrawable.stop();

            } else {
                Toast.makeText(this, "No Details", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        llProgress = findViewById(R.id.llProgress);
        progressbar = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar);
        taddress = findViewById(R.id.address);
        ttiming = findViewById(R.id.arrivalTime);
        items = findViewById(R.id.listview);
        totalPrice = findViewById(R.id.totalPrice);
        totalWeight = findViewById(R.id.totalWeight);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> {

            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });
        Intent intent = getIntent();
        String title = intent.getStringExtra("transaction_id");
        String address = intent.getStringExtra("address");
        toolbar.setTitle(title);
        taddress.setText(address);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        ttiming.setText(String.format("Arrival Time: %s", date));

        int trans_id = intent.getIntExtra("id", -1);

        getTransacionDetails(trans_id);

//        getTransacionDetails(trans_id);


        llProgress.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ib = new Intent();
        ib.putExtra("type", "0");
        setResult(1, ib);
        finish();

    }

    public void getTransacionDetails(int id) {
        llProgress.setVisibility(View.VISIBLE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
        progressDrawable.start();
        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        asyncRequest.setOnResultListener(asyncResult);
        asyncRequest.execute("http://ehostingcentre.com/gravo/gettransaction.php?type=withid&transactionid=" + id);
    }

    public View initView(String[] itemArray) {
        DecimalFormat df2 = new DecimalFormat("#.##");
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.history_item_details, null);
        TextView getWeight = view.findViewById(R.id.getWeight);
        TextView getPrice = view.findViewById(R.id.getPrice);
        TextView getRate = view.findViewById(R.id.getRate);
        TextView getTitle = view.findViewById(R.id.getTitle);
        ImageView itemImg = view.findViewById(R.id.getImage);
        Double price = Double.parseDouble(itemArray[1]);
        Double weight = Double.parseDouble(itemArray[2]);
        String rate = itemArray[3];
        String category = itemArray[0];
        itemImg.setBackgroundColor(getRates.getImageColour(category));
        itemImg.setImageDrawable(getDrawable(getRates.getImage(category)));
        getTitle.setText(category);
        getRate.setText(rate);
        getPrice.setText(String.format("$%s", df2.format(price)));
        getWeight.setText(String.valueOf(weight));
        Double doubleRate = Double.parseDouble(rate.split("/")[0]);

        return view;
    }

    public void StartLoading() {
        llProgress.setVisibility(View.VISIBLE);
    }

    public void StopLoading() {
        llProgress.setVisibility(View.GONE);
    }

}
