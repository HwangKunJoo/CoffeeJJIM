package com.coffeejjim.developers.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.coffeejjim.developers.CoffeeJJIMApplication;

/**
 * Created by Administrator on 2016-08-10.
 */
public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private static final String KEY_OWNER_ID = "ownerId";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REGISTRATION_ID = "fcmToken";
    private static final String KEY_PHONENUMBER = "phonenumber";

    private PropertyManager() {
        Context context = CoffeeJJIMApplication.getCoffeeJJIMApplicationContext();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();
    }

    public void setOwnerId(String ownerId) {
        mEditor.putString(KEY_OWNER_ID, ownerId);
        mEditor.commit();
    }

    public String getOwnerId() {
        return mPrefs.getString(KEY_OWNER_ID, "");
    }

    public void setPassword(String password) {
        mEditor.putString(KEY_PASSWORD, password);
        mEditor.commit();
    }

    public String getPassword() {
        return mPrefs.getString(KEY_PASSWORD, "");
    }

    public void setRegistrationId(String regid) {
        mEditor.putString(KEY_REGISTRATION_ID, regid);
        mEditor.commit();
    }

    public String getRegistrationId() {
        return mPrefs.getString(KEY_REGISTRATION_ID, "");
    }

    public void setKeyPhonenumber(String phonenumber){
        mEditor.putString(KEY_PHONENUMBER, phonenumber);
        mEditor.commit();
    }
    public String getKeyPhonenumber(){
        return mPrefs.getString(KEY_PHONENUMBER,"");
    }
}
