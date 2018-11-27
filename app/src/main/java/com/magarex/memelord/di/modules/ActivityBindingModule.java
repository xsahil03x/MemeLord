package com.magarex.memelord.di.modules;

import com.magarex.memelord.di.scopes.PerActivity;
import com.magarex.memelord.di.scopes.PerFragment;
import com.magarex.memelord.di.scopes.PerService;
import com.magarex.memelord.services.FetchMemeTemplatesJob;
import com.magarex.memelord.ui.addmeme.AddMemeActivity;
import com.magarex.memelord.ui.addmeme.PostMemeFragment;
import com.magarex.memelord.ui.addmeme.SelectAndEditFragment;
import com.magarex.memelord.ui.leaderboard.LeaderBoardActivity;
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

    @PerActivity
    @ContributesAndroidInjector()
    public abstract AddMemeActivity bindAddMemeActivity();

    @PerActivity
    @ContributesAndroidInjector()
    public abstract LeaderBoardActivity bindLeaderBoardActivity();

    // Fragments

    @PerFragment
    @ContributesAndroidInjector()
    public abstract HomeFragment bindHomeFragment();

    @PerFragment
    @ContributesAndroidInjector()
    public abstract ProfileFragment bindProfileFragment();

    @PerFragment
    @ContributesAndroidInjector()
    public abstract PostMemeFragment bindPostMemeFragment();

    @PerFragment
    @ContributesAndroidInjector()
    public abstract SelectAndEditFragment bindSelectAndEditFragment();

    // Services

    @PerService
    @ContributesAndroidInjector()
    public abstract FetchMemeTemplatesJob bindFetchMemeTemplateJob();
}