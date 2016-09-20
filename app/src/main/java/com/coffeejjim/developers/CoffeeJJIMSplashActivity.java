package com.coffeejjim.developers;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Owner;
import com.coffeejjim.developers.gcm.RegistrationIntentService;
import com.coffeejjim.developers.home.HomeActivity;
import com.coffeejjim.developers.login.LoginActivity;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.manager.PropertyManager;
import com.coffeejjim.developers.owner.OwnerHomeActivity;
import com.coffeejjim.developers.owner.auctionprocess.AuctionProcessActivity;
import com.coffeejjim.developers.request.OwnerLoginRequest;
import com.coffeejjim.developers.request.SessionCheckRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class CoffeeJJIMSplashActivity extends AppCompatActivity {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private SharedPreferences appSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_jjimsplash);

        //////////////////////////////////////////////////////////////////////////////////
        ////////////////////// 바탕화면 바로가기 만들기 /////////////////////////////////
        appSettings = getSharedPreferences("CoffeeJJIM", MODE_PRIVATE);
        // Make sure you only run addShortcut() once, not to create duplicate shortcuts.
        if (!appSettings.getBoolean("shortcut", false)) {
            addShortcut();
        }
        //////////////////////////////////////////////////////////////////////////////////

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                doRealStart();
            }
        };
        setUpIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAY_SERVICES_RESOLUTION_REQUEST &&
                resultCode == Activity.RESULT_OK) {
            setUpIfNeeded();
        }
    }

    private void setUpIfNeeded() {
        if (checkPlayServices()) {
            String regId = PropertyManager.getInstance().getRegistrationId();
            if (!regId.equals("")) {
                doRealStart();
            } else {
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }
        }
    }

    private void doRealStart() {
        SessionCheckRequest SCRequest = new SessionCheckRequest(this);
        NetworkManager.getInstance().getNetworkData(SCRequest, new NetworkManager.OnResultListener<NetworkResult<Object>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Object>> request, NetworkResult<Object> result) {
                if (result.getCode() == 1) { //shared 비교하는 게 있으면 좋을 듯
                    Toast.makeText(CoffeeJJIMSplashActivity.this, "점주 로그인 성공", Toast.LENGTH_SHORT).show();
                    moveOwnerHomeActivity();
                } else if (result.getCode() == 2) {
                    Toast.makeText(CoffeeJJIMSplashActivity.this, "사용자 로그인 성공", Toast.LENGTH_SHORT).show();
                    moveHomeActivity();
                } else if (result.getCode() == 0) {
                    if (result.getMessage().equals("no 로그인")) {
                        Toast.makeText(CoffeeJJIMSplashActivity.this, "not login", Toast.LENGTH_SHORT).show();
                        loginSharedPreference();
                        return;
                    }
                    moveLoginActivity();
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Object>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    private void loginSharedPreference() {
        if (isAutoLogin()) {
            processAutoLogin();
        } else {
            moveLoginActivity();
        }
    }

    private boolean isAutoLogin() {
        String ownerId = PropertyManager.getInstance().getOwnerId();
        return !TextUtils.isEmpty(ownerId);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Dialog dialog = apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

    private void processAutoLogin() {
        String ownerId = PropertyManager.getInstance().getOwnerId();
        if (!TextUtils.isEmpty(ownerId)) {
            String password = PropertyManager.getInstance().getPassword();
            String regid = PropertyManager.getInstance().getRegistrationId();
            OwnerLoginRequest request = new OwnerLoginRequest(this, ownerId, password, regid);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Owner>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<Owner>> request, NetworkResult<Owner> result) {
                    moveHomeActivity();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<Owner>> request, int errorCode, String errorMessage, Throwable e) {
                    moveLoginActivity();
                }
            });
        }
    }

    public void moveOwnerHomeActivity() {
        String key = getIntent().getStringExtra("key");
        if (!TextUtils.isEmpty(key)) {
            if (key.equals(AuctionProcessActivity.AUCTION_PROCESS_NOTI)) {
                Intent homeIntent = new Intent(this, OwnerHomeActivity.class);
                Intent auctionProcessIntent = new Intent(this, AuctionProcessActivity.class);
                Intent[] intents = {homeIntent, auctionProcessIntent};
                startActivities(intents);
                finish();
            }
        } else {
            Intent intent = new Intent(this, OwnerHomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void moveHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
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

    //////////////////////////////////////////////////////////////////////////////////
    ////////////////////// 바탕화면 바로가기 만들기 /////////////////////////////////
    private void addShortcut() {
        //Adding shortcut for MainActivity
        //on Home screen
        Intent shortcutIntent = new Intent(getApplicationContext(), CoffeeJJIMSplashActivity.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "CoffeeJJIM");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(getApplicationContext(),
                        R.drawable.launcher_192));
        addIntent.putExtra("duplicate", false);
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);

        SharedPreferences.Editor prefEditor = appSettings.edit();
        prefEditor.putBoolean("shortcut", true);
        prefEditor.commit();
    }
    //////////////////////////////////////////////////////////////////////////////////
}
