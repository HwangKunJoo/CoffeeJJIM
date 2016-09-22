package com.coffeejjim.developers.owner.usermanagement.uservisitcount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Customer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserVisitCountActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_visit_count);
        ButterKnife.bind(this);

        customer = (Customer) getIntent().getSerializableExtra("userManagementInfo");

        setCustomActionbar();

        if (savedInstanceState == null) {
            UserVisitCountFragment userVisitCountFragment = UserVisitCountFragment.newInstance(customer);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, userVisitCountFragment)
                    .commit();
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
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
