package com.magarex.memelord.di.modules.viewmodel;

import com.magarex.memelord.ui.addmeme.AddMemeViewModel;
import com.magarex.memelord.ui.base.ViewModelFactory;
import com.magarex.memelord.ui.leaderboard.LeaderBoardViewModel;
import com.magarex.memelord.ui.login.LoginViewModel;
import com.magarex.memelord.ui.main.HomeViewModel;
import com.magarex.memelord.ui.main.ProfileViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddMemeViewModel.class)
    abstract ViewModel bindAddMemeViewModel(AddMemeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LeaderBoardViewModel.class)
    abstract ViewModel bindLeaderBoardViewModel(LeaderBoardViewModel viewModel);

}
