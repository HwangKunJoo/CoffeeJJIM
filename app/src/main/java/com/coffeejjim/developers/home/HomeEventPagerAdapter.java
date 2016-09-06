package com.coffeejjim.developers.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coffeejjim.developers.data.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2016-08-23.
 */
public class HomeEventPagerAdapter extends FragmentPagerAdapter{

    List<Event> eventImages = new ArrayList<>();

    public HomeEventPagerAdapter(FragmentManager fm, List<Event> eventImages) {
        super(fm);
        this.eventImages = eventImages;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeEventPagerFragment.newInstance(eventImages.get(position));
    }

    @Override
    public int getCount() {
        return eventImages.size();
    }

}
