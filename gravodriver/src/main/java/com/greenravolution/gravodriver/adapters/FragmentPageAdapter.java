package com.greenravolution.gravodriver.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.greenravolution.gravodriver.bigitems;
import com.greenravolution.gravodriver.history;
import com.greenravolution.gravodriver.today;

public class FragmentPageAdapter extends FragmentPagerAdapter{
    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new today();
            case 1:
                return new history();
            case 2:
                return new bigitems();

            default:
                return null;
        }
    }

    @Override
    public String getPageTitle(int position){
        switch (position) {
            case 0:
                return "Today";
            case 1:
                return "History";
            case 2:
                return "Big Items";

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
