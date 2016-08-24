package com.coffeejjim.developers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class BestRecommendPagerAdapter extends FragmentPagerAdapter{

    private final int[] galImages = new int[] {
            R.drawable.bestsample1,
            R.drawable.bestsample2,
            R.drawable.hwang,
            R.drawable.ahn,
            R.drawable.bestsample1,
            R.drawable.bestsample2,
            R.drawable.hwang,
            R.drawable.ahn

    };


    public BestRecommendPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return BestRecommendPagerFragment.newInstance(galImages[position]);
    }

    @Override
    public int getCount() {
        return galImages.length;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.4f;
    }
}
