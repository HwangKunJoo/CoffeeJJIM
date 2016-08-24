package com.coffeejjim.developers.estimate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.login.LoginFragment;

public class EstimateSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_sheet);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new EstimateSheetFragment())
                    .commit();
        }
    }
}
