package com.greenravolution.gravo.contents;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.SuccessContinuation;
import com.greenravolution.gravo.R;

import java.util.Calendar;

public class ActivityCart extends AppCompatActivity {
    Toolbar toolbar;
    Button points, cash;
    TextView scheduleDate;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        LinearLayout cartItems = findViewById(R.id.cartItems);

        points = findViewById(R.id.points);
        points.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivitySuccessfullTransaction.class));
            finish();
        });
        cash = findViewById(R.id.cash);
        cash.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivitySuccessfullTransaction.class));
            finish();
        });

        scheduleDate = findViewById(R.id.scheduleDate);
        scheduleDate.setOnClickListener(v -> getScheduleDate());

        for (int i = 0; i < 2; i++) {
            cartItems.addView(initView());
        }
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

    public View initView() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            View view;
            view = inflater.inflate(R.layout.cart_items, null);
            Button delete, plus, minus;
            TextView itemWeight;
            delete = view.findViewById(R.id.delete);
            delete.setOnClickListener(v ->{
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setCancelable(false);
                dialog.setTitle("Delete Item");
                dialog.setMessage("Delete this item?");
                dialog.setPositiveButton("yes", (dialogInterface, i) -> view.setVisibility(View.GONE));
                dialog.setNegativeButton("No", (dialogInterface,i) ->{});
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


}
