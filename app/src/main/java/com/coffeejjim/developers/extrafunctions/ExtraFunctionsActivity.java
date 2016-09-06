package com.coffeejjim.developers.extrafunctions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExtraFunctionsActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static final int CUSTOMER = 10;
    public static final int PROVIDER = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_functions);
        ButterKnife.bind(this);
        setCustomActionbar();

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            int user = intent.getIntExtra("user", CUSTOMER);
            if(user == CUSTOMER ) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new CustomerExtraFunctionsFragment())
                        .commit();
            } else if(user == PROVIDER){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new ProviderExtraFunctionsFragment())
                        .commit();
            }
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