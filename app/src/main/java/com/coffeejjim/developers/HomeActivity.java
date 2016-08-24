package com.coffeejjim.developers;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class HomeActivity extends AppCompatActivity {

    ViewPager homeEventPager, bestRecommendPager, newRecommendPager;
    HomeEventPagerAdapter homeEventAdapter;
    BestRecommendPagerAdapter bestRecommendPagerAdapter;
    NewRecommendPagerAdapter newRecommendPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setCustomActionbar();

        homeEventPager = (ViewPager) findViewById(R.id.home_event_pager);
        homeEventAdapter = new HomeEventPagerAdapter(getSupportFragmentManager());
        homeEventPager.setAdapter(homeEventAdapter);

        bestRecommendPager = (ViewPager) findViewById(R.id.home_best_pager);
        bestRecommendPagerAdapter = new BestRecommendPagerAdapter(getSupportFragmentManager());
        bestRecommendPager.setAdapter(bestRecommendPagerAdapter);

        newRecommendPager = (ViewPager) findViewById(R.id.home_new_pager);
        newRecommendPagerAdapter = new NewRecommendPagerAdapter(getSupportFragmentManager());
        newRecommendPager.setAdapter(newRecommendPagerAdapter);

        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.floatsample);

        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton actionButton
                = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(this)
                .setContentView(icon).build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageResource(R.drawable.floatbutton);
        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.floatbutton);
        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.floatbutton);
        ImageView itemIcon4 = new ImageView(this);
        itemIcon4.setImageResource(R.drawable.floatbutton);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(actionButton)
                .build();

    }


    private void setCustomActionbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //set custom view layout
        View mCustomView = getLayoutInflater().inflate(R.layout.view_home_toolbar, null);
        getSupportActionBar().setCustomView(mCustomView);

        toolbar.setContentInsetsAbsolute(0, 0);

        getSupportActionBar().setCustomView(mCustomView);
    }
}



