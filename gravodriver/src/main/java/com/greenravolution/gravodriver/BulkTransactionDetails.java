package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BulkTransactionDetails extends AppCompatActivity {
    Toolbar toolbar;
    Button btnAccept, btnDecline;
    TextView bulkAddress, timing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_transaction_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> {
            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });
        btnAccept = findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(v->{
            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });
        btnDecline = findViewById(R.id.btnDecline);
        btnDecline.setOnClickListener(v->{
            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });
        Intent intent = getIntent();
        String title = intent.getStringExtra("transaction_id");
        String address = intent.getStringExtra("address");
        TextView toolbarTitle = findViewById(R.id.title);
        toolbarTitle.setText(title);
        bulkAddress = findViewById(R.id.bulkAddress);
        bulkAddress.setText(address);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        timing = findViewById(R.id.bulkArrivalTime);
        timing.setText(String.format("Arrival Time: %s", date));

    }
}
