package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
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
import com.greenravolution.gravodriver.functions.HttpReq;
import com.greenravolution.gravodriver.functions.PostAsyncRequest;
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
    TextView totalPrice, totalWeight;

    Rates getRates = new Rates();
    GetAsyncRequest.OnAsyncResult asyncResult = (resultCode, message) -> {
        llProgress.setVisibility(View.GONE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
        progressDrawable.stop();
        Double price = 0.00;
        try {

            SharedPreferences sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            final String rates = sessionManager.getString("rates", "");
            oal.clear();
            JSONObject object = new JSONObject(message);
            Log.e("MESSAGE", message);
            int status = object.getInt("status");
            if (status == 200) {
                JSONArray results = object.getJSONArray("result");
                for (int i = 0; i < results.length(); i++) {
                    Log.e("OAL SIZE: ", results.length() + "");
                    JSONObject detail = results.getJSONObject(i);
                    int id = detail.getInt("id");
                    Double w = detail.getDouble("weight");
                    Double p = detail.getDouble("price");
                    int cid = detail.getInt("category_id");
                    price = price + getRates.getRates(cid, w, rates);
                    int tid = detail.getInt("transaction_id");
                    oal.add(new OrderDetails(id, tid, String.valueOf(w), String.valueOf(p), cid));
                }
            } else {
                Toast.makeText(this, "No Details", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Double totalweight = 0.00;
        for (int i = 0; i < oal.size(); i++) {
            items.addView(initView(oal.get(i)));

            totalweight = totalweight + Double.parseDouble(oal.get(i).getWeight());
        }
        DecimalFormat df2 = new DecimalFormat("#.##");
        totalPrice.setText(String.format("$%s", String.valueOf(df2.format(price))));
        totalWeight.setText(String.format("%sKG", String.valueOf(totalweight)));

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


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

        getTransacionDetails(trans_id);

//        getTransacionDetails(trans_id);

        btnCfmNPay.setOnClickListener(v -> {
            if (Objects.equals(etGetName.getText().toString(), "") || Objects.equals(etGetName.getText().toString(), "")) {
                Intent ib = new Intent();
                ib.putExtra("type", "1");
                ib.putExtra("transaction_id", String.valueOf(trans_id));
                setResult(1, ib);
                UpdateTransactionStatus updateTransactionStatus = new UpdateTransactionStatus();
                Intent intent1 = getIntent();
                int transactionid = intent1.getIntExtra("id", -1);

                updateTransactionStatus.execute(String.valueOf(transactionid));
                finish();

                //update collection user.
            } else {
                String name = etGetName.getText().toString();
                String contact = etGetNumber.getText().toString();
                UpdateTransactionDetails updateTransactionDetails = new UpdateTransactionDetails();
                Intent intent1 = getIntent();
                int transactionid = intent1.getIntExtra("id", -1);
                updateTransactionDetails.execute(String.valueOf(transactionid), name, contact);
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
        oal.clear();
        llProgress.setVisibility(View.VISIBLE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
        progressDrawable.start();
        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        asyncRequest.setOnResultListener(asyncResult);
        asyncRequest.execute("https://greenravolution.com/API/gettransactiondetails.php?transactionid=" + id);
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

        Double price = Double.parseDouble(getRates.getRates(details.getCategory_id(), Double.parseDouble(details.getWeight()), rates) + "0");
        String rate = getRates.getRate(details.getCategory_id(), rates);
        String category = getRates.getItem(details.getCategory_id(), rates);
        itemImg.setBackgroundColor(getResources().getColor(getRates.getImageColour(details.getCategory_id(), rates)));
        itemImg.setImageDrawable(getResources().getDrawable(getRates.getImage(details.getCategory_id(), rates)));
        getTitle.setText(category);
        getRate.setText(rate);
        getPrice.setText(String.format("$%s", df2.format(price)));
        getWeight.setText(String.valueOf(details.getWeight()));
        Log.e("Order Detail: ", details + "");

        plus.setOnClickListener(v -> {
            String getweigh = details.getWeight();
            Double getweight = addWeight(Double.parseDouble(getweigh));
            details.setWeight(String.valueOf(getweight));
            double prices = Double.parseDouble(String.valueOf((getRates.getRates(details.getCategory_id(), Double.parseDouble(details.getWeight()), rates))));
            details.setPrice(String.valueOf(prices));
            getPrice.setText(String.format("$%s", df2.format(prices)));
            getWeight.setText(String.valueOf(details.getWeight()));
            Log.e("Order Detail: ", details + "");
            UpdateDetails updateDetails = new UpdateDetails();
            Log.e("id", details.getId() + "");
            updateDetails.execute(String.valueOf(details.getId()), String.valueOf(getweight), "0.00");


        });
        minus.setOnClickListener(v -> {
            String getweigh = details.getWeight();
            Double getweight = minusWeight(Double.parseDouble(getweigh));
            details.setWeight(String.valueOf(getweight));
            Double prices = Double.parseDouble(String.valueOf((getRates.getRates(details.getCategory_id(), Double.parseDouble(details.getWeight()), rates))));
            details.setPrice(String.valueOf(prices));
            getPrice.setText(String.format("$%s", df2.format(prices)));
            getWeight.setText(String.valueOf(details.getWeight()));
            Log.e("Order Detail: ", details + "");
            UpdateDetails updateDetails = new UpdateDetails();
            Log.e("id", details.getId() + "");
            updateDetails.execute(String.valueOf(details.getId()), String.valueOf(getweight), "0.00");

        });
        return view;
    }

    Double addWeight(Double i) {
        return i + 1.00;
    }

    Double minusWeight(Double i) {
        return i - 1.00;
    }

    public class UpdateDetails extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("https://greenravolution.com/API/updatetransactiondetails.php", "transactiondetailid=" + Integer.parseInt(strings[0]) + "&weight=" + strings[1] + "&price=" + strings[2]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("UPDATE DETAILS RESULT:", s);
        }
    }

    public class UpdateTransactionDetails extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Log.e("id", strings[0]);
            return req.PostRequest("https://www.greenravolution.com/API/updatetransactionuserandcontact.php", "transactionid=" + strings[0] + "&user=" + strings[1] + "&number=" + strings[2] + "&status=4");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("UPDATE TRANSACTIONS:", s + "");
        }
    }

    public class UpdateTransactionStatus extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Log.e("id", strings[0]);
            return req.PostRequest("https://www.greenravolution.com/API/updatetransactionstatus.php"
                    , "transactionid=" + strings[0] + "&status=4");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("UPDATE TRANSACTIONS:", s + "");
        }
    }
}
