package com.mynotes.project.di.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.mynotes.project.di.module.ContextModule;
import com.mynotes.project.di.scope.ApplicationContextScope;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class SharedPreferenceModule {
    Context mContext;

    @Inject
    public SharedPreferenceModule(Context mContext) {
        this.mContext = mContext;
    }

    //    @Provides
//    @ApplicationContextScope
//    public Context providesContext(){
//        return mContext;
//    }

    @Provides
    @ApplicationContextScope
    SharedPreferences providesSharedPreference() {
        return mContext.getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
    }
}