package com.magarex.memelord.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

/**
 * Created by sahil on 16/11/18.
 **/
public abstract class BaseFragment<VM extends BaseViewModel, VDB extends ViewDataBinding> extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private VM viewModel;
    private VDB viewDataBinding;

    @LayoutRes
    protected abstract int provideLayout();

    protected abstract Class<VM> provideViewModelClass();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, provideLayout(), container, false);

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(provideViewModelClass());

        return viewDataBinding.getRoot();
    }

    protected VM getViewModel() {
        return viewModel;
    }

    protected VDB getDataBinding() {
        return viewDataBinding;
    }

}
