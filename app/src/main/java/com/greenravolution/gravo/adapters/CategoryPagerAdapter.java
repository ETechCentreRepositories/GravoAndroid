package com.greenravolution.gravo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.greenravolution.gravo.CategoryFragments.Bulk;
import com.greenravolution.gravo.CategoryFragments.EWaste;
import com.greenravolution.gravo.CategoryFragments.Metals;
import com.greenravolution.gravo.CategoryFragments.Paper;

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
