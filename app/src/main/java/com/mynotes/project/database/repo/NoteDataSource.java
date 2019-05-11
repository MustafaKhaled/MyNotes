package com.mynotes.project.database.repo;

import com.mynotes.project.database.dao.NoteDao;
import com.mynotes.project.database.entity.NoteEntity;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class NoteDataSource implements NoteRepository{
    private NoteDao noteDao;

    @Inject
    public NoteDataSource(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public void insert(NoteEntity noteEntity) {
        noteDao.insertNewNote(noteEntity);
    }

    @Override
    public LiveData<List<NoteEntity>> getAllNotes() {
        return noteDao.getAllNotes();
    }

    @Override
    public LiveData<String> getNoteText(int id) {
        return noteDao.getNoteText(id);
    }

    @Override
    public void updateNote(String text, int id) {
        noteDao.updateNote(text,id);
    }

    @Override
    public void updateFavorite(boolean isfav, int id) {
         noteDao.updateFavorite(isfav,id);
    }

    @Override
    public void deleteNote(int id) {
         noteDao.deleteNote(id);
    }

    @Override
    public void updateAllFavorite(boolean isfav) {
        noteDao.updateAllFavorite(false);
    }
}
