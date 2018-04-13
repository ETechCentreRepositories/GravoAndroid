package com.greenravolution.gravo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.greenravolution.gravo.CategoryFragments.EWaste;
import com.greenravolution.gravo.CategoryFragments.Paper;
import com.greenravolution.gravo.CategoryFragments.Bulk;
import com.greenravolution.gravo.CategoryFragments.Metals;

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
                Paper paper = new Paper();
                return paper;
            case 1:
                EWaste eWaste = new EWaste();
                return eWaste;
            case 2:
                Metals metals = new Metals();
                return metals;
            case 3:
                Bulk bulk = new Bulk();
                return bulk;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
