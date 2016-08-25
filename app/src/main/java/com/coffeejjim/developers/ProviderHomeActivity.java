package com.coffeejjim.developers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.view.ViewGroup;

public class ProviderHomeActivity extends AppCompatActivity {

    ViewGroup rootContainer;
    Scene scene1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);
    }
}
