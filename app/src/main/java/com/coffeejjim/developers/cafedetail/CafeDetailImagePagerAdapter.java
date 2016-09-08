package com.coffeejjim.developers.cafedetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coffeejjim.developers.data.CafeDetail;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class CafeDetailImagePagerAdapter extends FragmentPagerAdapter{

    CafeDetail cafeDetail;


    public CafeDetailImagePagerAdapter(FragmentManager fm, CafeDetail cafeDetail) {
        super(fm);
        this.cafeDetail = cafeDetail;
    }

    @Override
    public Fragment getItem(int position) {
        return CafeDetailImageChildFragment.newInstance(cafeDetail.getImages().get(position));
    }

    @Override
    public int getCount() {
        return cafeDetail.getImages().size();
    }
}
