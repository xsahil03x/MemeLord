package com.magarex.memelord.ui.base;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable;

    protected BaseViewModel() {
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
