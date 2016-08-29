package com.coffeejjim.developers.provider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProviderHomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);
        ButterKnife.bind(this);
        setCustomActionbar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.provider_home_container, new ProviderHomeFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_provider_home, menu);
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
        if(id == android.R.id.home){
            finish();
        }
        else if (id == R.id.provider_home_item_badge)
        {
            Toast.makeText(this, "진행중인 경매 목록으로 이동합니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AuctionProcessActivity.class);
            startActivity(intent);
//            badgeCount--;
//            ActionItemBadge.update(item, badgeCount);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setCustomActionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    //edit 도중 백키나 홈애스업버튼을 눌렀을 때는 다시 홈 화면이 나와야됨
    public void changeProviderHomeEdit(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.provider_home_container, new ProviderHomeEditFragment())
                .commit();
    }

    public void changeProviderHome(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.provider_home_container, new ProviderHomeFragment())
                .commit();
    }
}
