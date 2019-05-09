package com.mynotes.project.features.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mynotes.project.database.entity.NoteEntity;
import com.mynotes.project.features.home.repo.HomeFragmentRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;

public class HomePageViewModel extends ViewModel {

    private HomeFragmentRepository homeFragmentRepository;

    @Inject
    public HomePageViewModel(HomeFragmentRepository homeFragmentRepository) {
        this.homeFragmentRepository = homeFragmentRepository;
    }

    public LiveData<List<NoteEntity>> getNotesFromRepository(){
        return homeFragmentRepository.getAllNotes();
    }

    public void insertNote(NoteEntity noteEntity){
        homeFragmentRepository.insert(noteEntity);
    }

}
