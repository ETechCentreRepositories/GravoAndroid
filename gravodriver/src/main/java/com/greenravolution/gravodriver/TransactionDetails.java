package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

import com.greenravolution.gravodriver.Objects.OrderDetails;
import com.greenravolution.gravodriver.functions.Rates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class TransactionDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView taddress, ttiming;
    LinearLayout items, llGetRecipient;
    Button btnAddRec, btnCfmNPay;
    EditText etGetName, etGetNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        toolbar = findViewById(R.id.toolbar);
        taddress = findViewById(R.id.address);
        ttiming = findViewById(R.id.arrivalTime);
        items = findViewById(R.id.listview);
        llGetRecipient = findViewById(R.id.llgetRecepient);
        llGetRecipient.setVisibility(View.GONE);
        btnAddRec = findViewById(R.id.addRecipient);
        btnCfmNPay = findViewById(R.id.cfmPayment);
        etGetName = findViewById(R.id.getRecName);
        etGetNumber = findViewById(R.id.getRecContact);

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

        btnAddRec.setOnClickListener(v -> {
            llGetRecipient.setVisibility(View.VISIBLE);
            btnAddRec.setVisibility(View.GONE);
        });
        btnCfmNPay.setOnClickListener(v -> {
            if(Objects.equals(etGetName.getText().toString(), "") || Objects.equals(etGetName.getText().toString(), "")){
                finish();
                 //update collection user.
            }else{
                String name = etGetName.getText().toString();
                String contact = etGetNumber.getText().toString();
                //update collection
                finish();
            }
        });

        Intent intent = getIntent();

        String title = intent.getStringExtra("transaction_id");
        String address = intent.getStringExtra("address");
        toolbar.setTitle("TRANSACTION: " + title);
        taddress.setText(address);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        ttiming.setText(String.format("Arrival Time: %s", date));

        String detailString;

        int trans_id = intent.getIntExtra("id", -1);

        try {

            detailString = getTransacionDetails(trans_id);
            JSONArray details = new JSONArray(detailString);
            for (int position = 0; position < details.length(); position++) {
                JSONObject detail = details.getJSONObject(position);
                final int item = detail.getInt("cat_id");
                final int weight = detail.getInt("weight");
                items.addView(initView(item, weight));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    public String getTransacionDetails(int id) {
        ArrayList<OrderDetails> details = new ArrayList<>();
        ArrayList<OrderDetails> selectedDetails = new ArrayList<>();
        details.clear();
        selectedDetails.clear();
        //get details of items
        details.add(new OrderDetails(1, 1, 20, 2.20, 1));
        details.add(new OrderDetails(2, 6, 30, 6.30, 2));
        details.add(new OrderDetails(3, 1, 30, 6.00, 5));
        details.add(new OrderDetails(4, 2, 20, 2.20, 4));
        details.add(new OrderDetails(5, 3, 30, 6.30, 2));
        details.add(new OrderDetails(6, 1, 30, 6.00, 5));
        details.add(new OrderDetails(7, 2, 20, 2.20, 1));
        details.add(new OrderDetails(8, 3, 30, 6.30, 2));
        details.add(new OrderDetails(9, 6, 30, 6.00, 5));
        StringBuilder JSONString = new StringBuilder("[");

        for (int i = 0; i < details.size(); i++) {
            if (details.get(i).getTransaction_id() == id) {
                selectedDetails.add(details.get(i));
                JSONString.append(details.get(i).toString());
                JSONString.append(",");
            }
        }

        JSONString.append("]");
        Log.e("JSONString", JSONString.toString());
        return JSONString.toString();
    }

    public View initView(int getItem, int getWeighs) {
        final int item = getItem;
        final int weight = getWeighs;
        final Rates getRates = new Rates();
        double price = Double.parseDouble(getRates.getRates(item, weight));
        double rate = getRates.getRate(item);
        String category = getRates.getItem(item);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.item_details, null);
        final TextView getWeight = view.findViewById(R.id.getWeight);
        final TextView getPrice = view.findViewById(R.id.getPrice);
        final TextView getRate = view.findViewById(R.id.getRate);
        TextView getTitle = view.findViewById(R.id.getTitle);
        ImageView itemImg = view.findViewById(R.id.getImage);
        final Button plus = view.findViewById(R.id.btnPlus);
        Button minus = view.findViewById(R.id.btnMinus);
        final DecimalFormat df2 = new DecimalFormat("#.##");
        itemImg.setBackgroundColor(getResources().getColor(getRates.getImageColour(item)));
        itemImg.setImageResource(getRates.getImage(item));
        getTitle.setText(category);
        getPrice.setText(String.format("$%s", df2.format(price)));
        getRate.setText(String.format("$%s/KG", df2.format(rate)));
        getWeight.setText(String.valueOf(weight));

        plus.setOnClickListener(v -> {
                int getweight = Integer.parseInt(getWeight.getText().toString());
                getweight = getweight + 1;
                getWeight.setText(String.valueOf(getweight));
                double prices = Double.parseDouble((getRates.getRates(item, getweight)));
                getPrice.setText(String.format("$%s", df2.format(prices)));

        });

        minus.setOnClickListener(v -> {
            int getweight = Integer.parseInt(getWeight.getText().toString());
            getweight = getweight - 1;
            getWeight.setText(String.valueOf(getweight));
            double prices = Double.parseDouble((getRates.getRates(item, getweight)));
            getPrice.setText(String.format("$%s", df2.format(prices)));
        });

        return view;
    }
}
