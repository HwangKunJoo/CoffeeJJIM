package com.coffeejjim.developers;

import android.app.Application;
import android.content.Context;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class CoffeeJJIMApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
