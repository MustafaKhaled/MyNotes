package com.mynotes.project.database.dao;

import com.mynotes.project.database.entity.NoteEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;
@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes")
    LiveData<List<NoteEntity>> getAllNotes();

    @Insert
    void insertNewNote(NoteEntity noteEntity);

    @Query("SELECT noteText from notes WHERE id=:id")
    LiveData<String> getNoteText(Integer id);

    @Query("UPDATE notes SET noteText=:notesText WHERE id=:id")
    void updateNote(String notesText , Integer id);

    @Query("UPDATE notes SET isFavorite=:isfav WHERE id=:id")
    void updateFavorite(boolean isfav, Integer id);

    @Query("DELETE FROM notes WHERE id=:id")
    void deleteNote(Integer id);

    @Query("UPDATE notes SET isFavorite=:isfav")
    void updateAllFavorite(boolean isfav);

    @Query("SELECT noteText from notes WHERE isFavorite='true'")
    String getFavoriteItem();

}
