package com.greenravolution.gravo.contents;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.adapters.CategoryPagerAdapter;

public class ActivityCategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        TabLayout tab = findViewById(R.id.tabs);
        View headerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.custom_tabs, null, false);

        LinearLayout ll1 = (LinearLayout) headerView.findViewById(R.id.ll1);
        LinearLayout ll2 = (LinearLayout) headerView.findViewById(R.id.ll2);
        LinearLayout ll3 = (LinearLayout) headerView.findViewById(R.id.ll3);
        LinearLayout ll4 = (LinearLayout) headerView.findViewById(R.id.ll4);
        tab.addTab(tab.newTab().setText("PAPER").setCustomView(ll1));
        tab.addTab(tab.newTab().setText("E-WASTE").setCustomView(ll2));
        tab.addTab(tab.newTab().setText("METALS").setCustomView(ll3));
        tab.addTab(tab.newTab().setText("BULK").setCustomView(ll4));
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        final CategoryPagerAdapter adapter = new CategoryPagerAdapter
                (getSupportFragmentManager(), tab.getTabCount());
        viewPager.setAdapter(adapter);
        Intent intent = getIntent();
        viewPager.setCurrentItem(intent.getIntExtra("category",0));
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
}
