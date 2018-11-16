package com.magarex.memelord.ui.main;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magarex.memelord.R;
import com.magarex.memelord.databinding.FragmentProfileBinding;
import com.magarex.memelord.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment<ProfileViewModel, FragmentProfileBinding> {

    @Override
    protected int provideLayout() {
        return R.layout.fragment_profile;
    }

    public static ProfileFragment getInstance() {
        return new ProfileFragment();
    }

    @Override
    protected Class<ProfileViewModel> provideViewModelClass() {
        return ProfileViewModel.class;
    }

}
