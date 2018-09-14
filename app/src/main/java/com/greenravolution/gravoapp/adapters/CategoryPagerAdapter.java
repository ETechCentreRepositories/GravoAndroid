package com.greenravolution.gravoapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.greenravolution.gravoapp.CategoryFragments.Bulk;
import com.greenravolution.gravoapp.CategoryFragments.EWaste;
import com.greenravolution.gravoapp.CategoryFragments.Metals;
import com.greenravolution.gravoapp.CategoryFragments.Paper;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public CategoryPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Paper();
            case 1:
                return new EWaste();
            case 2:
                return new Metals();
            case 3:
                return new Bulk();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
