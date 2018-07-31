package com.greenravolution.gravodriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenravolution.gravodriver.Objects.OrderDetails;
import com.greenravolution.gravodriver.Objects.Orders;
import com.greenravolution.gravodriver.adapters.FragmentPageAdapter;
import com.greenravolution.gravodriver.functions.GetAsyncRequest;
import com.greenravolution.gravodriver.functions.HttpReq;
import com.greenravolution.gravodriver.loginsignup.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final String SESSION = "login_status";
    LinearLayout llProgress;
    ImageView progressbar;
    SwipeRefreshLayout refreshLayout;

    TextView collectDate;
    SharedPreferences sessionManager;
    LinearLayout list;
    android.support.v7.widget.Toolbar toolbar;
    ImageView btnProfile;


    GetAsyncRequest.OnAsyncResult asyncResult = (resultCode, message) -> {
        llProgress.setVisibility(View.GONE);
        Log.e("GET TRANSACTION ALL: ", message);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();


        SharedPreferences.Editor editor = sessionManager.edit();
        editor.putString("alltransactionsObject",message);
        editor.commit();
        progressDrawable.stop();

    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Called","Mainactivity oncreate");
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());

        llProgress = findViewById(R.id.llProgress);
        progressbar = findViewById(R.id.progressBar);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        if(hasNetworkConnectivity()){
            getTransactions();
        } else {
            refreshLayout.setRefreshing(false);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            LayoutInflater li = LayoutInflater.from(this);
            final View gtnc = li.inflate(R.layout.dialog_noconnectivity, null);
            dialog.setCancelable(true);
            dialog.setView(gtnc);
            dialog.setPositiveButton("Ok", (dialogInterface, i) ->  startActivity(new Intent(this,Login.class)));
            AlertDialog dialogue = dialog.create();
            dialogue.show();
        }

//        onTabSelected(): triggered when a tab enters the selected state.
//        onTabUnselected(): invoked when a tab exits the selected state.
//        onTabReselected(): invoked when a tab that is already selected is chosen again by the user.
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnProfile = findViewById(R.id.btnProfile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) if (getSupportActionBar().getTitle() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, profile.class);
                startActivity(intent);
            }
        });

    }

    public void getTransactions() {     //get list of items
        llProgress.setVisibility(View.VISIBLE);
        AnimationDrawable progressDrawable = (AnimationDrawable) progressbar.getDrawable();
        progressDrawable.start();
        GetAsyncRequest asyncRequest = new GetAsyncRequest();
        asyncRequest.setOnResultListener(asyncResult);
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        String collectorid = sessionManager.getString("id","");
        asyncRequest.execute("http://ehostingcentre.com/gravo/gettransaction.php?type=withcollectorid&id="+collectorid);
    }



    @Override
    public void onBackPressed() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("Exit");
        dialog.setMessage("Are you sure you want to exit?");
        dialog.setPositiveButton("Yes", (dialogInterface, i) -> finish());
        dialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
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
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Logout");
                dialog.setMessage("Are you sure you want to log out?");
                dialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                    sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sessionManager.edit();
                    editor.clear();
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                });
                dialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
                AlertDialog dialogue = dialog.create();
                dialogue.show();

                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.e("res: ", String.valueOf(requestCode));
            if (data != null) {
                if (data.getStringExtra("type") != null) {
                    if (Objects.equals(data.getStringExtra("type"), "0")) {
                        Log.e("type", "back button");
                    } else if (Objects.equals(data.getStringExtra("type"), "1")) {
                        Log.e("type", "transaction done button");
                    }
                } else {
                    Log.e("type", "phone backpress");
                }
                Log.e("data", "null");
            }

        } else {
            Log.e("res: ", String.valueOf(requestCode));
        }
    }



    public Boolean hasNetworkConnectivity(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }



}
