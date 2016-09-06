package com.coffeejjim.developers.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.CafeImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class NewRecommendPagerAdapter extends FragmentPagerAdapter {

    List<CafeImage> newCafeImages = new ArrayList<>();


    public NewRecommendPagerAdapter(FragmentManager fm, List<CafeImage> newCafeImages) {
        super(fm);
        this.newCafeImages = newCafeImages;
    }

    @Override
    public Fragment getItem(int position) {
        return NewRecommendPagerFragment.newInstance(newCafeImages.get(position));
    }

    @Override
    public int getCount() {
        return newCafeImages.size();
    }
}
