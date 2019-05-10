package com.mynotes.project.features.notedetails.repo;

import androidx.lifecycle.LiveData;

import com.mynotes.project.database.repo.NoteRepository;

import javax.inject.Inject;

import dagger.multibindings.IntKey;

public class NoteDetailsRepository {

    private NoteRepository noteRepository;

    @Inject
    public NoteDetailsRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void updateNote(int id, String text){
        noteRepository.updateNote(text,id);
    }

    public void updateFavorite(int id, boolean b){
        noteRepository.updateFavorite(b,id);
    }

    public LiveData<String> loadNote(int id){
        return noteRepository.getNoteText(id);
    }
}
