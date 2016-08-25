package com.coffeejjim.developers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ProviderHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.provider_home_cotainer, new ProviderHomeFragment())
                    .commit();
        }
    }

    public void changeProviderHomeEdit(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.provider_home_cotainer, new ProviderHomeEditFragment())
                .addToBackStack(null)
                .commit();
    }

    public void changeProviderHome(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.provider_home_cotainer, new ProviderHomeFragment())
                .addToBackStack(null)
                .commit();
    }
}
