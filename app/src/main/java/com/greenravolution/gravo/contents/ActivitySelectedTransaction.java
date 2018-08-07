package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
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

    ImageView ivDetailImage;
    TextView tvDetailTitle, tvDetailPrice, tvDetailRate, tvDetailWeight;

    asyncGetSelectedTransaction.OnAsyncResult getSelectedTransaction = (resultCode, message) -> {
        Log.i("ActivitySelected", message);
        HideProgress();
        try {
            JSONObject messageObject = new JSONObject(message);
            if (messageObject.getInt("status") == 200) {
                JSONArray detailsArray = messageObject.getJSONArray("detailsArray");
                JSONArray transactionArray = messageObject.getJSONArray("transactionArray");
                JSONArray transactionHistory = messageObject.getJSONArray("transactionHistory");
                JSONObject transactionObject = transactionArray.getJSONObject(0);



                TextView tvTransaction = findViewById(R.id.tvTransaction);
                TextView tvDate = findViewById(R.id.date_of_collection);
                TextView tvRemarks = findViewById(R.id.tvRemarks);

                TextView tvStatus = findViewById(R.id.tvStatus);
                TextView tvStatusDetails = findViewById(R.id.tvStatusDetails);

                TextView tvBillingName = findViewById(R.id.tvBillingName);
                TextView tvBillingAddress = findViewById(R.id.tvBillingAddress);
                TextView tvBillingEmail = findViewById(R.id.tvBillingEmail);
                TextView tvBillingContact = findViewById(R.id.tvBillingContact);

                TextView tvPrice = findViewById(R.id.tvPrice);
                TextView tvWeight = findViewById(R.id.tvWeight);

                StringBuilder statuses = new StringBuilder();
                if(transactionHistory!=null){
                    for(int i = 0; i < transactionHistory.length(); i ++){
                        JSONObject getstatus = transactionHistory.getJSONObject(i);
                        statuses.append(getstatus.getString("status")).append("\n\n");
                    }
                    tvStatusDetails.setText(statuses.toString());
                }else{
                    tvStatusDetails.setText(String.format("Item Scheduled to be picked up on %s", transactionObject.getString("collection_date")));
                }

                Log.i("id key", transactionObject.getString("transaction_id_key"));
                tvTransaction.setText(String.format("TRANSACTION #%s", transactionObject.getString("transaction_id_key")));
                tvStatus.setText(transactionObject.getString("status_type"));
                tvDate.setText(dateformattodate(transactionObject.getString("collection_date"))+" ("+transactionObject.getString("collection_date_timing")+")");

                if (transactionObject.getString("status_id").equals("1")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    setStatusStepper("1");

                } else if (transactionObject.getString("status_id").equals("2")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    setStatusStepper("2");

                } else if (transactionObject.getString("status_id").equals("3")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    setStatusStepper("3");

                } else if (transactionObject.getString("status_id").equals("4")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    setStatusStepper("4");

                } else {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    setStatusStepper("null");
                }

                tvBillingName.setText(transactionObject.getString("collection_user"));
                if(transactionObject.getString("remarks").equals("")){
                    tvRemarks.setText(" - No Remarks - ");
                }else{
                    tvRemarks.setText(transactionObject.getString("remarks"));
                }

                tvBillingAddress.setText(transactionObject.getString("collection_address"));
                tvBillingContact.setText(transactionObject.getString("collection_contact_number"));
                sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                tvBillingEmail.setText(sessionManager.getString("user_email", "No Email"));

                Double totalPrice = 0.0;
                for (int detail = 0; detail < detailsArray.length(); detail++) {
                    totalPrice = totalPrice + Double.parseDouble((detailsArray.getJSONObject(detail)).getString("price"));
                }
                Log.e("TOTAL PRICE TRANSACTION", totalPrice.toString());
                tvPrice.setText(String.format("$%s", totalPrice));
                tvWeight.setText(transactionObject.getString("total_weight"));

                //Populating details layout
                if (detailsArray.length() > 0) {
                    detailList = findViewById(R.id.transaction_details_list);
                    for (int detail = 0; detail < detailsArray.length(); detail++) {
                        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                        View view_selected_transaction;
                        if (inflater != null) {
                            view_selected_transaction = inflater.inflate(R.layout.selected_transaction, null);

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

                            tvDetailPrice.setText(String.format("$%s", detailObject.getString("price")));
                            tvDetailRate.setText(String.format("$%s", detailObject.getString("category_rate")));

                            if (formattedType.equals("Paper") || formattedType.equals("Metals")) {
                                if(formattedType.equals("Paper")){
                                    tvDetailTitle.setText(detailObject.getString("category_type")+"\n");
                                }else{
                                    tvDetailTitle.setText(detailObject.getString("category_type").split("-")[0]+"\n"+detailObject.getString("category_type").split("-")[1]);
                                }
                                tvDetailWeight.setText(String.format("%s KG", detailObject.getString("weight")));

                            } else if (formattedType.equals("E-Waste")) {
                                tvDetailWeight.setText(String.format("%s Piece(s)", detailObject.getString("weight")));
                                tvDetailTitle.setText(detailObject.getString("category_type")+"\n");
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

    public void setStatusStepper(String status) {
        ImageView circle1, circle2, circle3, circle4, line1, line2, line3;
        circle1 = findViewById(R.id.circle1);
        circle2 = findViewById(R.id.circle2);
        circle3 = findViewById(R.id.circle3);
        circle4 = findViewById(R.id.circle4);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        switch (status) {
            case "1":
                Log.e("function status", "1");
                circle1.setImageResource(R.drawable.stepper_circle_pink);
                circle2.setImageResource(R.drawable.stepper_circle_grey);
                circle3.setImageResource(R.drawable.stepper_circle_grey);
                circle4.setImageResource(R.drawable.stepper_circle_grey);
                line1.setImageResource(R.drawable.stepper_line_pink);
                line2.setImageResource(R.drawable.stepper_line_grey);
                line3.setImageResource(R.drawable.stepper_line_grey);
                break;
            case "2":
                Log.e("function status", "2");
                circle1.setImageResource(R.drawable.stepper_circle_pink);
                circle2.setImageResource(R.drawable.stepper_circle_pink);
                circle3.setImageResource(R.drawable.stepper_circle_grey);
                circle4.setImageResource(R.drawable.stepper_circle_grey);
                line1.setImageResource(R.drawable.stepper_line_pink);
                line2.setImageResource(R.drawable.stepper_line_pink);
                line3.setImageResource(R.drawable.stepper_line_grey);
                break;
            case "3":
                Log.e("function status", "3");
                circle1.setImageResource(R.drawable.stepper_circle_pink);
                circle2.setImageResource(R.drawable.stepper_circle_pink);
                circle3.setImageResource(R.drawable.stepper_circle_pink);
                circle4.setImageResource(R.drawable.stepper_circle_grey);
                line1.setImageResource(R.drawable.stepper_line_pink);
                line2.setImageResource(R.drawable.stepper_line_pink);
                line3.setImageResource(R.drawable.stepper_line_pink);
                break;
            case "4":
                Log.e("function status", "4");
                circle1.setImageResource(R.drawable.stepper_circle_pink);
                circle2.setImageResource(R.drawable.stepper_circle_pink);
                circle3.setImageResource(R.drawable.stepper_circle_pink);
                circle4.setImageResource(R.drawable.stepper_circle_pink);
                line1.setImageResource(R.drawable.stepper_line_pink);
                line2.setImageResource(R.drawable.stepper_line_pink);
                line3.setImageResource(R.drawable.stepper_line_pink);
                break;
            case "null":
                Log.e("function status", "null");
                circle1.setImageResource(R.drawable.stepper_circle_grey);
                circle2.setImageResource(R.drawable.stepper_circle_grey);
                circle3.setImageResource(R.drawable.stepper_circle_grey);
                circle4.setImageResource(R.drawable.stepper_circle_grey);
                line1.setImageResource(R.drawable.stepper_line_grey);
                line2.setImageResource(R.drawable.stepper_line_grey);
                line3.setImageResource(R.drawable.stepper_line_grey);
                break;
            default:
                Log.e("function status", "default");
                circle1.setImageResource(R.drawable.stepper_circle_grey);
                circle2.setImageResource(R.drawable.stepper_circle_grey);
                circle3.setImageResource(R.drawable.stepper_circle_grey);
                circle4.setImageResource(R.drawable.stepper_circle_grey);
                line1.setImageResource(R.drawable.stepper_line_grey);
                line2.setImageResource(R.drawable.stepper_line_grey);
                line3.setImageResource(R.drawable.stepper_line_grey);
                break;

        }
    }

    public String dateformattodate(String date){
        String[] datesplit = date.split("-");
        if(datesplit[1].equals("01")){
            return datesplit[2]+" January "+datesplit[0];
        }else if (datesplit[1].equals("02")){
            return datesplit[2]+" February "+datesplit[0];
        }else if (datesplit[1].equals("03")){
            return datesplit[2]+" March "+datesplit[0];
        }else if (datesplit[1].equals("04")){
            return datesplit[2]+" April "+datesplit[0];
        }else if (datesplit[1].equals("05")){
            return datesplit[2]+" May "+datesplit[0];
        }else if (datesplit[1].equals("06")){
            return datesplit[2]+" June "+datesplit[0];
        }else if (datesplit[1].equals("07")){
            return datesplit[2]+" July "+datesplit[0];
        }else if (datesplit[1].equals("08")){
            return datesplit[2]+" August "+datesplit[0];
        }else if (datesplit[1].equals("09")){
            return datesplit[2]+" September "+datesplit[0];
        }else if (datesplit[1].equals("10")){
            return datesplit[2]+" October "+datesplit[0];
        }else if (datesplit[1].equals("11")){
            return datesplit[2]+" November "+datesplit[0];
        }else if (datesplit[1].equals("12")){
            return datesplit[2]+" December "+datesplit[0];
        }else{
            return "date unavailable";
        }
    }
}
