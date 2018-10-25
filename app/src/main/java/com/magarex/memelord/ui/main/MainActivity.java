package com.magarex.memelord.ui.main;

import android.os.Bundle;

import com.magarex.memelord.R;
import com.magarex.memelord.databinding.ActivityMainBinding;
import com.magarex.memelord.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    @Override
    protected int provideLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> provideViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
