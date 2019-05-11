package com.mynotes.project.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.mynotes.project.database.repo.NoteRepository;
import com.mynotes.project.di.module.ContextModule;
import com.mynotes.project.di.module.RoomDatabaseModule;
import com.mynotes.project.di.scope.ApplicationContextScope;
import com.mynotes.project.di.sharedpreference.SharedPreferenceModule;

import dagger.Component;
@ApplicationContextScope
@Component(modules = {ContextModule.class, RoomDatabaseModule.class, SharedPreferenceModule.class})
public interface AppComponent {
    Context exposeContext();
    NoteRepository exposeRepository();
    SharedPreferences exposeSharedPreference();

}
