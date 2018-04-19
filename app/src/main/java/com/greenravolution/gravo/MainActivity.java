package com.greenravolution.gravo;

import android.content.Intent;
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

import com.greenravolution.gravo.MainFragments.Calendar;
import com.greenravolution.gravo.MainFragments.Home;
import com.greenravolution.gravo.MainFragments.Notifications;
import com.greenravolution.gravo.MainFragments.Settings;
import com.greenravolution.gravo.MainFragments.Transactions;
import com.greenravolution.gravo.contents.ActivityCart;
import com.greenravolution.gravo.contents.ActivityHelp;
import com.greenravolution.gravo.contents.ActivityUser;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FragmentTransaction ft;
    ImageView home_page_logo;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home_page_logo = findViewById(R.id.home_page_logo);
        title = findViewById(R.id.title);
        title.setVisibility(View.GONE);

        NavigationView navigationView = findViewById(R.id.left_drawer);
        View headerview = navigationView.getHeaderView(0);
        LinearLayout userProfile = headerview.findViewById(R.id.userProfile);
        userProfile.setOnClickListener(v -> startActivity(new Intent(this, ActivityUser.class)));

        configureNavigationDrawer();
        configureToolbar();

        if (savedInstanceState == null) {
            replacefragment(new Home());
        }
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
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
                    title.setText("Settings");
                    home_page_logo.setVisibility(View.GONE);
                    title.setVisibility(View.VISIBLE);
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

}
