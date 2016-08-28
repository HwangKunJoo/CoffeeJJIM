package com.coffeejjim.developers.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coffeejjim.developers.R;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class NewRecommendPagerAdapter extends FragmentPagerAdapter {

    private final int[] galImages = new int[] {
            R.drawable.new1,
            R.drawable.new2,
            R.drawable.new3,
    };

    public NewRecommendPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NewRecommendPagerFragment.newInstance(galImages[position]);
    }

    @Override
    public int getCount() {
        return galImages.length;
    }
}
