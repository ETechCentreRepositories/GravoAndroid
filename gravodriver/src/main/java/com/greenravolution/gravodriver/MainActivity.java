package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.greenravolution.gravodriver.Objects.OrderDetails;
import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.adapters.OrdersAdapter;
import com.greenravolution.gravodriver.functions.Rates;
import com.greenravolution.gravodriver.loginsignup.Login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";
    ListView orders;
    OrdersAdapter oad;
    ArrayList<Orders> oal;
    TextView collectDate, totalWeight, totalPrice;
    Rates rates = new Rates();
    android.support.v7.widget.Toolbar toolbar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        oal = new ArrayList<Orders>();
        orders = findViewById(R.id.orders);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        collectDate = findViewById(R.id.collectDate);
        totalPrice = findViewById(R.id.totalPrice);
        totalWeight = findViewById(R.id.totalWeight);

        collectDate.setText(String.format("Pickups for Today: %s", date));

        oad = new OrdersAdapter(MainActivity.this, oal);
        orders.setAdapter(oad);
        oad.notifyDataSetChanged();


    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("Exit");
        dialog.setMessage("Are you sure you want to exit?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialogue = dialog.create();

        dialogue.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Logout");
                dialog.setMessage("Are you sure you want to exit?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sessionManager.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialogue = dialog.create();

                dialogue.show();

                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.e("res: ", String.valueOf(requestCode));
            if (data != null) {
                if (data.getStringExtra("type") != null) {
                    if (Objects.equals(data.getStringExtra("type"), "0")) {
                        Log.e("type", "back button");
                    } else if (Objects.equals(data.getStringExtra("type"), "1")) {
                        finish();
                    }
                } else {
                    Log.e("type", "null");
                }
                Log.e("data", "null");
            }

        } else {
            Log.e("res: ", String.valueOf(requestCode));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();

        oal.clear();
        oal.addAll(getTransactions());
        int getTotalWeight = rates.GetTotalWeight(oal);
        int getCollectedWeight = rates.GetCollectedWeight(oal);
        double totalPrices = rates.EstimateAmountPayment(oal);
        double amountPaid = rates.EstimateAmountPaid(oal);

        totalWeight.setText(String.format("Total Weight Collected: %sKG / %sKG", String.valueOf(getCollectedWeight), String.valueOf(getTotalWeight)));
        totalPrice.setText(String.format("Estimated Amount Paid: $%s0 / $%s0", String.valueOf(amountPaid), String.valueOf(totalPrices)));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    public ArrayList<Orders> getTransactions(){
        ArrayList<Orders> orders = new ArrayList<>();
        //get list of items
        orders.add(new Orders(1, "#0020", "Pickup 1", "Blk 279 Tampines Street 22 #08-220", "520279", "10am - 12pm", 0));
        orders.add(new Orders(2, "#0021", "Pickup 2", "Blk 159 Woodlands Street 32 #06-502", "730159", "12pm - 2pm", 0));
        orders.add(new Orders(3, "#0022", "Pickup 3", "Blk 279 Tampines Street 22 #08-220", "520279", "12pm - 2pm",1));
        orders.add(new Orders(4, "#0023", "Pickup 4", "Blk 159 Woodlands Street 32 #06-502", "730159", "12pm - 2pm",0));
        orders.add(new Orders(5, "#0024", "Pickup 5", "Blk 279 Tampines Street 22 #08-220", "520279", "10am - 12pm", 0));
        orders.add(new Orders(6, "#0025", "Pickup 6", "Blk 159 Woodlands Street 32 #06-502", "S730159", "12pm - 2pm", 0));

        return orders;
    }


}
