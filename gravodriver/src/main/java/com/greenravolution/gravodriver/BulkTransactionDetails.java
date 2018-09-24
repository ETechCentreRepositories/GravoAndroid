package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.greenravolution.gravodriver.functions.GetAsyncRequest;
import com.greenravolution.gravodriver.functions.HttpReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class BulkTransactionDetails extends AppCompatActivity {
    Toolbar toolbar;
    ImageView bulkImage;
    TextView bulkAddress, bulkArrivalTime,tvdescription, collectionFee, tvTitle,tvName;
    Button btncheck;
    SharedPreferences sessionManager;
    GetAsyncRequest.OnAsyncResult asyncResult = (resultCode, message) -> {
        SharedPreferences.Editor editor = sessionManager.edit();
        editor.putString("alltransactionsObject", message);
        editor.commit();

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_transaction_details);

        bulkImage = findViewById(R.id.bulk_image);
        bulkAddress = findViewById(R.id.bulkAddress);
        bulkArrivalTime = findViewById(R.id.bulkArrivalTime);
        tvdescription = findViewById(R.id.description);
        collectionFee = findViewById(R.id.collectionFee);
        tvTitle = findViewById(R.id.title);
        tvName = findViewById(R.id.name);
        btncheck= findViewById(R.id.btnCollected);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> {
            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        getdetails gd = new getdetails();
        try {
            String getbulkitem = gd.execute(String.valueOf(id)).get();
            Log.e("Bulk items", getbulkitem);
            JSONObject result = new JSONObject(getbulkitem);
            int status = result.getInt("status");
            JSONObject object = result.getJSONArray("result").getJSONObject(0);
            if(status == 200){
                String code = object.getString("transaction_id_key");
                String description = object.getString("description");
                String collectiondate = object.getString("collection_date");
                String collectiondatetiming = object.getString("collection_date_timing");
                String address = object.getString("address");
                String status_name = object.getString("status");
                String price_quote = object.getString("price_quote");
                String full_name = object.getString("full_name");
                String image = object.getString("image");
                Glide.with(BulkTransactionDetails.this).load(image).into(bulkImage);
                tvdescription.setText(description);
                bulkAddress.setText(address);
                collectionFee.setText(price_quote);
                bulkArrivalTime.setText(collectiondate+"\n"+collectiondatetiming);
                tvTitle.setText(code);
                tvName.setText(full_name);

            }
            btncheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Collected collected = new Collected();
                    collected.execute(String.valueOf(id));
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public class getdetails extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            String link = "http://ehostingcentre.com/gravo/getbulkitems.php?type=single_item&id="+strings[0];
            return req.GetRequest(link);
        }


    }
    public class Collected extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            String link = "http://ehostingcentre.com/gravo/updatetransactionbulkcomplete.php";
            String params = "id="+strings[0];
            return req.PostRequest(link,params);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject result = new JSONObject(s);
                int status = result.getInt("status");
                if(status==200){
                    getTransactions();
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void getTransactions() {     //get list of items
        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        asyncRequest.setOnResultListener(asyncResult);
        sessionManager = getSharedPreferences("login_status", Context.MODE_PRIVATE);
        String collectorid = sessionManager.getString("id", "");
        asyncRequest.execute("http://ehostingcentre.com/gravo/gettransaction.php?type=withcollectorid&id=" + collectorid);
    }
}
