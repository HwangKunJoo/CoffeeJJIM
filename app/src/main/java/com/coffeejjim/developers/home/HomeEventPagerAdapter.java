package com.coffeejjim.developers.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coffeejjim.developers.R;

/**
 * Created by acer on 2016-08-23.
 */
public class HomeEventPagerAdapter extends FragmentPagerAdapter{


    private final int[] galImages = new int[] {
            R.drawable.event1,
            R.drawable.event2,
            R.drawable.event3,
            R.drawable.event4,
    };

    public HomeEventPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return HomeEventPagerFragment.newInstance(galImages[position]);
    }

    @Override
    public int getCount() {
        return galImages.length;
    }

}
