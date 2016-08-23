package com.coffeejjim.developers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setCustomActionbar();
    }

    private void setCustomActionbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //set custom view layout
        View mCustomView = getLayoutInflater().inflate(R.layout.view_home_toolbar,null);
        getSupportActionBar().setCustomView(mCustomView);

        toolbar.setContentInsetsAbsolute(0,0);

        getSupportActionBar().setCustomView(mCustomView);
    }
}
