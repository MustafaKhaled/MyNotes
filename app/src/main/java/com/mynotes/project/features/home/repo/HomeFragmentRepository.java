package com.mynotes.project.features.home.repo;

import androidx.lifecycle.LiveData;

import com.mynotes.project.database.entity.NoteEntity;
import com.mynotes.project.database.repo.NoteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;

public class HomeFragmentRepository {
    private NoteRepository noteRepository;

    @Inject
    public HomeFragmentRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public LiveData<List<NoteEntity>> getAllNotes(){
        return noteRepository.getAllNotes();
    }

    public void insert(NoteEntity noteEntity){
        noteRepository.insert(noteEntity);
    }
}
