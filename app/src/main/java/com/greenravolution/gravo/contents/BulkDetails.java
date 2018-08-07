package com.greenravolution.gravo.contents;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.greenravolution.gravo.CategoryFragments.Bulk;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.HttpReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class BulkDetails extends AppCompatActivity {
    ImageView bulkimage;
    TextView bulkaddress, bulkname, bulkdescription, bulkstatus,bulktransactionid,bulkquote;
    Toolbar toolbar;
    SwipeRefreshLayout refreshLayout;
    LinearLayout statustwo, scheduledate, scheduleddate;
    TextView scheduleDate,scheduleDateTiming, collectiondate,collectiondatetiming;
    RelativeLayout statusthree;
    RelativeLayout btmlayout;
    Button accept, reject, confirmschedule;
    private int mYear, mMonth, mDay;
    public static final String SESSION = "login_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        bulkimage = findViewById(R.id.bulkimage);
        bulkaddress = findViewById(R.id.bulkaddress);
        bulkname = findViewById(R.id.bulkname);
        bulkdescription = findViewById(R.id.bulkdescription);
        bulkstatus = findViewById(R.id.bulkstatus);
        bulktransactionid = findViewById(R.id.bulktransactionid);
        refreshLayout = findViewById(R.id.swipelayout);
        bulkquote = findViewById(R.id.bulkquote);

        collectiondate = findViewById(R.id.bulkcollectiondate);
        collectiondatetiming = findViewById(R.id.bulkcollectiondatetiming);

        btmlayout = findViewById(R.id.btmlayout);
        statustwo = findViewById(R.id.statustwo);
        statusthree = findViewById(R.id.statusthree);
        btmlayout.setVisibility(View.GONE);
        statustwo.setVisibility(View.GONE);
        statusthree.setVisibility(View.GONE);
        scheduledate = findViewById(R.id.scheduledate);
        scheduleddate = findViewById(R.id.scheduleddate);
        scheduledate.setVisibility(View.GONE);
        scheduleddate.setVisibility(View.GONE);

        accept = findViewById(R.id.bulkaccept);
        reject = findViewById(R.id.bulkreject);
        confirmschedule = findViewById(R.id.bulkconfirmschedule);
        accept.setOnClickListener(v -> {
            AcceptTransaction acceptTransaction = new AcceptTransaction();
            acceptTransaction.execute("accept");
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcceptTransaction acceptTransaction = new AcceptTransaction();
                acceptTransaction.execute("reject");
            }
        });
        confirmschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scheduleDate.getText().toString().equals("SELECT DATE") || scheduleDateTiming.getText().toString().equals("SELECT TIME")){
                    Toast.makeText(BulkDetails.this, "Please select a date and time slot!", Toast.LENGTH_LONG).show();
                }else{
                    Scheduledate scheduledates = new Scheduledate();
                    scheduledates.execute();
                }
            }
        });

        scheduleDate = findViewById(R.id.scheduleDate);
        scheduleDateTiming = findViewById(R.id.scheduleDateTiming);
        scheduleDateTiming.setOnClickListener(v -> getScheduleDateTiming());
        scheduleDate.setOnClickListener(v -> getScheduleDate());

        refreshLayout.setOnRefreshListener(() -> getdetails());
    }
    public class Scheduledate extends AsyncTask<String, Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Intent intent = getIntent();
            int id = intent.getIntExtra("bulkid",-1);
            SharedPreferences preferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            return req.PostRequest("http://ehostingcentre.com/gravo/schedulebulkdate.php"
                    ,"id=" +String.valueOf(id)
                            +"&name="+preferences.getString("user_full_name","Unavailable")
                            +"&collectiondate="+scheduleDate.getText().toString()
                            +"&collectiondatetiming="+scheduleDateTiming.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null){
                if(!s.equals("")){
                    try {
                        Log.e("RESULT",s);
                        JSONObject result = new JSONObject(s);
                        int status = result.getInt("status");
                        Log.e("UPDATE STATUS",status+"");
                        if(status == 200){
                            JSONObject item = result.getJSONArray("result").getJSONObject(0);
                            Glide.with(BulkDetails.this).load(item.getString("image")).into(bulkimage);
                            bulkname.setText(item.getString("full_name"));
                            bulkquote.setText(item.getString("price_quote"));
                            bulkstatus.setText(item.getString("status"));
                            collectiondate.setText(item.getString("collection_date"));
                            collectiondatetiming.setText(item.getString("collection_date_timing"));

                            bulkaddress.setText(item.getString("address"));
                            bulkdescription.setText(item.getString("description"));
                            bulktransactionid.setText(item.getString("transaction_id_key"));
                            int status_id = item.getInt("bulk_transaction_status_id");
                            if(status_id==12){
                                btmlayout.setVisibility(View.VISIBLE);
                                statustwo.setVisibility(View.VISIBLE);
                                statusthree.setVisibility(View.GONE);
                                scheduledate.setVisibility(View.GONE);
                                scheduleddate.setVisibility(View.GONE);
                            }else if(status_id==6){
                                btmlayout.setVisibility(View.GONE);
                                statustwo.setVisibility(View.GONE);
                                statusthree.setVisibility(View.VISIBLE);
                                scheduledate.setVisibility(View.VISIBLE);
                                scheduleddate.setVisibility(View.GONE);
                            }else{
                                btmlayout.setVisibility(View.GONE);
                                statustwo.setVisibility(View.GONE);
                                statusthree.setVisibility(View.GONE);
                                scheduledate.setVisibility(View.GONE);
                                scheduleddate.setVisibility(View.VISIBLE);
                            }
                        }else if(status==404){
                            refreshLayout.setRefreshing(false);
                        }else{
                            refreshLayout.setRefreshing(false);
                            Toast.makeText(BulkDetails.this, "An unexpected error has occurred, please contact the administrator", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getdetails();
    }
    private class AcceptTransaction extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            Intent intent = getIntent();
            int id = intent.getIntExtra("bulkid",-1);
            SharedPreferences preferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            if(strings[0].equals("accept")){
                return req.PostRequest("http://ehostingcentre.com/gravo/acceptbulk.php","id="+String.valueOf(id)+"&name="+preferences.getString("user_full_name","Unavailable"));
            }else if(strings[0].equals("reject")){
                return req.PostRequest("http://ehostingcentre.com/gravo/rejectbulk.php","id="+String.valueOf(id)+"&name="+preferences.getString("user_full_name","Unavailable"));
            }else{
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null){
                if(!s.equals("")){
                    try {
                        Log.e("RESULT",s);
                        JSONObject result = new JSONObject(s);
                        int status = result.getInt("status");
                        Log.e("UPDATE STATUS",status+"");
                        if(status == 200){
                            JSONObject item = result.getJSONArray("result").getJSONObject(0);
                            Glide.with(BulkDetails.this).load(item.getString("image")).into(bulkimage);
                            bulkname.setText(item.getString("full_name"));
                            bulkquote.setText(item.getString("price_quote"));
                            bulkstatus.setText(item.getString("status"));
                            collectiondate.setText(item.getString("collection_date"));
                            collectiondatetiming.setText(item.getString("collection_date_timing"));

                            bulkaddress.setText(item.getString("address"));
                            bulkdescription.setText(item.getString("description"));
                            bulktransactionid.setText(item.getString("transaction_id_key"));
                            int status_id = item.getInt("bulk_transaction_status_id");
                            if(status_id==12){
                                btmlayout.setVisibility(View.VISIBLE);
                                statustwo.setVisibility(View.VISIBLE);
                                statusthree.setVisibility(View.GONE);
                                scheduledate.setVisibility(View.GONE);
                                scheduleddate.setVisibility(View.GONE);
                            }else if(status_id==6){
                                btmlayout.setVisibility(View.GONE);
                                statustwo.setVisibility(View.GONE);
                                statusthree.setVisibility(View.VISIBLE);
                                scheduledate.setVisibility(View.VISIBLE);
                                scheduleddate.setVisibility(View.GONE);
                            }else{
                                btmlayout.setVisibility(View.GONE);
                                statustwo.setVisibility(View.GONE);
                                statusthree.setVisibility(View.GONE);
                                scheduledate.setVisibility(View.GONE);
                                scheduleddate.setVisibility(View.VISIBLE);
                            }
                        }else if(status==404){
                            refreshLayout.setRefreshing(false);
                        }else{
                            refreshLayout.setRefreshing(false);
                            Toast.makeText(BulkDetails.this, "An unexpected error has occurred, please contact the administrator", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private class GetBulkItem extends AsyncTask<String, Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            Intent intent = getIntent();
            int id = intent.getIntExtra("bulkid",-1);
            HttpReq req = new HttpReq();

            return req.GetRequest("http://ehostingcentre.com/gravo/getbulkitems.php?type=single_item&id="+String.valueOf(id));
        }

        @Override
        protected void onPostExecute(String s) {
            refreshLayout.setRefreshing(false);
            super.onPostExecute(s);
            if(s.equals("")||s==null){
            }else{
                Log.e("RESULTS",s);
                try {
                    JSONObject result = new JSONObject(s);
                    int status = result.getInt("status");
                    if(status==200){
                        JSONObject item = result.getJSONArray("result").getJSONObject(0);
                        Glide.with(BulkDetails.this).load(item.getString("image")).into(bulkimage);
                        bulkname.setText(item.getString("full_name"));
                        bulkquote.setText(item.getString("price_quote"));
                        bulkstatus.setText(item.getString("status"));
                        bulkaddress.setText(item.getString("address"));
                        collectiondate.setText(item.getString("collection_date"));
                        collectiondatetiming.setText(item.getString("collection_date_timing"));
                        bulkdescription.setText(item.getString("description"));
                        bulktransactionid.setText(item.getString("transaction_id_key"));
                        int status_id = item.getInt("bulk_transaction_status_id");
                        if(status_id==12){
                            btmlayout.setVisibility(View.VISIBLE);
                            statustwo.setVisibility(View.VISIBLE);
                            statusthree.setVisibility(View.GONE);
                            scheduledate.setVisibility(View.GONE);
                            scheduleddate.setVisibility(View.GONE);
                        }else if(status_id==6){
                            btmlayout.setVisibility(View.GONE);
                            statustwo.setVisibility(View.GONE);
                            statusthree.setVisibility(View.VISIBLE);
                            scheduledate.setVisibility(View.VISIBLE);
                            scheduleddate.setVisibility(View.GONE);
                        }else{
                            btmlayout.setVisibility(View.GONE);
                            statustwo.setVisibility(View.GONE);
                            statusthree.setVisibility(View.GONE);
                            scheduledate.setVisibility(View.GONE);
                            scheduleddate.setVisibility(View.VISIBLE);
                        }

                    }else if(status==404){
                        refreshLayout.setRefreshing(false);
                    }else{
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(BulkDetails.this, "An unexpected error has occurred, please contact the administrator", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public void getdetails(){
        GetBulkItem getBulkItem = new GetBulkItem();
        getBulkItem.execute();
    }
    public void getScheduleDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) ->
                        scheduleDate.setText(dateformattodate(String.format("%s-%s-%d", String.valueOf(dayOfMonth), String.valueOf(monthOfYear + 1), year))), mYear, mMonth+1, mDay);

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
    public String dateformattodate(String date){
        String[] datesplit = date.split("-");
        if(datesplit[1].equals("1")){
            return datesplit[0]+" January "+datesplit[2];
        }else if (datesplit[1].equals("2")){
            return datesplit[0]+" February "+datesplit[2];
        }else if (datesplit[1].equals("3")){
            return datesplit[0]+" March "+datesplit[2];
        }else if (datesplit[1].equals("4")){
            return datesplit[0]+" April "+datesplit[2];
        }else if (datesplit[1].equals("5")){
            return datesplit[0]+" May "+datesplit[2];
        }else if (datesplit[1].equals("6")){
            return datesplit[0]+" June "+datesplit[2];
        }else if (datesplit[1].equals("7")){
            return datesplit[0]+" July "+datesplit[2];
        }else if (datesplit[1].equals("8")){
            return datesplit[0]+" August "+datesplit[2];
        }else if (datesplit[1].equals("9")){
            return datesplit[0]+" September "+datesplit[2];
        }else if (datesplit[1].equals("10")){
            return datesplit[0]+" October "+datesplit[2];
        }else if (datesplit[1].equals("11")){
            return datesplit[0]+" November "+datesplit[2];
        }else if (datesplit[1].equals("12")){
            return datesplit[0]+" December "+datesplit[2];
        }else{
            return "date unavailable";
        }
    }
    public void getScheduleDateTiming(){
        final CharSequence[] items = {"9:00am - 11:30am","12:30pm - 2:00pm","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(BulkDetails.this);
        builder.setTitle("Select Time Slot");
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("9:00am - 11:30am")) {
                scheduleDateTiming.setText("9:00am - 11:30am");
                dialog.dismiss();
            } else if (items[item].equals("12:30pm - 2:00pm")) {
                scheduleDateTiming.setText("12:30pm - 2:00pm");
                dialog.dismiss();
            } else {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
