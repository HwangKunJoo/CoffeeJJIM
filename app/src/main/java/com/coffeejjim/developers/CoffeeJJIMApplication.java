package com.coffeejjim.developers;

import android.app.Activity;
import android.app.Application;

import com.coffeejjim.developers.login.KakaoSDKAdapter;
import com.kakao.auth.KakaoSDK;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class CoffeeJJIMApplication extends Application {
    private static volatile CoffeeJJIMApplication obj = null;
    private static volatile Activity currentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        obj = this;
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    public static CoffeeJJIMApplication getCoffeeJJIMApplicationContext() {
        return obj;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
    public static void setCurrentActivity(Activity currentActivity) {
        CoffeeJJIMApplication.currentActivity = currentActivity;
    }
}
