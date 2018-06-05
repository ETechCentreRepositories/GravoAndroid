package com.greenravolution.gravo.contents;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.GetAsyncRequest;
import com.greenravolution.gravo.functions.HttpReq;
import com.greenravolution.gravo.functions.Rates;
import com.greenravolution.gravo.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityCart extends AppCompatActivity implements View.OnTouchListener {
    public static final String SESSION = "login_status";
    SharedPreferences preferences;
    Toolbar toolbar;
    Button points, cash;
    TextView scheduleDate;
    Boolean isPressed;
    GetAsyncRequest getcart;
    String requestParameters = "userid=";
    LinearLayout cartItems;


    private int mYear, mMonth, mDay;

    GetAsyncRequest.OnAsyncResult getRates = (resultCode, message) -> {
        try {
            JSONObject result = new JSONObject(message);
            int status = result.getInt("status");
            if(status == 404){
                TextView noitems = new TextView(getApplicationContext());
                noitems.setText("No items to recycle yet!");
                noitems.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                noitems.setPadding(00,250,0,00);
                noitems.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                noitems.setTextColor(getResources().getColor(R.color.grey));
                noitems.setTextSize(20);
                noitems.setAlpha((float) 0.50);
                cartItems.addView(noitems);

            }else if(status == 200){
                Log.e("CART DETAILS: ", message);
                for (int i = 0; i < 2; i++) {
                    cartItems.addView(initView());
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

    public View initView() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            View view;
            ArrayList<com.greenravolution.gravo.objects.Rates> ratesList = new ArrayList<>();
            view = inflater.inflate(R.layout.cart_items, null);
            Button delete, plus, minus;
            TextView itemWeight;
            SharedPreferences sessionManager = getApplication().getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            String rates = sessionManager.getString("rates", "");
            TextView getPrice = view.findViewById(R.id.getRate);

            delete = view.findViewById(R.id.delete);
            delete.setOnClickListener(v -> {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setCancelable(false);
                dialog.setTitle("Delete Item");
                dialog.setMessage("Delete this item?");
                dialog.setPositiveButton("yes", (dialogInterface, i) -> view.setVisibility(View.GONE));
                dialog.setNegativeButton("No", (dialogInterface, i) -> {
                });
                AlertDialog dialogue = dialog.create();
                dialogue.show();

            });
            itemWeight = view.findViewById(R.id.getWeight);
            plus = view.findViewById(R.id.btnPlus);
            minus = view.findViewById(R.id.btnMinus);
            minus.setOnClickListener((View v) -> {
                int getWeight = Integer.parseInt(itemWeight.getText().toString());
                if (getWeight <= 0) {
                    Log.i("Cart : ", "item is already 0");
                } else {
                    getWeight = getWeight - 1;
                    itemWeight.setText(String.valueOf(getWeight));

                }
            });
            plus.setOnClickListener((View v) -> {
                int getWeight = Integer.parseInt(itemWeight.getText().toString());
                getWeight = getWeight + 1;
                itemWeight.setText(String.valueOf(getWeight));

            });
            return view;
        }
        return null;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        API links = new API();
        GetAsyncRequest getcart = new GetAsyncRequest();
        getcart.setOnResultListener(getRates);


        preferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        String id = String.valueOf(preferences.getInt("user_id",0));
        String url = links.getCart()+requestParameters+id;
        Log.e("URL LINK: ", url);
        getcart.execute(url);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        cartItems = findViewById(R.id.cartItems);


        cash = findViewById(R.id.cash);
        cash.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivitySuccessfullTransaction.class));
            finish();
        });

        scheduleDate = findViewById(R.id.scheduleDate);
        scheduleDate.setOnClickListener(v -> getScheduleDate());

    }

    public void getScheduleDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> scheduleDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.btnPlus) {

        }
        return false;
    }

}
