package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.Rates;
import com.greenravolution.gravo.functions.asyncGetSelectedTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivitySelectedTransaction extends AppCompatActivity {
    public static final String SESSION = "login_status";
    Toolbar toolbar;
    TextView title;
    LinearLayout detailList, progressbar;
    SharedPreferences sessionManager;
    asyncGetSelectedTransaction.OnAsyncResult getSelectedTransaction = (resultCode, message) -> {
        Log.i("ActivitySelected", message);
        HideProgress();
        try {
            JSONObject messageObject = new JSONObject(message);
            if (messageObject.getInt("status") == 200) {
                JSONArray detailsArray = messageObject.getJSONArray("detailsArray");
                JSONArray transactionArray = messageObject.getJSONArray("transactionArray");
                JSONObject transactionObject = transactionArray.getJSONObject(0);

                TextView tvTransaction = findViewById(R.id.tvTransaction);
                TextView tvDate = findViewById(R.id.date_of_collection);

                TextView tvStatus = findViewById(R.id.tvStatus);
                TextView transactionStatus = findViewById(R.id.transaction_status);
                TextView tvStatusDetails = findViewById(R.id.tvStatusDetails);

                TextView tvBillingName = findViewById(R.id.tvBillingName);
                TextView tvBillingAddress = findViewById(R.id.tvBillingAddress);
                TextView tvBillingEmail = findViewById(R.id.tvBillingEmail);
                TextView tvBillingContact = findViewById(R.id.tvBillingContact);

                TextView tvPrice = findViewById(R.id.tvPrice);
                TextView tvWeight = findViewById(R.id.tvWeight);

                Log.i("id key", transactionObject.getString("transaction_id_key"));
                tvTransaction.setText(String.format("TRANSACTION #%s", transactionObject.getString("transaction_id_key")));
                tvStatus.setText(transactionObject.getString("status_type"));
                tvDate.setText(transactionObject.getString("collection_date"));
                transactionStatus.setText(transactionObject.getString("status_type"));
                tvStatusDetails.setText(String.format("Item Scheduled to be picked up on %s", transactionObject.getString("collection_date")));

//                if(transactionObject.getString("status_id") == "1"){
//
//                } else if (transactionObject.getString("status_id") == "2"){
//
//                } else if (transactionObject.getString("status_id") == "3"){
//
//                } else if (transactionObject.getString("status_id") == "4"){
//
//                } else {
//                    tvStatusDetails.setText("N/A");
//                }

                tvBillingName.setText(transactionObject.getString("collection_user"));
                tvBillingAddress.setText(transactionObject.getString("collection_address"));
                tvBillingContact.setText(transactionObject.getString("collection_contact_number"));
                sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                tvBillingEmail.setText(sessionManager.getString("user_email", "No Email"));

                Double totalPrice = 0.0;
                for (int detail = 0; detail < detailsArray.length(); detail++) {
                    totalPrice = totalPrice + Double.parseDouble((detailsArray.getJSONObject(detail)).getString("price"));
                }
                tvPrice.setText("$" + totalPrice);


                //Populating details layout
                if (detailsArray.length() > 0) {
                    detailList = findViewById(R.id.transaction_details_list);
                    for (int detail = 0; detail < detailsArray.length(); detail++) {
                        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                        View view_selected_transaction;
                        if (inflater != null) {
                            view_selected_transaction = inflater.inflate(R.layout.selected_transaction, null);
                            ImageView ivDetailImage;
                            TextView tvDetailTitle, tvDetailPrice, tvDetailRate, tvDetailWeight;

                            ivDetailImage = view_selected_transaction.findViewById(R.id.ivImage);
                            tvDetailTitle = view_selected_transaction.findViewById(R.id.tvTitle);
                            tvDetailPrice = view_selected_transaction.findViewById(R.id.tvPrice);
                            tvDetailRate = view_selected_transaction.findViewById(R.id.tvRate);
                            tvDetailWeight = view_selected_transaction.findViewById(R.id.tvWeight);

                            JSONObject detailObject = detailsArray.getJSONObject(detail);

                            String categoryType = detailObject.getString("category_type");
                            String formattedType = categoryType.substring(0, categoryType.indexOf(" "));

                            Rates rateClass = new Rates();
                            ivDetailImage.setBackgroundColor(getResources().getColor(rateClass.getImageColour(formattedType)));
                            ivDetailImage.setImageResource(rateClass.getImage(categoryType));

                            tvDetailTitle.setText(detailObject.getString("category_type"));
                            tvDetailPrice.setText(String.format("$%s", detailObject.getString("price")));
                            tvDetailRate.setText(String.format("$%s", detailObject.getString("category_rate")));

                            if (formattedType.equals("Paper") || formattedType.equals("Metals")) {
                                tvDetailWeight.setText(String.format("%s KG", detailObject.getString("weight")));
                            } else if (formattedType.equals("E-Waste")) {
                                tvDetailWeight.setText(String.format("%s Piece(s)", detailObject.getString("weight")));
                            }
                            detailList.addView(view_selected_transaction);

                        }

                    }
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_selected_transaction);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        progressbar = findViewById(R.id.progressbar);
        title = findViewById(R.id.transaction_title);


        asyncGetSelectedTransaction getSelected = new asyncGetSelectedTransaction();
        getSelected.setOnResultListener(getSelectedTransaction);

        Bundle extras = getIntent().getExtras();
        int chosenID = extras.getInt("intChosenID", 0);
        Log.i("chosenID", chosenID + "");
        String[] paramsArray = {chosenID + ""};
        ShowProgress();
        getSelected.execute(paramsArray);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.help:
                Log.i("MainActivity", "clicked on help");
                startActivity(new Intent(this, ActivityHelp.class));
                return true;
        }
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.needhelp_menu, menu);//Menu Resource, Menu
        return true;
    }

    public void HideProgress() {
        progressbar.setVisibility(View.GONE);
    }

    public void ShowProgress() {
        progressbar.setVisibility(View.VISIBLE);
    }
}
