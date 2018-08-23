package com.greenravolution.gravo.MainFragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivitySelectedTransaction;
import com.greenravolution.gravo.functions.GetAsyncRequest;
import com.greenravolution.gravo.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.vo.DateData;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Calendar extends Fragment {
    LinearLayout transactionLayout;
    LayoutInflater inflater;
    ExpCalendarView cvCalendar;
    TextView tvMonthYear;

    GetAsyncRequest.OnAsyncResult getDates = (resultCode, message) -> {
        Log.i("getTransaction Message", message);
        try {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            JSONObject result = new JSONObject(message);
            int status = result.getInt("status");

            if (status == 200) {

                JSONArray getTransactionArray = result.getJSONArray("result");

                for (int i = 0; i < getTransactionArray.length(); i++) {

                    JSONObject transactionObject = getTransactionArray.getJSONObject(i);
                    String transactionID = transactionObject.getString("id");
                    String transactionDate = transactionObject.getString("collection_date");
                    String transactionDateTiming = transactionObject.getString("collection_date_timing");
                    String transactionIDKey = transactionObject.getString("transaction_id_key");
                    int status_id = transactionObject.getInt("status_id");

                    String day = transactionDate.substring(transactionDate.lastIndexOf('-') + 1);
                    String month = transactionDate.substring(transactionDate.indexOf('-') + 1, transactionDate.lastIndexOf('-'));
                    String year = transactionDate.substring(0, transactionDate.indexOf('-'));

                    String fixedDate = day + "/" + month + "/" + year;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();

                    try {
                        date = formatter.parse(fixedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String newDate = date + "";
                    String monthWord = newDate.substring(newDate.indexOf(" ") + 1, newDate.indexOf(" ") + 4);

                    View fragmentCalendar = inflater.inflate(R.layout.transaction_page_item, null);
                    LinearLayout layout = fragmentCalendar.findViewById(R.id.layout);

                    TextView tvid = fragmentCalendar.findViewById(R.id.id);
                    TextView tvDetails = fragmentCalendar.findViewById(R.id.tvDetails);
                    cvCalendar = getView().findViewById(R.id.calendar);

                    String dayWord = newDate.substring(0, newDate.indexOf(" "));
                    cvCalendar.markDate(new DateData(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))).setBackgroundColor(getResources().getColor(R.color.brand_pink));

                    Log.e("long date", "getTime : " + date.getTime());
                    if (status_id == 4) {
                        layout.setBackgroundColor(getResources().getColor(R.color.brand_pink));
                        tvDetails.setText("Collected on " + day + " " + monthWord + " " + year + "\nbetween " + transactionDateTiming);
                    } else {
                        tvDetails.setText("Collection booked for " + day + " " + monthWord + " " + year + "\nbetween " + transactionDateTiming);
                    }

                    fragmentCalendar.setTag(transactionID);
                    tvid.setText("Transaction #" + transactionIDKey);


                    fragmentCalendar.setOnClickListener(v -> {
                        //Toast.makeText(getActivity(),"clicked "+fragmentCalendar.getTag(),Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), ActivitySelectedTransaction.class);
                        int chosenID = Integer.parseInt(fragmentCalendar.getTag().toString());
                        Log.i("getTag", chosenID + "");
                        intent.putExtra("intChosenID", chosenID);
                        startActivity(intent);


                    });
                    transactionLayout.addView(fragmentCalendar);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    };

    public Calendar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View calendarView = inflater.inflate(R.layout.fragment_calendar, container, false);
        transactionLayout = calendarView.findViewById(R.id.transactionDetails);
        tvMonthYear = calendarView.findViewById(R.id.monthYear);

        SharedPreferences preferences;
        API links = new API();
        GetAsyncRequest getTransactions = new GetAsyncRequest();
        getTransactions.setOnResultListener(getDates);

        List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

        preferences = getActivity().getSharedPreferences("login_status", Context.MODE_PRIVATE);
        String id = String.valueOf(preferences.getInt("user_id", 0));

        Log.e("calendar id", id + "");
        String url = links.getTransaction() + "?type=userid&userid=" + id;
        Log.e("URL LINK: ", url);
        getTransactions.execute(url);

        ExpCalendarView expCalendar = calendarView.findViewById(R.id.calendar);
        java.util.Calendar currCal = java.util.Calendar.getInstance();

        tvMonthYear.setText(monthList.get(currCal.get(java.util.Calendar.MONTH)) + " - " + currCal.get(java.util.Calendar.YEAR));
        expCalendar.setOnMonthChangeListener(new OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                tvMonthYear.setText(String.format("%s-%d", monthList.get(month - 1), year));

            }
        });

        return calendarView;
    }

}
