package com.mynotes.project.features.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.mynotes.project.MainActivity;
import com.mynotes.project.R;
import com.mynotes.project.database.repo.NoteRepository;
import com.mynotes.project.util.SharedPreferenceManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class MyFavoriteNoteWidget extends AppWidgetProvider {
    private SharedPreferenceManager sharedPreferenceManager;
    @Inject
    public MyFavoriteNoteWidget(SharedPreferenceManager sharedPreferenceManager) {
        this.sharedPreferenceManager = sharedPreferenceManager;
    }
    public static final String WIDGET_IDS_KEY ="mywidgetproviderwidgetids";
    public static final String WIDGET_DATA_KEY ="mywidgetproviderwidgetdata";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidget : appWidgetIds){
            Intent intent = new Intent(context, MainActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(MyFavoriteNoteWidget.WIDGET_IDS_KEY, appWidget);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
            RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.favorite_note_widget);
            remoteView.setTextViewText(R.id.my_favorite_text,sharedPreferenceManager.getStringData("favorite"));
            remoteView.setOnClickPendingIntent(R.id.my_favorite_text,pendingIntent);
            appWidgetManager.updateAppWidget(appWidget,remoteView);
        }
    }
}
