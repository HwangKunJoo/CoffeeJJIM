package com.coffeejjim.developers.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.coffeejjim.developers.HomeActivity;
import com.coffeejjim.developers.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

    public void changeSingup() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SignupFragment())
                .addToBackStack(null)
                .commit();
    }

    public void moveHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void moveFindInfoActivity() {
        Intent intent = new Intent(this, FindInfoActivity.class);
        startActivity(intent);
        finish();
    }

}
