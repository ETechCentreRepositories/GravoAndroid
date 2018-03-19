package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.greenravolution.gravodriver.Objects.OrderDetails;
import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.adapters.DetailAdapter;
import com.greenravolution.gravodriver.adapters.OrdersAdapter;
import com.greenravolution.gravodriver.functions.Rates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.Inflater;

public class TransactionDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView taddress, ttiming;
    LinearLayout items;
    Button addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        toolbar = findViewById(R.id.toolbar);
        taddress = findViewById(R.id.address);
        ttiming = findViewById(R.id.arrivalTime);
        items = findViewById(R.id.listview);
        addItem = findViewById(R.id.addItem);
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


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ib = new Intent();
        ib.putExtra("type", "0");
        setResult(1,ib);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        TextView testing = findViewById(R.id.testingJSONString);
        String jsonString = intent.getStringExtra("details");
        String title = intent.getStringExtra("transaction_id");
        String address = intent.getStringExtra("address");
        toolbar.setTitle("TRANSACTION: "+title);

        taddress.setText(address);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        ttiming.setText(String.format("Arrival Time: %s", date));

        try {
            JSONArray details = new JSONArray(jsonString);
            for(int position = 0; position < details.length(); position++){
                final JSONObject detail = details.getJSONObject(position);
                final int item = detail.getInt("waste_id");
                final int waste_item = detail.getInt("waste_item");
                int weight = detail.getInt("weight");
                final Rates getRates = new Rates();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                double price = getRates.getRates(item, waste_item, weight);
                String category = getRates.getItem(item, waste_item);
                final DecimalFormat format = new DecimalFormat("##.00");
                double rate =getRates.getRate(item, waste_item);
                Log.e("item_id "+item, waste_item+"");

                assert inflater != null;
                final View view = inflater.inflate(R.layout.item_details, null);
                TextView getTitle = view.findViewById(R.id.getTitle);
                final TextView getPrice = view.findViewById(R.id.getPrice);
                TextView getRate = view.findViewById(R.id.getRate);
                final EditText getWeight = view.findViewById(R.id.getWeight);
                Button btnPlusWeight = view.findViewById(R.id.btnPlusWeight);
                Button btnMinusWeight = view.findViewById(R.id.btnMinusWeight);
                Button btnDelete = view.findViewById(R.id.btnDelete);


                getRate.setText(String.format("$%s0/kg", String.valueOf(rate)));
                getPrice.setText(String.format("$%s", format.format(price)));
                getTitle.setText(category);
                getWeight.setText(String.format("%s", String.valueOf(weight)));

                btnPlusWeight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int weight = Integer.parseInt(getWeight.getText().toString());
                        weight = weight + 1;
                        getWeight.setText(String.valueOf(weight));
                        Rates getRates = new Rates();
                        String price = "$"+format.format(getRates.getRates(item, waste_item, weight));
                        getPrice.setText(price);

                    }
                });
                btnMinusWeight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int weight = Integer.parseInt(getWeight.getText().toString());
                        weight = weight - 1;
                        getWeight.setText(String.valueOf(weight));
                        Rates getRates = new Rates();
                        String price = "$"+format.format(getRates.getRates(item, waste_item, weight));
                        getPrice.setText(price);

                    }
                });

                items.addView(view);

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view.setVisibility(View.GONE);
                    }
                });

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                assert inflater != null;
                View view = inflater.inflate(R.layout.item_details_edit, null);
                final EditText getWeight = view.findViewById(R.id.getWeight);
                Button btnPlusWeight = view.findViewById(R.id.btnPlusWeight);
                Button btnMinusWeight = view.findViewById(R.id.btnMinusWeight);
                Button cfm = view.findViewById(R.id.btnAdd);
                cfm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                    }
                });

                items.addView(view);

            }
        });
    }
}
