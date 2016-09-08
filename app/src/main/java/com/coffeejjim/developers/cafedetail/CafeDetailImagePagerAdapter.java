package com.coffeejjim.developers.cafedetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coffeejjim.developers.data.CafeInfo;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class CafeDetailImagePagerAdapter extends FragmentPagerAdapter{

    CafeInfo cafeInfo;


    public CafeDetailImagePagerAdapter(FragmentManager fm, CafeInfo cafeInfo) {
        super(fm);
        this.cafeInfo = cafeInfo;
    }

    @Override
    public Fragment getItem(int position) {
        return CafeDetailImageChildFragment.newInstance(cafeInfo.getImages().get(position));
    }

    @Override
    public int getCount() {
        return cafeInfo.getImages().size();
    }
}
