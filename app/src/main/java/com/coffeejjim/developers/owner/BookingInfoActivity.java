package com.coffeejjim.developers.owner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingInfoActivity extends AppCompatActivity {

    //push때문에 액티비티로 있어야함

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info);


        ButterKnife.bind(this);
        setCustomActionbar();
        Estimate estimate =(Estimate)getIntent().getSerializableExtra("estimate");

        if (savedInstanceState == null) {
            BookingInfoFragment bookingInfoFragment = BookingInfoFragment.newInstance(estimate);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, bookingInfoFragment).commit();
        }
    }

    private void setCustomActionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
