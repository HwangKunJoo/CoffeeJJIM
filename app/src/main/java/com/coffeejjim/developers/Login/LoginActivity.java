package com.coffeejjim.developers.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.home.HomeActivity;
import com.coffeejjim.developers.provider.ProviderHomeActivity;

public class LoginActivity extends AppCompatActivity {

    private static final int SEARCH_ADDRESS = 1;

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

    public void changeReissuance(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ReissuanceFragment())
                .addToBackStack(null)
                .commit();
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


    public void moveProviderHomeActivity(){
        Intent intent = new Intent(this, ProviderHomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SEARCH_ADDRESS || resultCode == RESULT_OK){
            String findAddress = data.getExtras().getString("data");
            Toast.makeText(this, findAddress, Toast.LENGTH_SHORT).show();
        }else
            super.onActivityResult(requestCode, resultCode, data);

    }

}
