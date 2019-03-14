package com.example.a707446.alloetudiant.general;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static Context context;

    public static Context getAppContext() {
        return BaseApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication.context = getApplicationContext();
    }
}