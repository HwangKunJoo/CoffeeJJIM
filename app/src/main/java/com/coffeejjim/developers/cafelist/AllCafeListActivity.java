package com.coffeejjim.developers.cafelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCafeListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cafe_list);

        ButterKnife.bind(this);
        setCustomActionbar();

        if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new AllCafeListFragment())
                        .commit();
        }
    }

    private void setCustomActionbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        //set custom view layout
//        View mCustomView = getLayoutInflater().inflate(R.layout.view_cafe_reservation_toolbar, null);
//        getSupportActionBar().setCustomView(mCustomView);

        toolbar.setContentInsetsAbsolute(0, 0);

        //getSupportActionBar().setCustomView(mCustomView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_all_cafe_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }else{
            // 자신의 현재 위치 설정하는 코드 필요
        }
        return super.onOptionsItemSelected(item);
    }
}
