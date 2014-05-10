package com.tectual.patogh.patogh.models;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tectual.patogh.patogh.LoyaltyFragment;
import com.tectual.patogh.patogh.PatoghFragment;
import com.tectual.patogh.patogh.R;
import com.tectual.patogh.patogh.WalletFragment;

/**
 * Created by arash on 26/04/2014.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        titles = context.getResources().getStringArray(R.array.tab_names);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LoyaltyFragment();
            case 1:
                return new WalletFragment();
            default:
                return new PatoghFragment();
        }
    }

}
