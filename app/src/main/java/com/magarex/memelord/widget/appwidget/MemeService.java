package com.magarex.memelord.widget.appwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViewsService;

import com.magarex.memelord.data.StorageRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by sahil on 28/11/18.
 **/
public class MemeService extends RemoteViewsService {

    @Inject
    StorageRepository storageRepository;

    public static Intent createIntent(Context context, int appWidgetId) {
        Intent intent = new Intent(context, MemeService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        return intent;
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MemeFactory(this.getApplicationContext(), storageRepository);
    }
}
