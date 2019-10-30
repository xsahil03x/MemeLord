package com.magarex.memelord.di;

import android.app.Application;

import com.magarex.memelord.MemeLordApp;
import com.magarex.memelord.di.modules.ActivityBindingModule;
import com.magarex.memelord.di.modules.AppModule;
import com.magarex.memelord.di.modules.viewmodel.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBindingModule.class,
        AppModule.class, ViewModelFactoryModule.class})
public interface AppComponent extends AndroidInjector<MemeLordApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}