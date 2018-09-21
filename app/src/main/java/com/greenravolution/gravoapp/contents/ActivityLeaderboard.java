package com.greenravolution.gravoapp.contents;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.greenravolution.gravoapp.R;
import com.greenravolution.gravoapp.functions.HttpReq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityLeaderboard extends AppCompatActivity {
    public static final String SESSION = "login_status";
    Toolbar toolbar;
    TextView points, rank, name;
    SharedPreferences sessionManager;
    TextView share;
    CircleImageView profilpic;
    LinearLayout achievements, summary, totalkgpiece, layout;
    // Creating Separate Directory for saving Generated Images
    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/UserSignature/";
    String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String StoredPath = DIRECTORY + pic_name + ".png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_leaderboard);
        profilpic = findViewById(R.id.profile_image);
        name = findViewById(R.id.name);
        rank = findViewById(R.id.rank);
        points = findViewById(R.id.points);
        share = findViewById(R.id.share);


        achievements = findViewById(R.id.achievements);
        summary = findViewById(R.id.summary);
        totalkgpiece = findViewById(R.id.totalkgpiece);
        layout = findViewById(R.id.layout);

        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        Glide.with(ActivityLeaderboard.this).load(sessionManager.getString("user_image", "https://www.greenravolution.com/API/uploads/291d5076443149a4273f0199fea9db39a3ab4884.png")).into(profilpic);
        points.setText(String.valueOf(sessionManager.getInt("user_total_points", -1)));
        name.setText(sessionManager.getString("user_full_name", ""));
        rank.setText(sessionManager.getString("user_rank", "Status Unavailable"));
        share.setOnClickListener(v -> {
//            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//            sharingIntent.setType("text/plain");
//            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            String shareBody = "Hey… Use GRAVO to record what you recycle, what you achieve and the rewards you get. \n" +
                    " Join GRAVO and save Mother Earth\n\nDownload here NOW!\nhttps://www.greenravolution.com/";
//            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//            startActivity(Intent.createChooser(sharingIntent, "Share via"));
            layout.setPadding(20,20,20,20);
            share.setVisibility(View.GONE);
            layout.setDrawingCacheEnabled(true);
            layout.buildDrawingCache();
            Bitmap bitmap = layout.getDrawingCache();
            layout.setPadding(0,0,0,0);
            share.setVisibility(View.VISIBLE);
            // save bitmap to cache directory
            try {
                File cachePath = new File(getCacheDir(), "images");
                cachePath.mkdirs(); // don't forget to make the directory
                FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                stream.close();
                File imagePath = new File(getCacheDir(), "images");
                File newFile = new File(imagePath, "image.png");
                Uri contentUri = FileProvider.getUriForFile(this, "com.greenravolution.gravoapp.fileprovider", newFile);

                if (contentUri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.setType("image/png");
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                    shareIntent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
                    shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

// See if official Facebook app is found
//                    boolean facebookAppFound = false;
//                    List<ResolveInfo> matches = getPackageManager().queryIntentActivities(shareIntent, 0);
//                    for (ResolveInfo info : matches) {
//                        if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
//                            shareIntent.setPackage(info.activityInfo.packageName);
//                            facebookAppFound = true;
//                            break;
//                        }
//                    }
//
//// As fallback, launch sharer.php in a browser
//                    if (!facebookAppFound) {
//                        String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + shareBody;
//                        shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
//                    }
                    startActivity(Intent.createChooser(shareIntent, "Choose an app"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());

        GetAchievements getAchievements = new GetAchievements();
        getAchievements.execute();
    }

    public class GetAchievements extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpReq req = new HttpReq();
            sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
            int id = sessionManager.getInt("user_id", -1);
            String link = "http://ehostingcentre.com/gravo/getachievements.php?id=" + id;
            return req.GetRequest(link);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("ACH RES", s);
            try {
                JSONObject results = new JSONObject(s);
                int status = results.getInt("status");
                if (status == 200) {
                    JSONArray result = results.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject item = result.getJSONObject(i);
                        String count = String.valueOf(i + 1);
                        String title = item.getString("achievement_name");
                        String points = item.getString("points");
                        String category = item.getString("category_id");
                        if (category.equals("paper") || category.equals("metals") || category.equals("ewaste")) {
                            achievements.addView(initView(count, title, points, category));
                        } else if (category.equals("total_trees") || category.equals("total_co2")) {
                            TextView boxtitle = findViewById(R.id.title);
                            TextView boxtotalweight = findViewById(R.id.totalweight);
                            TextView boxtitle2 = findViewById(R.id.title2);
                            TextView boxtotalweight2 = findViewById(R.id.totalweight2);
                            if (category.equals("total_co2")) {
                                boxtotalweight.setText(String.format("%s", points));
                                boxtitle.setText(title.split("-")[0] + "\n" + title.split("-")[1]);
                            } else if (category.equals("total_trees")) {
                                boxtotalweight2.setText(String.format("%s", points));
                                boxtitle2.setText(title.split("-")[0] + "\n" + title.split("-")[1]);
                            }
                        } else if (category.equals("total_kg") || category.equals("total_price")) {
                            totalkgpiece.addView(initView(count, title, points, category));
                        }
                    }
                } else if (status == 404) {
                    Toast.makeText(ActivityLeaderboard.this, "Unable to get achievements", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public View initView(String getcount, String gettitle, String getpoints, String category) {
        if (category.equals("paper") || category.equals("metals") || category.equals("ewaste")) {
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

            if (inflater != null) {
                View view;
                view = inflater.inflate(R.layout.leaderboard_achievents, null);
                TextView count = view.findViewById(R.id.count);
                TextView title = view.findViewById(R.id.title);
                TextView points = view.findViewById(R.id.points);
                ProgressBar progress = view.findViewById(R.id.progress);
                ImageView achievementimage = view.findViewById(R.id.achievementimage);
                if (category.equals("paper")) {
                    achievementimage.setBackgroundColor(getResources().getColor(R.color.brand_yellow));
                    achievementimage.setBackground(getResources().getDrawable(R.drawable.paper_main));
                } else if (category.equals("metals")) {
                    achievementimage.setBackgroundColor(getResources().getColor(R.color.brand_orange));
                    achievementimage.setBackground(getResources().getDrawable(R.drawable.metal_main));
                } else if (category.equals("ewaste")) {
                    achievementimage.setBackgroundColor(getResources().getColor(R.color.brand_purple));
                    achievementimage.setBackground(getResources().getDrawable(R.drawable.ewaste_main));
                }
                count.setText(getcount);
                title.setText(gettitle);
                points.setText(getpoints + " Points");
                if (Integer.parseInt(getpoints) >= 100) {
                    progress.setProgress(100);
                } else {
                    progress.setProgress(Integer.parseInt(getpoints));
                }
                return view;
            } else {
                return null;
            }
        } else if (category.equals("total_kg") || category.equals("total_price")) {
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

            if (inflater != null) {
                View view;
                view = inflater.inflate(R.layout.total_kg_piece, null);
                TextView title = view.findViewById(R.id.title);
                TextView totalweight = view.findViewById(R.id.totalweight);
                title.setText(gettitle);
                if (category.equals("total_kg")) {
                    totalweight.setText(String.format("%s KG", getpoints));
                } else if (category.equals("total_co2")) {
                    totalweight.setText(String.format("%s", getpoints));
                } else if (category.equals("total_trees")) {
                    totalweight.setText(String.format("%.2f", Double.parseDouble(getpoints)));
                } else if (category.equals("total_price")) {
                    totalweight.setText(String.format("$%.2f", Double.parseDouble(getpoints)));
                }
                return view;
            }
        }
        return null;
    }


}

