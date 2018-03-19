package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.greenravolution.gravodriver.functions.Rates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransactionDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView taddress, ttiming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        toolbar = findViewById(R.id.toolbar);
        taddress = findViewById(R.id.address);
        ttiming = findViewById(R.id.arrivalTime);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ib = new Intent();
                ib.putExtra("type", "0");
                setResult(1, ib);
                finish();

            }
        });

        Intent intent = getIntent();
        TextView testing = findViewById(R.id.testingJSONString);
        String jsonString = intent.getStringExtra("details");
        String title = intent.getStringExtra("transaction_id");
        String address = intent.getStringExtra("address");
        toolbar.setTitle("TRANSACTION: "+title);
        StringBuilder testingString = new StringBuilder();
        taddress.setText(address);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        ttiming.setText(String.format("Arrival Time: %s", date));

        try {
            JSONArray details = new JSONArray(jsonString);
            for(int position = 0; position < details.length(); position++){
                JSONObject detail = details.getJSONObject(position);
                int item = detail.getInt("waste_id");
                int waste_item = detail.getInt("waste_item");
                int weight = detail.getInt("weight");
                Rates getRates = new Rates();
                String price = getRates.getRates(item, waste_item, weight);
                String category = getRates.getItem(item, waste_item);
                testingString.append(category).append(" Price: $").append(price).append("0\n");
                Log.e("item_id "+item, waste_item+"");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        testing.setText(testingString.toString());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ib = new Intent();
        ib.putExtra("type", "0");
        setResult(1,ib);
        finish();
    }
}
