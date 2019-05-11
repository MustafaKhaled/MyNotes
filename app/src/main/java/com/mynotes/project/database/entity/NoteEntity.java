package com.mynotes.project.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import dagger.Provides;

@Entity(tableName = "notes")
public class NoteEntity {



    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @NonNull
    private String name;
    @Nullable
    private String noteText;
    private boolean isFavorite;


        public NoteEntity( @NonNull String name, @Nullable String noteText, boolean isFavorite) {
        this.name = name;
        this.noteText = noteText;
        this.isFavorite = isFavorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
