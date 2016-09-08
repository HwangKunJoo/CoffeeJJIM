package com.coffeejjim.developers.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.home.HomeActivity;
import com.coffeejjim.developers.owner.OwnerHomeActivity;

public class LoginActivity extends AppCompatActivity {

    private static final int SEARCH_ADDRESS = 1;
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
//        } else {
//            FragmentTransaction ft = getSupportFragmentManager()
//                    .beginTransaction();
//            SignupFragment f = new SignupFragment();
//            f = SignupFragment.newInstance("data");
//            ft.replace(R.id.container, f, TAG_ONE);
//            ft.commitAllowingStateLoss();
//        }

    }

    public void moveHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    public void moveProviderHomeActivity() {
        Intent intent = new Intent(this, OwnerHomeActivity.class);
        startActivity(intent);
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == SEARCH_ADDRESS || resultCode == RESULT_OK) {
//            String findAddress = data.getExtras().getString("data");
//            Bundle addressData = new Bundle();
//            addressData.putString("data", findAddress);
//            SignupFragment sf = new SignupFragment();
//            sf.setArguments(addressData);
//            changeSingup();
//        } else
//            super.onActivityResult(requestCode, resultCode, data);
//
//    }

}
