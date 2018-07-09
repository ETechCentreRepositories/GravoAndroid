package com.greenravolution.gravo.MainFragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivitySelectedTransaction;
import com.greenravolution.gravo.functions.GetAsyncRequest;
import com.greenravolution.gravo.objects.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.vo.DateData;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Calendar extends Fragment {
    LinearLayout transactionLayout;
    LayoutInflater inflater;

    GetAsyncRequest.OnAsyncResult getDates = (resultCode, message) -> {
        Log.i("getTransaction Message",message);
        try{
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            JSONObject result = new JSONObject(message);
            int status = result.getInt("status");

            if(status == 200){

                JSONArray getTransactionArray = result.getJSONArray("result");


                for(int i = 0; i<getTransactionArray.length(); i++){

                    JSONObject transactionObject = getTransactionArray.getJSONObject(i);
                    String transactionID = transactionObject.getString("id");
                    String transactionDate = transactionObject.getString("collection_date");
                    String transactionIDKey = transactionObject.getString("transaction_id_key");

                    String day = transactionDate.substring(transactionDate.lastIndexOf('-')+1);
                    String month = transactionDate.substring(transactionDate.indexOf('-')+1,transactionDate.lastIndexOf('-'));
                    String year = transactionDate.substring(0,transactionDate.indexOf('-'));

                    String fixedDate = day+"/"+month+"/"+year;
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();

                    try{
                        date = formatter.parse(fixedDate);
                    } catch (ParseException e){
                        e.printStackTrace();
                    }

                    String newDate = date+"";
                    String dayWord = newDate.substring(0,newDate.indexOf(" "));
                    String monthWord = newDate.substring(newDate.indexOf(" ")+1,newDate.indexOf(" ")+4);

                    View fragmentCalendar = inflater.inflate(R.layout.transaction_page_item, null);
                    TextView tvDay = (TextView) fragmentCalendar.findViewById(R.id.tvDay);
                    TextView tvDetails = (TextView) fragmentCalendar.findViewById(R.id.tvDetails);
                    ExpCalendarView cvCalendar = getView().findViewById(R.id.calendar);

                    cvCalendar.markDate(new DateData(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day)));

                    Log.e("long date","getTime : " + date.getTime());

                    fragmentCalendar.setTag(transactionID);

                    tvDay.setText(day);
                    tvDetails.setText(dayWord + " " +monthWord + " " + year + " Transaction: " + transactionIDKey);

                    fragmentCalendar.setOnClickListener(v ->{
                        //Toast.makeText(getActivity(),"clicked "+fragmentCalendar.getTag(),Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), ActivitySelectedTransaction.class);
                        int chosenID = Integer.parseInt(fragmentCalendar.getTag().toString());
                        Log.i("getTag",chosenID+"");
                        intent.putExtra("intChosenID",chosenID);
                        startActivity(intent);


                    });
                    transactionLayout.addView(fragmentCalendar);
                }


            }
        } catch (JSONException e){
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

        SharedPreferences preferences;
        API links = new API();
        GetAsyncRequest getTransactions = new GetAsyncRequest();
        getTransactions.setOnResultListener(getDates);


        preferences = getActivity().getSharedPreferences("login_status",Context.MODE_PRIVATE);
        String id = String.valueOf(preferences.getInt("user_id",0));

        Log.e("calendar id",id+"");
        String url = links.getTransaction()+"?type=userid&userid="+id;
        Log.e("URL LINK: ", url);
        getTransactions.execute(url);

//        for(int i = 0; i<5; i++){
//            int idtest = 0;
//            View fragmentCalendar = inflater.inflate(R.layout.transaction_page_item, null);
//            TextView tvDay = (TextView) fragmentCalendar.findViewById(R.id.tvDay);
//            TextView tvDetails = (TextView) fragmentCalendar.findViewById(R.id.tvDetails);
//            fragmentCalendar.setTag(i);
//
//            tvDay.setText("" + i);
//            tvDetails.setText(" The details order "+i);
//            fragmentCalendar.setOnClickListener(v ->{
//                Toast.makeText(getActivity(),"clicked "+fragmentCalendar.getTag(),Toast.LENGTH_SHORT).show();
//
//            });
//            transactionLayout.addView(fragmentCalendar);
//            idtest++;
//        }


//        collect1 = view.findViewById(R.id.collect1);
//        collect2 = view.findViewById(R.id.collect2);
//        collect1.setOnClickListener(v->getActivity().startActivity(new Intent(getContext(), ActivitySelectedTransaction.class)));
//        collect2.setOnClickListener(v->getActivity().startActivity(new Intent(getContext(), ActivitySelectedTransaction.class)));


        return calendarView;
    }

}
