package com.greenravolution.gravodriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.functions.HttpReq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SummaryList extends AppCompatActivity {
    SwipeRefreshLayout refreshLayout;
    LinearLayout summarylist;
    Toolbar toolbar;
    SharedPreferences sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_list);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        summarylist = findViewById(R.id.summarylist);
        refreshLayout = findViewById(R.id.refreshlayout);
        Intent intent = getIntent();
        try {
            JSONArray summaries = new JSONArray(intent.getStringExtra("summaries"));
            listsummaries(summaries);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetSummaries();
            }
        });

    }
    public void listsummaries(JSONArray array){
        summarylist.removeAllViews();
        for(int i=0;i<=array.length();i++){
            try {
                JSONObject summary = array.getJSONObject(i);

                summarylist.addView(initView(summary,i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public View initView(JSONObject summary, int position){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.summary_item,null,false);

        CardView carditem = view.findViewById(R.id.carditem);
        TextView totalWeight = view.findViewById(R.id.total_weight);
        TextView weightlabel = view.findViewById(R.id.weightlabel);
        TextView totalPrice = view.findViewById(R.id.total_price);
        TextView pricelabel = view.findViewById(R.id.pricelabel);
        TextView totalPaper = view.findViewById(R.id.total_paper);
        TextView paperlabel = view.findViewById(R.id.paperlabel);
        TextView totalEwaste = view.findViewById(R.id.total_ewaste);
        TextView ewastelabel = view.findViewById(R.id.ewastelabel);
        TextView totalMetals = view.findViewById(R.id.total_metals);
        TextView metalslabel = view.findViewById(R.id.metalslabel);
        TextView date = view.findViewById(R.id.date);

        try {
            String weight = summary.getString("total_weight");
            String price = summary.getString("total_price");
            String paper = summary.getString("total_paper");
            String ewaste = summary.getString("total_ewaste");
            String metals = summary.getString("total_metals");
            String getdate = summary.getString("summary_date");

            totalWeight.setText(String.format("%sKG", weight));
            totalPaper.setText(String.format("%sKG", paper));
            totalEwaste.setText(String.format("%sKG", ewaste));
            totalMetals.setText(String.format("%sKG", metals));
            totalPrice.setText(String.format("$%s", price));
            if(position == 0){
                date.setText(String.format("%s (Today)", dateformattodate(getdate)));
            }else{
                carditem.setCardBackgroundColor(getResources().getColor(R.color.grey));
                date.setTextColor(getResources().getColor(R.color.white));
                totalEwaste.setTextColor(getResources().getColor(R.color.white));
                totalMetals.setTextColor(getResources().getColor(R.color.white));
                totalPaper.setTextColor(getResources().getColor(R.color.white));
                totalPrice.setTextColor(getResources().getColor(R.color.white));
                totalWeight.setTextColor(getResources().getColor(R.color.white));
                weightlabel.setTextColor(getResources().getColor(R.color.white));
                pricelabel.setTextColor(getResources().getColor(R.color.white));
                paperlabel.setTextColor(getResources().getColor(R.color.white));
                metalslabel.setTextColor(getResources().getColor(R.color.white));
                ewastelabel.setTextColor(getResources().getColor(R.color.white));
                date.setText(String.format("%s", dateformattodate(getdate)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
    public String dateformattodate(String date) {
        String[] datesplit = date.split("-");
        if (datesplit[1].equals("1")||datesplit[1].equals("01")) {
            return datesplit[2] + " January " + datesplit[0];
        } else if (datesplit[1].equals("2")||datesplit[1].equals("02")) {
            return datesplit[2] + " February " + datesplit[0];
        } else if (datesplit[1].equals("3")||datesplit[1].equals("03")) {
            return datesplit[2] + " March " + datesplit[0];
        } else if (datesplit[1].equals("4")||datesplit[1].equals("04")) {
            return datesplit[2] + " April " + datesplit[0];
        } else if (datesplit[1].equals("5")||datesplit[1].equals("05")) {
            return datesplit[2] + " May " + datesplit[0];
        } else if (datesplit[1].equals("6")||datesplit[1].equals("06")) {
            return datesplit[2] + " June " + datesplit[0];
        } else if (datesplit[1].equals("7")||datesplit[1].equals("07")) {
            return datesplit[2] + " July " + datesplit[0];
        } else if (datesplit[1].equals("8")||datesplit[1].equals("08")) {
            return datesplit[2] + " August " + datesplit[0];
        } else if (datesplit[1].equals("9")||datesplit[1].equals("09")) {
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
    public void GetSummaries(){
        try {
            String getsumm = new getsumamries().execute().get();
            JSONObject summaries = new JSONObject(getsumm);
            int status = summaries.getInt("status");
            if(status==200){
                JSONArray summaryarray = summaries.getJSONArray("summaries");
                listsummaries(summaryarray);
            }else if(status==404){
                Toast.makeText(this, "Unable to get your summary.", Toast.LENGTH_LONG).show();
            }else if(status==400){
                Toast.makeText(this, "An unexpected error has occurred", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "An unexpected error has occurred", Toast.LENGTH_LONG).show();
            }
            Log.e("SUMMARIES", getsumm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public class getsumamries extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            sessionManager = getSharedPreferences("login_status", Context.MODE_PRIVATE);
            String userid = sessionManager.getString("id","");
            return req.GetRequest("http://ehostingcentre.com/gravo/getsummaries.php?collectorid="+userid);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            refreshLayout.setRefreshing(false);
        }
    }
}
