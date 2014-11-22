package com.yogidev.android.livingroom;

import android.app.Application;
import android.content.Context;

/**
 * 
 * Class to retrieve the application context (used in the PreferencesManager singleton)
 * 
 * @author YoGi
 *
 */
public class LivingRoomApplication extends Application{
    private static final String TAG = LivingRoomApplication.class.getSimpleName();
    public static Context mContext = null;

    public LivingRoomApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}