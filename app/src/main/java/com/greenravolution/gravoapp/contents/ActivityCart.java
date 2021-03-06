package com.greenravolution.gravoapp.contents;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.greenravolution.gravoapp.R;
import com.greenravolution.gravoapp.functions.AsyncAddTransaction;
import com.greenravolution.gravoapp.functions.AsyncDeleteCartDetails;
import com.greenravolution.gravoapp.functions.AsyncEditCartDetails;
import com.greenravolution.gravoapp.functions.GetAsyncRequest;
import com.greenravolution.gravoapp.functions.PickUpDayReminder;
import com.greenravolution.gravoapp.functions.Rates;
import com.greenravolution.gravoapp.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivityCart extends AppCompatActivity implements View.OnTouchListener, OnItemClickListener {
    public static final String SESSION = "login_status";
    SharedPreferences preferences;
    Toolbar toolbar;
    Button cash;
    TextView scheduleDate, scheduleDateTiming, tvaddress;
    ImageView arrow;
    String requestParameters = "userid=";
    LinearLayout cartItems;
    LinearLayout summary;
    LinearLayout getaddresslayout, checkoutdetails;
    RelativeLayout checkout;
    ImageView ivItem;
    String itemType, itemRate;
    TextView tvTotalWeight, tvTotalPrice, tvNoOfItems;
    EditText etPhone, etRemarks, etFloor, etUnit;
    JSONObject item;
    ScrollView scollview;
    int no_of_items = 0;

    private DatePickerDialog.OnDateSetListener
            datePickerListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
            Date date = new Date(selectedYear, selectedMonth, selectedDay-1);
            String dayOfWeek = simpledateformat.format(date);
            Log.e("Day", dayOfWeek);
            Log.e("Day", date+"");
            if (dayOfWeek.equals("Sunday")) {
                Toast.makeText(ActivityCart.this, "Unfortunately, we do not collect on sundays,\nPlease try another day\n\nWe apologize for any inconvenience caused!", Toast.LENGTH_LONG).show();
            } else {
                scheduleDate.setText(dateformattodate(String.format("%s-%s-%d", String.valueOf(selectedDay), String.valueOf(selectedMonth+1), selectedYear)));
            }
        }
    };
    GetAsyncRequest.OnAsyncResult getRates = (resultCode, message) -> {
        try {
            JSONObject result = new JSONObject(message);
            int status = result.getInt("status");
            if (status == 404) {
                TextView noitems = new TextView(getApplicationContext());
                noitems.setText("No items to recycle yet!");
                noitems.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                noitems.setPadding(0, 350, 0, 350);
                noitems.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                noitems.setTextColor(getResources().getColor(R.color.grey));
                noitems.setTextSize(20);
                noitems.setAlpha((float) 0.50);
                cartItems.addView(noitems);
                checkout.setVisibility(View.GONE);

            } else if (status == 200) {
                checkout.setVisibility(View.VISIBLE);
                Log.e("CART DETAILS: ", message);
                JSONArray cartDetailsArray = result.getJSONArray("result");
                no_of_items = cartDetailsArray.length();
                tvNoOfItems = findViewById(R.id.no_of_items);
                tvNoOfItems.setText(no_of_items + " item(s)");
                TextView tvTotalPrice = findViewById(R.id.totalPrice);
                double totalPrice = 0.0;
                double totalWeight = 0.0;

                for (int i = 0; i < cartDetailsArray.length(); i++) {
                    JSONObject cartDetails = cartDetailsArray.getJSONObject(i);
                    Log.e("cartDetails", cartDetails.toString());
                    int cartid = cartDetails.getInt("id");
                    int catid = cartDetails.getInt("category_id");
                    Double itemTotalPrice = cartDetails.getDouble("price");
                    Double itemTotalWeight = cartDetails.getDouble("weight");

                    Log.e("price", itemTotalPrice + "");
                    itemRate = "";
                    totalWeight = totalWeight + itemTotalWeight;
                    totalPrice = totalPrice + itemTotalPrice;

                    //totalPrice = totalPrice + itemTotalPrice;
                    GetAsyncRequest.OnAsyncResult getCategoryById = (categoryResultCode, categoryMessage) -> {
                        try {
                            JSONObject results = new JSONObject(categoryMessage);
                            JSONArray rates = results.getJSONArray("result");
                            Log.e("rates", rates.toString() + "\n");
                            item = rates.getJSONObject(0);

                            itemType = item.getString("item");
                            itemRate = item.getString("rate");

                            String[] itemArray = {String.valueOf(cartid), String.valueOf(itemType), String.valueOf(itemTotalPrice), String.valueOf(itemTotalWeight), String.valueOf(itemRate)};

                            Log.e("cost", itemTotalPrice + " " + itemTotalWeight + cartid);

                            cartItems.addView(initView(itemArray));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    };
                    GetAsyncRequest asyncRequest2 = new GetAsyncRequest();
                    asyncRequest2.setOnResultListener(getCategoryById);
                    asyncRequest2.execute("http://ehostingcentre.com/gravo/getCategories.php?type=withid&category=" + catid);

                }

                tvTotalPrice.setText(String.format("$%.2f", totalPrice));
                Log.e("TOTAL PRICE", String.valueOf(totalPrice));
                Log.e("TOTAL WEIGHT", String.valueOf(totalWeight));
                tvTotalWeight.setText(totalWeight + "KG");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    };
    private int mYear, mMonth, mDay, mDate;
    private PlaceAutocompleteFragment placeAutocompleteFragment;

    public View initView(String[] itemArray) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {
            View view;
            ArrayList<com.greenravolution.gravoapp.objects.Rates> ratesList = new ArrayList<>();
            view = inflater.inflate(R.layout.cart_items, null);

            TextView tvTitle;
            Button delete, plus, minus;
            TextView tvTotalPrice;
            TextView tvWeight;
            TextView tvRate;

            ivItem = view.findViewById(R.id.getImage);
            tvTitle = view.findViewById(R.id.getTitle);
            delete = view.findViewById(R.id.delete);
            plus = view.findViewById(R.id.btnPlus);
            minus = view.findViewById(R.id.btnMinus);
            tvTotalPrice = view.findViewById(R.id.getPrice);
            tvWeight = view.findViewById(R.id.getWeight);
            tvRate = view.findViewById(R.id.getRate);
            tvTotalWeight = findViewById(R.id.totalWeight);

            int cartId = Integer.parseInt(itemArray[0]);
            String color = itemArray[1].substring(0, itemArray[1].indexOf(" "));

            Rates rateClass = new Rates();
            ivItem.setBackgroundColor(getResources().getColor(rateClass.getImageColour(color)));
            ivItem.setImageResource(rateClass.getImage(itemArray[1]));
            if (itemArray[1].split(" ")[0].equals("Metals")) {
                tvTitle.setText(itemArray[1].split("-")[0] + "\n" + itemArray[1].split("-")[1]);
            } else {
                tvTitle.setText(itemArray[1]);
            }
            tvTotalPrice.setText(String.format("$%s", String.valueOf(String.format("%.2f", Double.parseDouble(itemArray[2])))));
            tvWeight.setText(itemArray[3]);
            tvRate.setText(String.format("$%s", itemArray[4]));
            double doubleRate = Double.parseDouble(itemArray[4].substring(0, itemArray[4].indexOf("/")));
            delete.setOnClickListener(v -> {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setCancelable(false);
                dialog.setTitle("Delete Item");
                dialog.setMessage("Delete this " + itemArray[1].split(" ")[0] + " item?");
                dialog.setPositiveButton("yes", (dialogInterface, i) -> {

                    preferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                    String user_id = String.valueOf(preferences.getInt("user_id", 0));

                    API links = new API();
                    String deleteCartDetailsUrl = links.deleteCartDetails();
                    AsyncDeleteCartDetails deleteCart = new AsyncDeleteCartDetails();
                    TextView tvAllPrice = findViewById(R.id.totalPrice);

                    double allPrice = Double.parseDouble((tvAllPrice.getText().toString()).substring(1));
                    double itemPrice = Double.parseDouble((tvTotalPrice.getText().toString()).substring(1));
                    Log.i("allPrice,currentPrice", allPrice + " " + itemPrice + " " + tvRate);

                    allPrice = allPrice - itemPrice;

                    tvAllPrice.setText("$" + String.format("%.2f", allPrice));

                    String itemRates = tvRate.getText().toString();
                    Log.i("itemRates", itemRates.substring(itemRates.length() - 1));

                    String totalWeight = tvTotalWeight.getText().toString();
                    double numberOfPieces = Double.parseDouble(totalWeight.substring(0, totalWeight.indexOf("K")));
                    double newNumberOfPieces = numberOfPieces - Double.parseDouble(tvWeight.getText().toString());
                    String newStringPieces = String.valueOf(newNumberOfPieces);
                    tvTotalWeight.setText(newStringPieces + "KG");

                    String[] paramsArray = {deleteCartDetailsUrl, user_id, cartId + ""};
                    deleteCart.execute(paramsArray);

                    TextView noOfItems = findViewById(R.id.no_of_items);
                    int numberOfItems = Integer.parseInt((noOfItems.getText().toString()).substring(0, noOfItems.getText().toString().indexOf(" ")));
                    int newNumberOfItems = numberOfItems--;
                    noOfItems.setText(newNumberOfItems + " item(s)");

                    view.setVisibility(View.GONE);
                    no_of_items = no_of_items - 1;
                    tvNoOfItems = findViewById(R.id.no_of_items);
                    tvNoOfItems.setText(no_of_items + " item(s)");
                    Toast.makeText(ActivityCart.this, "Deleted", Toast.LENGTH_LONG).show();
                });
                dialog.setNegativeButton("No", (dialogInterface, i) -> {
                    Toast.makeText(getApplicationContext(), "Not deleted", Toast.LENGTH_LONG).show();
                });
                AlertDialog dialogue = dialog.create();
                dialogue.show();

            });
            minus.setOnClickListener((View v) -> {
                double getWeight = Double.parseDouble(tvWeight.getText().toString());
                double getPrice = Double.parseDouble(tvTotalPrice.getText().toString().substring(1));
                if (getWeight <= 1) {
                    Log.i("Cart : ", "item is already 0");
                    Toast.makeText(ActivityCart.this, "Cannot go below 1KG", Toast.LENGTH_LONG).show();

                } else {
                    String getNewTotalWeight = "";
                    String totalWeight = tvTotalWeight.getText().toString();
                    String getTotalWeight = totalWeight.split("KG")[0];
                    double newWeight = getWeight - 1;
                    tvWeight.setText(String.valueOf(newWeight));

                    Log.e("TYPE", itemArray[1].split(" ")[0]);

                    if (Double.parseDouble(getTotalWeight) == 1.0) {
                        Toast.makeText(ActivityCart.this, "Cannot go below 1KG", Toast.LENGTH_LONG).show();
                    } else {
                        getNewTotalWeight = String.valueOf(Double.parseDouble(getTotalWeight) - 1.0);

                        tvTotalWeight.setText(getNewTotalWeight + "KG");
                        Log.e("getNewTotalWeight", getNewTotalWeight);
                    }

                    double newPrice = getPrice - ((getWeight - newWeight) * doubleRate);
                    tvTotalPrice.setText(String.format("$%s", String.valueOf(String.format("%.2f", newPrice))));

                    TextView tvAllPrice = findViewById(R.id.totalPrice);

                    Double allPrice = Double.parseDouble((tvAllPrice.getText().toString()).substring(1));
                    allPrice = allPrice - (getPrice - newPrice);

                    tvAllPrice.setText(String.format("$%.2f", allPrice));

                    String user_id = String.valueOf(preferences.getInt("user_id", 0));

                    API links = new API();
                    String editCartDetailsUrl = links.editCartDetails();

                    AsyncEditCartDetails editCart = new AsyncEditCartDetails();
                    String[] paramsArray = {editCartDetailsUrl, user_id, newWeight + "", newPrice + "", cartId + ""};
                    for (int i = 0; i < paramsArray.length; i++) {
                        Log.e("params", paramsArray[i]);
                    }
                    editCart.execute(paramsArray);
                }
            });
            plus.setOnClickListener((View v) -> {
                double getWeight = Double.parseDouble(tvWeight.getText().toString());
                double getPrice = Double.parseDouble(tvTotalPrice.getText().toString().substring(1));
                if (getWeight >= 999) {
                    Log.i("Cart : ", "item is already 0");
                    Toast.makeText(ActivityCart.this, "Cannot go above 999KG", Toast.LENGTH_LONG).show();

                } else {
                    String getNewTotalWeight = "";

                    String totalWeight = tvTotalWeight.getText().toString();

                    String getTotalWeight = totalWeight.split("KG")[0];
                    Log.e("TOTAL WEIGHT", getTotalWeight);


                    double newWeight = getWeight + 1;
                    tvWeight.setText(String.valueOf(newWeight));

//                if (tvWeight.getText().toString().equals("0.0")) {
                    getNewTotalWeight = String.valueOf(Double.parseDouble(getTotalWeight) + 1.0);
                    tvTotalWeight.setText(getNewTotalWeight + "KG");
                    Log.e("newTotalWeight", getNewTotalWeight);


                    double newPrice = getPrice - ((getWeight - newWeight) * doubleRate);

                    tvTotalPrice.setText("$" + String.valueOf(String.format("%.2f", newPrice)));

                    TextView tvAllPrice = findViewById(R.id.totalPrice);

                    Double allPrice = Double.parseDouble((tvAllPrice.getText().toString()).substring(1));
                    allPrice = allPrice + (newPrice - getPrice);

                    tvAllPrice.setText("$" + String.format("%.2f", allPrice));

                    String user_id = String.valueOf(preferences.getInt("user_id", 0));

                    API links = new API();
                    String editCartDetailsUrl = links.editCartDetails();

                    AsyncEditCartDetails editCart = new AsyncEditCartDetails();
                    String[] paramsArray = {editCartDetailsUrl, user_id, newWeight + "", newPrice + "", cartId + ""};
                    for (int i = 0; i < paramsArray.length; i++) {
                        Log.e("params", paramsArray[i]);
                    }
                    editCart.execute(paramsArray);
                }
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
        String id = String.valueOf(preferences.getInt("user_id", 0));
        String name = String.valueOf(preferences.getString("user_name", ""));
        String url = links.getCart() + requestParameters + id;
        Log.e("URL LINK: ", url);
        getcart.execute(url);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        arrow = findViewById(R.id.arrow);

        cartItems = findViewById(R.id.cartItems);
        checkout = findViewById(R.id.checkout);
        checkoutdetails = findViewById(R.id.checkoutdetails);
        checkoutdetails.setVisibility(View.GONE);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkoutdetails.getVisibility() == View.GONE) {
                    checkoutdetails.setVisibility(View.VISIBLE);
                    arrow.animate().rotation(180).setDuration(100);

                } else {
                    checkoutdetails.setVisibility(View.GONE);
                    arrow.animate().rotation(0).setDuration(100);
                }
            }
        });
        scollview = findViewById(R.id.scrollview);
        summary = findViewById(R.id.summary);
        etPhone = findViewById(R.id.etPhone);
        etFloor = findViewById(R.id.etfloor);
        etUnit = findViewById(R.id.etunit);

        etRemarks = findViewById(R.id.etRemarks);
        tvTotalPrice = findViewById(R.id.totalPrice);
        tvTotalWeight = findViewById(R.id.totalWeight);
        tvTotalPrice.setText(R.string.defaultprice);
        tvTotalWeight.setText(R.string.defaultweight);

        placeAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        getaddresslayout = findViewById(R.id.getaddresslayout);
        getaddresslayout.setVisibility(View.GONE);

        tvaddress = findViewById(R.id.tvaddress);
        tvaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getaddresslayout.getVisibility() == View.GONE) {
                    getaddresslayout.setVisibility(View.VISIBLE);
                } else {
                    getaddresslayout.setVisibility(View.GONE);
                }
            }
        });

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder().setCountry("SG").build();
        placeAutocompleteFragment.setFilter(autocompleteFilter);
        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                tvaddress.setText(place.getAddress().toString());
                placeAutocompleteFragment.setText("");
                getaddresslayout.setVisibility(View.GONE);
            }

            @Override
            public void onError(Status status) {
            }
        });

        etPhone.setText(preferences.getString("user_contact", ""));
        tvaddress.setText(preferences.getString("user_address", ""));
        if (preferences.getString("user_address_unit", "").equals("") || preferences.getString("user_address_unit", "").equals("-")) {
            etFloor.setText("");
            etUnit.setText("");
        } else {
            etFloor.setText(preferences.getString("user_address_unit", "").split("-")[0]);
            etUnit.setText(preferences.getString("user_address_unit", "").split("-")[1]);
        }

        cash = findViewById(R.id.cash);
        cash.setOnClickListener((View v) -> {
            String userPhoneNo = etPhone.getText().toString();
            String userRemarks = etRemarks.getText().toString();
            String userPreTotalPrice = tvTotalPrice.getText().toString();
            String userTotalPrice = userPreTotalPrice.substring(1);
            if(Double.parseDouble(tvTotalWeight.getText().toString().substring(0,tvTotalWeight.getText().toString().length()-2))<50.0){
                Toast.makeText(this, "You need have 50kg or more to recycle with us!\nWe apologize for any inconvenience caused!", Toast.LENGTH_LONG).show();
            }else{
                Log.e("Cart total price", userTotalPrice);
                Log.e("price", userTotalPrice);
                if (etPhone.getText().toString().equals("") || scheduleDate.getText().toString().equals("SELECT DATE")
                        || scheduleDateTiming.getText().toString().equals("SELECT TIME")
                        || tvaddress.getText().toString().equals("Select address...")
                        || etFloor.getText().toString().equals("")
                        || etUnit.getText().toString().equals("")) {
                    Toast.makeText(this, "Please Fill in ALL Details", Toast.LENGTH_LONG).show();
                } else if (no_of_items == 0) {
                    Toast.makeText(this, "No items in cart", Toast.LENGTH_LONG).show();
                } else {
                    int status_id = 6;

                    String newdate = datetodateformat(scheduleDate.getText().toString());
                    String[] getdatesplit = newdate.split("-");
                    String addTransactionUrl = links.addTransaction();
                    Log.e("CHANGED DATE =", newdate);
                    Calendar calAlarm = Calendar.getInstance();
                    calAlarm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(getdatesplit[0]));
                    calAlarm.set(Calendar.MONTH, Integer.parseInt(getdatesplit[1]));
                    calAlarm.set(Calendar.YEAR, Integer.parseInt(getdatesplit[2]));
                    calAlarm.set(Calendar.HOUR, 0);
                    calAlarm.set(Calendar.HOUR_OF_DAY, 0);
                    calAlarm.set(Calendar.MINUTE, 0);
                    calAlarm.set(Calendar.SECOND, 0);
                    calAlarm.set(Calendar.AM_PM, 0);
                    setAlarmForPickUpDay(calAlarm);
                    AsyncAddTransaction addTransaction = new AsyncAddTransaction();
                    String[] paramsArray = {addTransactionUrl, newdate, tvaddress.getText().toString() + " #" + etFloor.getText().toString() + "-" + etUnit.getText().toString(), name, userPhoneNo, userTotalPrice, tvTotalWeight.getText().toString(), userRemarks, "00000", id, status_id + "", scheduleDateTiming.getText().toString()};
                    for (int i = 0; i < paramsArray.length; i++) {
                        Log.e("params", paramsArray[i]);
                    }
                    AsyncAddTransaction.OnAsyncResult getTransactionId = (resultCode, message) -> {
                        Log.i("message", message);
                        Log.i("resultCodeHere", resultCode + "");
                        if (resultCode == 200) {
                            Log.e("TRANSACTION ID", message.split(" ")[1]);
                            startActivity(new Intent(this, ActivitySuccessfullTransaction.class).putExtra("date", scheduleDate.getText().toString()).putExtra("transactionid", message.split(" ")[1]));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "An unexpected error has occured, Please notify us through the help centre.", Toast.LENGTH_LONG).show();
                        }
                    };
                    addTransaction.setOnResultListener(getTransactionId);
                    addTransaction.execute(paramsArray);
                    Log.i("resultCodeHere", "successtransactionpage");
                }
            }
        });
        scheduleDate = findViewById(R.id.scheduleDate);
        scheduleDateTiming = findViewById(R.id.scheduleDateTiming);
        scheduleDateTiming.setOnClickListener(v -> getScheduleDateTiming());
        scheduleDate.setOnClickListener(v -> getScheduleDate());
    }


    public void getScheduleDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mDate = c.get(Calendar.DATE);
        DatePickerDialog datePickerDialog;


