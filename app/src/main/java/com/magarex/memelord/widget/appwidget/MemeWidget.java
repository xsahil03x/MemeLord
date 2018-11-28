package com.magarex.memelord.widget.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.magarex.memelord.R;
import com.magarex.memelord.ui.main.MainActivity;

/**
 * Created by sahil on 28/11/18.
 **/
public class MemeWidget extends AppWidgetProvider {

    private static final String TAG = "MemeWidget";

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.meme_list_widget);

        remoteViews.setRemoteAdapter(R.id.stack_widget, MemeService.createIntent(context, appWidgetId));
        remoteViews.setEmptyView(R.id.stack_widget, R.id.tvEmptyWidget);

        // launch app when empty MemeWidget is clicked
        Intent appOpenIntent = new Intent(context, MainActivity.class);
        PendingIntent appOpenPI = PendingIntent.getActivity(context,
                0, appOpenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.tvEmptyWidget, appOpenPI);

        // Instruct the appwidget_preview manager to update the appwidget_preview
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {

    }
}
