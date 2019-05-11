package com.mynotes.project.util;

import android.app.Application;

import com.mynotes.project.di.component.AppComponent;
import com.mynotes.project.di.component.DaggerAppComponent;
import com.mynotes.project.di.module.ContextModule;
import com.mynotes.project.di.module.RoomDatabaseModule;
import com.mynotes.project.di.sharedpreference.SharedPreferenceModule;

public class MyApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().contextModule(new ContextModule(getApplicationContext())).
                roomDatabaseModule(new RoomDatabaseModule(getApplicationContext())).sharedPreferenceModule(new SharedPreferenceModule(getApplicationContext())).build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
