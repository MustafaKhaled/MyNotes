package com.mynotes.project.di.module;

import android.content.Context;

import com.mynotes.project.database.dao.NoteDao;
import com.mynotes.project.database.impl.NoteDatabase;
import com.mynotes.project.database.repo.NoteDataSource;
import com.mynotes.project.database.repo.NoteRepository;
import com.mynotes.project.di.scope.ApplicationContextScope;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomDatabaseModule  {
    public NoteDatabase noteDatabase;

    public RoomDatabaseModule(Context context) {
        noteDatabase = Room.databaseBuilder(context,NoteDatabase.class,"notes_db").build();
    }
    @ApplicationContextScope
    @Provides
    NoteDatabase providesNoteDatabase(){
        return noteDatabase;
    }

    @ApplicationContextScope
    @Provides
    NoteDao providesNoteDao(NoteDatabase noteDatabase){
        return noteDatabase.noteDao();
    }
    @ApplicationContextScope
    @Provides
    NoteRepository providesDataSource(NoteDao noteDao){
        return new NoteDataSource(noteDao);
    }

}
