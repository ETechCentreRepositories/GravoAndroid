package com.greenravolution.gravo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.greenravolution.gravo.MainFragments.Calendar;
import com.greenravolution.gravo.MainFragments.Home;
import com.greenravolution.gravo.MainFragments.Notifications;
import com.greenravolution.gravo.MainFragments.Settings;
import com.greenravolution.gravo.MainFragments.Transactions;
import com.greenravolution.gravo.contents.ActivityCart;
import com.greenravolution.gravo.contents.ActivityHelp;
import com.greenravolution.gravo.contents.ActivityUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sessionManager;
    public static final String SESSION = "login_status";
    DrawerLayout drawerLayout;
    FragmentTransaction ft;
    ImageView home_page_logo;
    TextView title, user_name, user_points;
    CircleImageView profileimage;
    Toolbar toolbar;

    public void updateprofile() {
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        Glide.with(MainActivity.this).load(sessionManager.getString("user_image", "https://www.greenravolution.com/API/uploads/291d5076443149a4273f0199fea9db39a3ab4884.png")).into(profileimage);
        user_points.setText(String.valueOf("Points: " + sessionManager.getInt("user_total_points", 0)) + " ("+sessionManager.getString("user_rank","No rank yet")+")");
        user_name.setText(sessionManager.getString("user_full_name", ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home_page_logo = findViewById(R.id.home_page_logo);
        title = findViewById(R.id.title);
        title.setVisibility(View.GONE);
        sessionManager = getSharedPreferences(SESSION, Context.MODE_PRIVATE);

        NavigationView navigationView = findViewById(R.id.left_drawer);
        View headerview = navigationView.getHeaderView(0);
        LinearLayout userProfile = headerview.findViewById(R.id.userProfile);
        profileimage = headerview.findViewById(R.id.profile_image);

        Glide.with(MainActivity.this).load(sessionManager.getString("user_image", "https://www.greenravolution.com/API/uploads/291d5076443149a4273f0199fea9db39a3ab4884.png")).into(profileimage);

        user_name = headerview.findViewById(R.id.user_name);
        user_name.setText(sessionManager.getString("user_full_name", ""));

        user_points = headerview.findViewById(R.id.user_points);
        user_points.setText(String.format("%s (No rank yet)", String.valueOf("Points: " + sessionManager.getInt("user_total_points", 0))));

        userProfile.setOnClickListener(v -> startActivity(new Intent(this, ActivityUser.class)));

        configureNavigationDrawer();
        configureToolbar();

        if (savedInstanceState == null) {
            replacefragment(new Home());
        }
    }

    private void configureToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setHomeAsUpIndicator(R.drawable.ic_hamburger);
        actionbar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void configureNavigationDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.left_drawer);
        navView.setNavigationItemSelectedListener(menuItem -> {

            int itemId = menuItem.getItemId();

            switch (itemId) {
                case R.id.main:
                    Log.i("MainActivity", "clicked on main item");
                    replacefragment(new Home());
                    home_page_logo.setVisibility(View.VISIBLE);
                    title.setVisibility(View.GONE);
                    CloseDrawer();
                    return true;

                case R.id.notifications:
                    Log.i("MainActivity", "clicked on notifications item");
                    replacefragment(new Notifications());
                    home_page_logo.setVisibility(View.GONE);
                    title.setVisibility(View.VISIBLE);
                    title.setText("Notifications");
                    CloseDrawer();
                    return true;

                case R.id.transactions:
                    Log.i("MainActivity", "clicked on transaction item");
                    replacefragment(new Transactions());
                    title.setText("Transactions");
                    home_page_logo.setVisibility(View.GONE);
                    title.setVisibility(View.VISIBLE);
                    CloseDrawer();
                    return true;

                case R.id.calendar:
                    Log.i("MainActivity", "clicked on calendar item");
                    replacefragment(new Calendar());
                    title.setText("Calendar");
                    home_page_logo.setVisibility(View.GONE);
                    title.setVisibility(View.VISIBLE);
                    CloseDrawer();
                    return true;

                case R.id.settings:
                    replacefragment(new Settings());
                    Log.i("MainActivity", "clicked on settings item");
                    title.setText("About Us");
                    home_page_logo.setVisibility(View.GONE);
                    title.setVisibility(View.VISIBLE);
                    CloseDrawer();
                    return true;
                case R.id.share:
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Hey there! I feel good about recycling. Best Thing? I make money too.\n" +
                            "Download the GRAVO App now to save mother earth and make some money too.\nhttp://www.greenravolution.com";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    CloseDrawer();
                    return true;

            }
            return false;
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            // Android home
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    CloseDrawer();
                    return true;
                } else {
                    OpenDrawer();
                    return true;
                }
                // manage other entries if you have it ...
            case R.id.cart:
                Log.i("MainActivity", "clicked on cart");
                startActivity(new Intent(this, ActivityCart.class));
                return true;
            case R.id.help:
                Log.i("MainActivity", "clicked on help");
                startActivity(new Intent(this, ActivityHelp.class));
                return true;

        }

        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);//Menu Resource, Menu
        return true;
    }

    public void replacefragment(Fragment fragment) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }

    public void CloseDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void OpenDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateprofile();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateprofile();
    }
}
