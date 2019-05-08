package com.mynotes.project.database.impl;

import com.mynotes.project.database.dao.NoteDao;
import com.mynotes.project.database.entity.NoteEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = NoteEntity.class,version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
        public abstract NoteDao noteDao();

    }
