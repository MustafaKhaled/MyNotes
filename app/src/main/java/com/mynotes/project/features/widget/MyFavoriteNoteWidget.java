package com.mynotes.project.features.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.mynotes.project.MainActivity;
import com.mynotes.project.R;
import com.mynotes.project.database.repo.NoteRepository;
import com.mynotes.project.features.widget.di.component.DaggerMyFavoriteWidgetComponent;
import com.mynotes.project.features.widget.di.component.MyFavoriteWidgetComponent;
import com.mynotes.project.util.MyApplication;
import com.mynotes.project.util.SharedPreferenceManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class MyFavoriteNoteWidget extends AppWidgetProvider {
    private static final String TAG = "MyFavoriteNoteWidget";
    @Inject
    SharedPreferenceManager sharedPreferenceManager;
    @Inject
    Context mContext;
    MyFavoriteWidgetComponent myFavoriteWidgetComponent;

    public static final String WIDGET_IDS_KEY = "mywidgetproviderwidgetids";
    public static final String WIDGET_DATA_KEY = "mywidgetproviderwidgetdata";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if(sharedPreferenceManager == null){
            myFavoriteWidgetComponent = DaggerMyFavoriteWidgetComponent.builder().appComponent(((MyApplication) context.getApplicationContext()).getAppComponent()).build();
            myFavoriteWidgetComponent.inject(this);

        }
        for (int appWidget : appWidgetIds) {
            Log.d(TAG, "onUpdate: Widget updated");
            Intent intent = new Intent(context, MainActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(MyFavoriteNoteWidget.WIDGET_IDS_KEY, appWidget);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.favorite_note_widget);
            Log.d(TAG, "onUpdate: Text on Update is : "+sharedPreferenceManager.getStringData("favorite"));
            remoteView.setTextViewText(R.id.my_favorite_text, sharedPreferenceManager.getStringData("favorite"));
            remoteView.setOnClickPendingIntent(R.id.my_favorite_text, pendingIntent);
            appWidgetManager.updateAppWidget(appWidget, remoteView);
        }
    }
}
