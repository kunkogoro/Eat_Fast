package com.example.eat_fast.shareReferences;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getBaseContext());
    }
}
