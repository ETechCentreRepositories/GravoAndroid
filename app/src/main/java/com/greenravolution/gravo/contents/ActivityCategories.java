package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.adapters.CategoryPagerAdapter;

public class ActivityCategories extends AppCompatActivity {
    ViewPager viewPager;
    CategoryPagerAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());

        TabLayout tab = findViewById(R.id.tabs);
        View headerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.custom_tabs, null, false);

        LinearLayout ll1 = headerView.findViewById(R.id.ll1);
        LinearLayout ll2 = headerView.findViewById(R.id.ll2);
        LinearLayout ll3 = headerView.findViewById(R.id.ll3);
        LinearLayout ll4 = headerView.findViewById(R.id.ll4);
        tab.addTab(tab.newTab().setText("PAPER").setCustomView(ll1));
        tab.addTab(tab.newTab().setText("E-WASTE").setCustomView(ll2));
        tab.addTab(tab.newTab().setText("METALS").setCustomView(ll3));
        tab.addTab(tab.newTab().setText("BULK").setCustomView(ll4));
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = findViewById(R.id.viewPager);
        adapter = new CategoryPagerAdapter
                (getSupportFragmentManager(), tab.getTabCount());
        viewPager.setAdapter(adapter);
        Intent intent = getIntent();
        viewPager.setCurrentItem(intent.getIntExtra("category", 0));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    public int getCurrentTab() {
        return viewPager.getCurrentItem();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            // Android home
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
}
