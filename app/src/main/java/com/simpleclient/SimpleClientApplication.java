package com.simpleclient;


import android.app.Application;
import android.content.Context;

public class SimpleClientApplication extends Application {

    private static SimpleClientApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }
}
