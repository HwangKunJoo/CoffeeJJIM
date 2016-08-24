package com.coffeejjim.developers;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    ViewPager homeEventPager, bestRecommendPager, newRecommendPager;
    HomeEventPagerAdapter homeEventAdapter;
    BestRecommendPagerAdapter bestRecommendPagerAdapter;
    NewRecommendPagerAdapter newRecommendPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        setCustomActionbar();
//
//        homeEventPager = (ViewPager)findViewById(R.id.home_event_pager);
//        homeEventAdapter = new HomeEventPagerAdapter(getSupportFragmentManager());
//        homeEventPager.setAdapter(homeEventAdapter);
//
//        bestRecommendPager = (ViewPager)findViewById(R.id.home_best_pager);
//        bestRecommendPagerAdapter = new BestRecommendPagerAdapter(getSupportFragmentManager());
//        bestRecommendPager.setAdapter(bestRecommendPagerAdapter);
//
//        newRecommendPager = (ViewPager)findViewById(R.id.home_new_pager);
//        newRecommendPagerAdapter = new NewRecommendPagerAdapter(getSupportFragmentManager());
//        newRecommendPager.setAdapter(newRecommendPagerAdapter);
//
    }

//
//    private void setCustomActionbar(){
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        //set custom view layout
//        View mCustomView = getLayoutInflater().inflate(R.layout.view_home_toolbar,null);
//        getSupportActionBar().setCustomView(mCustomView);
//
//        toolbar.setContentInsetsAbsolute(0,0);
//
//        getSupportActionBar().setCustomView(mCustomView);
//    }


}
