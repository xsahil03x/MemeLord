package com.magarex.memelord.ui.main;


import android.app.Fragment;

import com.magarex.memelord.R;
import com.magarex.memelord.databinding.FragmentHomeBinding;
import com.magarex.memelord.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> {

    @Override
    protected int provideLayout() {
        return R.layout.fragment_home;
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    protected Class<HomeViewModel> provideViewModelClass() {
        return HomeViewModel.class;
    }
}
