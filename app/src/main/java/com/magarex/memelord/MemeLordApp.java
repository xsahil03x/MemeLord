package com.magarex.memelord;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import com.magarex.memelord.di.DaggerAppComponent;

public class MemeLordApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(MemeLordApp.this).build();
    }
}
