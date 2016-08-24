package com.coffeejjim.developers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.coffeejjim.developers.login.LoginActivity;

public class CoffeeJJIMSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_jjimsplash);

        moveLoginActivity();
    }

    private void moveLoginActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(CoffeeJJIMSplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }

    Handler mHandler = new Handler(Looper.getMainLooper());
}
