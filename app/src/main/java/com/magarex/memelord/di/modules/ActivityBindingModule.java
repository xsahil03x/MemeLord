package com.magarex.memelord.di.modules;

import com.magarex.memelord.di.scopes.PerActivity;
import com.magarex.memelord.di.scopes.PerFragment;
import com.magarex.memelord.ui.login.LoginActivity;
import com.magarex.memelord.ui.main.HomeFragment;
import com.magarex.memelord.ui.main.MainActivity;
import com.magarex.memelord.ui.main.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    // Activities

    @PerActivity
    @ContributesAndroidInjector()
    public abstract LoginActivity bindLoginActivity();

    // Fragments

    @PerFragment
    @ContributesAndroidInjector()
    public abstract HomeFragment bindHomeFragment();

    @PerFragment
    @ContributesAndroidInjector()
    public abstract ProfileFragment bindProfileFragment();
}