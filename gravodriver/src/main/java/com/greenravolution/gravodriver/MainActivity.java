package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.adapters.OrdersAdapter;
import com.greenravolution.gravodriver.functions.Rates;
import com.greenravolution.gravodriver.loginsignup.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView orders;
    ArrayAdapter<Orders> oad;
    ArrayList<Orders> oal;
    TextView collectDate, totalWeight, totalPrice;
    Rates rates = new Rates();
    android.support.v7.widget.Toolbar toolbar;
    public static final String SESSION = "login_status";
    public static final String SESSION_ID = "session";


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

        collectDate.setText(String.format("Pickups for Today:%s", date));
        oal.add(new Orders(1, "Pickup 1", "Blk 279 Tampines Street 22 #08-220", "520279", "10am - 12pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",0));
        oal.add(new Orders(2, "Pickup 2", "Blk 159 Woodlands Street 32 #06-502", "730159", "12pm - 2pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",0));
        oal.add(new Orders(3, "Pickup 3", "Blk 279 Tampines Street 22 #08-220", "520279", "12pm - 2pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",1));
        oal.add(new Orders(4, "Pickup 4", "Blk 159 Woodlands Street 32 #06-502", "730159", "12pm - 2pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",0));
        oal.add(new Orders(5, "Pickup 5", "Blk 279 Tampines Street 22 #08-220", "520279", "10am - 12pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",0));
        oal.add(new Orders(6, "Pickup 6", "Blk 159 Woodlands Street 32 #06-502", "730159", "12pm - 2pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",0));
        oal.add(new Orders(7, "Pickup 7", "Blk 279 Tampines Street 22 #08-220", "520279", "12pm - 2pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20,}{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",0));
        oal.add(new Orders(8, "Pickup 8", "Blk 159 Woodlands Street 32 #06-502", "730159", "12pm - 2pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",1));
        oal.add(new Orders(9, "Pickup 9", "Blk 279 Tampines Street 22 #08-220", "520279", "10am - 12pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",0));
        oal.add(new Orders(10, "Pickup 10", "Blk 159 Woodlands Street 32 #06-502", "730159", "12pm - 2pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",0));
        oal.add(new Orders(11, "Pickup 11", "Blk 279 Tampines Street 22 #08-220", "520279", "12pm - 2pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",1));
        oal.add(new Orders(12, "Pickup 12 ", "Blk 159 Woodlands Street 32 #06-502", "730159", "12pm - 2pm","[{'id':1,'waste_id':1,'waste_item':1,'weight':20},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30},{'id':1,'waste_id':2,'waste_item':7,'weight':30}]",0));

        oad = new OrdersAdapter(MainActivity.this, oal);
        orders.setAdapter(oad);
        oad.notifyDataSetChanged();

        int getTotalWeight = rates.GetTotalWeight(oal);
        int getCollectedWeight = rates.GetCollectedWeight(oal);
        double totalPrices = rates.EstimateAmountPayment(oal);
        double amountPaid = rates.EstimateAmountPaid(oal);
        totalWeight.setText("Total Weight Collected: "+String.valueOf(getCollectedWeight)+"KG / "+String.valueOf(getTotalWeight)+"KG");
        totalPrice.setText("Estimated Amount Paid: $"+String.valueOf(amountPaid)+"0 / $"+String.valueOf(totalPrices)+"0");

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
                SharedPreferences sessionManager =  getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sessionManager.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }



}
