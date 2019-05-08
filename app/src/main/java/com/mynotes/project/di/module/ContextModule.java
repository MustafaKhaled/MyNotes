package com.mynotes.project.di.module;

import android.content.Context;

import com.mynotes.project.di.scope.ApplicationContextScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }
    @ApplicationContextScope
    @Provides
    public Context provideContext(){
        return context;
    }
}
