package com.coffeejjim.developers.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coffeejjim.developers.data.CafeImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class BestRecommendPagerAdapter extends FragmentPagerAdapter{

    List<CafeImage> bestCafeImages = new ArrayList<>();


    public BestRecommendPagerAdapter(FragmentManager fm, List<CafeImage> bestCafeImages) {
        super(fm);
        this.bestCafeImages = bestCafeImages;
    }

    @Override
    public Fragment getItem(int position) {
        return BestRecommendPagerFragment.newInstance(bestCafeImages.get(position));
    }

    @Override
    public int getCount() {
        return bestCafeImages.size();
    }

    @Override
    public float getPageWidth(int position) {
        return 0.6f;
    }
}