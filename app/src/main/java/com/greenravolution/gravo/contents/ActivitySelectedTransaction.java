package com.greenravolution.gravo.contents;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.HttpReq;
import com.greenravolution.gravo.functions.Rates;
import com.greenravolution.gravo.functions.asyncGetSelectedTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class ActivitySelectedTransaction extends AppCompatActivity {
    public static final String SESSION = "login_status";
    Toolbar toolbar;
    TextView title;
    LinearLayout detailList, progressbar, transactionchanges;
    SharedPreferences sessionManager;
    TextView tvRemarks;
    Button reschedule, cancel;
    ImageView ivDetailImage;
    TextView tvDetailTitle, tvDetailPrice, tvDetailRate, tvDetailWeight, tvEditRemarks;
    TextView date, timeslot;
    private int mYear, mMonth, mDay;
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
                tvRemarks = findViewById(R.id.tvRemarks);
                TextView tvStatus = findViewById(R.id.tvStatus);
                TextView tvStatusDetails = findViewById(R.id.tvStatusDetails);
                TextView tvBillingName = findViewById(R.id.tvBillingName);
                TextView tvBillingAddress = findViewById(R.id.tvBillingAddress);
                TextView tvBillingEmail = findViewById(R.id.tvBillingEmail);
                TextView tvBillingContact = findViewById(R.id.tvBillingContact);
                TextView tvPrice = findViewById(R.id.tvPrice);
                TextView tvWeight = findViewById(R.id.tvWeight);
                StringBuilder statuses = new StringBuilder();
                if (transactionHistory != null) {
                    for (int i = 0; i < transactionHistory.length(); i++) {
                        JSONObject getstatus = transactionHistory.getJSONObject(i);
                        statuses.append(getstatus.getString("status")).append("\n\n");
                    }
                    tvStatusDetails.setText(statuses.toString());
                } else {
                    tvStatusDetails.setText(String.format("Item Scheduled to be picked up on %s", transactionObject.getString("collection_date")));
                }

                Log.i("id key", transactionObject.getString("transaction_id_key"));
                tvTransaction.setText(String.format("TRANSACTION #%s", transactionObject.getString("transaction_id_key")));
                tvStatus.setText(transactionObject.getString("status_type"));
                tvDate.setText(dateformattodate2(transactionObject.getString("collection_date")) + " (" + transactionObject.getString("collection_date_timing") + ")");
                hideedit(Integer.parseInt(transactionObject.getString("status_id")));

                if (transactionObject.getString("status_id").equals("1")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    transactionchanges.setVisibility(View.VISIBLE);
                    setStatusStepper("1");

                } else if (transactionObject.getString("status_id").equals("2")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    transactionchanges.setVisibility(View.VISIBLE);
                    setStatusStepper("2");

                } else if (transactionObject.getString("status_id").equals("3")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    transactionchanges.setVisibility(View.GONE);
                    setStatusStepper("3");

                } else if (transactionObject.getString("status_id").equals("4")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    transactionchanges.setVisibility(View.GONE);
                    setStatusStepper("4");

                } else if (transactionObject.getString("status_id").equals("5")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    transactionchanges.setVisibility(View.GONE);
                    setStatusStepper("4");

                } else if (transactionObject.getString("status_id").equals("8")) {
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    transactionchanges.setVisibility(View.GONE);
                    setStatusStepper("null");

                } else {
                    transactionchanges.setVisibility(View.VISIBLE);
                    Log.e("Transaction status", transactionObject.getString("status_id"));
                    setStatusStepper("null");
                }
                tvBillingName.setText(transactionObject.getString("collection_user"));
                if (transactionObject.getString("remarks").equals("")) {
                    tvRemarks.setText(" - No Remarks - ");
                } else {
                    tvRemarks.setText(transactionObject.getString("remarks"));
                }
                cancel.setOnClickListener(v -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySelectedTransaction.this)
                            .setCancelable(false).setMessage("Are you sure you want to cancel this transaction?")
                            .setTitle("Cancel transaction");
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CancelTransaction cancel = new CancelTransaction();
                            cancel.execute();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                });
                reschedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(ActivitySelectedTransaction.this);
                        dialog.setContentView(R.layout.reschedule_dialog);
                        dialog.setTitle("Re-schedule Transaction");
                        date = dialog.findViewById(R.id.date);
                        timeslot = dialog.findViewById(R.id.timeslot);
                        Button confirm = dialog.findViewById(R.id.submit);
                        date.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getScheduleDate();
                            }
                        });
                        timeslot.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getScheduleDateTiming();
                            }
                        });
                        confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (timeslot.getText().toString().equals("SELECT TIME") || date.getText().toString().equals("SELECT DATE")) {
                                    Toast.makeText(ActivitySelectedTransaction.this, "Please select both details", Toast.LENGTH_SHORT).show();
                                } else {
                                    ReScheduleTransaction reScheduleTransaction = new ReScheduleTransaction();
                                    reScheduleTransaction.execute(timeslot.getText().toString(), datetodateformat(date.getText().toString()));
                                    dialog.dismiss();
                                }
                            }
                        });

                        dialog.show();
                    }
                });
                tvBillingAddress.setText(transactionObject.getString("collection_address"));
                tvBillingContact.setText(transactionObject.getString("collection_contact_number"));
                sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                tvBillingEmail.setText(sessionManager.getString("user_email", "No Email"));

                Double totalPrice = 0.0;
                for (int detail = 0; detail < detailsArray.length(); detail++) {
                    totalPrice = totalPrice + Double.parseDouble((detailsArray.getJSONObject(detail)).getString("price"));
                }
                Log.e("TOTAL PRICE TRANSACTION", totalPrice.toString());
                tvPrice.setText(String.format("$%.2f", totalPrice));
                tvWeight.setText(transactionObject.getString("total_weight"));

                //Populating details layout
                if (detailsArray.length() > 0) {
                    detailList = findViewById(R.id.transaction_details_list);
                    detailList.removeAllViews();
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
                                if (formattedType.equals("Paper")) {
                                    tvDetailTitle.setText(detailObject.getString("category_type") + "\n");
                                } else {
                                    tvDetailTitle.setText(detailObject.getString("category_type").split("-")[0] + "\n" + detailObject.getString("category_type").split("-")[1]);
                                }
                                tvDetailWeight.setText(String.format("%s KG", detailObject.getString("weight")));

                            } else if (formattedType.equals("E-Waste")) {
                                tvDetailWeight.setText(String.format("%s Piece(s)", detailObject.getString("weight")));
                                tvDetailTitle.setText(detailObject.getString("category_type") + "\n");
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
    public void getScheduleDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) ->
                        date.setText(dateformattodate(String.format("%s-%s-%d", String.valueOf(dayOfMonth), String.valueOf(monthOfYear + 1), year))), mYear, mMonth + 1, mDay);

        DatePicker datePicker = datePickerDialog.getDatePicker();

        Calendar minC = Calendar.getInstance();
        minC.add(Calendar.DAY_OF_YEAR, 1);
        long minDate = minC.getTimeInMillis();

        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, 14);
        long maxDate = current.getTimeInMillis();

        datePicker.setMinDate(minDate);
        datePicker.setMaxDate(maxDate);

        datePickerDialog.show();
    }
    public void getScheduleDateTiming() {
        final CharSequence[] items = {"9:00am - 12:00pm", "1:00pm - 4:00pm", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySelectedTransaction.this);
        builder.setTitle("Select Time Slot");
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("9:00am - 12:00pm")) {
                timeslot.setText("9:00am - 12:00pm");
                dialog.dismiss();
            } else if (items[item].equals("1:00pm - 4:00pm")) {
                timeslot.setText("1:00pm - 4:00pm");
                dialog.dismiss();
            } else {
                dialog.dismiss();
            }
        });
        builder.show();
    }

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

        Bundle extras = getIntent().getExtras();
        int chosenID = extras.getInt("intChosenID", 0);

        transactionchanges = findViewById(R.id.transactionchanges);
        transactionchanges.setVisibility(View.GONE);
        reschedule = findViewById(R.id.reschedule);
        cancel = findViewById(R.id.cancel);
        tvEditRemarks = findViewById(R.id.tv_edit_remarks);
        tvEditRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySelectedTransaction.this);
                builder.setTitle("Edit Remarks");
                final EditText input = new EditText(ActivitySelectedTransaction.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                input.setHint("Remarks");
                builder.setView(input);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (input.getText().toString().equals("")) {
                            dialog.dismiss();
                        } else {
                            UpdateTransactionRemarks updateTransactionRemarks = new UpdateTransactionRemarks();
                            updateTransactionRemarks.execute(String.valueOf(chosenID), input.getText().toString());

                        }
                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        asyncGetSelectedTransaction getSelected = new asyncGetSelectedTransaction();
        getSelected.setOnResultListener(getSelectedTransaction);


        Log.i("chosenID", chosenID + "");
        String[] paramsArray = {String.valueOf(chosenID)};
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

    public String dateformattodate2(String date) {
        Log.e("Selected Date", date);
        String[] datesplit = date.split("-");
        if (datesplit[1].equals("01")) {
            return datesplit[2] + " January " + datesplit[0];
        } else if (datesplit[1].equals("1")) {
            return datesplit[2] + " January " + datesplit[0];
        } else if (datesplit[1].equals("02")) {
            return datesplit[2] + " February " + datesplit[0];
        } else if (datesplit[1].equals("2")) {
            return datesplit[2] + " February " + datesplit[0];
        } else if (datesplit[1].equals("03")) {
            return datesplit[2] + " March " + datesplit[0];
        } else if (datesplit[1].equals("3")) {
            return datesplit[2] + " March " + datesplit[0];
        } else if (datesplit[1].equals("04")) {
            return datesplit[2] + " April " + datesplit[0];
        } else if (datesplit[1].equals("4")) {
            return datesplit[2] + " April " + datesplit[0];
        } else if (datesplit[1].equals("05")) {
            return datesplit[2] + " May " + datesplit[0];
        } else if (datesplit[1].equals("5")) {
            return datesplit[2] + " May " + datesplit[0];
        } else if (datesplit[1].equals("06")) {
            return datesplit[2] + " June " + datesplit[0];
        } else if (datesplit[1].equals("6")) {
            return datesplit[2] + " June " + datesplit[0];
        } else if (datesplit[1].equals("07")) {
            return datesplit[2] + " July " + datesplit[0];
        } else if (datesplit[1].equals("7")) {
            return datesplit[2] + " July " + datesplit[0];
        } else if (datesplit[1].equals("08")) {
            return datesplit[2] + " August " + datesplit[0];
        } else if (datesplit[1].equals("8")) {
            return datesplit[2] + " August " + datesplit[0];
        } else if (datesplit[1].equals("09")) {
            return datesplit[2] + " September " + datesplit[0];
        } else if (datesplit[1].equals("9")) {
            return datesplit[2] + " September " + datesplit[0];
        } else if (datesplit[1].equals("10")) {
            return datesplit[2] + " October " + datesplit[0];
        } else if (datesplit[1].equals("11")) {
            return datesplit[2] + " November " + datesplit[0];
        } else if (datesplit[1].equals("12")) {
            return datesplit[2] + " December " + datesplit[0];
        } else {
            return "date unavailable";
        }
    }

    public String dateformattodate(String date) {
        Log.e("Selected Date", date);
        String[] datesplit = date.split("-");
        if (datesplit[1].equals("01")) {
            return datesplit[0] + " January " + datesplit[2];
        } else if (datesplit[1].equals("1")) {
            return datesplit[0] + " January " + datesplit[2];
        } else if (datesplit[1].equals("02")) {
            return datesplit[0] + " February " + datesplit[2];
        } else if (datesplit[1].equals("2")) {
            return datesplit[0] + " February " + datesplit[2];
        } else if (datesplit[1].equals("03")) {
            return datesplit[0] + " March " + datesplit[2];
        } else if (datesplit[1].equals("3")) {
            return datesplit[0] + " March " + datesplit[2];
        } else if (datesplit[1].equals("04")) {
            return datesplit[0] + " April " + datesplit[2];
        } else if (datesplit[1].equals("4")) {
            return datesplit[0] + " April " + datesplit[2];
        } else if (datesplit[1].equals("05")) {
            return datesplit[0] + " May " + datesplit[2];
        } else if (datesplit[1].equals("5")) {
            return datesplit[0] + " May " + datesplit[2];
        } else if (datesplit[1].equals("06")) {
            return datesplit[0] + " June " + datesplit[2];
        } else if (datesplit[1].equals("6")) {
            return datesplit[0] + " June " + datesplit[2];
        } else if (datesplit[1].equals("07")) {
            return datesplit[0] + " July " + datesplit[2];
        } else if (datesplit[1].equals("7")) {
            return datesplit[0] + " July " + datesplit[2];
        } else if (datesplit[1].equals("08")) {
            return datesplit[0] + " August " + datesplit[2];
        } else if (datesplit[1].equals("8")) {
            return datesplit[0] + " August " + datesplit[2];
        } else if (datesplit[1].equals("09")) {
            return datesplit[0] + " September " + datesplit[2];
        } else if (datesplit[1].equals("9")) {
            return datesplit[0] + " September " + datesplit[2];
        } else if (datesplit[1].equals("10")) {
            return datesplit[0] + " October " + datesplit[2];
        } else if (datesplit[1].equals("11")) {
            return datesplit[0] + " November " + datesplit[2];
        } else if (datesplit[1].equals("12")) {
            return datesplit[0] + " December " + datesplit[2];
        } else {
            return "date unavailable";
        }
    }

    public String datetodateformat(String date) {
        String[] datesplit = date.split(" ");
        if (datesplit[1].equals("January")) {
            return datesplit[2] + "-1-" + datesplit[0];
        } else if (datesplit[1].equals("February")) {
            return datesplit[2] + "-2" + datesplit[0];
        } else if (datesplit[1].equals("March")) {
            return datesplit[2] + "-3-" + datesplit[0];
        } else if (datesplit[1].equals("April")) {
            return datesplit[2] + "-4-" + datesplit[0];
        } else if (datesplit[1].equals("May")) {
            return datesplit[2] + "-5-" + datesplit[0];
        } else if (datesplit[1].equals("June")) {
            return datesplit[2] + "-6-" + datesplit[0];
        } else if (datesplit[1].equals("July")) {
            return datesplit[2] + "-7-" + datesplit[0];
        } else if (datesplit[1].equals("August")) {
            return datesplit[2] + "-8-" + datesplit[0];
        } else if (datesplit[1].equals("September")) {
            return datesplit[2] + "-9-" + datesplit[0];
        } else if (datesplit[1].equals("October")) {
            return datesplit[2] + "-10-" + datesplit[0];
        } else if (datesplit[1].equals("November")) {
            return datesplit[2] + "-11-" + datesplit[0];
        } else if (datesplit[1].equals("December")) {
            return datesplit[2] + "-12-" + datesplit[0];
        } else {
            return "date unavailable";
        }
    }

    public class ReScheduleTransaction extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Intent extras = getIntent();
            int chosenID = extras.getIntExtra("intChosenID", 0);
            return req.PostRequest("http://ehostingcentre.com/gravo/rescheduletransaction.php", "id=" + chosenID
                    + "&time=" + strings[0]
                    + "&date=" + strings[1]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("") || s.length() <= 0) {
                Toast.makeText(ActivitySelectedTransaction.this, "an unexpected has occurred\nerror 404(result empty)", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject result = new JSONObject(s);
                    int status = result.getInt("status");
                    if (status == 200) {
                        asyncGetSelectedTransaction getSelected = new asyncGetSelectedTransaction();
                        getSelected.setOnResultListener(getSelectedTransaction);
                        Bundle extras = getIntent().getExtras();
                        int chosenID = extras.getInt("intChosenID", 0);
                        Log.i("chosenID", chosenID + "");
                        String[] paramsArray = {chosenID + ""};
                        ShowProgress();
                        getSelected.execute(paramsArray);
                    } else {
                        Toast.makeText(ActivitySelectedTransaction.this, "an unexpected error has occurred. please contact the administrator\nerror " + String.valueOf(status) + "\n" + result.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public class CancelTransaction extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Intent extras = getIntent();
            int chosenID = extras.getIntExtra("intChosenID", 0);
            return req.PostRequest("http://ehostingcentre.com/gravo/canceltransaction.php", "id=" + chosenID);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("") || s.length() <= 0) {
                Toast.makeText(ActivitySelectedTransaction.this, "an unexpected has occurred\nerror 404(result empty)", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject result = new JSONObject(s);
                    int status = result.getInt("status");
                    if (status == 200) {
                        Toast.makeText(ActivitySelectedTransaction.this, "Transaction Cancelled", Toast.LENGTH_SHORT).show();
                        asyncGetSelectedTransaction getSelected = new asyncGetSelectedTransaction();
                        getSelected.setOnResultListener(getSelectedTransaction);
                        Bundle extras = getIntent().getExtras();
                        int chosenID = extras.getInt("intChosenID", 0);
                        Log.i("chosenID", chosenID + "");
                        String[] paramsArray = {chosenID + ""};
                        ShowProgress();
                        getSelected.execute(paramsArray);
                    } else {
                        Toast.makeText(ActivitySelectedTransaction.this, "an unexpected error has occurred. please contact the administrator\nerror " + String.valueOf(status) + "\n" + result.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public class UpdateTransactionRemarks extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            return req.PostRequest("http://ehostingcentre.com/gravo/updatetransactionremarks.php", "id=" + strings[0] + "&remarks=" + strings[1]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            hideKeyboard(ActivitySelectedTransaction.this);
            Log.e("REMARKS RESULT", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                int status = jsonObject.getInt("status");
                if (status == 200) {
                    String newremarks = jsonObject.getJSONArray("result").getJSONObject(0).getString("remarks");
                    tvRemarks.setText(newremarks);
                } else {
                    Toast.makeText(ActivitySelectedTransaction.this, "An unexpected error has occurred", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void hideedit(int id) {
        if (id == 5) {
            tvEditRemarks.setVisibility(View.GONE);
        } else if (id == 4) {
            tvEditRemarks.setVisibility(View.GONE);
        } else if (id == 3) {
            tvEditRemarks.setVisibility(View.GONE);
        } else {
            tvEditRemarks.setVisibility(View.VISIBLE);
        }
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
