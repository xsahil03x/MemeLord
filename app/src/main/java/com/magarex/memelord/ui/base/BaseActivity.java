package com.magarex.memelord.ui.base;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends DaggerAppCompatActivity {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private VM viewModel;
    private VDB viewDataBinding;

    @LayoutRes
    protected abstract int provideLayout();

    protected abstract Class<VM> provideViewModelClass();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewDataBinding = DataBindingUtil.setContentView(this, provideLayout());

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(provideViewModelClass());
    }

    protected VM getViewModel() {
        return viewModel;
    }

    protected VDB getBinding() {
        return viewDataBinding;
    }

}
