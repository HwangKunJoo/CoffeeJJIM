package com.coffeejjim.developers.reservation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.coffeejjim.developers.R;

public class CafeReservationActivity extends AppCompatActivity {

    boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_reservation);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState == null) {
            if(!isChecked) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new CafeReservationListFragment())
                        .commit();
            }else // 입찰카페가 없으면 빈 화면 표시
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new CafeListEmptyFragment())
                        .commit();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new CafeReservationListFragment())
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
