package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.greenravolution.gravo.R;
import com.greenravolution.gravo.functions.GetAsyncRequest;
import com.greenravolution.gravo.functions.HttpReq;
import com.greenravolution.gravo.objects.Rates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityLeaderboard extends AppCompatActivity {
    public static final String SESSION = "login_status";
    Toolbar toolbar;
    TextView points, rank, name;
    SharedPreferences sessionManager;
    TextView share, invite;
    CircleImageView profilpic;
    LinearLayout achievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_leaderboard);
        profilpic = findViewById(R.id.profile_image);
        name = findViewById(R.id.name);
        rank = findViewById(R.id.rank);
        points = findViewById(R.id.points);
        share = findViewById(R.id.share);
        invite = findViewById(R.id.invite);
        achievements = findViewById(R.id.achievements);
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        Glide.with(ActivityLeaderboard.this).load(sessionManager.getString("user_image", "https://www.greenravolution.com/API/uploads/291d5076443149a4273f0199fea9db39a3ab4884.png")).into(profilpic);
        points.setText(String.valueOf("Points: " + sessionManager.getInt("user_total_points", -1)));
        name.setText(sessionManager.getString("user_full_name", ""));
        rank.setText(sessionManager.getString("user_rank", "No Rank"));
        share.setOnClickListener(v -> {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            String shareBody = "I have " + String.valueOf(sessionManager.getInt("user_total_points", -1)) + " Points in the gravo app doing recycling!\n you can join me too!\n\nhttps://www.greenravolution.com/\n\nCome and Join me now!";
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        });
        invite.setOnClickListener(v -> {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            String shareBody = "I have " + String.valueOf(sessionManager.getInt("user_total_points", -1)) + " Points in the gravo app doing recycling!\n you can join me too!\n\nhttps://www.greenravolution.com/\n\nCome and Join me now!";
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        });
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());

        GetAchievements getAchievements = new GetAchievements();
        getAchievements.execute();
    }
    public class GetAchievements extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            int id = sessionManager.getInt("user_id", -1);
            String link = "http://ehostingcentre.com/gravo/getachievements.php?id="+id;
            return req.GetRequest(link);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("ACH RES", s);
            try {
                JSONObject results = new JSONObject(s);
                int status = results.getInt("status");
                if(status == 200){
                    JSONArray result = results.getJSONArray("result");
                    for(int i = 0; i < result.length(); i++){
                        JSONObject item = result.getJSONObject(i);
                        String count = String.valueOf(i+1);
                        String title = item.getString("achievement_name");
                        String points = item.getString("points");
                        achievements.addView(initView(count, title, points));
                    }
                }else if(status == 404){
                    Toast.makeText(ActivityLeaderboard.this, "Unable to get achievements", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public View initView(String getcount, String gettitle, String getpoints){
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {
            View view;
            view = inflater.inflate(R.layout.leaderboard_achievents, null);
            TextView count = view.findViewById(R.id.count);
            TextView title = view.findViewById(R.id.title);
            TextView points = view.findViewById(R.id.points);
            ProgressBar progress = view.findViewById(R.id.progress);
            count.setText(getcount);
            title.setText(gettitle);
            points.setText(getpoints);
            if(Integer.parseInt(getpoints)>=100){
                progress.setProgress(100);
            }else{
                progress.setProgress(Integer.parseInt(getpoints));
            }


            return view;
        }else{
            return null;
        }

    }
}

