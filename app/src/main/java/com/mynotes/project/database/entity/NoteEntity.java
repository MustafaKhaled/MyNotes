package com.mynotes.project.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import dagger.Provides;

@Entity(tableName = "notes")
public class NoteEntity {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    int id;
    @NonNull
    String name;
    @Nullable
    String noteText;
    @Nullable
    boolean isFavorite;

    public NoteEntity(int id, @NonNull String name, @Nullable String noteText, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.noteText = noteText;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Nullable
    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(@Nullable String noteText) {
        this.noteText = noteText;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
