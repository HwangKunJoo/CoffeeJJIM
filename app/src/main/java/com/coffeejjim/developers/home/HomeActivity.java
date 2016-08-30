package com.coffeejjim.developers.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.cafelist.AllCafeListActivity;
import com.coffeejjim.developers.estimate.EstimateSheetActivity;
import com.coffeejjim.developers.extrafunctions.ExtraFunctionsActivity;
import com.coffeejjim.developers.extrafunctions.likelist.LikeListActivity;
import com.coffeejjim.developers.reservation.CafeReservationListActivity;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    ViewPager homeEventPager, bestRecommendPager, newRecommendPager;
    HomeEventPagerAdapter homeEventAdapter;
    BestRecommendPagerAdapter bestRecommendPagerAdapter;
    NewRecommendPagerAdapter newRecommendPagerAdapter;

    public static final int CUSTOMER = 10;


//    private static final int SAMPLE_ID = 34536;
//    private int badgeCount = 10;
//    //setTitle로 제목 달고 뱃지넘버로


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
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
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveHomeActivity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveAllCafeListActivity();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               moveLikeListActivity();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveExtraFunctionsActivity();
            }
        });
    }

    @OnClick(R.id.home_estimate_image)
    public void onEstimateSheet(){
        moveEstimateSheetActivity();
    }

    public void moveEstimateSheetActivity(){
        Intent intent = new Intent(this, EstimateSheetActivity.class);
        startActivity(intent);
    }
    public void moveHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void moveAllCafeListActivity(){
        Intent intent = new Intent(this, AllCafeListActivity.class);
        startActivity(intent);
    }

    public void moveLikeListActivity(){
        Intent intent = new Intent(this, LikeListActivity.class);
        startActivity(intent);
    }

    public void moveExtraFunctionsActivity(){
        Intent intent = new Intent(this, ExtraFunctionsActivity.class);
        intent.putExtra("user", CUSTOMER);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;


        // 뱃지 알림 처리 알고리즘 추가
//        //you can add some logic (hide it if the count == 0)
//        if (badgeCount > 0) {
//            ActionItemBadge.update(this, menu.findItem(R.id.item_samplebadge), FontAwesome.Icon.faw_bell, ActionItemBadge.BadgeStyles.RED, badgeCount);
//        } else {
//            ActionItemBadge.hide(menu.findItem(R.id.item_samplebadge));
//        }

        //If you want to add your ActionItem programmatically you can do this too. You do the following:

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_samplebadge)
        {
            Toast.makeText(this, R.string.sample_3, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CafeReservationListActivity.class);
            startActivity(intent);
//            badgeCount--;
//            ActionItemBadge.update(item, badgeCount);
        }
        else if(id == R.id.item_sampleEstimate)
        {
            moveEstimateSheetActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setCustomActionbar() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

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



