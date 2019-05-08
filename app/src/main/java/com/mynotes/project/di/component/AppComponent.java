package com.mynotes.project.di.component;

import android.content.Context;

import com.mynotes.project.database.repo.NoteRepository;
import com.mynotes.project.di.module.ContextModule;
import com.mynotes.project.di.module.RoomDatabaseModule;
import com.mynotes.project.di.scope.ApplicationContextScope;

import dagger.Component;
@ApplicationContextScope
@Component(modules = {ContextModule.class, RoomDatabaseModule.class})
public interface AppComponent {
    Context exposeContext();
    NoteRepository exposeRepository();

}
