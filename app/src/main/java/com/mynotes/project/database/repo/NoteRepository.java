package com.mynotes.project.database.repo;

import com.mynotes.project.database.entity.NoteEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;

public interface NoteRepository {

    void insert(NoteEntity noteEntity);
    LiveData<List<NoteEntity>> getAllNotes();
    LiveData<String> getNoteText(int id);
    void updateNote(String text, int id);
    void updateFavorite(boolean isfav, int id);
    void deleteNote(int id);
    void updateAllFavorite(boolean isfav);
    String getFavoriteNote();
}
