package com.magarex.memelord.di.modules;

import com.magarex.memelord.di.scopes.PerActivity;
import com.magarex.memelord.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    // Activities

    @PerActivity
    @ContributesAndroidInjector()
    public abstract MainActivity bindMainActivity();

}