//        datePickerDialog = new DatePickerDialog(this,
//                (view, year, month, day) ->
//                        scheduleDate.setText(dateformattodate(String.format("%s-%s-%d", String.valueOf(day), String.valueOf(month + 1), year))), mYear, mMonth + 1, mDay);

        datePickerDialog = new DatePickerDialog(this, datePickerListener, mYear, mMonth, mDay);
        DatePicker datePicker = datePickerDialog.getDatePicker();
        Calendar minC = Calendar.getInstance();
        minC.add(Calendar.DAY_OF_YEAR, 1);
        long minDate = minC.getTimeInMillis();

        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, 14);
        long maxDate = current.getTimeInMillis();
        Log.e("Current hour", current.get(Calendar.HOUR_OF_DAY) + "");

        if (current.get(Calendar.HOUR_OF_DAY) < 18) {
            datePicker.setMinDate(minDate);
            datePicker.setMaxDate(maxDate);
            datePicker.updateDate(mYear, mMonth, mDay + 1);
        } else {
            datePicker.setMinDate(minDate + 86400000);
            datePicker.setMaxDate(maxDate + 86400000);
            datePicker.updateDate(mYear, mMonth, mDay + 2);
        }


        datePickerDialog.show();
    }

    public void getScheduleDateTiming() {
        final CharSequence[] items = {"9:00am - 12:00pm", "1:00pm - 4:00pm", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityCart.this);
        builder.setTitle("Select Time Slot");
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("9:00am - 12:00pm")) {
                scheduleDateTiming.setText("9:00am - 12:00pm");
                dialog.dismiss();
            } else if (items[item].equals("1:00pm - 4:00pm")) {
                scheduleDateTiming.setText("1:00pm - 4:00pm");
                dialog.dismiss();
            } else {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.btnPlus) {

        }
        return false;
    }

    public void setAlarmForPickUpDay(Calendar calAlarm) {
        Log.i("calAlarm Status", calAlarm.toString());
        Intent iReminder = new Intent(this, PickUpDayReminder.class);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(this, 1234, iReminder,
                        PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calAlarm.getTimeInMillis(), pendingIntent);
//        am.set(AlarmManager.RTC_WAKEUP, 2000, pendingIntent);
    }

    public void onItemClick(AdapterView adapterView, View view, int position, long id) {
        String str = (String) adapterView.getItemAtPosition(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        hideSoftKeyBoard();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        hideSoftKeyBoard();
    }

    //    private void hideSoftKeyBoard() {
//        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        if (imm != null && imm.isAcceptingText()) { // verify if the soft keyboard is open
//            imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
//        }
//    }
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

    public String dateformattodate(String date) {
        String[] datesplit = date.split("-");
        if (datesplit[1].equals("1")) {
            return datesplit[0] + " January " + datesplit[2];
        } else if (datesplit[1].equals("2")) {
            return datesplit[0] + " February " + datesplit[2];
        } else if (datesplit[1].equals("3")) {
            return datesplit[0] + " March " + datesplit[2];
        } else if (datesplit[1].equals("4")) {
            return datesplit[0] + " April " + datesplit[2];
        } else if (datesplit[1].equals("5")) {
            return datesplit[0] + " May " + datesplit[2];
        } else if (datesplit[1].equals("6")) {
            return datesplit[0] + " June " + datesplit[2];
        } else if (datesplit[1].equals("7")) {
            return datesplit[0] + " July " + datesplit[2];
        } else if (datesplit[1].equals("8")) {
            return datesplit[0] + " August " + datesplit[2];
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
}