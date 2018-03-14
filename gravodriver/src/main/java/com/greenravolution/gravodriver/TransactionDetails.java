package com.greenravolution.gravodriver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.greenravolution.gravodriver.functions.Rates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TransactionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        Intent intent = getIntent();
        TextView testing = findViewById(R.id.testingJSONString);
        String jsonString = intent.getStringExtra("details");
        String testingString = "";
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
                testingString += category+" Price: $"+price+"0\n";

                Log.e("item_id "+item, waste_item+"");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        testing.setText(testingString);
    }
}
