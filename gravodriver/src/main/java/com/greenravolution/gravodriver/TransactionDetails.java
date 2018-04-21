package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
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
import android.widget.Toast;

import com.greenravolution.gravodriver.Objects.OrderDetails;
import com.greenravolution.gravodriver.functions.GetAsyncRequest;
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
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    Toolbar toolbar;
    TextView taddress, ttiming;
    LinearLayout items, llGetRecipient;
    Button btnAddRec, btnCfmNPay;
    EditText etGetName, etGetNumber;
    LinearLayout llProgress;
    ArrayList<OrderDetails> oal;
    ImageView progressbar;
    Rates getRates = new Rates();
    GetAsyncRequest.OnAsyncResult asyncResult = (resultCode, message) -> {
        llProgress.setVisibility(View.GONE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
        progressDrawable.stop();
        try {
            JSONObject object = new JSONObject(message);
            int status = object.getInt("status");
            if (status == 200) {
                JSONArray results = object.getJSONArray("result");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject detail = results.getJSONObject(i);
                    int id = detail.getInt("id");
                    String w = detail.getString("weight");
                    String p = detail.getString("price");
                    int cid = detail.getInt("category_id");
                    int tid = detail.getInt("transaction_id");

                    oal.add(new OrderDetails(id, tid, w, p, cid));
                }
            } else {
                Toast.makeText(this, "No Details", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < oal.size(); i++) {
            items.addView(initView(oal.get(i)));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        llProgress = findViewById(R.id.llProgress);
        progressbar = findViewById(R.id.progressBar);
        oal = new ArrayList<>();


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
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(v -> {
            Intent ib = new Intent();
            ib.putExtra("type", "0");
            setResult(1, ib);
            finish();
        });

        btnAddRec.setOnClickListener(v -> {
            llGetRecipient.setVisibility(View.VISIBLE);
            btnAddRec.setVisibility(View.GONE);
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

        int id = 0;
        String w = "30";
        String p = "3.00";
        int cid = 3;
        int tid = 3;

        oal.add(new OrderDetails(id, tid, w, p, cid));

        int id2 = 0;
        String w2 = "20";
        String p2 = "80.00";
        int cid2 = 14;
        int tid2 = 3;

        oal.add(new OrderDetails(id2, tid2, w2, p2, cid2));

        int id3 = 0;
        String w3 = "20";
        String p3 = "40.00";
        int cid3 = 16;
        int tid3 = 4;

        oal.add(new OrderDetails(id3, tid3, w3, p3, cid3));

        int id4 = 0;
        String w4 = "30";
        String p4 = "3.00";
        int cid4 = 3;
        int tid4 = 4;

        oal.add(new OrderDetails(id4, tid4, w4, p4, cid4));

        for (int i = 0; i < oal.size(); i++) {
            if (oal.get(i).getTransaction_id() == trans_id) {
                items.addView(initView(oal.get(i)));
            }
        }

//        getTransacionDetails(trans_id);

        btnCfmNPay.setOnClickListener(v -> {
            if (Objects.equals(etGetName.getText().toString(), "") || Objects.equals(etGetName.getText().toString(), "")) {
                Intent ib = new Intent();
                ib.putExtra("type", "1");
                ib.putExtra("transaction_id", String.valueOf(trans_id));
                setResult(1, ib);
                finish();
                //update collection user.
            } else {
                String name = etGetName.getText().toString();
                String contact = etGetNumber.getText().toString();
                //update collection
                Intent ib = new Intent();
                ib.putExtra("type", "1");
                ib.putExtra("transaction_id", trans_id);
                setResult(1, ib);
                finish();
            }
        });
        //temp
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
        asyncRequest.execute("http://greenravolution.com/API/detail.php?api_type=withID&get_id=" + id);
    }

    public View initView(OrderDetails details) {
        DecimalFormat df2 = new DecimalFormat("#.##");
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.item_details, null);

        TextView getWeight = view.findViewById(R.id.getWeight);
        TextView getPrice = view.findViewById(R.id.getPrice);
        TextView getRate = view.findViewById(R.id.getRate);
        TextView getTitle = view.findViewById(R.id.getTitle);
        ImageView itemImg = view.findViewById(R.id.getImage);
        Button plus = view.findViewById(R.id.btnPlus);
        Button minus = view.findViewById(R.id.btnMinus);
        SharedPreferences sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        final String rates = sessionManager.getString("rates", "");

        double price = Double.parseDouble(getRates.getRates(details.getCategory_id(), Integer.parseInt(details.getWeight()), rates));
        String rate = getRates.getRate(details.getCategory_id(), rates);
        String category = getRates.getItem(details.getCategory_id(), rates);
        itemImg.setBackgroundColor(getResources().getColor(getRates.getImageColour(details.getCategory_id(), rates)));
        itemImg.setImageResource(getRates.getImage(details.getCategory_id(), rates));
        getTitle.setText(category);
        getRate.setText(rate);
        getPrice.setText(String.format("$%s", df2.format(price)));
        getWeight.setText(String.valueOf(details.getWeight()));
        Log.e("Order Detail: ", details + "");
        plus.setOnClickListener(v -> {
            String getweigh = details.getWeight();
            int getweight = addWeight(Integer.parseInt(getweigh));
            details.setWeight(String.valueOf(getweight));
            double prices = Double.parseDouble((getRates.getRates(details.getCategory_id(), Integer.parseInt(details.getWeight()), rates)));
            details.setPrice(String.valueOf(prices));
            getPrice.setText(String.format("$%s", df2.format(prices)));
            getWeight.setText(String.valueOf(details.getWeight()));
            Log.e("Order Detail: ", details + "");

        });
        minus.setOnClickListener(v -> {
            String getweigh = details.getWeight();
            int getweight = minusWeight(Integer.parseInt(getweigh));
            details.setWeight(String.valueOf(getweight));
            double prices = Double.parseDouble((getRates.getRates(details.getCategory_id(), Integer.parseInt(details.getWeight()), rates)));
            details.setPrice(String.valueOf(prices));
            getPrice.setText(String.format("$%s", df2.format(prices)));
            getWeight.setText(String.valueOf(details.getWeight()));
            Log.e("Order Detail: ", details + "");
        });
        return view;
    }

    int addWeight(int i) {
        return i + 1;
    }

    int minusWeight(int i) {
        return i - 1;
    }
}
