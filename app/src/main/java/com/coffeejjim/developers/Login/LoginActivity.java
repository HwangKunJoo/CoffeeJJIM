package com.coffeejjim.developers.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.home.HomeActivity;
import com.coffeejjim.developers.owner.OwnerHomeActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG_ONE = "tag_one";

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


    public void changeReissuance() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ReissuanceFragment())
                .addToBackStack(null)
                .commit();
    }

    public void changeSingup() {
        Fragment old = getSupportFragmentManager()
                .findFragmentByTag(TAG_ONE);
        if (old == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SignupFragment(), TAG_ONE)
                    .addToBackStack(null)
                    .commit();
        }
    }
    public void moveHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    public void moveProviderHomeActivity() {
        Intent intent = new Intent(this, OwnerHomeActivity.class);
        startActivity(intent);
        finish();
    }

}
